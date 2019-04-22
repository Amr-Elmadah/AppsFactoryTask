package com.tasks.appsfactorytask.dataSource.remoteDataSource.retrofit

import com.tasks.appsfactorytask.dataSource.remoteDataSource.NetworkCallBacks
import com.tasks.appsfactorytask.dataSource.remoteDataSource.RemoteDataSource
import com.tasks.appsfactorytask.dataSource.remoteDataSource.retrofit.services.ApiService
import com.tasks.appsfactorytask.model.Vehicle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceUsingRetrofit(private var apiClient: ApiService) : RemoteDataSource {
    class DefaultRetrofitHandler<ResponseType> constructor(private val callbacks: NetworkCallBacks.BaseNetworkCallBacks<ResponseType>) :
        Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>?, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                callbacks.onSuccess(response.body()!!)
            } else {
                if (response.code() != 400) {
                    callbacks.onError(response.errorBody()?.string()!!)
                } else {
                    callbacks.onNoInternetConnection()
                }
            }
        }

        override fun onFailure(call: Call<ResponseType>?, t: Throwable) {
            callbacks.onNoInternetConnection()
            t.printStackTrace()
        }
    }

    override fun getVehicles(
        callBacks: NetworkCallBacks.BaseNetworkCallBacks<List<Vehicle>>
    ) {
        apiClient.getVehicles().enqueue(DefaultRetrofitHandler(callBacks))
    }
}