package com.tasks.appsfactorytask.base.presentation.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.tasks.appsfactorytask.base.domain.exception.AppsFactoryException

class ObservableResource<T> : SingleLiveEvent<T>() {

    val error: SingleLiveEvent<AppsFactoryException> = ErrorLiveData()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun observe(
        owner: LifecycleOwner, successObserver: Observer<T>,
        loadingObserver: Observer<Boolean>? = null,
        commonErrorObserver: Observer<AppsFactoryException>,
        httpErrorConsumer: Observer<AppsFactoryException>? = null,
        networkErrorConsumer: Observer<AppsFactoryException>? = null,
        unExpectedErrorConsumer: Observer<AppsFactoryException>? = null,
        serverDownErrorConsumer: Observer<AppsFactoryException>? = null,
        timeOutErrorConsumer: Observer<AppsFactoryException>? = null,
        unAuthorizedErrorConsumer: Observer<AppsFactoryException>? = null
    ) {
        super.observe(owner, successObserver)
        loadingObserver?.let { loading.observe(owner, it) }
        (error as ErrorLiveData).observe(
            owner, commonErrorObserver, httpErrorConsumer, networkErrorConsumer, unExpectedErrorConsumer,
            serverDownErrorConsumer, timeOutErrorConsumer, unAuthorizedErrorConsumer
        )
    }


    class ErrorLiveData : SingleLiveEvent<AppsFactoryException>() {
        private var ownerRef: LifecycleOwner? = null
        private var httpErrorConsumer: Observer<AppsFactoryException>? = null
        private var networkErrorConsumer: Observer<AppsFactoryException>? = null
        private var unExpectedErrorConsumer: Observer<AppsFactoryException>? = null
        private var commonErrorConsumer: Observer<AppsFactoryException>? = null

        private var serverDownErrorConsumer: Observer<AppsFactoryException>? = null
        private var timeOutErrorConsumer: Observer<AppsFactoryException>? = null
        private var unAuthorizedErrorConsumer: Observer<AppsFactoryException>? = null

        override fun setValue(t: AppsFactoryException?) {
            ownerRef?.also {
                removeObservers(it)
                t?.let { vale -> addProperObserver(vale) }
                super.setValue(t)
            }

        }

        override fun postValue(value: AppsFactoryException) {
            ownerRef?.also {
                removeObservers(it)
                addProperObserver(value)
                super.postValue(value)
            }

        }

        private fun addProperObserver(value: AppsFactoryException) {
            when (value.kind) {
                AppsFactoryException.Kind.NETWORK -> networkErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)
                AppsFactoryException.Kind.HTTP -> httpErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)
                AppsFactoryException.Kind.UNEXPECTED -> unExpectedErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                AppsFactoryException.Kind.SERVER_DOWN -> serverDownErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                AppsFactoryException.Kind.TIME_OUT -> timeOutErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                AppsFactoryException.Kind.UNAUTHORIZED -> unAuthorizedErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                else -> {
                }
            }
        }


        fun observe(
            owner: LifecycleOwner, commonErrorConsumer: Observer<AppsFactoryException>,
            httpErrorConsumer: Observer<AppsFactoryException>? = null,
            networkErrorConsumer: Observer<AppsFactoryException>? = null,
            unExpectedErrorConsumer: Observer<AppsFactoryException>? = null,

            serverDownErrorConsumer: Observer<AppsFactoryException>? = null,
            timeOutErrorConsumer: Observer<AppsFactoryException>? = null,
            unAuthorizedErrorConsumer: Observer<AppsFactoryException>? = null
        ) {
            super.observe(owner, commonErrorConsumer)
            this.ownerRef = owner
            this.commonErrorConsumer = commonErrorConsumer
            this.httpErrorConsumer = httpErrorConsumer
            this.networkErrorConsumer = networkErrorConsumer
            this.unExpectedErrorConsumer = unExpectedErrorConsumer
            this.serverDownErrorConsumer = serverDownErrorConsumer
            this.timeOutErrorConsumer = timeOutErrorConsumer
            this.unAuthorizedErrorConsumer = unAuthorizedErrorConsumer
        }
    }
}