package com.example.naproject

import android.os.Parcelable
import com.example.naproject.data.entity.Prices
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Computer(
    val id: Int,
    val name: String,
    val cpu: String,
    val ram: String,
    val gpu: String,
    val storage: String,
    val prices: @RawValue List<Prices>,
    val image: String
) : Parcelable