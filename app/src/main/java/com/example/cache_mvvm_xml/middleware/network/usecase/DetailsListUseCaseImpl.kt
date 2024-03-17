package com.example.cache_mvvm_xml.middleware.network.usecase

import com.example.cache_mvvm_xml.base.Result
import com.example.cache_mvvm_xml.data.Details
import com.example.cache_mvvm_xml.middleware.network.repo.DetailsListRepo
import javax.inject.Inject

class DetailsListUseCaseImpl @Inject constructor(
    private val repo: DetailsListRepo
) : DetailsListUseCase {
    override suspend fun getDetailsList(): Result<List<Details>> {
        return repo.getDetailsList()
    }
}