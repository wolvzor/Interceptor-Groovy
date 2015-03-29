package com.wolviegames.interceptor.game

import com.wolviegames.interceptor.system.DiceRoller


class InitiativeTracker {

    private DiceRoller diceRoller

    InitiativeTracker() {
        diceRoller = new DiceRoller()
    }

    public Faction rollInitiative() {
        return (diceRoller.roll() > diceRoller.roll()) ?  Faction.RENEGADE :  Faction.TOG
    }

    public Faction switchInitiative(Faction currentInitiative) {
        return currentInitiative.equals(Faction.TOG) ? Faction.RENEGADE : Faction.TOG
    }
}
