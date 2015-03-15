package com.wolviegames.interceptor.system


class DiceRoller {
    // Random number gen here
    private Random random
    private Integer max = 9
    private Integer min = 0

    private static final INSTANCE = new DiceRoller()

    static getInstance(){
        return INSTANCE
    }

    private DiceRoller() {
        random = new Random()
    }

    def roll() {return random.nextInt(max - min + 1) + min}
}
