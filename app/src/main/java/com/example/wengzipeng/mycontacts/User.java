package com.example.wengzipeng.mycontacts;

/**
 * Created by wengzipeng on 2016/11/20.
 */
public class User {
    public final static String NAME = "name";
    public final static String MOBLIE = "mobile";
    public final static String DANWEI = "danwei";
    public final static String QQ = "qq";
    public final static String ADDRESS = "address";

    private String name;
    private String mobile;
    private String danwei;
    private String qq;
    private String address;
    private int id_DB = -1;

    public static String getNAME() {
        return NAME;
    }

    public static String getMOBLIE() {
        return MOBLIE;
    }

    public static String getDANWEI() {
        return DANWEI;
    }

    public static String getQQ() {
        return QQ;
    }

    public static String getADDRESS() {
        return ADDRESS;
    }

    public int getId_DB() {
        return id_DB;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId_DB(int id_DB) {
        this.id_DB = id_DB;
    }
}
