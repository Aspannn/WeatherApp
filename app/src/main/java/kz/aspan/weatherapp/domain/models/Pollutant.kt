package kz.aspan.weatherapp.domain.models

data class Pollutant(
    val concentration: Double,
    val breakpoints: List<Double>,
    val aqiValues: List<Int> =  listOf(0, 50, 100, 150, 200, 300, 400, 500)
) {
}