package oddJob;
/*
  @author: Dallen Corry
 * @version: 2.2
 * @since: 2022/Nov/08
 * @created: 2022/Oct/06
 * Class: driver
 * */
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static oddJob.Defaults.*;

public class driver extends Application {

    User u;//Current user
    ArrayList<User> users = new ArrayList();
    ArrayList<Job> jobs = new ArrayList();

    @Override
    public void start(Stage stage) throws Exception {
        //Create Objects
        LaunchScreenPane launch = new LaunchScreenPane();
        LandingPane lp = new LandingPane();
        MainScreenPane main = new MainScreenPane();
        SignInPane signIn = new SignInPane();
        NewJobPane newJob = new NewJobPane(u);

        Button btnCreateUser = new Button("Next");
        BorderPane.setAlignment(btnCreateUser, Pos.CENTER);
        launch.setPadding(new Insets(10));
        Button btnSignIn = new Button("Sign In");

        Button btnNewJob = new Button("+");
        Button btnCreateJob = new Button("Finish");

        Button btnWrite = new Button("Write");
        Button btnRead = new Button("Read");

        //Scene
        launch.setCenter(lp);
        Scene scene = new Scene(launch);

        scene.getStylesheets().add(STYLE_SHEET);

        //Load Data
        users=readUsers();
        jobs = readJobs();

        //Display
        stage.setTitle("OddJob");
        stage.setScene(scene);
        stage.show();


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
//                genTestData(10);
                main.setRight(btnNewJob);
                jobs = readJobs();
                main.addJobsToCenter(jobs);
                stage.sizeToScene();
                u = createNewUser(data);
            }

            stage.sizeToScene();
        });
        btnSignIn.setOnAction(e-> {
            if (signIn.login()) {
                scene.setRoot(main);
                VBox tempButtons = new VBox();
                tempButtons.getChildren().addAll(btnNewJob, btnWrite);
//                main.setRight(btnNewJob);
                main.setRight(tempButtons);
                //put in the default jobs
                jobs = readJobs();
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
            System.out.println(jobs.size());
            jobs = readJobs();
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
            main.setCenter(newJob);
            main.setBottom(btnCreateJob);
            stage.sizeToScene();
        });
        btnCreateJob.setOnAction(e-> {
            System.out.println("Adding a Job");
            String[] arr = newJob.getData(u);
            if(arr !=null) {//check if valid
                //Create job
                Job j = new Job(arr);
                //Add job to Driver's list
                writeJob(j);
//                main.setBottom();
//                main.setCenter(main);
                jobs = readJobs();
                main.addJobsToCenter(jobs);
            }
        });


        btnWrite.setOnAction(e->{
            genTestData(10);
        });

        btnRead.setOnAction(e -> {
            users = (readUsers());
            System.out.println("Read"+users.size());
        });

    }

    public User createNewUser(String[] data) {
        //fake user

//        u.addToDatabase();
        User user = new User(data);
//        user.addToDatabase();
        writeUser(user);
        return user;
        //Check if user already exists with username/email. (username is already checked on creation)
    }

    /**
     * Could be made generic for both Jobs and Users
     * Hardcoded to write to "users.dat" file.
     * @param us The user to write to the file.
     */
    public static void writeUser(User us) {
        //Read everything out, then put it all in with new data.
        ArrayList<User> tmpUsers = new ArrayList<>();
        if (new File("users.dat").exists()) {
            try (FileInputStream fis = new FileInputStream("users.dat");
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    tmpUsers.add((User) ois.readObject());
                }
            } catch (EOFException ex) {
                //Pass to next
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try (FileOutputStream fos = new FileOutputStream("users.dat",false); //append false;
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            //Write everything that was in the file, and then add.
            for(User u: tmpUsers) {
                oos.writeObject(u);
            }
            oos.writeObject(us);
            System.out.println("Successfully wrote User "+us.getName());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<User> readUsers() {
        ArrayList<User> tmpUsers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("users.dat");
        ) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true) {
                tmpUsers.add((User)ois.readObject());
            }

        } catch (EOFException ex){
            //End of file reached
            System.out.println("All users read");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tmpUsers;
    }

    public static void writeJob(Job j) {
        //Read everything out, then put it all in with new data.
        ArrayList<Job> tmpJobs = new ArrayList<>();
        if (new File("jobs.dat").exists()) {
            try (FileInputStream fis = new FileInputStream("jobs.dat");
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    tmpJobs.add((Job) ois.readObject());
                }
            } catch (EOFException ex) {
                //Pass to next
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try (FileOutputStream fos = new FileOutputStream("jobs.dat",false); //append false;
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            //Write everything that was in the file, and then add.
            for(Job job: tmpJobs) {
                oos.writeObject(job);
            }
            oos.writeObject(j);
            System.out.println("Successfully wrote job "+j.getTitle());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Job> readJobs() {
        ArrayList<Job> tmpUsers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("jobs.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            while(true) {
                tmpUsers.add((Job)ois.readObject());
            }
        } catch (EOFException ex){
            //End of file reached
            System.out.println("All jobs read");
        } catch (FileNotFoundException ex) {
            System.out.println("No File to load from\n"+ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tmpUsers;
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
            writeUser(userArr[i]);
            writeJob(jobArr[i]);
        }
    }

    /**
     * Entry to run the program
     */
    public static void main(String[] args) {launch(args);}


    //TODO:
    // - Create UserProfilePane with image, stars, text, and buttons
    // - Create other pages
    //      - Earnings
    //          top part w/ graph, then bottom part a tab pane. one for worked, one for posted.
    //          Text below with # of jobs total worked/posted
    //      - Profile
    //      - More/Settings
    // - Store users and jobs somewhere
    // - Add Sign in Functionality
    // - be able to read user data
    // - add links in job info pane to Creator's profile
    // -
    // - make the job info pane prettier(text wrap and pref width on Text Area.
    // - New job, make Description box smaller, fiddle with sizes of others
    //      -make new pane to select an image from a list of 6 or so.
    // - remove user & Job IDs from the job & job info panes.


//TODO :Actual
// Make Accept Job button do something
//      delete the job from main so others can't take it.
//      Add to your jobs and creator's jobs
// User sees accepted jobs somehow?
//      myJobs tab or smthn
}
