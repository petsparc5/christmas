package com.image.selftraining.button;

import java.awt.Dimension;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn(value = "buttonsListener")
public class Buttons {

    private final Logger logger = LoggerFactory.getLogger(Buttons.class);
    private JButton toggleButton;
    private ImageIcon toggleButtonIcon = createImageIcon("/icons/AutoViewOn.png");
    private JButton nextButton = new JButton();
    private JButton previousButton = new JButton();
    @Autowired
    private ButtonsListener listener;

    @PostConstruct
    public void setUp() {
        setUpToggleButton();
        setUpButton("next", nextButton);
        setUpButton("prev", previousButton);
    }

    public void addButtons(JPanel panel) {
        panel.add(previousButton);
        panel.add(toggleButton);
        panel.add(nextButton);
    }

    private void setUpToggleButton() {
        toggleButton = new JButton(toggleButtonIcon);
        toggleButton.setPreferredSize(new Dimension(132, 84));
        toggleButton.setOpaque(false);
        toggleButton.setActionCommand("toggle");
        toggleButton.addActionListener(listener);
    }

    private void setUpButton(String buttonName, JButton button) {
        ImageIcon buttonIcon = createImageIcon("/icons/" + buttonName + "Button.png");
        button.setIcon(buttonIcon);
        button.setPreferredSize(new Dimension(54, 54));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setActionCommand(buttonName);
        button.addActionListener(listener);
        button.setEnabled(false);
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            return icon;
        }
        logger.error("Could not load Icon at: {}", path);
        return null;
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

    public void toggleAutoViewMessage() {
        if (listener.isAutoViewOff()) {
            toggleButtonIcon = createImageIcon("/icons/AutoViewOff.png");
            toggleButton.setIcon(toggleButtonIcon);
        } else {
            toggleButtonIcon = createImageIcon("/icons/AutoViewOn.png");
            toggleButton.setIcon(toggleButtonIcon);
        }
    }

}
