package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.system.Coordinates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GamePanel extends JPanel implements Runnable {

    BufferedImage dbImage;
    Graphics dbg;
    // TODO Will make these fighters into a collection laters!
    Fighter fighter;
    Fighter fighter2;
    Fighter fighter3;
    int width;
    int height;

    public GamePanel(long period, int width, int height) {
        this.width = width;
        this.height = height;
        setBackground(Color.white);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        readyForTermination();

        // Create game components
        fighter = new Fighter(new Coordinates(xCoord:0, yCoord:0));
        fighter2 = new Fighter(new Coordinates(xCoord:2, yCoord:0));
        fighter3 = new Fighter(new Coordinates(xCoord:2, yCoord:2));

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

        HexGrid hexGrid = new HexGrid()

        try {
            hexGrid.draw(dbg, width, height)
            // TODO make this go through a loop of applicable game objects
            fighter.draw(dbg)
            fighter2.draw(dbg)
            fighter3.draw(dbg)

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