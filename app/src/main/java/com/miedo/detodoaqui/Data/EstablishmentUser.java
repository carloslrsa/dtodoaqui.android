package com.miedo.detodoaqui.Data;

public class EstablishmentUser {

    private int id;
    private String title;
    private float rating;
    private Integer reviews;
    private String urlImage;

    public EstablishmentUser(String title, float rating, Integer reviews, String urlImage) {
        this.title = title;
        this.rating = rating;
        this.reviews = reviews;
        this.urlImage = urlImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "EstablishmentUser{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", reviews=" + reviews +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}