package com.webperside.brogrammersspecialforum.enums;

public enum Trend {
    ;

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
        return null;
        //todo trendNotFound exception
    }
}
