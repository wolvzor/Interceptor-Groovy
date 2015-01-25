package com.wolviegames.interceptor.display;

import java.awt.*;

public class HexGrid {

    HexTile hexTile;
    int HEX_HEIGHT = 384
    int HEX_WIDTH = 256
    double scale = 1.0
    int scaledHeight = HEX_HEIGHT
    int scaledWidth = HEX_WIDTH

    HexGrid() {
        hexTile = new HexTile()
    }

    HexGrid(double scale){
        hexTile = new HexTile()
        this.scale = scale
        changeScale(scale)
    }

    public void changeScale(double scale){
        this.scale = scale
        scaledHeight = (HEX_HEIGHT * scale).intValue()
        scaledWidth = (HEX_WIDTH * scale).intValue()
    }

    public void draw(Graphics graphics, int screenWidth, int screenHeight){
        int height = 0
        int width
        while(height < screenHeight) {
            width = 0
            while(width < screenWidth) {
                hexTile.draw(graphics, width, height, scale)
                width += scaledWidth
            }
            height += scaledHeight
        }
    }

}
