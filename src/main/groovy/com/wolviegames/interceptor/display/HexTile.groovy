package com.wolviegames.interceptor.display

import javax.imageio.ImageIO
import java.awt.Graphics
import java.awt.Image
import java.awt.image.BufferedImage

class HexTile {

    BufferedImage image;

    HexTile() {
        image = ImageIO.read(getClass().getClassLoader().getResource("HexTile.png"));
    }

    public void draw(Graphics graphics, int xCoord, int yCoord, double scale=1.0, DrawingOffset drawingOffset) {
        coordinates.drawAtCoordinates(graphics, image, scale, drawingOffset, direction)
        graphics.drawImage(image, xCoord + drawingOffset.width_offset, yCoord + drawingOffset.height_offset, (image.getWidth()*scale).intValue(), (image.getHeight()*scale).intValue(), null);
    }
}
