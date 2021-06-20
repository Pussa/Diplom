package com.example.naproject.domain

import com.example.naproject.Display
import com.example.naproject.di.DiplomApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDisplaysUseCase constructor(private val diplomApi: DiplomApi) {
    suspend operator fun invoke():List<Display> = withContext(Dispatchers.IO){
        val displays = diplomApi.getDisplays()
        val product = displays.map {
            Display(
                id = it.id!!,
                name = it.name!!,
                size = it.size!!,
                resolution = it.resolution!!,
                matrix_type = it.matrix_type!!,
                frequency = it.frequency!!,
                prices = it.prices!!,
                image = it.image!!
            )
        }
        product
    }
}