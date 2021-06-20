package com.example.naproject.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0003j\b\u0012\u0004\u0012\u00020\b`\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\t"}, d2 = {"Lcom/example/naproject/di/DiplomApi;", "", "getComputers", "Ljava/util/ArrayList;", "Lcom/example/naproject/data/entity/Computers;", "Lkotlin/collections/ArrayList;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDisplays", "Lcom/example/naproject/data/entity/Displays;", "app_debug"})
public abstract interface DiplomApi {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "display")
    public abstract java.lang.Object getDisplays(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.ArrayList<com.example.naproject.data.entity.Displays>> p0);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "computer")
    public abstract java.lang.Object getComputers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.ArrayList<com.example.naproject.data.entity.Computers>> p0);
}