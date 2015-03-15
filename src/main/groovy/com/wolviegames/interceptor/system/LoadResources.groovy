package com.wolviegames.interceptor.system;

import com.wolviegames.interceptor.game.Fighter;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class LoadResources {

    Map<String, Image> imageMap;
    Map<String, Fighter> fighterMap;
    String fighterPath = "fighter/small";

    public LoadResources() {

        initializeMaps()

        loadResource(fighterPath, fighterMap)

    }

    protected void initializeMaps() {
        fighterMap = new HashMap<String, Fighter>()

    }

    protected void loadResource(String resourcePath, Map<String, ?> resourceMap){
        imageMap = new HashMap<String, Image>();

        // Load the Images
        try {
            URL directory;
            Image image;
            Enumeration<URL> imageEnumeration = getClass().getClassLoader().getResources(resourcePath);
            while (imageEnumeration.hasMoreElements()) {
                directory = imageEnumeration.nextElement();
                File fileMetaInf=new File(directory.toURI());

                List<File> files= Arrays.asList(fileMetaInf.listFiles());
                for(File file: files) {
                    image = ImageIO.read(file);
                    imageMap.put(file.getName(), image);
                    resourceMap.put(file.getName(), new Fighter(image));

                }
            }
        } catch (IOException ie) {
            System.out.println("Could not open in resources: " + resourcePath);
            System.out.println(ie);
            System.exit(1);
        } catch (URISyntaxException use){
            System.out.println("Could not read file.");
            System.out.println(use);
            System.exit(1);
        }
    }

    public Fighter getFighter(String fighterName){
        return fighterMap.get(fighterName);
    }

    public Fighter getFighter(String fighterName, Coordinates initialCoordinates, Direction direction){
        Fighter fighter = getFighter(fighterName);
        fighter.setCoordinates(initialCoordinates);
        fighter.setDirection(direction)
        return fighter
    }
}
