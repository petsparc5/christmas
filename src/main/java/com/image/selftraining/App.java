package com.image.selftraining;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        DisplayImages displayer = ctx.getBean(DisplayImages.class);
        displayer.setUp();
        displayer.setBack();
        displayer.setUpFrame();
        displayer.display();
    }
}
