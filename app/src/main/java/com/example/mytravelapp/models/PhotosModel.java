package com.example.mytravelapp.models;

public final class PhotosModel {
    String img_url;
    String img_subject;

    public PhotosModel() {
    }

    public PhotosModel(String img_url, String img_subject) {
        this.img_url = img_url;
        this.img_subject = img_subject;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_subject() {
        return img_subject;
    }

    public void setImg_subject(String img_subject) {
        this.img_subject = img_subject;
    }
}
