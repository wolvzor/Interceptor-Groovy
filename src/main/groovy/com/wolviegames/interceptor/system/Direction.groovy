package com.wolviegames.interceptor.system

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Image
import java.awt.geom.AffineTransform

public enum Direction {
    WEST(0),
    NORTHWEST(1),
    NORTHEAST(2),
    EAST(3),
    SOUTHEAST(4),
    SOUTHWEST(5);

    private final Integer directionValue;

    public Direction(Integer directionValue){
        this.directionValue = directionValue
    }

    public Integer directionValue() {
        return directionValue;
    }

    /* Ultimately, I don't want this to live here. I want this to live in a generic GameObject class. */
    public void rotateImage(Graphics graphics, Image image, Direction direction, int xDimension, int yDimension){
        Graphics2D graphics2d=(Graphics2D)graphics

        graphics2d.rotate(direction.directionValue * Math.PI / 3, xDimension, yDimension)

    }

}