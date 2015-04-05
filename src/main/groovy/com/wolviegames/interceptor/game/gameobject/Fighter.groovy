package com.wolviegames.interceptor.game.gameobject

import java.awt.image.BufferedImage

class Fighter extends GameObject{

    // TODO Add fighter specific logic here.
    Boolean hasMoved

    Fighter() {
        super()
    }

    Fighter(BufferedImage image){
        super(image)
    }

}
