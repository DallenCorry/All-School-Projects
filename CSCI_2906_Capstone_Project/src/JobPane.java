package sample;
/**
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Oct/10
 * @created: 2022/Oct/10
 * Class: JobPane
 * */
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.text.Text;


public class JobPane extends Pane {
    Job job;
    TextArea t;
    Image img;
    private static final int defaultWidth = 200;
    private static final int defaultHeight = 100;
    private static final Image defaultImage = new Image("imageNotFound.png");
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
        if (job.getJobImage() == null) {
            img = defaultImage;
        } else {
            img = job.getJobImage();
        }
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
        HBox box = new HBox();
        ImageView view = new ImageView(img);
        box.getChildren().addAll(view,t);

        getChildren().add(box);
    }
}
