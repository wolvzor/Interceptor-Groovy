package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.game.Asteroid
import com.wolviegames.interceptor.game.Faction
import com.wolviegames.interceptor.game.Fighter
import com.wolviegames.interceptor.game.Missile
import com.wolviegames.interceptor.game.Team
import com.wolviegames.interceptor.system.Coordinates
import com.wolviegames.interceptor.system.DiceRoller
import com.wolviegames.interceptor.system.Direction
import com.wolviegames.interceptor.system.LoadResources

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage

public class GamePanel extends JPanel implements Runnable {

    BufferedImage dbImage
    Graphics dbg
    List<Team> teams
    List<Asteroid> asteroids
    List<Missile> missiles

    DiceRoller diceRoller
    int width;
    int height;
    double scale = 0.5;
    LoadResources resources;
    DrawingOffset drawingOffset = new DrawingOffset(width_offset: 300, height_offset: 200)
    GameCanvas gameCanvas

    int previousMousePositionX
    int previousMousePositionY

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        setBackground(Color.white);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        readyForTermination();

        // Initiate canvas
        gameCanvas = new GameCanvas(width, height)

        // Load resources
        resources = new LoadResources();

        // Initialize dice roller
        diceRoller = DiceRoller.instance

        // Create teams
        teams = new ArrayList<Team>()

        // TODO Demo code - will replace with actual team creation code later.
        Team togTeam = new Team("First Team", Faction.TOG)
        togTeam.addFighter(resources.getFighter("Spiculum_small.gif", new Coordinates(xCoord:0, yCoord:0), Direction.WEST));
        togTeam.addFighter(resources.getFighter("Verutum_small.gif", new Coordinates(xCoord:2, yCoord:0), Direction.EAST));
        teams.add(togTeam)

        Team renegadeTeam = new Team("Second Team", Faction.RENEGADE)
        renegadeTeam.addFighter(resources.getFighter("Petal_small.gif", new Coordinates(xCoord:2, yCoord:2), Direction.NORTHWEST))
        renegadeTeam.addFighter(resources.getFighter("Guardian_small.gif", new Coordinates(xCoord:-1, yCoord:-1), Direction.SOUTHEAST))
        teams.add(renegadeTeam)

        // Asteroids
        asteroids = new ArrayList<Asteroid>()
        asteroids.add(resources.getAsteroid("01.gif", new Coordinates(xCoord:-1, yCoord:-2), Direction.SOUTHWEST))
        asteroids.add(resources.getAsteroid("02.gif", new Coordinates(xCoord: 3, yCoord:0), Direction.NORTHEAST))

        // Missiles
        missiles = new ArrayList<>()
        missiles.add(resources.getMissile("sss.gif", new Coordinates(xCoord:-1, yCoord: 0), Direction.WEST))
        missiles.add(resources.getMissile("tgm.gif", new Coordinates(xCoord:-2, yCoord: -1), Direction.EAST))

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                System.out.println("Mouse Pressed! " + event)
                previousMousePositionX = event.getX()
                previousMousePositionY = event.getY()
            }

        });

        // Apparently we need a different mouse listener purely for motion listeners...
        addMouseMotionListener(new MouseAdapter() {

            // TODO Fix screen tearing :/
            public void mouseDragged(MouseEvent event){
                System.out.println("Mouse dragged! " + event)
                drawingOffset.height_offset += previousMousePositionY - event.getY()
                drawingOffset.width_offset += previousMousePositionX - event.getX()
                previousMousePositionY = event.getY()
                previousMousePositionX = event.getX()
            }
        })

        // As well as for mouse wheel listeners. >.>
        addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent event) {
                System.out.println("Mouse wheel moved! " + event)
            }
        })

        addKeyListener(new KeyAdapter() {

            // TODO Do to whatever the active fighter is.
            @Override
            void keyPressed(KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode()
                if (keyCode == KeyEvent.VK_LEFT) {
                    renegadeTeam.getFighters().get(0).turnLeft()
                }
                if (keyCode == KeyEvent.VK_RIGHT) {
                    renegadeTeam.getFighters().get(0).turnRight()
                }
                if (keyCode == KeyEvent.VK_UP){
                    renegadeTeam.getFighters().get(0).moveForward()
                }
                super.keyPressed(keyEvent)
            }
        })

    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            gameUpdate();
            gameRender();
            paintScreen();
        }
    }

    private void gameUpdate() {

    }

    private void gameRender() {
        if (dbImage == null) {
            dbImage = (BufferedImage) createImage(width, height);
            if (dbImage == null) {
                System.out.println("dbImage is null");
                System.exit(0);
            } else dbg = dbImage.getGraphics();
        }

        gameCanvas.paint(dbg, scale, drawingOffset,teams, asteroids,missiles)

    }

    private void paintScreen() {
        Graphics graphics;
        try {
            graphics = this.getGraphics();
            if (graphics != null && dbImage != null) graphics.drawImage(dbImage, 0, 0, null);
            Toolkit.getDefaultToolkit().sync();
            graphics.dispose();
        } catch (Exception e) {
            // I'll fix this to a log later. system.out? Bitch please.
            System.out.println("Graphics context error: " + e);
        }

    }

    public void readyForTermination() {

    }



}
