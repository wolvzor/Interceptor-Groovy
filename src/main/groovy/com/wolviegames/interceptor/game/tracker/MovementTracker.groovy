package com.wolviegames.interceptor.game.tracker

import com.wolviegames.interceptor.game.Faction
import com.wolviegames.interceptor.game.Team
import com.wolviegames.interceptor.game.gameobject.Fighter


class MovementTracker {

    List<Team> teams
    Faction currentFaction
    int currentFighterTracker


    MovementTracker(List<Team> teams, Faction startingFaction){
        this.teams = teams.clone()
        currentFaction = startingFaction
        currentFighterTracker = 0
    }

    public startNewMovementPhase(List<Team> teams, Faction startingFaction) {
        this.teams = teams.clone()
        currentFaction = startingFaction
        currentFighterTracker = 0
    }

    public Fighter currentFighter() {
        return teams.get(currentFaction).fighters.get(currentFighterTracker)
    }

    public Fighter nextFighter() {
        currentFaction = currentFaction.nextFaction()

        // Check if the fighter has already moved
        if (teams.get(currentFaction).getFighters().get(currentFighterTracker).hasMoved()){
            currentFighterTracker++
        }

        // Check if the current team is out of fighters
        if (teams.get(currentFaction).getFighters().size() < currentFighterTracker){
            currentFaction = currentFaction.nextFaction()
            // Check if all the current teams are out of fighters
            if (teams.get(currentFaction).getFighters().size() < currentFighterTracker){
                return null
            }
        }

        return teams.get(currentFaction).getFighters().get(currentFighterTracker)
    }

}
