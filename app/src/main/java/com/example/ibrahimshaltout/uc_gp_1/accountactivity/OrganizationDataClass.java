package com.example.ibrahimshaltout.uc_gp_1.accountactivity;

public class OrganizationDataClass {
    // public MaterialBetterSpinner signUpAs;
    public String name;
    public String email;
    public String phone;
    public String orgType;
    String gender;

    public OrganizationDataClass() {

    }

    public OrganizationDataClass(String orgType, String name, String email, String phone) {
        // this.signUpAs=signUpAs;
        this.orgType = orgType;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
