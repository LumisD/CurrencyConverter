package com.vladus177.currencycheck.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.currencycheck.common.extensions.LiveResult
import com.vladus177.currencycheck.domain.GetCurrencyUseCase
import com.vladus177.currencycheck.domain.model.CurrencyRequestParam
import com.vladus177.currencycheck.domain.model.RatesModel
import javax.inject.Inject

class CurrencyConverterViewModel @Inject constructor(private val getCurrencyUseCase: GetCurrencyUseCase) : ViewModel() {

    val currency = LiveResult<RatesModel>()

    fun getCurrency(code: String?, forceUpdate: Boolean) {
        if (forceUpdate) {
            getCurrencyUseCase.executeRepeating(
                liveData = currency,
                params = CurrencyRequestParam(code, forceUpdate),
                repeatTime = 1000L
            )
        } else {
            getCurrencyUseCase.execute(
                liveData = currency,
                params = CurrencyRequestParam(code, forceUpdate)
            )
        }

    }
}