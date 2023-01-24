package com.godwin.testmusic.di

import com.godwin.testmusic.mock.NetworkTestManagerImpl
import com.godwin.testmusic.util.NetworkConnectionManager
import com.godwin.testmusic.util.NetworkConnectionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
@Module
abstract class FakeAppModule {
    companion object {
        @Provides
        @Singleton
        fun provideCoroutineScope() =
            CoroutineScope(Dispatchers.Default + SupervisorJob())
    }

    @Binds
    abstract fun bindNetworkConnectionManager(networkConnectionManagerImpl: NetworkTestManagerImpl): NetworkConnectionManager
}