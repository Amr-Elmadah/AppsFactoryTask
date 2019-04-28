package com.tasks.appsfactorytask.ui.searchartist.remote.apiparams

data class SearchParams(val artistName: String, var page: Int = 1, val limit: Int = 20)