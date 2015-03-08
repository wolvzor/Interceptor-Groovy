package com.wolviegames.interceptor.game

import com.wolviegames.interceptor.display.DrawingOffset
import com.wolviegames.interceptor.system.Coordinates
import com.wolviegames.interceptor.system.Direction
import spock.lang.Specification

import javax.imageio.ImageIO
import java.awt.Graphics
import java.awt.Image

class FighterSpec extends Specification {

    def "Can create fighter with just coordinates which will create w/ a default image"() {
        when:
        Coordinates coordinates = new Coordinates(xCoord: 0, yCoord: 0)
        Fighter fighter = new Fighter(coordinates, Direction.EAST)

        then:
        fighter.coordinates == coordinates
        fighter.direction == Direction.EAST
        fighter.image != null
    }

    def "Can create fighter with just an image which will create a default coordinate position"() {
        when:
        Image image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"))
        Fighter fighter = new Fighter(image)

        then:
        fighter.image == image
        fighter.coordinates.xCoord == 0
        fighter.coordinates.yCoord == 0
        fighter.coordinates.offset == 0
    }

    def "Can create fighter with both image and coordinates"() {
        when:
        Image image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"))
        Coordinates coordinates = new Coordinates(xCoord: 1, yCoord: 2, offset: 3)
        Fighter fighter = new Fighter(image, coordinates)

        then:
        fighter.image == image
        fighter.coordinates == coordinates
    }

    def "Fighter can draw at coordinates"() {
        setup:
        Image image = ImageIO.read(getClass().getClassLoader().getResource("fighter/small/Cheetah_small.gif"))
        Coordinates mockCoordinates = Mock()
        Fighter fighter = new Fighter(image, mockCoordinates, Direction.WEST)
        Graphics mockGraphics = Mock()
        DrawingOffset offset = new DrawingOffset()

        when:
        fighter.draw(mockGraphics, 1.0, offset)

        then:
        1 * mockCoordinates.drawAtCoordinates(mockGraphics,image, 1.0, offset, Direction.WEST)
    }

}