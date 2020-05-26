package com.vladus177.currencycheck.data.remote.net

import com.vladus177.currencycheck.data.Constants.API_URL
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class CurrencyRestApiFactory {

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val currencyApi: CurrencyRestApi = retrofit().create(CurrencyRestApi::class.java)

}