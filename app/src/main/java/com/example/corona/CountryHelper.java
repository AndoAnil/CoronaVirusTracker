package com.example.corona;

public class CountryHelper {
    private String flag,country,cases,todaydeaths,deaths,todayCases,recovered,active,critical;

    public CountryHelper(){}

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public CountryHelper(String flag, String country, String cases, String todaydeaths, String deaths, String todayCases, String recovered, String active, String critical) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaydeaths = todaydeaths;
        this.deaths = deaths;
        this.todayCases = todayCases;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
    }
}
