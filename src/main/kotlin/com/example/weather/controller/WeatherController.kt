package com.example.weather.controller

import com.example.weather.model.DailyWeatherReport
import com.example.weather.service.WeatherService
import com.fasterxml.jackson.databind.JsonNode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/weather")
class WeatherController(
        private val weatherService: WeatherService
) {
    @GetMapping
    suspend fun callWeatherApi(): Mono<DailyWeatherReport> {
        val forecastGenerator : JsonNode? = withContext(Dispatchers.IO) {
            WebClient.create().get()
                    .uri("https://api.weather.gov/gridpoints/MLB/33,70/forecast")
                    .retrieve()
                    .bodyToMono<JsonNode>()
                    .block()
        }?.get("properties")?.get("periods");
        return weatherService.processWeatherForecast(forecastGenerator)
    }
}