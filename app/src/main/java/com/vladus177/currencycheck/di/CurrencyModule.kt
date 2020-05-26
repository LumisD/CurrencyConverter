package com.vladus177.currencycheck.di

import androidx.lifecycle.ViewModel
import com.vladus177.currencycheck.presentation.CurrencyConverterViewModel
import com.vladus177.currencycheck.ui.fragment.CurrencyConverterFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CurrencyModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun currencyConverterFragment(): CurrencyConverterFragment

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyConverterViewModel::class)
    abstract fun bindViewModel(viewmodel: CurrencyConverterViewModel): ViewModel
}