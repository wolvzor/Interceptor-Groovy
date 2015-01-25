package com.wolviegames.interceptor.display;

import java.awt.*;

public class HexGrid {

    HexTile hexTile;

    HexGrid() {
        hexTile = new HexTile();
    }

    public void draw(Graphics graphics){
        // Going to draw some basic hexTiles for now
        // TODO Make this a proper grid later.
        hexTile.draw(graphics, 0,0);
        hexTile.draw(graphics, 256, 0);
        hexTile.draw(graphics, 512, 0);
        hexTile.draw(graphics, 768, 0);
        hexTile.draw(graphics, 0,384);
        hexTile.draw(graphics, 256, 384);
        hexTile.draw(graphics, 512, 384);
        hexTile.draw(graphics, 768, 384);
    }

}
