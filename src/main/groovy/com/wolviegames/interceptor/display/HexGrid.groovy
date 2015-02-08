package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.system.GlobalValues;

import java.awt.*;

public class HexGrid {

    HexTile hexTile;
    double scale =1.0
    int scaledHeight
    int scaledWidth

    HexGrid() {
        hexTile = new HexTile()
        changeScale(scale)
    }

    HexGrid(double scale){
        hexTile = new HexTile()
        this.scale = scale
        changeScale(scale)
    }

    public void changeScale(double scale){
        this.scale = scale
        scaledHeight = (GlobalValues.HEX_HEIGHT * scale).intValue()
        scaledWidth = (GlobalValues.HEX_WIDTH * scale).intValue()
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
