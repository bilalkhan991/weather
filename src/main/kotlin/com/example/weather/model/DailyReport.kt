package com.example.weather.model

import com.fasterxml.jackson.annotation.JsonProperty

data class DailyReport(
        @JsonProperty("day_name") val dayName : String,
        @JsonProperty("temp_high_celsius") val tempHighCelsius: String,
        @JsonProperty("forecast_blurp") val foreCastBlurp: String
)


