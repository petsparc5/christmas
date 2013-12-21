package com.image.selftraining;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    
    @Autowired
    ImportedImages images;
    private final Logger logger = LoggerFactory.getLogger(Converter.class);
    
    public void convertAll() {
        images.loadAll("pictures");
        List<BufferedImage> imageList = images.getImageList();
        for (int i = 0; i < imageList.size(); i++) {
            BufferedImage image = rescaleImage(imageList.get(i));
            convert(image, i);
        }        
    }
    
    private BufferedImage rescaleImage(BufferedImage image) {
        
        int newWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width * 4 / 5;
        int newHeigh = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height * 3 / 5;
        
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeigh, image.getType());

        Graphics2D graphics = (Graphics2D) resizedImage.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(image, 0, 0, newWidth, newHeigh, 0, 0, image.getWidth(), image.getHeight(), null);
        graphics.dispose();
        
        return resizedImage;
    }

    private void convert(BufferedImage image, int n) {
        String filename = "convertedPictures/Sea" + String.valueOf(n) + ".jpg";
        try { 
            File outputfile = new File(filename);
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e) {
            logger.error("Could not convert: " + filename);
        }
    }

}