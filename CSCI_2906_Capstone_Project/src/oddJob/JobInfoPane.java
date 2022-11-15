package oddJob;
/*
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Nov/08
 * @created: 2022/Nov/08
 * Class: JobInfoPane
 * */
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static oddJob.Defaults.*;

public class JobInfoPane extends VBox {
    Job job;
    Button btnAccept = new Button("Accept Job");

    JobInfoPane(Job job) {
        super();
        this.job = job;
        ImageView imgView = new ImageView(new Image(job.getJobImagePath().equals("") ? IMAGE_NOT_FOUND : job.getJobImagePath()));
        Text title = new Text(job.getTitle());
        TextArea txt = new TextArea(getJobText());
        txt.setEditable(false);
        txt.setPrefSize(DEFAULT_WIDTH/2,DEFAULT_HEIGHT);
        Hyperlink hLink = new Hyperlink(job.getCreator().getName());
        Label txtCreator = new Label("Job Poster:", hLink);
        txtCreator.setContentDisplay(ContentDisplay.RIGHT);
        getChildren().addAll(imgView,title,txtCreator,txt,btnAccept);

        setPrefSize(DEFAULT_WIDTH/2,DEFAULT_HEIGHT);
    }

    private String getJobText() {
        String s = "";
        s+= "\nDate of Job:" + job.getDateOfJob();
        s+= "\n\nPay: $" + job.getPay() + (job.isPayIsHourly() ? "/hr":" - Flat rate");
        s+= "\n Date Posted: "+ job.getDatePosted();
        s+="\n\nDescription:\n" + job.getDescription();
        return s;
    }
}
