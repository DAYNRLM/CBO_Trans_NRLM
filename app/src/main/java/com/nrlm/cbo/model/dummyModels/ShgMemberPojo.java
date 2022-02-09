package com.nrlm.cbo.model.dummyModels;

public class ShgMemberPojo {

    public String shgCode;
    public String shgMemberCode;
    public String shgMemberName;
    public int position;
    boolean memberCb;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public String getShgCode() {
        return shgCode;
    }

    public void setShgCode(String shgCode) {
        this.shgCode = shgCode;
    }

    public String getShgMemberCode() {
        return shgMemberCode;
    }

    public void setShgMemberCode(String shgMemberCode) {
        this.shgMemberCode = shgMemberCode;
    }

    public String getShgMemberName() {
        return shgMemberName;
    }

    public void setShgMemberName(String shgMemberName) {
        this.shgMemberName = shgMemberName;
    }

    public boolean isMemberCb() {
        return memberCb;
    }

    public void setMemberCb(boolean memberCb) {
        this.memberCb = memberCb;
    }


}
