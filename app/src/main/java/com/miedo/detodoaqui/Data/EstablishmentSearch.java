package com.miedo.detodoaqui.Data;

public class EstablishmentSearch {
    private String name;
    private String address;
    private String urlImage;
    private float rating;

    public EstablishmentSearch(String name, String address, String urlImage, float rating) {
        this.name = name;
        this.address = address;
        this.urlImage = urlImage;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
