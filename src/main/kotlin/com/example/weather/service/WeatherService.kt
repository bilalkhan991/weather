package com.example.weather.service

import com.example.weather.model.DailyReport
import com.example.weather.model.DailyWeatherReport
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WeatherService {

    fun processWeatherForecast(forecast : JsonNode?): Mono<DailyWeatherReport> {
        val dailyReports : ArrayList<DailyReport> = ArrayList()
        forecast?.forEach { f ->
            val dailyReport =
                    DailyReport(f.get("name").toString(),
                            f.get("temperature").toString(),
                            f.get("shortForecast").toString())
            dailyReports.add(dailyReport);
        }

        return Mono.just(DailyWeatherReport(dailyReports));
    }

}