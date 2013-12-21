package com.image.selftraining;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;




@Component
@DependsOn(value="buttonsListener")
public class Buttons {

    private final Logger logger = LoggerFactory.getLogger(Buttons.class);
    private JButton toggleButton;
    private JButton nextButton;
    private JButton previousButton;
    @Autowired
    private ButtonsListener listener;
    @Autowired
    private TimerListener timerListener;

    @PostConstruct
    public void setUp() {
        setUpButtonOne();
        setUpButtonTwo();
        setUpButtonThree();
        logger.warn("Csabi is wrong!");
    }

    public void addButtons(JPanel panel) {
        panel.add(previousButton);
        panel.add(toggleButton);
        panel.add(nextButton);
    }

    private void setUpButtonOne() {
        //ImageIcon toggleButtonIcon = createImageIcon("icons/toggleButton.png");
        toggleButton = new JButton("AutoViewing ON");
        toggleButton.setActionCommand("toggle");
        toggleButton.addActionListener(listener);
        //toggleButton.addActionListener(timerListener);
    }

    private void setUpButtonTwo() {
        //ImageIcon nextButtonIcon = createImageIcon("icons/nextButton.png");
        nextButton = new JButton("Next Image");
        nextButton.setActionCommand("next");
        nextButton.addActionListener(listener);
        nextButton.setEnabled(false);
    }

    private void setUpButtonThree() {
        //ImageIcon previousButtonIcon = createImageIcon("icons/previousButton.png");
        previousButton = new JButton("Previous Image");
        previousButton.setActionCommand("prev");
        previousButton.addActionListener(listener);
        previousButton.setEnabled(false);
    }

    public JButton getToggleButton() {
        return toggleButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPreviousButton() {
        return previousButton;
    }

    /*    private ImageIcon createImageIcon(String path) {
            URL imgURL = Buttons.class.getResource(path);
            if (imgURL == null) {
                logger.error("Could not load Icon at: {}", path);
                throw new IllegalArgumentException("Incorrect Path!");
            }
            ImageIcon imageIcon = new ImageIcon(imgURL);
            return imageIcon;
        }*/

}
