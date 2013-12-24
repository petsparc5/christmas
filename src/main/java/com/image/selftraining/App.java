package com.image.selftraining;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.image.selftraining.configuration.Config;
import com.image.selftraining.display.Player;
import com.image.selftraining.frame.DisplayFrame;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Player displayer = ctx.getBean(Player.class);
        DisplayFrame frame = ctx.getBean(DisplayFrame.class);
        frame.setUpFrame();
        displayer.display();
        ctx.close();
    }
}
