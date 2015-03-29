package com.wolviegames.interceptor.game;

import com.wolviegames.interceptor.game.gameobject.Fighter

public class Team {

    String teamName;
    Faction faction;
    List<Fighter> fighters;

    Team(String teamName, Faction faction) {
        this.teamName = teamName;
        this.faction = faction;
        fighters = new ArrayList<Fighter>();
    }

    public void addFighter(Fighter fighter){
        fighters.add(fighter);
    }

    public List<Fighter> getFighters(){
        return fighters;
    }

}
