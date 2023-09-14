package com.carrot.domain;

public class LocationVO {
    private String loc1;
    private String loc2;
    private String loc3;

    public LocationVO() {
    }

    public LocationVO(String loc1, String loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    public LocationVO(String loc1) {
        this.loc1 = loc1;

    }

    public String getLoc1() {
        return loc1;
    }

    public void setLoc1(String loc1) {
        this.loc1 = loc1;
    }

    public String getLoc2() {
        return loc2;
    }

    public void setLoc2(String loc2) {
        this.loc2 = loc2;
    }

    public String getLoc3() {
        return loc3;
    }

    public void setLoc3(String loc3) {
        this.loc3 = loc3;
    }

    @Override
    public String toString() {
        return "LocationVO{" +
                "loc1='" + loc1 + '\'' +
                ", loc2='" + loc2 + '\'' +
                ", loc3='" + loc3 + '\'' +
                '}';
    }
}
