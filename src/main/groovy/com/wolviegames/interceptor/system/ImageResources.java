package com.wolviegames.interceptor.system;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class ImageResources {

    Map<String, Image> imageMap;
    String basicPath = "fighter/small";

    public ImageResources() {
        imageMap = new HashMap<String, Image>();

        // Load the Images
        try {
            URL directory;
            Enumeration<URL> imageEnumeration = getClass().getClassLoader().getResources(basicPath);
            while (imageEnumeration.hasMoreElements()) {
                directory = imageEnumeration.nextElement();
                System.out.println(directory.getPath());
                File fileMetaInf=new File(directory.toURI());

                List<File> files= Arrays.asList(fileMetaInf.listFiles());
                for(File file: files) {
                    System.out.println(file.getPath());
                    imageMap.put(file.getPath(), ImageIO.read(file));
                }
            }
        } catch (IOException ie) {
            System.out.println("Could not open in resources: " + basicPath);
            System.out.println(ie);
            System.exit(1);
        } catch (URISyntaxException use){
            System.out.println("Could not read file.");
            System.out.println(use);
            System.exit(1);
        }


    }


}
