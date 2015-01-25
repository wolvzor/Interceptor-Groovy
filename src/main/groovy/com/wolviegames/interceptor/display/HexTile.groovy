package com.wolviegames.interceptor.display

import javax.imageio.ImageIO
import java.awt.Graphics
import java.awt.Image

class HexTile {

    Image image;

    HexTile() {
        image = ImageIO.read(getClass().getClassLoader().getResource("HexTile.png"));
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, 30, 30, null);
    }

    public void draw(Graphics graphics, int xCoord, int yCoord) {
        graphics.drawImage(image, xCoord, yCoord, null);
    }
}
