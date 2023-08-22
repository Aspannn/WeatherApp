package kz.aspan.weatherapp.presentation.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import kz.aspan.weatherapp.presentation.WeatherApp
import kz.aspan.weatherapp.databinding.ActivityWeatherBinding
import kz.aspan.weatherapp.domain.models.Weather
import kz.aspan.weatherapp.presentation.extension.dpToPixel
import kz.aspan.weatherapp.presentation.ViewModelFactory
import kz.aspan.weatherapp.presentation.adapter.WeatherDividerItemDecoration
import kz.aspan.weatherapp.presentation.adapter.WeatherInfoAdapter
import javax.inject.Inject

class WeatherActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as WeatherApp).component
    }

    private val binding by lazy {
        ActivityWeatherBinding.inflate(layoutInflater)
    }

    private val adapter = WeatherInfoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]

        initRecycler()
        searchInputListener()
        observeViewState()
    }

    private fun initRecycler() {
        binding.rvWeather.adapter = adapter
        val itemDecoration = WeatherDividerItemDecoration(
            outerBottom = dpToPixel(16f),
            outerTop = dpToPixel(16f),
            inner = dpToPixel(8f),
            outerHorizontal = dpToPixel(16f)
        )
        binding.rvWeather.addItemDecoration(itemDecoration)
    }

    private fun searchInputListener() {
        binding.edtSearchKey.addTextChangedListener {
            if (!it.isNullOrBlank())
                viewModel.onInputTextChanged(it.toString())
        }
    }

    private fun observeViewState() {
        viewModel.viewState.observe(this) {
            onStateChanged(it)
        }
    }

    private fun onStateChanged(curState: WeatherViewState) {
        Log.d("WeatherRepositoryImpl", "checkIfWeatherListIsEmpty ${curState.listOfWeather}")
        updateProgressBar(curState.isLoading)
        updateSearchText(curState.lastInput)
        checkIfWeatherListIsEmpty(curState.listOfWeather)
    }

    private fun updateProgressBar(isLoading: Boolean) {
        val progressVisibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        binding.progressHorizontal.visibility = progressVisibility
    }

    private fun updateSearchText(lastInput: String?) {
        if (lastInput != null)
            binding.edtSearchKey.setText(lastInput)
    }

    private fun checkIfWeatherListIsEmpty(weatherList: List<Weather>?) {
        adapter.submitList(weatherList)
        if (weatherList != null && weatherList.isEmpty()) {
            binding.rvWeather.visibility = View.INVISIBLE
            binding.tvPlaceholder.visibility = View.VISIBLE
        } else {
            binding.rvWeather.visibility = View.VISIBLE
            binding.tvPlaceholder.visibility = View.GONE
        }
    }


}

