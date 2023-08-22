package kz.aspan.weatherapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import kz.aspan.weatherapp.domain.models.Weather

object WeatherInfoDiffCallback : DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem == newItem
    }
}