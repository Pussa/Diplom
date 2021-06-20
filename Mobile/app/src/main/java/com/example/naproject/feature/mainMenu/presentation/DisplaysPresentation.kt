package com.example.naproject.feature.mainMenu.presentation

import android.util.Log
import com.example.naproject.Display
import com.example.naproject.domain.GetDisplaysUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class DisplaysPresentation(private val getDisplaysUseCase: GetDisplaysUseCase):  MvpPresenter<MainMenuView>(){
    lateinit var displaysList: MutableList<Display>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(isShow = true)
        presenterScope.launch(CoroutineExceptionHandler{ context, throwable ->
            viewState.showLoading(isShow = false)
            Log.e("tag",throwable.message,throwable)
        }) {
            val displays = getDisplaysUseCase()
            displaysList = displays.toMutableList()
            viewState.showDisplays(displays.sort())
            viewState.showLoading(isShow = false)
        }
    }
    fun List<Display>.sort(): List<Display> = this.sortedBy { it.name }
    
    fun filterDisplaysByName(name:String) =
        viewState.showDisplays(displaysList.filter { it.name.contains(name, ignoreCase = true) })
    fun filterDisplaysBySize(size:String) =
        viewState.showDisplays(displaysList.filter { it.size.contains(size, ignoreCase = true) })
    fun filterDisplaysByResolution(resolution:String) =
        viewState.showDisplays(displaysList.filter { it.resolution.contains(resolution, ignoreCase = true) })
    fun filterDisplaysByMatrixType(matrixType:String) =
        viewState.showDisplays(displaysList.filter { it.matrix_type.contains(matrixType, ignoreCase = true) })
    fun filterDisplaysByFrequency(frequency:String) =
        viewState.showDisplays(displaysList.filter { it.frequency.toString().contains(frequency, ignoreCase = true) })
}