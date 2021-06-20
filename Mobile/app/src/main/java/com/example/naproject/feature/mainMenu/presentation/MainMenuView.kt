package com.example.naproject.feature.mainMenu.presentation

import com.example.naproject.Computer
import com.example.naproject.Display
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainMenuView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showDisplays(rockets: List<Display>)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(isShow: Boolean)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showComputers(rockets: List<Computer>)
}