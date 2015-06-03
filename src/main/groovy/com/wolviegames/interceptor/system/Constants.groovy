package com.wolviegames.interceptor.system

import groovy.transform.Canonical

@Canonical
class GlobalValues {

    static int HEX_HEIGHT = 384
    static int HEX_WIDTH = 256
    static int HEX_ACTUAL_HEIGHT = 295

    // TODO Fix the hex drawing code for this stupid offset.
    static int HEX_HEIGHT_OFFSET = 5
    static int HEX_WIDTH_OFFSET = 5
}
