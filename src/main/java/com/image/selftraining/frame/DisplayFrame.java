package com.image.selftraining.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.image.selftraining.button.Buttons;
import com.image.selftraining.button.ButtonsListener;
import com.image.selftraining.reader.ImportedImages;

@Component
public class DisplayFrame {
    
    @Autowired
    private ImportedImages images;
    @Autowired
    private Buttons buttons;
    @Autowired
    private ButtonsListener buttonListener;
    @Autowired
    private Converter converter;
    private JFrame frame = new JFrame();
    private Image img;
    
    private final Logger logger = LoggerFactory.getLogger(DisplayFrame.class);
    
    
    private int screenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    private int screenHeigh = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    
    @PostConstruct
    public void initialise() {
        converter.convertAll();
        images.loadAll("convertedPictures");
        frame.setSize(screenWidth, screenHeigh);
    }    

    private JPanel makePicturePanel() {
        if (!buttonListener.isAutoViewOff()) {
            images.setRandomImage();
        }
        BufferedImage image = images.getImage();
        int width = (int) (frame.getWidth() * 0.8);
        int height = (int) (frame.getHeight() * 0.7);
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel lblimage = new JLabel(new ImageIcon(newImage));
        JPanel picturePanel = new JPanel(new BorderLayout());
        picturePanel.add(lblimage);
        return picturePanel;
    }

    private JPanel makeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(true);
        buttons.addButtons(buttonPanel);
        return buttonPanel;
    }

    public void setFrameName() {
        frame.setTitle("SeaPictures (" + Integer.toString(images.getPosition()+1) + "/4)");
    }
    
    public void setUpFrame() {
        setBackGround();
        JPanel picturePanel = makePicturePanel();
        JPanel buttonPanel = makeButtonPanel();
        buttonPanel.setOpaque(false);
        picturePanel.setOpaque(false);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(picturePanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    private void setBackGround() {
        try {
            //blue-christmas-background.jpg
            img = ImageIO.read(getClass().getClassLoader().getResource("background/MerryChristmasLayout.jpg"));
        } catch (IOException e) {
            logger.warn("BackGround Picture is unavailiable!");
        }
        frame.setContentPane(new JPanel(new BorderLayout()) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        });
    }
}
