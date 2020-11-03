package com.webperside.brogrammersspecialforum.enums;

public enum NotificationType {

    FOLLOW(0),
    REPLY(1);

    private final Byte value;

    NotificationType(int value) {
        this.value = (byte) value;
    }

    public Byte getValue() {
        return value;
    }

    public static NotificationType getTypeByValue(int value){
        for(NotificationType type : values()){
            if(type.getValue().equals((byte) value)){
                return type;
            }
        }
        return null;
        // todo notificationNotFound exception
    }
}
