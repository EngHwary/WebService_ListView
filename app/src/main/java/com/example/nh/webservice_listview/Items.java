package com.example.nh.webservice_listview;

public class Items {

    String name;
    int likes;
    String image;

    public String getName() {
        return name;
    }

    public int getLikes() {
        return likes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;

    }

    public Items(String name, int likes, String image) {
        this.name = name;
        this.likes = likes;
        this.image = image;

    }
}
