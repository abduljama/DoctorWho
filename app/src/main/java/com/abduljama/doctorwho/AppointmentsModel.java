package com.abduljama.doctorwho;

/**
 * Created by abduljama on 6/28/17.
 */

public class AppointmentsModel {
    String doctor;

    String hospital;

    String date;
    String time ;

    public AppointmentsModel() {

    }

    public AppointmentsModel(String doctor, String hospital, String date, String time) {
        this.doctor = doctor;
        this.hospital = hospital;
        this.date = date;
        this.time = time;
    }


    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
