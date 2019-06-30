package com.liuyihui.common.io;

import java.util.Objects;

public class UploadFaceInfo {

    private double age;
    private long tId;
    private double gender;
    private long pId;
    private long ts;

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    public double getGender() {
        return gender;
    }

    public void setGender(double gender) {
        this.gender = gender;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UploadFaceInfo)) return false;
        UploadFaceInfo that = (UploadFaceInfo) o;
        return gettId() == that.gettId() && getpId() == that.getpId() && getTs() == that.getTs();
    }

    @Override public int hashCode() {
        return Objects.hash(gettId(), getpId(), getTs());
    }
}
