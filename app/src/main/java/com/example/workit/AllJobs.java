package com.example.workit;

import android.view.View;

import java.util.ArrayList;

public class AllJobs {
        private String price;
        private String request;
        private String jobtitle ;
        private String jobdescription;
        private String names;
        private  String delivery_date;
        private String request_deadline;
        private String phone_number;
        private View.OnClickListener requestBtnClickListener1;
    public AllJobs() {
    }
    public AllJobs(String price, String request, String jobtitle, String jobdescription, String names, String delivery_date, String request_deadline, String phone_number) {
        this.price = price;
        this.request = request;
        this.jobtitle = jobtitle;
        this.jobdescription = jobdescription;
        this.names = names;
        this.delivery_date = delivery_date;
        this.request_deadline = request_deadline;
        this.phone_number = phone_number;
    }
    public View.OnClickListener getRequestBtnClickListener1() {
        return requestBtnClickListener1;
    }

    public void setRequestBtnClickListener1(View.OnClickListener requestBtnClickListener1) {
        this.requestBtnClickListener1 = requestBtnClickListener1;
    }

    public String getPrice() {
        return price;
    }

    public String getRequest() {
        return request;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public String getJobdescription() {
        return jobdescription;
    }



    public String getNames() {
        return names;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public String getRequest_deadline() {
        return request_deadline;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }


    public void setNames(String names) {
        this.names = names;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public void setRequest_deadline(String request_deadline) {
        this.request_deadline = request_deadline;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }






    public static ArrayList<AllJobs> getTestingList1() {
        ArrayList<AllJobs> Alljobs = new ArrayList<>();
        Alljobs.add(new AllJobs("14dt","7","laundry","easy to do","dhia","30/12/2019","tomorrow", "20168788"));
        Alljobs.add(new AllJobs("14dt", "8", "car wash", "hard to do", "iheb", "31/12/2019","today","50168788"));
        Alljobs.add(new AllJobs("14dt", "9", "drug deal", "in need of someone to sell for me while i fuck my bitches", "haroun", "31/12/2019","everyday","44168788"));

        return Alljobs;

    }
}
