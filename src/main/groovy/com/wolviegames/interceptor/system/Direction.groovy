package com.wolviegames.interceptor.system

enum Direction {
    WEST(0),
    NORTHWEST(1),
    NORTHEAST(2),
    EAST(3),
    SOUTHEAST(4),
    SOUTHWEST(5);

    private final Integer directionValue;

    public Integer directionValue() {
        return directionValue;
    }


}