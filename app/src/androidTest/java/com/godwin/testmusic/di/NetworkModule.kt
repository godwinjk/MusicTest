package com.godwin.testmusic.di

import com.godwin.testmusic.mock.ICloudApiServiceImpl
import com.godwin.testmusic.network.ICloudApiManager
import com.godwin.testmusic.network.ICloudApiManagerImpl
import com.godwin.testmusic.network.ICloudApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
@Module
class FakeNetworkModule {

    @Singleton
    @Provides
    fun provideICloudApi(): ICloudApiService {
        return ICloudApiServiceImpl()
    }

    @Provides
    fun provideICloudManager(service: ICloudApiService): ICloudApiManager {
        return ICloudApiManagerImpl(service)
    }
}