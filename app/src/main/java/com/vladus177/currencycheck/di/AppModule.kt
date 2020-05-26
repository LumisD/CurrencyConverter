package com.vladus177.currencycheck.di

import com.vladus177.currencycheck.data.CurrencyDataMapper
import com.vladus177.currencycheck.data.remote.net.CurrencyRestApi
import com.vladus177.currencycheck.data.remote.net.CurrencyRestApiFactory
import com.vladus177.currencycheck.data.remote.source.CurrencyRemoteDataSource
import com.vladus177.currencycheck.data.repository.CurrencyRepository
import dagger.Module
import dagger.Provides


@Module()
object ApplicationModule {

    @JvmStatic
    @Provides
    fun provideCurrencyRepository(currencyRemoteDataSource: CurrencyRemoteDataSource,
                                  dataMapper: CurrencyDataMapper): CurrencyRepository {
        return CurrencyRepository(currencyRemoteDataSource, dataMapper)
    }

    @JvmStatic
    @Provides
    fun provideCurrencyRemoteDataSource(currencyApi: CurrencyRestApi): CurrencyRemoteDataSource {
        return CurrencyRemoteDataSource(currencyApi)
    }

    @JvmStatic
    @Provides
    fun provideCurrencyRestApi() = CurrencyRestApiFactory().currencyApi
}