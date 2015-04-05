package com.wolviegames.interceptor.game.tracker

import com.wolviegames.interceptor.display.MovementHex
import com.wolviegames.interceptor.game.Faction
import com.wolviegames.interceptor.game.Team
import com.wolviegames.interceptor.game.gameobject.Asteroid
import com.wolviegames.interceptor.game.gameobject.Fighter


class MovementTracker {

    List<Team> teams
    List<Asteroid> asteroids
    Faction currentFaction
    int currentFighterTracker
    MovementHex movementHex


    MovementTracker(List<Team> teams, List<Asteroid> asteroids, Faction startingFaction){
        this.teams = teams.clone()
        this.asteroids = asteroids.clone()

        currentFaction = startingFaction
        currentFighterTracker = 0
        movementHex = new MovementHex()
        resetMovementHex()
    }

    public startNewMovementPhase(List<Team> teams, Faction startingFaction) {
        this.teams = teams.clone()
        for(Team team: teams){
            for(Fighter fighter: team.getFighters()){
                fighter.setHasMoved(false)
            }
        }
        currentFaction = startingFaction
        currentFighterTracker = 0
        resetMovementHex()
    }

    public Fighter currentFighter() {
        return teams.get(currentFaction.factionValue()).fighters.get(currentFighterTracker)
    }

    public Boolean allFightersHaveMoved() {
        // Check if the current team is out of fighters
        if (teams.get(currentFaction.factionValue()).getFighters().size() <= currentFighterTracker){
            Faction nextFaction = currentFaction.nextFaction()
            // Check if all the current teams are out of fighters
            if (teams.get(nextFaction.factionValue()).getFighters().size() <= currentFighterTracker){
                return true
            }
        }
        return false
    }

    public Fighter nextFighter() {
        // Update that the fighter has moved
        teams.get(currentFaction.factionValue()).fighters.get(currentFighterTracker).hasMoved = Boolean.TRUE

        // Change the faction
        currentFaction = currentFaction.nextFaction()

        // Check if the fighter has already moved
        if (teams.get(currentFaction.factionValue()).getFighters().get(currentFighterTracker).getHasMoved()){
            currentFighterTracker++
        }

        // Check if the current team is out of fighters
        if (teams.get(currentFaction.factionValue()).getFighters().size() <= currentFighterTracker){
            currentFaction = currentFaction.nextFaction()
            // Check if all the current teams are out of fighters
            if (teams.get(currentFaction.factionValue()).getFighters().size() <= currentFighterTracker){
                return null
            }
        }

        resetMovementHex()

        return teams.get(currentFaction.factionValue()).getFighters().get(currentFighterTracker)
    }

    public void moveAsteroids() {
        for(Asteroid asteroid: asteroids){
            asteroid.moveForward()
        }
    }

    public void resetMovementHex() {
        movementHex.coordinates = teams.get(currentFaction.factionValue()).fighters.get(currentFighterTracker).coordinates
    }

}
