package com.example.hp.blooddonation;

public class Users {
    String user_name, user_gender, location,bloodgroup,user_email,user_contact, user_password;
    int donation;

    public Users(){}

    public Users(String user_name, String user_email, String user_password,String user_gender, String location, String bloodgroup,String contact, int donation) {
        this.user_name = user_name;
        this.user_gender = user_gender;
        this.location = location;
        this.bloodgroup = bloodgroup;
        this.user_email = user_email;
        this.user_contact=contact;
        this.donation=donation;
        this.user_password=user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
