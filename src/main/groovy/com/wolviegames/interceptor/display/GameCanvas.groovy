package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.game.Team
import com.wolviegames.interceptor.game.gameobject.Asteroid
import com.wolviegames.interceptor.game.gameobject.Fighter
import com.wolviegames.interceptor.game.gameobject.Missile
import com.wolviegames.interceptor.system.Coordinates

import java.awt.*
import java.util.List

class GameCanvas extends Canvas{

    public GameCanvas(int width, int height){
        setBackground(Color.BLACK)
        setSize(width, height)

    }

    public void paint(Graphics graphics, double scale, DrawingOffset drawingOffset, List<Team> teams,
                      List<Asteroid> asteroids, List<Missile> missiles, MovementHex movementHex){

        // Clear the background
        //HexGrid hexGrid = new HexGrid(scale)
        graphics.setColor(Color.BLACK)
        graphics.fillRect(0,0,width, height)


        try {
            //hexGrid.draw(graphics, width, height, drawingOffset)
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

            movementHex.draw(graphics, scale, drawingOffset)

            MovementHex samplehex = new MovementHex()
            samplehex.coordinates = new Coordinates(2,2)
            samplehex.draw(graphics, scale, drawingOffset)

            samplehex.coordinates = new Coordinates(1,1)
            samplehex.draw(graphics, scale, drawingOffset)

            samplehex.coordinates = new Coordinates(1,2)
            samplehex.draw(graphics, scale, drawingOffset)

        } catch(IOException e){
            System.out.println("Load image error.");
            System.exit(1);
        }
    }
}
