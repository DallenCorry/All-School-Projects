package oddJob;

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
        ImageView imgView = new ImageView(job.getJobImage()==null ? new Image(IMAGE_NOT_FOUND) : job.getJobImage());
        Text title = new Text(job.getTitle());
        TextArea txt = new TextArea(getJobText());
        getChildren().addAll(imgView,title,txt,btnAccept);

        setPrefSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    private String getJobText() {
        String s = job.getJobID()+"\n";
        s+= "\nJob Poster:" + job.getCreator().getName();
        s+= "\nDate of Job:" + job.getDateOfJob();
        s+= "\n\nPay: $" + job.getPay() + (job.isPayIsHourly() ? "/hr":" - Flat rate");
        s+= "\n Date Posted: "+ job.getDatePosted();
        s+="\n\nDescription:\n" + job.getDescription();
        return s;
    }
}
