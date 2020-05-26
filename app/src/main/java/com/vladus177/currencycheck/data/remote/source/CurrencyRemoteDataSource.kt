package com.vladus177.currencycheck.data.remote.source

import com.vladus177.currencycheck.data.remote.model.CurrencyResponseEntry
import com.vladus177.currencycheck.data.remote.net.CurrencyRestApi
import javax.inject.Inject

open class CurrencyRemoteDataSource  @Inject constructor(
    private val currencyRestApi: CurrencyRestApi
) {
    suspend fun getCurrencyRates(currencyCode: String?): CurrencyResponseEntry? {
        return currencyRestApi.getCurrencyRates(currencyCode)
    }
}