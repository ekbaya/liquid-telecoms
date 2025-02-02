package com.example.liquidscholarke.models;

import java.util.Comparator;

public class ModelUsers {
    //user same name as in firebase database
    String name, email, phone, search, image,cover, uid, onlineStatus, typingTo;

    public ModelUsers() {

    }

    public static  final Comparator<ModelUsers> BY_NAME_ALPHABETICAL = new Comparator<ModelUsers>() {
        @Override
        public int compare(ModelUsers o1, ModelUsers o2) {
            return o1.name.compareTo(o2.name);
        }
    };

    public ModelUsers(String name, String email, String phone, String search, String image, String cover, String uid, String onlineStatus, String typingTo) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.search = search;
        this.image = image;
        this.cover = cover;
        this.uid = uid;
        this.onlineStatus = onlineStatus;
        this.typingTo = typingTo;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getTypingTo() {
        return typingTo;
    }

    public void setTypingTo(String typingTo) {
        this.typingTo = typingTo;
    }
}
