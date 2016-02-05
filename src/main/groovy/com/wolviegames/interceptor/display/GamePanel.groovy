package com.wolviegames.interceptor.display

import com.wolviegames.interceptor.game.Faction
import com.wolviegames.interceptor.game.Team
import com.wolviegames.interceptor.game.gameobject.Asteroid
import com.wolviegames.interceptor.game.gameobject.Missile
import com.wolviegames.interceptor.game.tracker.InitiativeTracker
import com.wolviegames.interceptor.game.tracker.MovementTracker
import com.wolviegames.interceptor.system.Coordinates
import com.wolviegames.interceptor.system.DiceRoller
import com.wolviegames.interceptor.system.Direction
import com.wolviegames.interceptor.system.LoadResources
import com.wolviegames.interceptor.system.InterceptorConstant;

import javax.swing.*
import java.awt.*
import java.awt.event.*
import java.awt.image.BufferedImage
import java.util.List

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

    // Game phase trackers
    InitiativeTracker initiativeTracker
    MovementTracker movementTracker

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.setDoubleBuffered(true)
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

        // Initialize initiative tracker
        initiativeTracker = new InitiativeTracker()

        // Create teams
        teams = createTeams()

        // Determine Initiative
        Faction factionInitiative = initiativeTracker.rollInitiative()

        // Initialize movement tracker
        movementTracker = new MovementTracker(teams, asteroids, factionInitiative)


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                //System.out.println("Mouse Pressed! " + event)
                previousMousePositionX = event.getX()
                previousMousePositionY = event.getY()
            }

        });

        // Apparently we need a different mouse listener purely for motion listeners...
        addMouseMotionListener(new MouseAdapter() {

            // TODO Fix screen tearing :/
            public void mouseDragged(MouseEvent event){
                //System.out.println("Mouse dragged! " + event)
                drawingOffset.height_offset -= (previousMousePositionY - event.getY())
                drawingOffset.width_offset -= (previousMousePositionX - event.getX())
                previousMousePositionY = event.getY()
                previousMousePositionX = event.getX()
            }
        })

        // As well as for mouse wheel listeners. >.>
        addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent event) {
                scale -= (event.getWheelRotation()/InterceptorConstant.MOUSE_ZOOM_INCREMENT)
                if (scale < InterceptorConstant.MOUSE_ZOOM_MIN) scale = InterceptorConstant.MOUSE_ZOOM_MIN
                System.out.println("Mouse wheel moved! " + event)
                System.out.println(scale)
            }
        })

        addKeyListener(new KeyAdapter() {

            @Override
            void keyPressed(KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode()
                if (keyCode == KeyEvent.VK_LEFT) {
                    movementTracker.currentFighter().turnLeft()
                }
                if (keyCode == KeyEvent.VK_RIGHT) {
                    movementTracker.currentFighter().turnRight()
                }
                if (keyCode == KeyEvent.VK_UP){
                    movementTracker.currentFighter().moveForward()
                }
                if (keyCode == KeyEvent.VK_SPACE){
                    movementTracker.nextFighter()
                }
                // Continue to do movement loop for now
                // TODO Replace this with proper initiative/movement phase later
                if (movementTracker.allFightersHaveMoved()){
                    movementTracker.moveAsteroids()
                    factionInitiative = initiativeTracker.switchInitiative()
                    movementTracker.startNewMovementPhase(teams, factionInitiative)
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


        DrawingOffset renderDrawingOffset = new DrawingOffset(drawingOffset);
        gameCanvas.paint(dbg, scale, renderDrawingOffset,teams, asteroids,missiles, movementTracker.movementHex)

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

    protected ArrayList<Team> createTeams() {
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

        return teams
    }



}
