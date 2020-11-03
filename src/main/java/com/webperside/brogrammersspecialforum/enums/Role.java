package com.webperside.brogrammersspecialforum.enums;

public enum Role {
    ADMIN(0, "ROLE_ADMIN"),
    USER(1, "ROLE_USER");

    private final Byte value;
    private final String name;

    Role(int value, String name) {
        this.value = (byte) value;
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Role getRoleByValue(int value) {
        for (Role role : values()) {
            if (role.getValue().equals((byte) value)){
                return role;
            }
        }
        return null;
        //todo roleNotFound exception
    }
}
