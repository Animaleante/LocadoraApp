package com.example.opet.locadoraapp.model;

public class Filme {

    private int id;
    private String name;
    private String genre;
    private int user_id;

    public Filme() {
    }

    public Filme(int id, String name, String genre, int user_id) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
