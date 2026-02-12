package com.example.larica.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaDTO {

    @JsonProperty("weather")
    private List<WeatherInfo> weather;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherInfo {
        @JsonProperty("main")
        private String main; // Ex: "Rain", "Clear", "Clouds"
    }
}
