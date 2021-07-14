package com.ValeriiBamburov.stockScreener.model;

public class Stock {
    private String name;
    private Double forecast;
    private Double bestForecast;
    private Double currentPrice;
    private Integer smartScore;

    public Stock(String name, Double forecast, Double bestForecast, Integer smartScore, Double currentPrice) {
        this.name = name;
        this.forecast = forecast;
        this.bestForecast = bestForecast;
        this.smartScore = smartScore;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public Double getForecast() {
        return forecast;
    }

    public Double getBestForecast() {
        return bestForecast;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public Integer getSmartScore() {
        return smartScore;
    }

    @Override
    public String toString(){
        return "Name: " + name + "\t Mark: " + smartScore + "\t Forecast: " + forecast + "%\t Best forecast: " + bestForecast + "%\t Current Price in USD: " + currentPrice;
    }
}
