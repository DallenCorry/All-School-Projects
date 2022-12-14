package oddJob;
/*
 * @author: Dallen Corry
 * @version: 1.2
 * @since: 2022/Nov/15
 * @created: 2022/Nov/08
 * Class: JobInfoPane
 * */
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static oddJob.Defaults.*;

public class JobInfoPane extends VBox {
    Job job;
    Hyperlink hLink;
    Button btnAccept = new Button("Accept Job");
    Button btnBack = new Button("<-");

    JobInfoPane(Job job) {
        super();
        this.job = job;
        setContent();

        setPrefSize(DEFAULT_WIDTH/2,DEFAULT_HEIGHT);


        btnAccept.setOnAction(actionEvent -> {
            driver.u.addJobWorked(job);
            job.setActive(false);
            // close the dialog.
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });

        hLink.setOnAction(e-> {
            getChildren().clear();
            getChildren().addAll(btnBack, new UserPane(job.getCreator(),false));
            setAlignment(Pos.TOP_LEFT);
        });

        btnBack.setOnAction(e->setContent());
    }

    /**
     * gets the necessary information from the instance variable 'job' and
     * returns it in a formatted string.
     * @return a String containing date of job, date posted, pay, job time, location, number of workers, and description.
     */
    private String getJobText() {
        String s = "";
        s+= "Date of Job: " + job.getDateOfJob();
        s+= "\nDate Posted: "+ job.getDatePosted();
        s+= "\n\nPay: $" + job.getPay() + (job.isPayIsHourly() ? "/hr":" - Flat rate");
        s+= "\nEstimated time: "+job.getJobTime() + " hrs";
        s+= "\n\nLocation: "+job.getLocation();
        s+= "\nNumber of workers wanted: " +job.getNumWorkersWanted();
        s+="\n\nDescription:\n" + job.getDescription();
        return s;
    }

    /**
     * Sets the content of the HBox to contain all the instance elements.
     */
    private void setContent() {
        ImageView imgView = new ImageView(new Image(job.getJobImagePath().equals("") ? IMAGE_NOT_FOUND : job.getJobImagePath()));
        Text title = new Text(job.getTitle());
        TextArea txt = new TextArea(getJobText());
        txt.setEditable(false);
        txt.setPrefSize(DEFAULT_WIDTH/2,DEFAULT_HEIGHT);
        txt.setWrapText(true);
        hLink = new Hyperlink(job.getCreator().getName());
        Label txtCreator = new Label("Job Poster:", hLink);
        txtCreator.setContentDisplay(ContentDisplay.RIGHT);
        getChildren().clear();
        getChildren().addAll(imgView,title,txtCreator,txt,btnAccept);
        setAlignment(Pos.CENTER);
    }
}
