package com.example.workit;

import android.view.View;

import java.util.ArrayList;

public class MyTasks {
    private String price;
    private String jobtitle;
    private String jobdescription;
    private String request_count;
    private String deliverytime;
    private String deadlinetime;
    private String name;
    private String phone;

    private View.OnClickListener requestBtnClickListener;

    public MyTasks() {
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public void setRequest_count(String request_count) {
        this.request_count = request_count;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }

    public void setDeadlinetime(String deadlinetime) {
        this.deadlinetime = deadlinetime;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public String getPrice() {
        return price;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public String getRequest_count() {
        return request_count;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public String getDeadlinetime() {
        return deadlinetime;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyTasks(String price, String jobtitle, String jobdescription, String request_count, String deliverytime, String deadlinetime, String name, String phone) {
        this.price = price;
        this.jobtitle = jobtitle;
        this.jobdescription = jobdescription;
        this.request_count = request_count;
        this.deliverytime = deliverytime;
        this.deadlinetime = deadlinetime;
        this.name = name;
        this.phone = phone;

    }
    public static ArrayList<MyTasks> getTestingList2() {
        ArrayList<MyTasks> MyTasks = new ArrayList<>();
        MyTasks.add(new MyTasks("14dt", "laundry", "easy to do laundry shores for students", "7", "32/12/2019", "tomorrow","dhia","20168544"));
        MyTasks.add(new MyTasks("15dt", "car wash", "car washing every sunday for 15dt", "3", "31/12/2019", "today","ahmed","20111111"));
        MyTasks.add(new MyTasks("6dt", "moving", "easy to do whole day job", "5", "33/12/2019", "next tuesday","salah","40251256"));


        return MyTasks;

    }
}