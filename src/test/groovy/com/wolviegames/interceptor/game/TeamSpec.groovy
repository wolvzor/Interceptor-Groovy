package com.wolviegames.interceptor.game

import com.wolviegames.interceptor.system.Coordinates
import spock.lang.Specification

class TeamSpec extends Specification{

    def "Team will create a teamname and a faction"() {
        when:
        Team renegadeTeam = new Team("renegadeTeamName", Faction.RENEGADE)
        Team togTeam = new Team("togTeamName", Faction.TOG)

        then:
        renegadeTeam.teamName == "renegadeTeamName"
        renegadeTeam.faction == Faction.RENEGADE
        togTeam.teamName == "togTeamName"
        togTeam.faction == Faction.TOG
    }

    def "Team can add fighters to its team"() {
        setup:
        Team renegadeTeam = new Team("renegadeTeamName", Faction.RENEGADE)
        Fighter fighterOne = new Fighter()
        fighterOne.coordinates = new Coordinates(xCoord:0,yCoord:0)
        Fighter fighterTwo = new Fighter()
        fighterTwo.coordinates = new Coordinates(xCoord:0,yCoord:1)

        when:
        renegadeTeam.addFighter(fighterOne)
        renegadeTeam.addFighter(fighterTwo)

        then:
        renegadeTeam.getFighters().size() == 2
        renegadeTeam.getFighters().contains(fighterOne)
        renegadeTeam.getFighters().contains(fighterTwo)
    }
}
