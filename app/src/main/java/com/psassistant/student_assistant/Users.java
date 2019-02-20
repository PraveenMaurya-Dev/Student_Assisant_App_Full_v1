package com.psassistant.student_assistant;

public class Users {

        public  String  firstname , lastname ,year , collegename;


    public Users(){

    }


    public Users(String firstname, String lastname, String selection, String collegename) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.year = selection;
        this.collegename = collegename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String selection) {
        this.year = selection;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }
}


