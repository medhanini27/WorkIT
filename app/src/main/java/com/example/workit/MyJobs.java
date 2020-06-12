package com.example.workit;

import android.view.View;

import java.util.ArrayList;


public class MyJobs {
    private String price;
    private String jobtitle ;
    private String jobdescription;
    private String request_count;
    private String deliverytime;
    private String deadlinetime;
    private View.OnClickListener requestBtnClickListener;

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

    public String getRequest_count() {
        return request_count;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public String getDeadlinetime() {
        return deadlinetime;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    public MyJobs(String price, String jobtitle, String jobdescription, String request_count, String deliverytime, String deadlinetime) {
        this.price = price;
        this.jobtitle = jobtitle;
        this.jobdescription = jobdescription;
        this.request_count = request_count;
        this.deliverytime = deliverytime;
        this.deadlinetime = deadlinetime;
    }

    public MyJobs(){

    }
    public static ArrayList<MyJobs> getTestingList() {
        ArrayList<MyJobs> Myjobs = new ArrayList<>();
        Myjobs.add(new MyJobs("14dt","laundry","easy to do laundry shores for students","7","32/12/2019","tomorrow"));
        Myjobs.add(new MyJobs("15dt","car wash","car washing every sunday for 15dt","3","31/12/2019","today"));
        Myjobs.add(new MyJobs("6dt","moving","easy to do whole day job","5","33/12/2019","next tuesday"));



        return Myjobs;

    }


}
