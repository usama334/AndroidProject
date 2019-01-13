package com.example.hp.blooddonation;

public class my_requests_model_class {
    String my_req_name, my_req_no, my_req_loc, my_req_city,my_req_bg,my_req_date,my_req_msg;
    public my_requests_model_class(){

    }

    public my_requests_model_class(String my_req_name, String my_req_no, String my_req_loc, String my_req_city, String my_req_bg,String my_req_msg,String my_req_date) {
        this.my_req_name = my_req_name;
        this.my_req_no = my_req_no;
        this.my_req_loc = my_req_loc;
        this.my_req_city = my_req_city;
        this.my_req_bg = my_req_bg;

        my_req_date=this.my_req_date;
        my_req_msg=this.my_req_msg;
    }

    public String getMy_req_name() {
        return my_req_name;
    }

    public void setMy_req_name(String my_req_name) {
        this.my_req_name = my_req_name;
    }

    public String getMy_req_no() {
        return my_req_no;
    }

    public void setMy_req_no(String my_req_no) {
        this.my_req_no = my_req_no;
    }

    public String getMy_req_loc() {
        return my_req_loc;
    }

    public void setMy_req_loc(String my_req_loc) {
        this.my_req_loc = my_req_loc;
    }

    public String getMy_req_city() {
        return my_req_city;
    }

    public void setMy_req_city(String my_req_city) {
        this.my_req_city = my_req_city;
    }

    public String getMy_req_bg() {
        return my_req_bg;
    }

    public void setMy_req_bg(String my_req_bg) {
        this.my_req_bg = my_req_bg;
    }


}
