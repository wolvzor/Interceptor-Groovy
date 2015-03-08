package com.wolviegames.interceptor.game

import com.wolviegames.interceptor.display.DrawingOffset
import com.wolviegames.interceptor.system.Coordinates
import com.wolviegames.interceptor.system.Direction

import javax.imageio.ImageIO
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Image
import java.awt.geom.AffineTransform

class Fighter {

    Image image;
    Coordinates coordinates
    Direction direction

    Fighter(Coordinates coordinates, Direction direction) {
        image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"));
        this.coordinates = coordinates
        this.direction = direction
    }

    Fighter(Image image){
        this.image = image;
        coordinates = new Coordinates(0,0,0)
        this.direction = Direction.WEST
    }

    Fighter(Image image, Coordinates coordinates, Direction direction){
        this.image = image
        this.coordinates = coordinates
        this.direction = direction
    }

    public void draw(Graphics graphics, double scale = 1.0, DrawingOffset drawingOffset) {

        coordinates.drawAtCoordinates(graphics, image, scale, drawingOffset, direction)

        //direction.resetRotation(graphics, image, direction, coordinates)
    }

}
