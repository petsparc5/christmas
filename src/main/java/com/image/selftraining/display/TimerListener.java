package com.image.selftraining.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.image.selftraining.frame.DisplayFrame;

@Component
public class TimerListener implements ActionListener{

    @Autowired
    private DisplayFrame displayer;
    @Autowired
    private Player display;

    public void actionPerformed(ActionEvent e) {
        display.sleep();
        displayer.setUpFrame();
        displayer.setFrameName();
    }

    
}
