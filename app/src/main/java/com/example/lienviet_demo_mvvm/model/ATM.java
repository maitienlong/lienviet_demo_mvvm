package com.example.lienviet_demo_mvvm.model;

import android.text.TextUtils;

public class ATM {
    private String Number;
    private String name;
    private  String type;
private String form;
    public ATM() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public ATM(String number, String name, String type) {
        this.Number = number;
        this.name = name;
        this.type = type;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid(){
        return !TextUtils.isEmpty(getNumber());
    }
    public boolean form(){
        boolean status = false;
        if(getForm().equals("Chuyen tien den so tai khoan")){
            status = true;
        }else if(getForm().equals("Chuyen tien qua so the")){
            status = false;
        }
        return status;
    }

}
