package com.example.cache_mvvm_xml.middleware.db.usecase

import com.example.cache_mvvm_xml.base.Result
import com.example.cache_mvvm_xml.data.Details

interface DbDetailsListUseCase {
    suspend fun geDetailsListFromRemote() : Result<List<Details>>
    suspend fun geDetailsListFromLocal() : List<Details>
}