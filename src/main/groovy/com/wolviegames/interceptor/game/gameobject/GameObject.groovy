package com.wolviegames.interceptor.game.gameobject

import com.wolviegames.interceptor.display.DrawingOffset
import com.wolviegames.interceptor.system.Coordinates
import com.wolviegames.interceptor.system.Direction

import javax.imageio.ImageIO
import java.awt.*
import java.awt.image.BufferedImage

class GameObject {

    public BufferedImage image
    public Coordinates coordinates
    public Direction direction

    GameObject(){
        image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"));
        coordinates = new Coordinates(0,0,0)
        this.direction = Direction.WEST
    }

    GameObject(Coordinates coordinates){
        image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"));
        this.coordinates = coordinates
        this.direction = Direction.WEST
    }

    GameObject(Coordinates coordinates, Direction direction) {
        image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"));
        this.coordinates = coordinates
        this.direction = direction
    }

    GameObject(BufferedImage image){
        this.image = image;
        coordinates = new Coordinates(0,0,0)
        this.direction = Direction.WEST
    }

    GameObject(BufferedImage image, Coordinates coordinates){
        this.image = image
        this.coordinates = coordinates
        this.direction = Direction.WEST
    }

    GameObject(BufferedImage image, Coordinates coordinates, Direction direction){
        this.image = image
        this.coordinates = coordinates
        this.direction = direction
    }

    public void draw(Graphics graphics, double scale = 1.0, DrawingOffset drawingOffset) {

        coordinates.drawAtCoordinates(graphics, image, scale, drawingOffset, direction)
    }

    public void turnLeft() {
        this.direction = direction.turnLeft()
    }

    public void turnRight() {
        this.direction = direction.turnRight()
    }

    public void moveForward() {
        this.coordinates = coordinates.moveForward(direction)
    }
}
