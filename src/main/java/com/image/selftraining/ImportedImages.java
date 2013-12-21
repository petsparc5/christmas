package com.image.selftraining;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ImportedImages {
    
    private final Logger logger = LoggerFactory.getLogger(ImportedImages.class);
    private List<BufferedImage> imageList = new ArrayList<BufferedImage>();
    private int position;
    
    //@PostConstruct
    public void loadAll(String folder) {
        imageList = new ArrayList<BufferedImage>();
        String filename;
        for (int i = 0; i < 2 + 2; i++) {
            filename = folder + "/Sea" + String.valueOf(i) + ".jpg";
            loadToTheList(filename);
        }
    }

    private void loadToTheList(String filename) {
        try {
            BufferedImage img = ImageIO.read(new File(filename));
            imageList.add(img);
        } catch (IOException e) {
            logger.error("File {} could not be located!", filename);
        }
    }

    public void setRandomImage() {
        Random generator = new Random();
        position = generator.nextInt(4);
    }
    
    public void setNextImage() {
        position++;
        if (position == imageList.size()) {
            position = 0;
        }
    }
    
    public void setPrevImage() {
        if (position == 0) {
            position = imageList.size();
        }
        position--;
    }
    
    public BufferedImage getImage() {
        BufferedImage image = imageList.get(position);
        return image;
    }

    public List<BufferedImage> getImageList() {
        return imageList;
    }

}