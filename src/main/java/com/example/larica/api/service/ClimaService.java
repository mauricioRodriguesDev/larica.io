package com.example.larica.api.service;

import com.example.larica.api.dto.ClimaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class ClimaService {

    private final RestTemplate restTemplate;

    @Value("${openweathermap.api.url}")
    private String apiUrl;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.city.id}")
    private String cityId;

    public ClimaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<String> obterCondicaoClimaticaAtual() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("id", cityId)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric");

        try {
            ClimaDTO response = restTemplate.getForObject(uriBuilder.toUriString(), ClimaDTO.class);
            if (response != null && response.getWeather() != null && !response.getWeather().isEmpty()) {
                return Optional.of(response.getWeather().get(0).getMain());
            }
        } catch (Exception e) {
            // Logar o erro em um cenário real
            System.err.println("Erro ao buscar dados do clima: " + e.getMessage());
        }
        return Optional.empty();
    }
}
