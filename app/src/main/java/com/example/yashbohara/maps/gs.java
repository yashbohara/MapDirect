package com.example.yashbohara.maps;
public class gs {
    float lat,lng;
    String name;

    public gs(float lat, float lng,String name) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;

    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
       this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
