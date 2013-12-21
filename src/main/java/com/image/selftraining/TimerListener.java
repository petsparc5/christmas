package com.image.selftraining;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerListener implements ActionListener{

    @Autowired
    private DisplayImages displayer;

    public void actionPerformed(ActionEvent e) {
        displayer.sleep();
        displayer.setUpFrame();
    }

    
}
