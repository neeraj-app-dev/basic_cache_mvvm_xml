package com.example.cache_mvvm_xml.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cache_mvvm_xml.base.Error
import com.example.cache_mvvm_xml.base.Success
import com.example.cache_mvvm_xml.data.Details
import com.example.cache_mvvm_xml.middleware.db.usecase.DbDetailsListUseCase
import com.example.cache_mvvm_xml.middleware.network.usecase.DetailsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
 private val detailsListUseCase: DbDetailsListUseCase
) : ViewModel() {

    private val _detailsList: MutableStateFlow<MainViewState> =
        MutableStateFlow(MainViewState.Initial)
    val detailsList: Flow<MainViewState> = _detailsList

    val detailsListLiveData: MutableLiveData<List<Details>> by lazy {
        MutableLiveData<List<Details>>()
    }


    fun getDetailsList() {
        viewModelScope.launch(Dispatchers.IO) {
            _detailsList.value = MainViewState.Loading( detailsListUseCase.geDetailsListFromLocal())
            when (val response = detailsListUseCase.geDetailsListFromRemote()) {
                is Success -> _detailsList.value = MainViewState.Success(response.data)
                is Error -> _detailsList.value =
                    MainViewState.Error(response.message, response.dbData)
            }
        }
    }
}