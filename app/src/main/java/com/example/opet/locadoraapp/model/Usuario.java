package com.example.opet.locadoraapp.model;

public class Usuario {

    private int id;
    private String login;
    private String pswd;

    public Usuario() {
    }

    public Usuario(int id, String login, String pswd) {
        this.id = id;
        this.login = login;
        this.pswd = pswd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
