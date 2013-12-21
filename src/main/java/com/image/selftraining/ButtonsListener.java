package com.image.selftraining;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("B")
public class ButtonsListener implements ActionListener {

    @Autowired
    private Buttons buttons;
    @Autowired
    private ImportedImages images;
    private boolean autoViewOff = false;
    @Autowired
    private DisplayImages displayImages;

    public void actionPerformed(ActionEvent e) {

        if ("toggle".equals(e.getActionCommand())) {
            displayImages.stopTimer();
            autoViewOff = !autoViewOff;
            buttons.getNextButton().setEnabled(autoViewOff);
            buttons.getPreviousButton().setEnabled(autoViewOff);
            buttons.getToggleButton().setText(getAutoViewMessage());
            displayImages.setSleep();
            displayImages.startTimer();
        }

        if ("next".equals(e.getActionCommand())) {
            images.setNextImage();
        }

        if ("prev".equals(e.getActionCommand())) {
            images.setPrevImage();
        }
    }
    
    private String getAutoViewMessage() {
        String message;
        if (autoViewOff) {
            message = "AutoViewing OFF";
        } else {
            message = "AutoViewing ON";
        }
        return message;
    }

    public boolean isAutoViewOff() {
        return autoViewOff;
    }

}
