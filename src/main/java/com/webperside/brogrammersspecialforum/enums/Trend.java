package com.webperside.brogrammersspecialforum.enums;

import com.webperside.brogrammersspecialforum.exception.RestException;

public enum Trend {
    NOT_TREND(0),
    TREND(1);

    private final Byte value;

    Trend(int value) {
        this.value = (byte) value;
    }

    public Byte getValue() {
        return value;
    }

    public static Trend getByValue(int value){
        for(Trend trend : values()){
            if(trend.getValue().equals((byte) value)){
                return trend;
            }
        }
        throw new RestException(ErrorEnum.ENUM_NOT_FOUND_EXCEPTION);
    }
}
