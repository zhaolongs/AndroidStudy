package com.example.studyapp.common.bean;

public class VideoBean {

    private  UserBean userBean;
    private String title;
    private String image;
    private String videoUrl;
    private int pariseNumber;
    private int messageNumber;
    private int collectNumber;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getPariseNumber() {
        return pariseNumber;
    }

    public void setPariseNumber(int pariseNumber) {
        this.pariseNumber = pariseNumber;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }
}
