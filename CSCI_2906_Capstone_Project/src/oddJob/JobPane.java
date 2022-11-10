package oddJob;
/*
 * @author: Dallen Corry
 * @version: 1.2
 * @since: 2022/Nov/08
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
        if (job.getJobImagePath().equals("")) {
            img = defaultImage;
        } else {
            img = new Image(job.getJobImagePath());
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
        //TODO: Use TextFlow to make the job pane pretty:)
//        Text text1 = new Text("Big italic red text");
//        text1.setFill(Color.RED);
//        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 40));
//
//        Text text2 = new Text(" little bold blue text");
//        text2.setFill(Color.BLUE);
//        text2.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
//
//        TextFlow textFlow = new TextFlow(text1, text2);

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
            newStage.setScene(scene);
            newStage.show();
        }
    }
}
