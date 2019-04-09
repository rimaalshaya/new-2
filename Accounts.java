package com.example.lockerapp;

class Accounts {
    private String UserName;
    private String PhoneNumber;
    private String Email;
    private String PassWord;
    private String Credit;
    private String locker;

    public Accounts(String userName,String passWord, String email, String phoneNumber , String credit, String locker) {
        UserName = userName;
        PhoneNumber = phoneNumber;
        Email = email;
        PassWord = passWord;
        Credit = credit;
        this.locker = locker;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getCredit() {
        return Credit;
    }

    public String getLocker() {
        return locker;
    }
}