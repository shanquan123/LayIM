package com.pers.yefei.LayIM.enumenate;

public enum UserMsgTypeEnum {
    TEXT(1, "文本"),
    FILE(2, "文件"),
    VOICE(3, "语音");


    private int msgType;
    private String desc;
    UserMsgTypeEnum(int msgType, String desc) {
        this.desc = desc;
        this.msgType = msgType;
    }

    public int getMsgType() {
        return msgType;
    }

    public String getDesc() {
        return desc;
    }
}
