package com.example.naproject.feature.mainMenu.presentation

import android.util.Log
import com.example.naproject.Display
import com.example.naproject.domain.GetDisplaysUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class DisplaysPresentation(private val getDisplaysUseCase: GetDisplaysUseCase):  MvpPresenter<MainMenuView>(){

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(isShow = true)
        presenterScope.launch(CoroutineExceptionHandler{ context, throwable ->
            viewState.showLoading(isShow = false)
            Log.e("tag",throwable.message,throwable)
        }) {
            val displays = getDisplaysUseCase()
            viewState.showDisplays(displays.sort())
            viewState.showLoading(isShow = false)
        }
    }
    fun List<Display>.sort(): List<Display> = this.sortedBy { it.resolution }
}