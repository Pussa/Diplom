package com.example.naproject.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Displays(
    @SerialName("id")
    val id:Int?,
    @SerialName("name")
    val name:String?,
    @SerialName("size")
    val size:String?,
    @SerialName("resolution")
    val resolution:String?,
    @SerialName("matrix_type")
    val matrix_type:String?,
    @SerialName("frequency")
    val frequency:Int?,
    @SerialName("prices")
    val prices:List<Prices>,
    @SerialName("image")
    val image:String?,
)