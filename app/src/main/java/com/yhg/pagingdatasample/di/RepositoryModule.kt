package com.yhg.pagingdatasample.di

import com.yhg.pagingdatasample.network.MainService
import com.yhg.pagingdatasample.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMainRepository(mainService: MainService): MainRepository = MainRepository(mainService)
}