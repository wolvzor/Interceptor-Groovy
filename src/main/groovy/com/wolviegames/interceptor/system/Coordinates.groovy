package com.wolviegames.interceptor.system

import com.wolviegames.interceptor.display.DrawingOffset
import groovy.transform.Canonical

import java.awt.*
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage

@Canonical
class Coordinates {

    int xCoord
    int yCoord
    int offset

    void drawAtCoordinates(Graphics graphics, BufferedImage image, double scale = 1.0, DrawingOffset drawingOffset, Direction direction){

        double xDimension = (((xCoord*GlobalValues.HEX_WIDTH) + GlobalValues.HEX_WIDTH / 2 - image.getWidth()/2)) + GlobalValues.HEX_WIDTH_OFFSET*scale;
        double yDimension = ((yCoord * GlobalValues.HEX_HEIGHT/2) + (GlobalValues.HEX_HEIGHT/2) - image.getHeight()) + (GlobalValues.HEX_HEIGHT_OFFSET*scale);

        // Traveling up/down the y dimension will shift the x dimension accordingly.
        xDimension -= (GlobalValues.HEX_WIDTH / 2) * yCoord

        // TODO: Oh dear gods, please clean this up.
        AffineTransform saveAT = ((Graphics2D)graphics).getTransform();
        direction.rotateImage(graphics, image, direction, (xDimension * scale).intValue() + drawingOffset.width_offset + (image.getWidth()*scale/2).intValue(),
                (yDimension * scale).intValue() + drawingOffset.height_offset + (image.getHeight()* scale /2).intValue())
        graphics.drawImage(image, (xDimension * scale).intValue() + drawingOffset.width_offset, (yDimension * scale).intValue() + drawingOffset.height_offset,
                (image.getWidth()*scale).intValue(), (image.getHeight()*scale).intValue(), null);
        ((Graphics2D)graphics).setTransform(saveAT)
    }

    Coordinates moveForward(Direction direction){
        switch(direction){
            case Direction.WEST:
                xCoord--
                break
            case Direction.NORTHWEST:
                xCoord--
                yCoord--
                break
            case Direction.NORTHEAST:
                yCoord--
                break
            case Direction.EAST:
                xCoord++
                break
            case Direction.SOUTHEAST:
                xCoord++
                yCoord++
                break
            case Direction.SOUTHWEST:
                yCoord++
                break
        }

        return this
    }
}
