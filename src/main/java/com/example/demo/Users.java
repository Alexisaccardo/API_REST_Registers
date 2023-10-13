package com.example.demo;

public class Users {

    public String id;
    public String name;
    public String ally;
    public String status;

    public Users(String id, String name, String ally, String status) {
        this.id = id;
        this.name = name;
        this.ally = ally;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlly() {
        return ally;
    }

    public void setAlly(String ally) {
        this.ally = ally;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
