package com.image.selftraining.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.image.selftraining.display.Player;
import com.image.selftraining.reader.ImportedImages;

@Component
public class ButtonsListener implements ActionListener {

    @Autowired
    private Buttons buttons;
    @Autowired
    private ImportedImages images;
    private boolean autoViewOff = false;
    @Autowired
    private Player displayImages;

    public void actionPerformed(ActionEvent e) {

        if ("toggle".equals(e.getActionCommand())) {
            displayImages.stopTimer();
            autoViewOff = !autoViewOff;
            buttons.getNextButton().setEnabled(autoViewOff);
            buttons.getPreviousButton().setEnabled(autoViewOff);
            buttons.toggleAutoViewMessage();
            displayImages.sleep();
            displayImages.startTimer();
        }

        if ("next".equals(e.getActionCommand())) {
            images.setNextImage();
        }

        if ("prev".equals(e.getActionCommand())) {
            images.setPrevImage();
        }
    }

    public boolean isAutoViewOff() {
        return autoViewOff;
    }

}
