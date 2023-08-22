package kz.aspan.weatherapp.presentation

import android.app.Application
import kz.aspan.weatherapp.di.DaggerApplicationComponent

class WeatherApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}