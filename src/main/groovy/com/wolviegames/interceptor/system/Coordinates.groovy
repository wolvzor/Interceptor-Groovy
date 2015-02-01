package com.wolviegames.interceptor.system

import groovy.transform.Canonical

import java.awt.Graphics
import java.awt.Image

@Canonical
class Coordinates {

    int xCoord
    int yCoord
    int offset

    void drawAtCoordinates(Graphics graphics, Image image, double scale = 1.0){
        offset = (yCoord == 0) ? 1 : yCoord % 2 -1
        graphics.drawImage(image, ((xCoord*GlobalValues.HEX_WIDTH) + (GlobalValues.HEX_WIDTH / 2 * offset) - image.getWidth()/2).intValue(),
                ((yCoord * GlobalValues.HEX_HEIGHT/2) + (image.getHeight())).intValue(),
                (image.getWidth()*scale).intValue(), (image.getHeight()*scale).intValue(), null);
    }
}
