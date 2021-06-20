package com.example.naproject

import android.os.Parcelable
import com.example.naproject.data.entity.Prices
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Display(
    val id: Int,
    val name: String,
    val size: String,
    val resolution: String,
    val matrix_type: String,
    val frequency: Int,
    val prices: @RawValue List<Prices>,
    val image: String
) : Parcelable