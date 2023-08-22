package kz.aspan.weatherapp.presentation.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kz.aspan.weatherapp.domain.repository.WeatherRepository
import kz.aspan.weatherapp.domain.use_case.GetLastInputUseCase
import kz.aspan.weatherapp.domain.use_case.GetWeatherListUseCase
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getWeatherListUseCase: GetWeatherListUseCase,
    private val getLastInputUseCase: GetLastInputUseCase,
) : ViewModel() {
    private val initialViewState = WeatherViewState()

    private val _viewState: MutableLiveData<WeatherViewState> = MutableLiveData(initialViewState)
    val viewState: LiveData<WeatherViewState> = _viewState

    private val unexpectedErrorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("WeatherViewModel", "exception message: ${throwable.message}")
    }


    private val inputTextFlow = MutableStateFlow("")
    private val searchFlow = flow<Nothing> {
        inputTextFlow
            .filter { _viewState.value?.lastInput != it && it.trim().length >= 2 }
            .debounce(300)
            .collectLatest {
                getWeatherList(it)
            }
    }.launchIn(viewModelScope)

    init {
        getLatestData()
    }

    fun onInputTextChanged(text: String) {
        inputTextFlow.value = text
    }

    private fun getWeatherList(locationName: String) {
        val curState = viewState.value ?: initialViewState
        _viewState.value = curState.copy(isLoading = true, lastInput = null)
        viewModelScope.launch(unexpectedErrorHandler) {
            getWeatherListUseCase(locationName)
                .collect {
                    _viewState.value = viewState.value?.copy(
                        isLoading = false,
                        listOfWeather = it
                    )
                }
        }
    }

    private fun getLatestData() {
        viewModelScope.launch {
            getLastInputUseCase()?.let { lastInput ->
                _viewState.value = initialViewState.copy(lastInput = lastInput.input)
                getWeatherList(lastInput.input)
            }
        }
    }
}