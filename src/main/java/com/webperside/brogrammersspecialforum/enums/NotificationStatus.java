package com.webperside.brogrammersspecialforum.enums;

public enum NotificationStatus {
    NOT_READ(0),
    READ(1);

    private final Byte value;

    NotificationStatus(int value) {
        this.value = (byte) value;
    }

    public Byte getValue() {
        return value;
    }

    public static NotificationStatus getStatusByValue(int value){
        for(NotificationStatus status : values()){
            if(status.getValue().equals((byte) value)){
                return status;
            }
        }
        return null;
        //todo notificationStatusNotFound exception
    }
}
