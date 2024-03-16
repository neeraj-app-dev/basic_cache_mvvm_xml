package com.example.cache_mvvm_xml.middleware

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cache_mvvm_xml.middleware.db.DbDao
import com.example.cache_mvvm_xml.middleware.db.LocalDbData
import com.example.cache_mvvm_xml.middleware.db.Localdb
import com.example.cache_mvvm_xml.middleware.db.usecase.DbDetailsListUseCaseImpl
import com.example.cache_mvvm_xml.middleware.network.Api
import com.example.cache_mvvm_xml.middleware.network.usecase.DetailsListUseCase
import com.example.cache_mvvm_xml.middleware.network.usecase.DetailsListUseCaseImpl
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MiddlewareModule {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalDb(@ApplicationContext context: Context): DbDao {
        return Room.databaseBuilder(
            context,
            Localdb::class.java, "cache_data"
        ).build().dao()
    }

    @Provides
    @Singleton
    fun provideDetailsListFromRemote(detailsListUseCaseImpl: DetailsListUseCaseImpl) : DetailsListUseCase {
        return detailsListUseCaseImpl
    }

}