package oddJob;
/**
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Oct/10
 * @created: 2022/Oct/10
 * Class: JobPane
 * */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static oddJob.Defaults.*;

public class JobPane extends Pane {
    Job job;
    TextArea t;
    Image img;
    private static final int jobWidth = 200;
    private static final int jobHeight = 100;
    private static final Image defaultImage = new Image(IMAGE_NOT_FOUND);
    JobPane() {
        super();
        job = null;
        t = new TextArea("No Job");
        img = defaultImage;
        formatPane();
    }

    //TODO fix make this work
    JobPane(Job job) {
        super();
        this.job=job;
        t = new TextArea(job.toString());
        if (job.getJobImage() == null) {
            img = defaultImage;
        } else {
            img = job.getJobImage();
        }
        formatPane();
//        getChildren().add(t);

        jobClickedHandler x = new jobClickedHandler();
        setOnMouseClicked(x);
        t.setOnMouseClicked(x);
    }

    public void formatPane() {
        t.setLayoutY(1);
        t.setLayoutX(1);
        t.setPrefWidth(jobWidth);
        t.setPrefHeight(jobHeight);
        t.setWrapText(true);
        t.setEditable(false);
        HBox box = new HBox();
        ImageView view = new ImageView(img);
        box.getChildren().addAll(view,t);

        getChildren().add(box);
    }
    class jobClickedHandler implements EventHandler<InputEvent> {
        @Override
        public void handle(InputEvent e) {
            System.out.println("Clicked on a Job");
            Scene scene = new Scene(new JobInfoPane(job));
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
        }
    }
}
