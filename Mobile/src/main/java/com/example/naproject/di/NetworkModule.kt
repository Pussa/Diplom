package com.example.naproject.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

val dipliomApi:DiplomApi = Retrofit.Builder().baseUrl("http://pozdravko.ru:8000/")
    .client(OkHttpClient.Builder().addInterceptor{  chain ->
        val request = chain.request().newBuilder().build()
        chain.proceed(request)
    }.build())
    .addConverterFactory(Json(builderAction = {
        ignoreUnknownKeys = true
    }).asConverterFactory("application/json".toMediaType()))
    .build()
    .create()


