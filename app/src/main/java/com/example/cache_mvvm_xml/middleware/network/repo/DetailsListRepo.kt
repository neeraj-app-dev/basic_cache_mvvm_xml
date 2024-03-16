package com.example.cache_mvvm_xml.middleware.network.repo

import android.util.Log
import com.example.cache_mvvm_xml.base.Result
import com.example.cache_mvvm_xml.base.toResult
import com.example.cache_mvvm_xml.data.Details
import com.example.cache_mvvm_xml.middleware.network.Api
import javax.inject.Inject

class DetailsListRepo @Inject constructor(
    private val api: Api
) {
   suspend fun getDetailsList() : Result<List<Details>>{
       val response = api.getDetails()
       return response.toResult()
   }
}