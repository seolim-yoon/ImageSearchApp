package com.example.data.datasource.remote

import com.example.data.datasource.remote.api.SearchApi
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
   private val searchApi: SearchApi
) {

}