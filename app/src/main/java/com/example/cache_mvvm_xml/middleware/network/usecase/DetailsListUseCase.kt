package com.example.cache_mvvm_xml.middleware.network.usecase

import com.example.cache_mvvm_xml.base.Result
import com.example.cache_mvvm_xml.data.Details

interface DetailsListUseCase {
    suspend fun getDetailsList() : Result<List<Details>>
}