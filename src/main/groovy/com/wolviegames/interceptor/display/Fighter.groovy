package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.system.Coordinates

import javax.imageio.ImageIO
import java.awt.Graphics
import java.awt.Image

class Fighter {

    Image image;
    Coordinates coordinates

    Fighter(Coordinates coordinates) {
        image = ImageIO.read(getClass().getClassLoader().getResource("ship.gif"));
        this.coordinates = coordinates
    }

    public void draw(Graphics graphics) {
        coordinates.drawAtCoordinates(graphics, image)
        //graphics.drawImage(image, 30, 90, null);
    }

}
