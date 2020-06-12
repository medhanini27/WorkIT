package com.example.workit;

import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class user {
    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("lastname")
    @Expose
    String lastname;

    @SerializedName("adress")
    @Expose
    String adress;

    @SerializedName("salt")
    @Expose
    String salt;

    @SerializedName("uniqueid")
    @Expose
    String uniqueid ;

    @SerializedName("created-at")
    @Expose
    Date created;

    @SerializedName("uploaded-at")
    @Expose
    Date uploaded;


    @SerializedName("encrypted")
    @Expose
    String password;

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("phone")
    @Expose
    String phone;
     View.OnClickListener requestBtnClickListener;

    public user(String name, String email, String lastname, String adress, String salt, String uniqueid, Date created, Date uploaded, String password, int id) {
        this.name = name;
        this.email = email;
        this.lastname = lastname;
        this.adress = adress;
        this.salt = salt;
        this.uniqueid = uniqueid;
        this.created = created;
        this.uploaded = uploaded;
        this.password = password;
        this.id = id;
    }

    public user(String name, String email, String lastname, String adress, String uniqueid, Date created, Date uploaded,String phone) {
        this.name = name;
        this.email = email;
        this.lastname = lastname;
        this.adress = adress;
        this.uniqueid = uniqueid;
        this.created = created;
        this.uploaded = uploaded;
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }
}
