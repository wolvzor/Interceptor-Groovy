package com.wolviegames.interceptor.display

import javax.imageio.ImageIO
import java.awt.Graphics
import java.awt.Image

class Fighter {

    Image image;

    Fighter() {
        image = ImageIO.read(getClass().getClassLoader().getResource("ship.gif"));
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, 30, 30, null);
    }

}
