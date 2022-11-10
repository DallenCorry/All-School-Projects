package oddJob;
/*
 * @author: Dallen Corry
 * @version: 1.3
 * @since: 2022/Nov/08
 * @created: 2022/Oct/16
 * Class: MainScreenPane
 * */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

import static oddJob.Defaults.*;

public class MainScreenPane extends BorderPane {
    Button btnHome = new Button();
    Button btnEarnings = new Button();
    Button btnProfile = new Button();
    Button btnMore = new Button();
    private static final double buttonImageSize = 20;

    ScrollPane sp = new ScrollPane();
    VBox jobs = new VBox();
    HBox buttons = new HBox();
    StackPane logo = new StackPane(new ImageView(new Image(LOGO_PATH)));
    MainScreenPane() {
        super();
        logo.setAlignment(Pos.CENTER);
        logo.setPadding(new Insets(5,5,5,5));
        setTop(logo);

        buttons.getChildren().addAll(btnHome, btnEarnings, btnProfile, btnMore);
        buttons.setSpacing(5);
        buttons.setPadding(new Insets(5,5,5,5));
        buttons.setAlignment(Pos.CENTER);
        setBottom(buttons);

        //Style images
        ImageView home = new ImageView(new Image("images/homeIcon.png"));
        home.setFitHeight(buttonImageSize);
        home.setFitWidth(buttonImageSize);
        btnHome.setGraphic(home);
        ImageView earn = new ImageView(new Image("images/earningsIcon.png"));
        earn.setFitHeight(buttonImageSize);
        earn.setFitWidth(buttonImageSize);
        btnEarnings.setGraphic(earn);
        ImageView prof = new ImageView(new Image("images/profileIcon.png"));
        prof.setFitHeight(buttonImageSize);
        prof.setFitWidth(buttonImageSize);
        btnProfile.setGraphic(prof);
        ImageView more = new ImageView(new Image("images/moreIcon.png"));
        more.setFitHeight(buttonImageSize);
        more.setFitWidth(buttonImageSize);
        btnMore.setGraphic(more);


        setPadding(new Insets(5,15,5,15));
        setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        setCenter(sp);

        sp.setContent(jobs);
    }

    MainScreenPane(ArrayList<Job> jobArr) {
        this();
        addJobsToCenter(jobArr);
    }

    public void addJobsToCenter(Job[] jobArr) {
        jobs.getChildren().clear();
        for(Job j:jobArr) {
            jobs.getChildren().add(new JobPane(j));
        }
        setCenter(sp);
        setBottom(buttons);
    }

    public void addJobsToCenter(ArrayList<Job> jobArr) {
        //Convert array list to array
        Job[] arr = new Job[jobArr.size()];
        for (int i=0; i<arr.length; i++){
            arr[i] = jobArr.get(i);
        }
        addJobsToCenter(arr);
    }
}
