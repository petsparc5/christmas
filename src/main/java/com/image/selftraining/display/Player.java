package com.image.selftraining.display;

import javax.swing.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.image.selftraining.button.ButtonsListener;

@Component
@DependsOn(value="timerListener")
public class Player {


    @Autowired
    private TimerListener timerListener;
    @Autowired
    private ButtonsListener buttonListener;
    private Timer t;
    
    public void sleep() {
        if (!buttonListener.isAutoViewOff()) {
            t.setDelay(5000);            
        } else {
            t.setDelay(50);
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
