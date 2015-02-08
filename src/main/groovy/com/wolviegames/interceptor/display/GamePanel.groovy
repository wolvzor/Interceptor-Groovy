package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.game.Faction
import com.wolviegames.interceptor.game.Fighter
import com.wolviegames.interceptor.game.Team
import com.wolviegames.interceptor.system.Coordinates
import com.wolviegames.interceptor.system.LoadResources

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage

public class GamePanel extends JPanel implements Runnable {

    BufferedImage dbImage
    Graphics dbg
    List<Team> teams
    int width;
    int height;
    double scale = 0.5;
    LoadResources resources;
    DrawingOffset drawingOffset = new DrawingOffset(width_offset: 300, height_offset: 200)

    public GamePanel(long period, int width, int height) {
        this.width = width;
        this.height = height;
        setBackground(Color.white);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        readyForTermination();

        // Load resources
        resources = new LoadResources();

        // Create teams
        teams = new ArrayList<Team>()

        Team togTeam = new Team("First Team", Faction.TOG)
        togTeam.addFighter(resources.getFighter("Spiculum_small.gif", new Coordinates(xCoord:0, yCoord:0)));
        togTeam.addFighter(resources.getFighter("Verutum_small.gif", new Coordinates(xCoord:2, yCoord:0)));
        teams.add(togTeam)

        Team renegadeTeam = new Team("Second Team", Faction.RENEGADE)
        renegadeTeam.addFighter(resources.getFighter("Peacekeeper_small.gif", new Coordinates(xCoord:2, yCoord:2)))
        renegadeTeam.addFighter(resources.getFighter("Guardian_small.gif", new Coordinates(xCoord:-1, yCoord:-1)))
        teams.add(renegadeTeam)

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                // TODO mouse presses?
            }

        });

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


        // Clear the background
        dbg.setColor(Color.white);
        dbg.fillRect(0, 0, width, height);

        dbg.setColor(Color.blue);
        dbg.setFont(getFont());

        dbg.drawString("Interceptor!", 20, 25);
        dbg.setColor(Color.black);

        HexGrid hexGrid = new HexGrid(scale)

        try {
            hexGrid.draw(dbg, width, height, drawingOffset)
            // TODO make this go through a loop of applicable game objects
            for(Team team: teams){
                for(Fighter fighter: team.getFighters()){
                    fighter.draw(dbg, scale, drawingOffset)
                }
            }

        } catch(IOException e){
            System.out.println("Load image error.");
            System.exit(1);
        }

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
