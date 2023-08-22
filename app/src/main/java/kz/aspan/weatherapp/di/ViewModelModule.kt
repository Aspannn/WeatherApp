package kz.aspan.weatherapp.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kz.aspan.weatherapp.presentation.weather.WeatherViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel

}
