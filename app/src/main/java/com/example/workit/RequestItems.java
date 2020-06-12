package com.example.workit;

public class RequestItems {
    private String name;
    private String lastname;
    private String id;
    private String idservice;

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public RequestItems(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public RequestItems() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdservice() {
        return idservice;
    }

    public void setIdservice(String idservice) {
        this.idservice = idservice;
    }
}
