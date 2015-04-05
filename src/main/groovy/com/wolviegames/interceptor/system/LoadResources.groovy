package com.wolviegames.interceptor.system

import com.wolviegames.interceptor.game.gameobject.Asteroid
import com.wolviegames.interceptor.game.gameobject.Fighter
import com.wolviegames.interceptor.game.gameobject.Missile

import javax.imageio.ImageIO
import java.awt.*
import java.awt.image.BufferedImage
import java.util.List;

public class LoadResources {

    Map<String, Image> imageMap
    Map<String, Fighter> fighterMap
    Map<String, Asteroid> asteroidMap
    Map<String, Missile> missileMap

    String fighterPath = "fighter/small"
    String asteroidPath = "asteroid"
    String missilePath = "missile"

    public LoadResources() {

        initializeMaps()

        loadResource(fighterPath, fighterMap, "com.wolviegames.interceptor.game.gameobject.Fighter")
        loadResource(asteroidPath, asteroidMap, "com.wolviegames.interceptor.game.gameobject.Asteroid")
        loadResource(missilePath, missileMap, "com.wolviegames.interceptor.game.gameobject.Missile")

    }

    protected void initializeMaps() {
        fighterMap = new HashMap<String, Fighter>()
        asteroidMap = new HashMap<String, Asteroid>()
        missileMap = new HashMap<String, Missile>()
    }

    protected void loadResource(String resourcePath, Map<String, ?> resourceMap, String className){
        imageMap = new HashMap<String, BufferedImage>();
        def instance

        // Load the Images
        try {
            URL directory;
            BufferedImage image;
            Enumeration<URL> imageEnumeration = getClass().getClassLoader().getResources(resourcePath);
            while (imageEnumeration.hasMoreElements()) {
                directory = imageEnumeration.nextElement();
                File fileMetaInf=new File(directory.toURI());

                List<File> files= Arrays.asList(fileMetaInf.listFiles());
                for(File file: files) {
                    image = ImageIO.read(file);
                    imageMap.put(file.getName(), image);
                    instance = getClass().forName(className)?.newInstance()
                    instance.image = image
                    resourceMap.put(file.getName(), instance);
                }
            }
        } catch (IOException ie) {
            System.out.println("Could not open in resources: " + resourcePath)
            System.out.println(ie)
            System.exit(1)
        } catch (URISyntaxException use){
            System.out.println("Could not read file.")
            System.out.println(use)
            System.exit(1)
        }
    }

    // TODO Perhaps these are too specific - find a way to generalize this.

    public Fighter getFighter(String fighterName){
        return fighterMap.get(fighterName)
    }

    public Fighter getFighter(String fighterName, Coordinates initialCoordinates, Direction direction){
        Fighter fighter = getFighter(fighterName)
        fighter.coordinates = initialCoordinates
        fighter.direction = direction
        return fighter
    }

    public Asteroid getAsteroid(String asteroidName){
        return asteroidMap.get(asteroidName)
    }

    public Asteroid getAsteroid(String asteroidName, Coordinates initialCoordinates, Direction direction){
        Asteroid asteroid = getAsteroid(asteroidName)
        asteroid.coordinates = initialCoordinates
        asteroid.direction = direction
        return asteroid
    }

    public Missile getMissile(String missileName){
        return missileMap.get(missileName)
    }

    public Missile getMissile(String missileName, Coordinates initialCoordinates, Direction direction){
        Missile missile = getMissile(missileName)
        missile.coordinates = initialCoordinates
        missile.direction = direction
        return missile
    }

}
