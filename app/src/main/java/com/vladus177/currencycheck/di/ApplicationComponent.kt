package com.vladus177.currencycheck.di

import android.content.Context
import com.vladus177.currencycheck.CurrencyCheckApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        CurrencyModule::class,
        OkHttpClientModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CurrencyCheckApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}