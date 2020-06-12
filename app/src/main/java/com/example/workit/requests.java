package com.example.workit;

import java.sql.Date;

public class requests {

    int id;
    String idservice;
    String idemploye;
    String date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdservice() {
        return idservice;
    }

    public void setIdservice(String idservice) {
        this.idservice = idservice;
    }

    public String getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(String idemploye) {
        this.idemploye = idemploye;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public requests(int id,String idservice, String idemploye, String date) {
        this.id = id;
        this.idservice = idservice;
        this.idemploye = idemploye;
        this.date = date;
    }

    public requests() {
    }
}
