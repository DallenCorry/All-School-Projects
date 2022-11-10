package oddJob;
/*
  @author: Dallen Corry
 * @version: 2.2
 * @since: 2022/Nov/08
 * @created: 2022/Oct/06
 * Class: driver
 * */
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static oddJob.Defaults.*;

public class driver extends Application {

    User u = new User("Bob", "bob1234","12345", LocalDate.now(),"male","bob.bob@bobmail.bob");;//Current user
    ArrayList<User> users = new ArrayList();
    ArrayList<Job> jobs = new ArrayList();

//    private final URL URL_TO_USER_DATA = getClass().getResource("users.txt");
    @Override
    public void start(Stage stage) throws Exception {
        //Create Objects
        LaunchScreenPane launch = new LaunchScreenPane();
        LandingPane lp = new LandingPane();
        MainScreenPane main = new MainScreenPane();
        SignInPane signIn = new SignInPane();
//        ScrollPane jobScroll = new ScrollPane();

        Button btnCreateUser = new Button("Next");
        BorderPane.setAlignment(btnCreateUser, Pos.CENTER);
        launch.setPadding(new Insets(10));
        Button btnSignIn = new Button("Sign In");

        Button btnNewJob = new Button("+");


        //Scene
        launch.setCenter(lp);
        Scene scene = new Scene(launch);

        scene.getStylesheets().add(STYLE_SHEET);



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
                genTestData(10);
                main.setRight(btnNewJob);
                main.addJobsToCenter(jobs);
                stage.sizeToScene();
//                    createNewUser(data);

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
            scene.setRoot(main);
            main.setRight(btnNewJob);
            main.addJobsToCenter(jobs);
            stage.sizeToScene();
        });
        main.btnEarnings.setOnAction(e-> {
            stage.sizeToScene();
            main.setRight(null);
        });
        main.btnProfile.setOnAction(e->{
            main.setCenter(new UserPane(u));
            main.setRight(null);
            stage.sizeToScene();
        });
        main.btnMore.setOnAction(e-> {
            main.setRight(null);
            stage.sizeToScene();
        });
        btnNewJob.setOnAction(e-> {
//            jobs[1].encode();
            main.setCenter(new NewJobPane(u));
            stage.sizeToScene();
        });



        //Display
        stage.setTitle("OddJob");
        stage.setScene(scene);
        stage.show();
    }

    public void createNewUser(String[] data) {
        //fake user

//        u.addToDatabase();
        User user = new User(data);
        user.addToDatabase();

        //Check if user already exists with username/email. (username is already checked on creation)
    }

    public void genTestData(int num) {
        User[] userArr =  {
                new User("Bob", "bob123", "password",LocalDate.parse("2000-01-01")),
                new User("Alice", "alice456","RSA",LocalDate.parse("1990-10-10")),
                new User("Admin", "admin", "adminadmin",LocalDate.parse("1900-01-01")),
                new User("Jeff", "JeffRocks1","J3ffRocks1!",LocalDate.parse("1985-05-05")),
                new User("George Georgeson", "ggson","password",LocalDate.now()),
                new User("Steve Jobs", "Apple","Apple",LocalDate.parse("1955-02-24"))
        };
        Job[] jobArr = {
                new Job(userArr[0],"Lawn Mowing",LocalDate.now(),20.00,false,"Cedar City"),
                new Job(userArr[1],"Dog Walking",LocalDate.parse("2022-11-17"),10.00,true,2,"Walk my chihuahua socks for about 2 hours each week.","100 S 300 W",1,""),
                new Job(userArr[2],"Drywall",LocalDate.parse("2022-11-19"),15.00,true,"Cedar Walmart"),
                new Job(userArr[3],"Fix my Internet", LocalDate.now(),30.00,true,5,"my house has no wifi and i need it for work!!","My house",1,"electronic help"),
                new Job(userArr[4],"Help Stock",LocalDate.parse("2022-11-15"),10.0,false,"St. George Rentals")
        };
        for (int i=0; i<num && i< userArr.length && i<jobArr.length; i++) {
            users.add(userArr[i]);
            jobs.add(jobArr[i]);
        }
    }

    /**
     * Entry to run the program
     */
    public static void main(String[] args) {launch(args);}


    //TODO:
    // - Create UserProfilePane with image, stars, text, and buttons
    // - Create other pages
    //      - New Job pane
    //      - Earnings
    //          top part w/ graph, then bottom part a tab pane. one for worked, one for posted.
    //          Text below with # of jobs total worked/posted
    //      - Profile
    //      - More/Settings
    // - be able to make a new Job
    // - Store users and jobs somewhere
    // - Add Sign in Functionality
    // - be able to read user data
    // - add links in job info pane to Creator's profile



    //ADD created jobs
    //Make Accept Job button do something
    //USer sees accepted jobs somehow?
}
