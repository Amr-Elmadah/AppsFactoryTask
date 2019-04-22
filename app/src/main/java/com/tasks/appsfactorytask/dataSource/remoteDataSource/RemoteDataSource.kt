package com.tasks.appsfactorytask.dataSource.remoteDataSource

import com.tasks.appsfactorytask.model.Vehicle

interface RemoteDataSource {
    fun getVehicles(callBacks: NetworkCallBacks.BaseNetworkCallBacks<List<Vehicle>>)
}