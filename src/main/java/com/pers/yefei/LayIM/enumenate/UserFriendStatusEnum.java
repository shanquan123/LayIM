package com.pers.yefei.LayIM.enumenate;

public enum UserFriendStatusEnum {
    APPLIED(1, "已申请"),
    SENDED(2, "已推送给用户"),
    AGREED(3, "已同意"),
    REFUSED(4, "已拒绝"),
    IGNORED(5, "已忽略");


    private int status;
    private String desc;
    UserFriendStatusEnum(int status, String desc) {
        this.desc = desc;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
