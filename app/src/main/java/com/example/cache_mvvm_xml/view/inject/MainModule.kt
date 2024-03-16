package com.example.cache_mvvm_xml.view.inject

import com.example.cache_mvvm_xml.middleware.db.usecase.DbDetailsListUseCase
import com.example.cache_mvvm_xml.middleware.db.usecase.DbDetailsListUseCaseImpl
import com.example.cache_mvvm_xml.middleware.network.usecase.DetailsListUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {

    @Binds
    abstract fun provideDetailsList(detailsListUseCaseImpl: DbDetailsListUseCaseImpl) : DbDetailsListUseCase


}