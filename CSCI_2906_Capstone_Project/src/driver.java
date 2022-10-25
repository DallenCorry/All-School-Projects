package sample;
/**
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Oct/06
 * @created: 2022/Oct/06
 * Class: driver
 * */
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class driver extends Application {

    User u;//Temp user to create bogus jobs/profile

    @Override
    public void start(Stage stage) throws Exception {
        //Create Objects
        LaunchScreenPane launch = new LaunchScreenPane();
        LandingPage lp = new LandingPage();
        MainScreenPane main = new MainScreenPane();

        Button btnNext = new Button("Next");
        launch.setAlignment(btnNext, Pos.CENTER);
        launch.setPadding(new Insets(10));


        //Scene
        launch.setCenter(lp);
        Scene scene = new Scene(launch);

        //Actions

        lp.btnWorker.setOnAction(e-> {
            launch.setCenter(new NewUserPane());
            launch.setBottom(btnNext);
            stage.sizeToScene();
        });

        lp.btnEmployer.setOnAction(e-> {
            launch.setCenter(new NewUserPane());
            stage.sizeToScene();
            launch.setBottom(btnNext);
        });

        btnNext.setOnAction(e->{
            System.out.println(launch.getCenter().getClass());
            System.out.println(((NewUserPane)launch.getCenter()).test());
        });

        lp.signInLink.setOnAction(e-> {
            System.out.println("Clicked Link");
        });

        main.btnHome.setOnAction(e-> {
            System.out.println("In Driver");
            stage.sizeToScene();
        });

        main.btnEarnings.setOnAction(e-> {
            main.setCenter(new JobPane());
        });

        main.btnProfile.setOnAction(e->{
            main.setCenter(new UserPane());
        });

        //Display
        stage.setTitle("OddJob");
        stage.setScene(scene);
        stage.show();
    }

    public void createNewUser(LocalDate date) {
        u = new User("Bob", "bob1234","12345", date);
    }

    public ArrayList<JobPane> generateJobList() {
        ArrayList<JobPane> jobsArray = new ArrayList<JobPane>();
        createNewUser(LocalDate.parse("2000-01-01"));
        jobsArray.add(new JobPane(new Job(u,"Title",LocalDate.now(), 10.0, false, "")));
        jobsArray.add(new JobPane(new Job(u,"Title2",LocalDate.now(), 10.0, false, "")));
        jobsArray.add(new JobPane(new Job(u,"Title3",LocalDate.now(), 10.0, false, "")));
//        homePane.getChildren().addAll(jobsArray);
//        center.setContent(homePane);
        return jobsArray;
    }
    /**
     * Entry to run the program
     */
    public static void main(String[] args) {launch(args);}


    //TODO:
    // - Create JobPane with image and text
    // - Create JobInfoPane with image and text and buttons
    // - Create UserProfilePane with image, stars, text, and buttons
    // - Create CSS page and link it
    // - Create other pages
    //      - Landing
    //          2 buttons(work or post?), and hyperlink to sign in
    //      - Create user
    //          form with text areas
    //      - Login
    //          2 text areas
    //      - Home
    //      - Earnings
    //          top part w/ graph, then bottom part a tab pane. one for worked, one for posted. Text below with # of jobs total worked/posted
    //      - Profile (custom class??)
    //      - More/Settings
    // - images for buttons
    // - create logo
    // - floating button/button in the corner +
    // -
    // - Make encoding and decoding
    // - Store users and jobs somewhere
}
