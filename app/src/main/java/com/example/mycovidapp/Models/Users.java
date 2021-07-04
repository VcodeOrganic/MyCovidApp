package com.example.mycovidapp.Models;

public class Users {
    String profilePic , userName, mail, password, userID, phoneNo, pincode, DOB;

    public Users(String profilePic, String userName, String mail, String password, String userID, String phoneNo, String pincode, String DOB) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userID = userID;
        this.phoneNo = phoneNo;
        this.pincode = pincode;
        this.DOB = DOB;
    }

    public Users(){}

    public Users(String userName, String mail, String password) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    public Users(String userName, String mail, String password, String phoneNo, String pincode, String DOB) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.phoneNo = phoneNo;
        this.pincode = pincode;
        this.DOB = DOB;
    }

    public Users(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
