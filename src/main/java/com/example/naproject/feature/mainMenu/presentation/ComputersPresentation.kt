package com.example.naproject.feature.mainMenu.presentation

import android.util.Log
import com.example.naproject.Computer
import com.example.naproject.domain.GetComputerUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class ComputersPresentation(private val getComputerUseCase: GetComputerUseCase): MvpPresenter<MainMenuView>(){

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(isShow = true)
        presenterScope.launch(CoroutineExceptionHandler{ context, throwable ->
            viewState.showLoading(isShow = false)
            Log.e("tag",throwable.message,throwable)
        }) {
            val computers = getComputerUseCase()
            viewState.showComputers(computers.sort())
            viewState.showLoading(isShow = false)
        }
    }
    fun List<Computer>.sort(): List<Computer> = this.sortedBy { it.cpu }
}