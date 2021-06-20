package com.example.naproject.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prices(
    @SerialName("shop")
    val shop: String,
    @SerialName("value")
    val value: Float
)