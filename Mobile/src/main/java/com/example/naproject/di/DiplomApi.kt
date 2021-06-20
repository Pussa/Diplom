package com.example.naproject.di

import com.example.naproject.data.entity.Computers
import com.example.naproject.data.entity.Displays
import retrofit2.http.GET

interface DiplomApi {
    @GET("display")
    suspend fun getDisplays():ArrayList<Displays>
    @GET("computer")
    suspend fun getComputers():ArrayList<Computers>
}