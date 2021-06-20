package com.example.naproject.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Computers (
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("cpu")
    val cpu: String?,
    @SerialName("ram")
    val ram: String?,
    @SerialName("gpu")
    val gpu: String?,
    @SerialName("storage")
    val storage: String?,
    @SerialName("prices")
    val prices:  List<Prices>,
    @SerialName("image")
    val image: String?,
)