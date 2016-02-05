package com.wolviegames.interceptor.system

import groovy.transform.Canonical

@Canonical
class InterceptorConstant {

    static int HEX_HEIGHT = 383
    static int HEX_WIDTH = 255
    static int HEX_ACTUAL_HEIGHT = 294

    // TODO Fix the hex drawing code for this stupid offset.
    static int HEX_HEIGHT_OFFSET = 5
    static int HEX_WIDTH_OFFSET = 5

    // Mouse wheel constants
    static double MOUSE_ZOOM_MIN = 0.3
    static double MOUSE_ZOOM_INCREMENT = 25
}
