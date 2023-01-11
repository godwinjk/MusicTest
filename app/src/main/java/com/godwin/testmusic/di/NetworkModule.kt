package com.godwin.testmusic.di

import android.content.Context
import com.godwin.testmusic.network.ICloudApiManager
import com.godwin.testmusic.network.ICloudApiManagerImpl
import com.godwin.testmusic.network.ICloudApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideICloudApi(): ICloudApiService {
        return ICloudApiService.create()
    }

    @Provides
    fun provideICloudManager(service: ICloudApiService): ICloudApiManager {
        return ICloudApiManagerImpl(service)
    }
}