package com.example.naproject.domain

import com.example.naproject.Computer
import com.example.naproject.di.DiplomApi
import com.example.naproject.di.dipliomApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetComputerUseCase constructor(private val diplomApi: DiplomApi) {
    suspend operator fun invoke():List<Computer> = withContext(Dispatchers.IO){
        val computers = dipliomApi.getComputers()
        val product = computers.map {
            Computer(
                id = it.id!!,
                name = it.name!!,
                cpu = it.cpu!!,
                gpu = it.gpu!!,
                ram = it.ram!!,
                storage =  it.storage!!,
                prices = it.prices,
                image = it.image!!
            )
        }
        product
    }
}