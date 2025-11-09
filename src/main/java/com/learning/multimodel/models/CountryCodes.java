package com.learning.multimodel.models;

import java.util.List;
import java.util.Objects;

public record CountryCodes(String country, List<String> cities) {
    public CountryCodes {
        country = Objects.requireNonNull(country, "country must not be null");
        cities = List.copyOf(Objects.requireNonNull(cities, "cities must not be null"));
    }

    // Preserve original accessor names for compatibility
    public String getCountry() {
        return country;
    }

    public List<String> getCities() {
        return cities;
    }
}