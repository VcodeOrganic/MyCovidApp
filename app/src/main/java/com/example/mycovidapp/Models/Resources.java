package com.example.mycovidapp.Models;

public class Resources {

    public String instiName, instiphone, instimail, instiAddress, currVaccine, currICUBeds, currNBeds, currOxygen, currPlasma;

    public Resources(){}

    public Resources(String instiName, String instiPhone, String instiMail, String instiAddress, String currVaccine, String currICUBeds, String currNBeds, String currOxygen, String currPlasma) {
        this.instiName = instiName;
        this.instiphone = instiPhone;
        this.instimail = instiMail;
        this.instiAddress = instiAddress;
        this.currVaccine = currVaccine;
        this.currICUBeds = currICUBeds;
        this.currNBeds = currNBeds;
        this.currOxygen = currOxygen;
        this.currPlasma = currPlasma;
    }

    public String getInstiName() {
        return instiName;
    }

    public void setInstiName(String instiName) {
        this.instiName = instiName;
    }

    public String getInstiPhone() {
        return instiphone;
    }

    public void setInstiPhone(String instiPhone) {
        this.instiphone = instiPhone;
    }

    public String getInstiMail() {
        return instiphone;
    }

    public void setInstiMail(String instiMail) {
        this.instiphone = instiMail;
    }

    public String getInstiAddress() {
        return instiAddress;
    }

    public void setInstiAddress(String instiAddress) {
        this.instiAddress = instiAddress;
    }

    public String getCurrVaccine() {
        return currVaccine;
    }

    public void setCurrVaccine(String currVaccine) {
        this.currVaccine = currVaccine;
    }

    public String getCurrICUBeds() {
        return currICUBeds;
    }

    public void setCurrICUBeds(String currICUBeds) {
        this.currICUBeds = currICUBeds;
    }

    public String getCurrNBeds() {
        return currNBeds;
    }

    public void setCurrNBeds(String currNBeds) {
        this.currNBeds = currNBeds;
    }

    public String getCurrOxygen() {
        return currOxygen;
    }

    public void setCurrOxygen(String currOxygen) {
        this.currOxygen = currOxygen;
    }

    public String getCurrPlasma() {
        return currPlasma;
    }

    public void setCurrPlasma(String currPlasma) {
        this.currPlasma = currPlasma;
    }
}

