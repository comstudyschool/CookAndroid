package com.example.recipe;

public class CarouselItem {
    private int imageResource;
    private String text;

    public CarouselItem(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }
}
