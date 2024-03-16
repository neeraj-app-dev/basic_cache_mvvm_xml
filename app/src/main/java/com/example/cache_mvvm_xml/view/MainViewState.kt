package com.example.cache_mvvm_xml.view

import com.example.cache_mvvm_xml.data.Details

sealed class MainViewState{
     object Initial : MainViewState()
    data class Loading(val data : List<Details>? = null) : MainViewState()
    data class Success(val data : List<Details>) : MainViewState()
    data class Error(val message : String, val data: List<Details>? = null) : MainViewState()
}
