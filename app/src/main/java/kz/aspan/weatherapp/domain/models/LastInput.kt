package kz.aspan.weatherapp.domain.models

data class LastInput(
    val id:Long = UNDEFINED_ID,
    val input: String,
    val createAt: Long
) {
    companion object {
        const val UNDEFINED_ID = 0L
    }
}
