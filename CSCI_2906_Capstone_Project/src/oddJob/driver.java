package oddJob;
/**
 * @author: Dallen Corry
 * @version: 1.2
 * @since: 2022/Nov/01
 * @created: 2022/Oct/06
 * Class: driver
 * */
import java.net.URL;
import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class driver extends Application {

    User u;//Temp user to create bogus jobs/profile
    User[] testUsers;
    Job[] testJobs;

    private final URL URL_TO_USER_DATA = getClass().getResource("users.txt");
    @Override
    public void start(Stage stage) throws Exception {
        //Create Objects
        LaunchScreenPane launch = new LaunchScreenPane();
        LandingPage lp = new LandingPage();
        MainScreenPane main = new MainScreenPane();
        SignInPane signIn = new SignInPane();

        Button btnCreateUser = new Button("Next");
        launch.setAlignment(btnCreateUser, Pos.CENTER);
        launch.setPadding(new Insets(10));
        Button btnSignIn = new Button("Sign In");

        Button btnNewJob = new Button("+");


        //Scene
        launch.setCenter(lp);
        Scene scene = new Scene(launch);

        scene.getStylesheets().add("css/styles.css");

        //Actions

        lp.btnWorker.setOnAction(e-> {
            launch.setCenter(new NewUserPane(true));
            launch.setBottom(btnCreateUser);
            stage.sizeToScene();
        });

        lp.btnEmployer.setOnAction(e-> {
            launch.setCenter(new NewUserPane(false));
            launch.setBottom(btnCreateUser);
            stage.sizeToScene();
        });

        lp.signInLink.setOnAction( e-> {
            launch.setCenter(signIn);
            launch.setBottom(btnSignIn);
            stage.sizeToScene();
        });

        btnCreateUser.setOnAction(e->{
            String[] data = ((NewUserPane) launch.getCenter()).getData();
            if (data != null) {
                scene.setRoot(main);
//                createNewUser(data);
                genTestData(10);
                main.setRight(btnNewJob);
            }
            stage.sizeToScene();
        });
        btnSignIn.setOnAction(e-> {
            signIn.login();
//            stage.setScene(new Scene(main));
            scene.setRoot(main);
            main.setRight(btnNewJob);
        });

        main.btnHome.setOnAction(e-> {
            stage.sizeToScene();
        });

        main.btnEarnings.setOnAction(e-> {
            main.setCenter(new JobPane());
            stage.sizeToScene();
        });

        main.btnProfile.setOnAction(e->{
            main.setCenter(new UserPane());
            stage.sizeToScene();
        });

        //Display
        stage.setTitle("OddJob");
        stage.setScene(scene);
        stage.show();
    }

    public void createNewUser(String[] data) {
        //fake user
//        u = new User("Bob", "bob1234","12345", LocalDate.now());
//        u.addToDatabase();
        User user = new User(data);
        user.addToDatabase();

        //Check if user already exists with username/email. (username is already checked on creation)
    }

    public void genTestData(int num) {
        testUsers = new User[num];
        testJobs = new Job[num];
        for (int i=0; i<num; i++) {
            testUsers[i] = new User();
            testJobs[i] = new Job();
        }
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
