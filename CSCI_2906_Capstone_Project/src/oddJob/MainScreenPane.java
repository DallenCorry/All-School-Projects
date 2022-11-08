package oddJob;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static oddJob.Defaults.*;

public class MainScreenPane extends BorderPane {
    Button btnHome = new Button();
    Button btnEarnings = new Button();
    Button btnProfile = new Button();
    Button btnMore = new Button();
    private static final double buttonImageSize = 20;

    ScrollPane sp = new ScrollPane();
    VBox jobs = new VBox();
    MainScreenPane() {
        super();
        StackPane logo = new StackPane(new ImageView(new Image(LOGO_PATH)));
        logo.setAlignment(Pos.CENTER);
        logo.setPadding(new Insets(5,5,5,5));
        setTop(logo);

        HBox buttons = new HBox();
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

        setCenter(sp);

        sp.setContent(jobs);
    }

    public void addJobsToCenter(Job[] jobArr) {
        for(Job j:jobArr) {
            jobs.getChildren().add(new JobPane(j));
        }
    }
}
