package kz.aspan.weatherapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kz.aspan.weatherapp.R
import kz.aspan.weatherapp.databinding.ItemWeatherBinding
import kz.aspan.weatherapp.domain.models.Weather

class WeatherInfoAdapter(
    private val context: Context
) : ListAdapter<Weather, WeatherInfoViewHolder>(WeatherInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherInfoViewHolder {
        val binding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherInfoViewHolder, position: Int) {
        val weather = getItem(position)

        with(holder.binding) {
            tvLocation.text = weather.location
            tvMaxMinTemp.text = context.resources.getString(
                R.string.max_min_temp,
                weather.maxTemp,
                weather.minTemp
            )
            tvCurrentTemp.text = context.resources.getString(R.string.cur_temp, weather.currentTemp)
        }
    }
}