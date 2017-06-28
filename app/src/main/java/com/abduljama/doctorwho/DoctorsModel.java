package com.abduljama.doctorwho;

/**
 * Created by abduljama on 6/28/17.
 */

public class DoctorsModel {
    String name;
    String speciality;
    String hospital;
    int  img_profile;

    public DoctorsModel(String name, String speciality, String hospital, int img_profile) {
        this.name = name;
        this.speciality = speciality;
        this.hospital = hospital;
        this.img_profile = img_profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(int img_profile) {
        this.img_profile = img_profile;
    }
}
