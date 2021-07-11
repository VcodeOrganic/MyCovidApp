package com.example.mycovidapp.Models;

public class Doctors {
    public String docName, docInsti, docPhone, docCallingHours;

    public Doctors(){}

    public Doctors(String docName, String docPhone, String docInsti, String docCallingHours) {
        this.docName = docName;
        this.docPhone = docPhone;
        this.docInsti = docInsti;
        this.docCallingHours = docCallingHours;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocInsti() {
        return docInsti;
    }

    public void setDocInsti(String instiName) {
        this.docInsti = instiName;
    }

    public String getDocPhone() {
        return docPhone;
    }

    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }

    public String getDocCallingHours() {
        return docCallingHours;
    }

    public void setDocCallingHours(String docCallingHours) {
        this.docCallingHours = docCallingHours;
    }
}
