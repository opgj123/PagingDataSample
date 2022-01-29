package com.yhg.pagingdatasample.di

import com.yhg.pagingdatasample.network.MainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesAuthInterceptor() : HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    @Provides
    fun providesOkHttpClient(authInterceptor: HttpLoggingInterceptor) : OkHttpClient =
        OkHttpClient().newBuilder().apply {
            addInterceptor(authInterceptor)
        }.build()

    @Provides
    @Named("main")
    fun providesMainRetrofitClient(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl("http://www.naver.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesMainService(@Named("main") retrofit: Retrofit): MainService = retrofit.create(MainService::class.java)
}