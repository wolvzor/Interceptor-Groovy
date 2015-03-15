package com.wolviegames.interceptor.system

import spock.lang.Specification
import spock.lang.Unroll


class DiceRollerSpec extends Specification {

    def "test dice roller ranges"() {
        setup:
        DiceRoller dice = DiceRoller.instance

        when:
        Integer result = dice.roll()

        then:
        result >= 0
        result <= 9

        where:
        i << (1..1000)
    }

}