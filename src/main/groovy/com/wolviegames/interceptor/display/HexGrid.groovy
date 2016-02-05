package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.system.Coordinates
import com.wolviegames.interceptor.system.InterceptorConstant;

import java.awt.*;

public class HexGrid {

    GridHex gridHex;
    double scale =1.0
    int scaledHeight
    int scaledWidth

    HexGrid(double scale){
        gridHex = new GridHex()
        this.scale = scale
        changeScale(scale)
    }

    public void changeScale(double scale){
        this.scale = scale
        scaledHeight = (InterceptorConstant.HEX_HEIGHT * scale).intValue()
        scaledWidth = (InterceptorConstant.HEX_WIDTH * scale).intValue()
    }

    public void draw(Graphics graphics, int screenWidth, int screenHeight, DrawingOffset drawingOffset){

        // Calculate coordinates on screen
        // kludging it in the meantime
        int minXCoord = -10;
        int minYCoord = -10;
        int maxXCoord = 10;
        int maxYCoord = 10;

        int xCoord = minXCoord
        while(xCoord < maxXCoord) {
            int yCoord = minYCoord
            while(yCoord < maxYCoord) {
                gridHex.coordinates = new Coordinates(xCoord,yCoord)
                gridHex.draw(graphics, scale, drawingOffset)
                yCoord++
            }
            xCoord++
        }
    }

}
