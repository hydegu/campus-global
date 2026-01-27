package com.example.merchant.api.enums;


public enum BusinessLicenseType {
    INDIVIDUAL(2,"个人"),
    COMPANY(1,"企业");

    BusinessLicenseType(int type,String typeName) {
        this.typeName = typeName;
        this.type = type;
    }

    private final String typeName;
    private final int type;
}
