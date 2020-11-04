package com.webperside.brogrammersspecialforum.enums;

import com.webperside.brogrammersspecialforum.exception.RestException;

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
        throw new RestException(ErrorEnum.ENUM_NOT_FOUND_EXCEPTION);
    }
}
