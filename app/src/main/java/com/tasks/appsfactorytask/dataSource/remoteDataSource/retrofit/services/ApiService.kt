package com.tasks.appsfactorytask.dataSource.remoteDataSource.retrofit.services

import com.tasks.appsfactorytask.model.Vehicle
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

	@GET("vehicles")
	fun getVehicles(): Call<List<Vehicle>>
}