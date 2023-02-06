package com.example.mytravelapp.models;

public final class HomeModel {
    String  message;

    public HomeModel(){
    }
    public HomeModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
