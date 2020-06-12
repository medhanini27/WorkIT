package com.example.workit;

import android.view.View;



public class Services  {

int id;
String uid;
String idp;

String title;
int price;
String description;
String datecreation;
String deadline;
Double placelat;
Double placelng;
int etat;
private View.OnClickListener requestBtnClickListener;
    private View.OnClickListener callBtnClickListener;
    private View.OnClickListener call1BtnClickListener;

    private View.OnClickListener deleteBtnClickListener;
    private View.OnClickListener showreqBtnClickListener;
    private View.OnClickListener showplaceBtnClickListener;
    private View.OnClickListener showplace1BtnClickListener;




    public Services() {
    }

    public Services(int id, String uid, String idp, String title, int price, String description, String datecreation, String deadline, Double placelat, Double placelng, int etat) {
        this.id = id;
        this.uid = uid;
        this.idp = idp;
        this.title = title;
        this.price = price;
        this.description = description;
        this.datecreation = datecreation;
        this.deadline = deadline;
        this.placelat = placelat;
        this.placelng = placelng;
        this.etat = etat;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Double getPlacelat() {
        return placelat;
    }

    public void setPlacelat(Double placelat) {
        this.placelat = placelat;
    }

    public Double getPlacelng() {
        return placelng;
    }

    public void setPlacelng(Double placelng) {
        this.placelng = placelng;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }


    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public View.OnClickListener getCallBtnClickListener() {
        return callBtnClickListener;
    }

    public void setCallBtnClickListener(View.OnClickListener callBtnClickListener) {
        this.callBtnClickListener = callBtnClickListener;
    }

    public View.OnClickListener getDeleteBtnClickListener() {
        return deleteBtnClickListener;
    }

    public void setDeleteBtnClickListener(View.OnClickListener deleteBtnClickListener) {
        this.deleteBtnClickListener = deleteBtnClickListener;
    }

    public View.OnClickListener getShowreqBtnClickListener() {
        return showreqBtnClickListener;
    }

    public void setShowreqBtnClickListener(View.OnClickListener showreqBtnClickListener) {
        this.showreqBtnClickListener = showreqBtnClickListener;
    }

    public View.OnClickListener getShowplaceBtnClickListener() {
        return showplaceBtnClickListener;
    }

    public void setShowplaceBtnClickListener(View.OnClickListener showplaceBtnClickListener) {
        this.showplaceBtnClickListener = showplaceBtnClickListener;
    }

    public View.OnClickListener getCall1BtnClickListener() {
        return call1BtnClickListener;
    }

    public void setCall1BtnClickListener(View.OnClickListener call1BtnClickListener) {
        this.call1BtnClickListener = call1BtnClickListener;
    }

    public View.OnClickListener getShowplace1BtnClickListener() {
        return showplace1BtnClickListener;
    }

    public void setShowplace1BtnClickListener(View.OnClickListener showplace1BtnClickListener) {
        this.showplace1BtnClickListener = showplace1BtnClickListener;
    }
}
