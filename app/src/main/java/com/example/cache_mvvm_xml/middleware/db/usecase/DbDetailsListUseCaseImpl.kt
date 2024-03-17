package com.example.cache_mvvm_xml.middleware.db.usecase

import com.example.cache_mvvm_xml.base.Result
import com.example.cache_mvvm_xml.data.Details
import com.example.cache_mvvm_xml.middleware.db.repo.DbRepo
import javax.inject.Inject

class DbDetailsListUseCaseImpl @Inject constructor(
    private val repo: DbRepo
) : DbDetailsListUseCase {

    override suspend fun geDetailsListFromRemote(): Result<List<Details>> {
        return repo.getDetailsListFromRemote()
    }

    override suspend fun geDetailsListFromLocal(): List<Details> {
        return repo.getDetailsFromLocal()
    }
}