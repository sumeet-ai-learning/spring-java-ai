package com.learning.multimodel.models;

import java.util.List;
import java.util.Objects;

public final class CountryCodes {
    private final String country;
    private final List<String> cities;

    public CountryCodes(String country, List<String> cities) {
        this.country = Objects.requireNonNull(country, "country must not be null");
        this.cities = List.copyOf(Objects.requireNonNull(cities, "cities must not be null"));
    }

    public String getCountry() {
        return country;
    }

    public List<String> getCities() {
        return cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryCodes)) return false;
        CountryCodes that = (CountryCodes) o;
        return country.equals(that.country) && cities.equals(that.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, cities);
    }

    @Override
    public String toString() {
        return "CountryCodes{" +
                "country='" + country + '\'' +
                ", cities=" + cities +
                '}';
    }
}
