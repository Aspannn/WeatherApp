package kz.aspan.weatherapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import kz.aspan.weatherapp.presentation.weather.WeatherActivity
import kz.aspan.weatherapp.presentation.WeatherApp


@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        UseCaseModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: WeatherActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}