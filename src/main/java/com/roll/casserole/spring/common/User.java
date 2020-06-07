package com.roll.casserole.spring.common;

import java.util.Arrays;

/**
 * @author roll
 * created on 2020/6/7 3:01 下午
 */
public class User {
    private Long id;

    private String name;

    private City city;

    private City[] cities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", cities=" + Arrays.toString(cities) +
                '}';
    }
}
