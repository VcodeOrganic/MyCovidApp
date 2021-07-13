package com.example.mycovidapp.Models;

public class Resources {

    public String instiName, instiphone, instimail, instiAddress, instipincode, currVaccine, currICUBeds, currOxygen, currPlasma;

    public Resources(){}

    public String getrInstipincode() {
        return instipincode;
    }

    public void setrInstipincode(String instipincode) {
        this.instipincode = instipincode;
    }

    public Resources(String rinstiName, String rinstiphone, String rinstimail, String rinstiAddress, String rinstiPincode, String rcurrVaccine, String rcurrICUBeds, String rcurrOxygen, String rcurrPlasma) {
        this.instiName = rinstiName;
        this.instiphone = rinstiphone;
        this.instimail = rinstimail;
        this.instiAddress = rinstiAddress;
        this.currVaccine = rcurrVaccine;
        this.currICUBeds = rcurrICUBeds;
        this.currOxygen = rcurrOxygen;
        this.currPlasma = rcurrPlasma;
        this.instipincode = rinstiPincode;
    }

    public String getrInstiName() {
        return instiName;
    }

    public void setrInstiName(String instiName) {
        this.instiName = instiName;
    }

    public String getrInstiPhone() {
        return instiphone;
    }

    public void setrInstiPhone(String instiPhone) {
        this.instiphone = instiPhone;
    }

    public String getrInstiMail() {
        return instimail;
    }

    public void setrInstiMail(String instiMail) {
        this.instimail = instiMail;
    }

    public String getrInstiAddress() {
        return instiAddress;
    }

    public void setrInstiAddress(String instiAddress) {
        this.instiAddress = instiAddress;
    }

    public String getrCurrVaccine() {
        return currVaccine;
    }

    public void setrCurrVaccine(String currVaccine) {
        this.currVaccine = currVaccine;
    }

    public String getrCurrICUBeds() {
        return currICUBeds;
    }

    public void setrCurrICUBeds(String currICUBeds) {
        this.currICUBeds = currICUBeds;
    }

    public String getrCurrOxygen() {
        return currOxygen;
    }

    public void setrCurrOxygen(String currOxygen) {
        this.currOxygen = currOxygen;
    }

    public String getrCurrPlasma() {
        return currPlasma;
    }

    public void setrCurrPlasma(String currPlasma) {
        this.currPlasma = currPlasma;
    }
}

