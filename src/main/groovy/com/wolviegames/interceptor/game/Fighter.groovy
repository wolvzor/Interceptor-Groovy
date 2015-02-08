package com.wolviegames.interceptor.game

import com.wolviegames.interceptor.system.Coordinates

import javax.imageio.ImageIO
import java.awt.Graphics
import java.awt.Image

class Fighter {

    Image image;
    Coordinates coordinates

    Fighter(Coordinates coordinates) {
        image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"));
        this.coordinates = coordinates
    }

    Fighter(Image image){
        this.image = image;
        coordinates = new Coordinates(0,0,0)
    }

    Fighter(Image image, Coordinates coordinates){
        this.image = image
        this.coordinates = coordinates
    }

    public void draw(Graphics graphics) {
        coordinates.drawAtCoordinates(graphics, image)
    }

}
