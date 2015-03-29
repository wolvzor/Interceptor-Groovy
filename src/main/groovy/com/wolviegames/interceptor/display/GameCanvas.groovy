package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.game.Team
import com.wolviegames.interceptor.game.gameobject.Asteroid
import com.wolviegames.interceptor.game.gameobject.Fighter
import com.wolviegames.interceptor.game.gameobject.Missile

import java.awt.*
import java.util.List

class GameCanvas extends Canvas{

    public GameCanvas(int width, int height){
        setBackground(Color.BLACK)
        setSize(width, height)

    }

    public void paint(Graphics graphics, double scale, DrawingOffset drawingOffset, List<Team> teams, List<Asteroid> asteroids, List<Missile> missiles){

        // Clear the background
        HexGrid hexGrid = new HexGrid(scale)

        try {
            hexGrid.draw(graphics, width, height, drawingOffset)
            // TODO make this go through a loop of applicable game objects
            for(Team team: teams){
                for(Fighter fighter: team.getFighters()){
                    fighter.draw(graphics, scale, drawingOffset)
                }
            }

            for(Asteroid asteroid: asteroids){
                asteroid.draw(graphics, scale, drawingOffset)
            }

            for(Missile missile: missiles){
                missile.draw(graphics, scale, drawingOffset)
            }


        } catch(IOException e){
            System.out.println("Load image error.");
            System.exit(1);
        }
    }
}
