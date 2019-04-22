package com.tasks.appsfactorytask.dataSource

import com.tasks.appsfactorytask.dataSource.localDataSourse.LocalDataSource
import com.tasks.appsfactorytask.dataSource.remoteDataSource.RemoteDataSource

interface RepositorySource : RemoteDataSource, LocalDataSource