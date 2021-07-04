package com.example.mycovidapp.Models;

public class Institutes {
    String InstiName, Instiphone, Instimail, Instipass, InstiID,  Instipincode, InstiAddress;

    public Institutes(String instiName, String instiphone, String instimail, String instipass, String instiID, String instipincode, String instiAddress) {
        InstiName = instiName;
        Instiphone = instiphone;
        Instimail = instimail;
        Instipass = instipass;
        InstiID = instiID;
        Instipincode = instipincode;
        InstiAddress = instiAddress;
    }

    public Institutes(){}

    public Institutes(String instiName, String instiphone, String instimail, String instipass, String instipincode, String instiAddress) {
        InstiName = instiName;
        Instiphone = instiphone;
        Instimail = instimail;
        Instipass = instipass;
        Instipincode = instipincode;
        InstiAddress = instiAddress;
    }

    public String getInstiName() {
        return InstiName;
    }

    public void setInstiName(String instiName) {
        InstiName = instiName;
    }

    public String getInstiphone() {
        return Instiphone;
    }

    public void setInstiphone(String instiphone) {
        Instiphone = instiphone;
    }

    public String getInstimail() {
        return Instimail;
    }

    public void setInstimail(String instimail) {
        Instimail = instimail;
    }

    public String getInstipass() {
        return Instipass;
    }

    public void setInstipass(String instipass) {
        Instipass = instipass;
    }

    public String getInstiID() {
        return InstiID;
    }

    public void setInstiID(String instiID) {
        InstiID = instiID;
    }

    public String getInstipincode() {
        return Instipincode;
    }

    public void setInstipincode(String instipincode) {
        Instipincode = instipincode;
    }

    public String getInstiAddress() {
        return InstiAddress;
    }

    public void setInstiAddress(String instiAddress) {
        InstiAddress = instiAddress;
    }
}
