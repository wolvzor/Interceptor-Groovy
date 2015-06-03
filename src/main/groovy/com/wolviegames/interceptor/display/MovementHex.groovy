package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.system.Coordinates
import com.wolviegames.interceptor.system.Direction

import javax.imageio.ImageIO
import java.awt.*
import java.awt.image.BufferedImage

class MovementHex {

    BufferedImage image
    public Coordinates coordinates
    Direction direction

    MovementHex() {
        image = ImageIO.read(getClass().getClassLoader().getResource("MovementHex.gif"));
        direction = Direction.EAST
        //coordinates = new Coordinates(xCoord:1, yCoord: 1)
    }

    public void draw(Graphics graphics, double scale = 1.0, DrawingOffset drawingOffset) {

        coordinates.drawAtCoordinates(graphics, image, scale, drawingOffset, direction)
    }
}
