package com.wolviegames.interceptor.game

import com.wolviegames.interceptor.game.gameobject.Fighter
import spock.lang.Specification

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class FighterSpec extends Specification {


    def "Can create fighter with just an image which will create a default coordinate position"() {
        when:
        BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"))
        Fighter fighter = new Fighter(image)

        then:
        fighter.image == image
        fighter.coordinates.xCoord == 0
        fighter.coordinates.yCoord == 0
        fighter.coordinates.offset == 0
    }

}