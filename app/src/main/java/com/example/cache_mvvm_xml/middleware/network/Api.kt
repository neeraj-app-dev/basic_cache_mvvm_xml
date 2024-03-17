package com.example.cache_mvvm_xml.middleware.network

import com.example.cache_mvvm_xml.base.ResponseData
import com.example.cache_mvvm_xml.base.Result
import com.example.cache_mvvm_xml.data.Details
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET

interface Api {

    @GET("posts")
    suspend fun getDetails() : NetworkResponse<List<Details>, ResponseData>
}