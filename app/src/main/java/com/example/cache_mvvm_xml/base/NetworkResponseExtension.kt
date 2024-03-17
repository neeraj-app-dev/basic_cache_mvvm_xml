package com.example.cache_mvvm_xml.base

import android.util.Log
import com.haroldadmin.cnradapter.NetworkResponse


fun <T:Any, > NetworkResponse<T, ResponseData>.toResult() : Result<T>{
return when(this){
    is NetworkResponse.Success -> Success(this.body)
    is NetworkResponse.ServerError -> Error(this.error, this.body?.message ?: "Server issue")
    is NetworkResponse.NetworkError-> Error(this.error, "please check your network connection")
    is NetworkResponse.UnknownError-> Error(this.error,"Something went wrong")
}
}


