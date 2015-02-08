package com.wolviegames.interceptor.system

import com.wolviegames.interceptor.display.DrawingOffset
import groovy.transform.Canonical

import java.awt.Graphics
import java.awt.Image

@Canonical
class Coordinates {

    int xCoord
    int yCoord
    int offset

    void drawAtCoordinates(Graphics graphics, Image image, double scale = 1.0, DrawingOffset drawingOffset){
        offset = (yCoord == 0) ? 1 : yCoord % 2 -1

        double xDimension = (((xCoord*GlobalValues.HEX_WIDTH) + (GlobalValues.HEX_WIDTH / 2 * offset) - image.getWidth()/2));
        double yDimension = ((yCoord * GlobalValues.HEX_HEIGHT/2) + (GlobalValues.HEX_HEIGHT/2) - image.getHeight()) + GlobalValues.HEX_HEIGHT_OFFSET;

        graphics.drawImage(image, (xDimension * scale).intValue() + drawingOffset.width_offset, (yDimension * scale).intValue() + drawingOffset.height_offset,
                (image.getWidth()*scale).intValue(), (image.getHeight()*scale).intValue(), null);
    }
}
