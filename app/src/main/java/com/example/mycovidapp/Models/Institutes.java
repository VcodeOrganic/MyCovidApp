package com.example.mycovidapp.Models;

public class Institutes {
    String InstiName, Instiphone, Instimail, Instipass, InstiID,  Instipincode, InstiAddress, KycStatus, CurrVaccine, CurrOxygen, CurrICUBeds, CurrPlasma, Latitude, Longitude;

    public Institutes(String instiName, String instiphone, String instimail, String instipass, String instiID, String instipincode, String instiAddress, String kycStatus) {
        InstiName = instiName;
        Instiphone = instiphone;
        Instimail = instimail;
        Instipass = instipass;
        InstiID = instiID;
        Instipincode = instipincode;
        InstiAddress = instiAddress;
        KycStatus = kycStatus;
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

    public Institutes(String instiName, String instiphone, String instimail, String instipass, String instipincode, String instiAddress,String kycStatus, String currVaccine, String currOxygen, String currICUBeds, String currPlasma, String myLatitude, String myLongitude) {
        InstiName = instiName;
        Instiphone = instiphone;
        Instimail = instimail;
        Instipass = instipass;
        Instipincode = instipincode;
        InstiAddress = instiAddress;
        KycStatus = kycStatus;
        CurrVaccine=currVaccine;
        CurrOxygen=currOxygen;
        CurrICUBeds=currICUBeds;
        CurrPlasma=currPlasma;
        Latitude = myLatitude;
        Longitude = myLongitude;
    }

    public String getMyLatitude() {
        return Latitude;
    }

    public void setMyLatitude(String myLatitude) {
       Latitude = myLatitude;
    }

    public String getMyLongitude() {
        return Longitude;
    }

    public void setMyLongitude(String myLongitude) {
        Longitude = myLongitude;
    }

    public String getCurrICUBeds() {
        return CurrICUBeds;
    }

    public void setCurrICUBeds(String currICUBeds) {
        CurrICUBeds = currICUBeds;
    }


    public String getCurrVaccine() {
        return CurrVaccine;
    }

    public void setCurrVaccine(String currVaccine) {
        CurrVaccine = currVaccine;
    }

    public String getCurrOxygen() {
        return CurrOxygen;
    }

    public void setCurrOxygen(String currOxygen) {
        CurrOxygen = currOxygen;
    }

    public String getCurrPlasma() {
        return CurrPlasma;
    }

    public void setCurrPlasma(String currPlasma) {
        CurrPlasma = currPlasma;
    }

    public String getKycStatus() {
        return KycStatus;
    }

    public void setKycStatus(String kycStatus) {
        KycStatus = kycStatus;
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
