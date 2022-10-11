package com.example.oddjob;
/**
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Oct/10
 * @created: 2022/Oct/10
 * Class: JobPane
 * */
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;


public class JobPane extends Pane {
    Job job;
    TextArea t;
    Image img;
    private static final int defaultWidth = 350;
    private static final int defaultHeight = 100;
    private static final Image defaultImage = new Image("https://www.salonlfc.com/en/image-not-found-2/#top_of_page");
    JobPane() {
        super();
        job = null;
        t = new TextArea("No Job");
        img = defaultImage;
        formatPane();
    }

    JobPane(Job job) {
        super();
        this.job=job;
        t = new TextArea(job.toString());
        img = job.getJobImage();
        formatPane();
//        getChildren().add(t);
    }

    public void formatPane() {
        t.setLayoutY(1);
        t.setLayoutX(1);
        t.setPrefWidth(defaultWidth);
        t.setPrefHeight(defaultHeight);
        t.setWrapText(true);
        t.setEditable(false);
        getChildren().add(t);
        //add image. Put it and text in a Hbox. Probably need ImageView instead.
    }
}
