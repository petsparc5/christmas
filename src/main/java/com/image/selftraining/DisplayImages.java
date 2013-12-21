package com.image.selftraining;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn(value="timerListener")
public class DisplayImages {

    @Autowired
    private ImportedImages images;
    @Autowired
    private Buttons buttons;
    @Autowired
    private TimerListener timerListener;
    @Autowired
    private ButtonsListener buttonListener;
    @Autowired
    private Converter converter;
    private JFrame frame = new JFrame("SeaPictures (1/7)");
    private Timer t;
    private Image img;
    
    @PostConstruct
    public void initialise() throws IOException {
        converter.convertAll();
        images.loadAll("convertedPictures");
        setUp();
        setBack();
    }

    private JPanel makePicturePanel() {
        if (!buttonListener.isAutoViewOff()) {
            images.setRandomImage();
        }
        BufferedImage image = images.getImage();
        int width = (int) (frame.getWidth() * 0.8);
        int height = (int) (frame.getHeight() * 0.6);
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
    
    public void setBack() throws IOException {
        img = ImageIO.read(new File("blue-christmas-background.jpg"));
        frame.setContentPane(new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        });
    }

    public void setUpFrame() {
        try {
            setBack();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    
    public void sleep() {
        if (!buttonListener.isAutoViewOff()) {
            t.setDelay(5000);            
        } else {
            t.setDelay(50);
        }
    }
    
    public void setUp() {
        frame.setSize(1920, 1000);
    }
    
    public void setSleep() {
        if (!buttonListener.isAutoViewOff()) {
            t.setDelay(5000);            
        } else {
            t.setDelay(100);
        }
    }
    
    public void stopTimer() {
        t.stop();
    }
    
    public void startTimer() {
        t.start();
    }

    public void display() {
        t = new Timer(5000, timerListener);
        t.setInitialDelay(100);
        t.start();
    }
}
