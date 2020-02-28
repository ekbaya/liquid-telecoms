package com.example.liquidscholarke.models;

public class ModelUserInfo {
    //user same name as in firebase database
    String name, email, phone, uid, university, course, scholarshipType, country, career, profile;

    public ModelUserInfo() {
    }

    public ModelUserInfo(String name, String email, String phone, String uid, String university, String course, String scholarshipType, String country, String career, String profile) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.uid = uid;
        this.university = university;
        this.course = course;
        this.scholarshipType = scholarshipType;
        this.country = country;
        this.career = career;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getScholarshipType() {
        return scholarshipType;
    }

    public void setScholarshipType(String scholarshipType) {
        this.scholarshipType = scholarshipType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
