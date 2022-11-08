package oddJob;
/**
 * @author: Dallen Corry
 * @version: 1.2
 * @since: 2022/Nov/01
 * @created: 2022/Oct/06
 * Class: driver
 * */
import java.net.URL;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static oddJob.Defaults.*;

public class driver extends Application {

    User u;//Temp user to create bogus jobs/profile
    User[] users;
    Job[] jobs;

    private final URL URL_TO_USER_DATA = getClass().getResource("users.txt");
    @Override
    public void start(Stage stage) throws Exception {
        //Create Objects
        LaunchScreenPane launch = new LaunchScreenPane();
        LandingPane lp = new LandingPane();
        MainScreenPane main = new MainScreenPane();
        SignInPane signIn = new SignInPane();
//        ScrollPane jobScroll = new ScrollPane();

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
//                    createNewUser(data);
                    genTestData(10);
                main.setRight(btnNewJob);
            }

            stage.sizeToScene();
        });
        btnSignIn.setOnAction(e-> {
            if (signIn.login()) {
                scene.setRoot(main);
                main.setRight(btnNewJob);
                //put in the default jobs
                    genTestData(10);
    //                getJobsFromFile();
                main.addJobsToCenter(jobs);
                stage.sizeToScene();
            } else {
                signIn.txtErr.setText("Incorrect Username or Password");
            }
        });

        main.btnHome.setOnAction(e-> {
//            main.setCenter(new JobPane(new Job()));
            main.setRight(btnNewJob);
            main.addJobsToCenter(jobs);
            stage.sizeToScene();
        });

        main.btnEarnings.setOnAction(e-> {
            stage.sizeToScene();
            main.setRight(null);
        });

        main.btnProfile.setOnAction(e->{
            main.setCenter(new UserPane());
            main.setRight(null);
            stage.sizeToScene();
        });

        main.btnMore.setOnAction(e-> {
            main.setRight(null);
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
        users = new User[num];
        jobs = new Job[num];
        for (int i=0; i<num; i++) {
            users[i] = new User("Name"+i,randomString(10),randomString(10), LocalDate.now());
            jobs[i] = new Job(users[i],"Title"+i,LocalDate.now(),10.0,i%2==0,randomString());
        }
    }

    /**
     * Entry to run the program
     */
    public static void main(String[] args) {launch(args);}


    //TODO:
    // - Create JobInfoPane with image and text and buttons
    // - Create UserProfilePane with image, stars, text, and buttons
    // - Create CSS page and link it
    // - Create other pages
    //      - Home
    //      - Earnings
    //          top part w/ graph, then bottom part a tab pane. one for worked, one for posted. Text below with # of jobs total worked/posted
    //      - Profile (custom class??)
    //      - More/Settings
    // - Store users and jobs somewhere
}
