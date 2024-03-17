package com.example.cache_mvvm_xml.middleware.db.repo

import android.util.Log
import com.example.cache_mvvm_xml.base.Error
import com.example.cache_mvvm_xml.base.Result
import com.example.cache_mvvm_xml.base.Success
import com.example.cache_mvvm_xml.data.Details
import com.example.cache_mvvm_xml.middleware.db.DbDao
import com.example.cache_mvvm_xml.middleware.db.LocalDbData
import com.example.cache_mvvm_xml.middleware.network.usecase.DetailsListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DbRepo @Inject constructor(
    private val detailsListUseCase: DetailsListUseCase,
    private val dbDao : DbDao
) {
      suspend fun getDetailsListFromRemote() : Result<List<Details>>{
           return when(val data = detailsListUseCase.getDetailsList()){
               is Success ->{
                 CoroutineScope(Dispatchers.IO).launch {
                    for (i in 0.. data.data.lastIndex){
                       with(data.data[i]){
                           dbDao.insertItems(LocalDbData(id, userId, title,body))
                       }
                        if (i == data.data.lastIndex) {
                            this.cancel()
                        }
                    }
                 }
                   Success(data.data)
               }
               is Error -> {
                    val listFromDb = dbDao.getItems().
                        map { Details(
                            id = it.id,
                            userId =it.userId,
                            title= it.title,
                            body= it.body
                        ) }
                   Error(data.throwable, data.message, listFromDb)
               }
           }
      }

       fun getDetailsFromLocal() : List<Details>{
          return  dbDao.getItems().map { Details(
              id = it.id,
              userId =it.userId,
              title= it.title,
              body= it.body
          ) }
      }
}