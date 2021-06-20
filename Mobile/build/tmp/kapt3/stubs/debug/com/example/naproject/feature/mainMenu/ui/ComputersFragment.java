package com.example.naproject.feature.mainMenu.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0016J\u001a\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0016\u0010\u0013\u001a\u00020\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0016J\u0016\u0010\u0017\u001a\u00020\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015H\u0016J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t\u00a8\u0006\u001c"}, d2 = {"Lcom/example/naproject/feature/mainMenu/ui/ComputersFragment;", "Lmoxy/MvpAppCompatFragment;", "Lcom/example/naproject/feature/mainMenu/presentation/MainMenuView;", "()V", "computersAdapter", "Lcom/example/naproject/feature/mainMenu/ui/ComputerAdapter;", "presenter", "Lcom/example/naproject/feature/mainMenu/presentation/ComputersPresentation;", "getPresenter", "()Lcom/example/naproject/feature/mainMenu/presentation/ComputersPresentation;", "presenter$delegate", "Lmoxy/ktx/MoxyKtxDelegate;", "onDestroyView", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showComputers", "rockets", "", "Lcom/example/naproject/Computer;", "showDisplays", "Lcom/example/naproject/Display;", "showLoading", "isShow", "", "app_debug"})
public final class ComputersFragment extends moxy.MvpAppCompatFragment implements com.example.naproject.feature.mainMenu.presentation.MainMenuView {
    private final moxy.ktx.MoxyKtxDelegate presenter$delegate = null;
    private com.example.naproject.feature.mainMenu.ui.ComputerAdapter computersAdapter;
    private java.util.HashMap _$_findViewCache;
    
    private final com.example.naproject.feature.mainMenu.presentation.ComputersPresentation getPresenter() {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void showDisplays(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.naproject.Display> rockets) {
    }
    
    @java.lang.Override()
    public void showLoading(boolean isShow) {
    }
    
    @java.lang.Override()
    public void showComputers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.naproject.Computer> rockets) {
    }
    
    public ComputersFragment() {
        super();
    }
}