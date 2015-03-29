package com.wolviegames.interceptor.game;

public enum Faction {
    RENEGADE(1),
    TOG(0);

    private final Integer factionValue;

    private Faction(Integer factionValue){
        this.factionValue = factionValue
    }

    public Integer factionValue() {
        return factionValue;
    }

    public Faction nextFaction(){
        return this.equals(RENEGADE) ? TOG : RENEGADE
    }
}
