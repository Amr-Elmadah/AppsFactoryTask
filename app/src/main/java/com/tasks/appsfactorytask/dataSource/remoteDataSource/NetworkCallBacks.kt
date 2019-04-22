package com.tasks.appsfactorytask.dataSource.remoteDataSource

import com.tasks.appsfactorytask.App
import com.tasks.appsfactorytask.R

interface NetworkCallBacks {

    interface BaseNetworkCallBacks<ReturnType> {
        fun onSuccess(result: ReturnType)

        fun onError(err: String) {
            App.instance.showToast(err)
        }

        fun onNoInternetConnection() {
            App.instance.showToast(R.string.internet_connection)
        }
    }
}