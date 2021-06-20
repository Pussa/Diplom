package com.example.naproject.feature.mainMenu.presentation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\'J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lcom/example/naproject/feature/mainMenu/presentation/MainMenuView;", "Lmoxy/MvpView;", "showComputers", "", "rockets", "", "Lcom/example/naproject/Computer;", "showDisplays", "Lcom/example/naproject/Display;", "showLoading", "isShow", "", "app_debug"})
public abstract interface MainMenuView extends moxy.MvpView {
    
    @moxy.viewstate.strategy.StateStrategyType(value = moxy.viewstate.strategy.AddToEndSingleStrategy.class)
    public abstract void showDisplays(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.naproject.Display> rockets);
    
    @moxy.viewstate.strategy.StateStrategyType(value = moxy.viewstate.strategy.AddToEndSingleStrategy.class)
    public abstract void showLoading(boolean isShow);
    
    @moxy.viewstate.strategy.StateStrategyType(value = moxy.viewstate.strategy.AddToEndSingleStrategy.class)
    public abstract void showComputers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.naproject.Computer> rockets);
}