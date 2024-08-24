package com.example.qradmin;


public class User {
    private String name;
    private String bio;
    private String website;
    private String imageUrl;

    public User() {
        // No-argument constructor needed for Firebase serialization
    }

    public User(String name, String bio, String website, String imageUrl) {
        this.name = name;
        this.bio = bio;
        this.website = website;
        this.imageUrl = imageUrl;
    }

    // Getters and setters...
}
