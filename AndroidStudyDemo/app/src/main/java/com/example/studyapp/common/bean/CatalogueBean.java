package com.example.studyapp.common.bean;

public class CatalogueBean {
    public String pageName;
    public Class aClass;
    public String subTitle = "";

    public CatalogueBean(String pageName, Class aClass) {
        this.pageName = pageName;
        this.aClass = aClass;
    }
}
