package oddJob;
/*
 * @author: Dallen Corry
 * @version: 1.3
 * @since: 2022/Nov/15
 * @created: 2022/Oct/10
 * Class: JobPane
 * */
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
    private static final int jobWidth = 250;
    private static final int jobHeight = 100;
    private static final Image defaultImage = new Image(IMAGE_NOT_FOUND);

    JobPane(Job job) {
        super();
        this.job=job;
        t = new TextArea(job.toString());
        if (job.getJobImagePath().equals("")) {
            img = defaultImage;
        } else {
            img = new Image(job.getJobImagePath());
        }
        formatPane();

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
        //TODO: Use TextFlow to make the job pane pretty:)

        HBox box = new HBox();
        ImageView view = new ImageView(img);
        box.getChildren().addAll(view,t);

        getChildren().add(box);
    }
    class jobClickedHandler implements EventHandler<InputEvent> {
        @Override
        public void handle(InputEvent e) {
            Scene scene = new Scene(new JobInfoPane(job));
            scene.getStylesheets().add(STYLE_SHEET);
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image(ICON_PATH));
            newStage.setTitle(job.getTitle());
            newStage.setScene(scene);
            newStage.show();
        }
    }
}
