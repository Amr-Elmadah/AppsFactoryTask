package com.tasks.appsfactorytask.model

/**
 * Created by Amr on 4/5/2019.
 */
data class Vehicle(
	val id: Int,
	val name: String,
	val description: String,
	val latitude: Double,
	val longitude: Double,
	val batteryLevel: Int,
	val timestamp: String,
	val price: Double,
	val priceTime: Long,
	val currency: String
)