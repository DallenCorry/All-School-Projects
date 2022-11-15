package oddJob;
/*
  @author: Dallen Corry
 * @version: 2.2
 * @since: 2022/Nov/08
 * @created: 2022/Oct/06
 * Class: driver
 * Uncomment line 59 to get random data.
 * */
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static oddJob.Defaults.*;

public class driver extends Application {

    User u;//Current user
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Job> jobs = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        //Create Objects
        LaunchScreenPane launch = new LaunchScreenPane();
        LandingPane lp = new LandingPane();
        MainScreenPane main = new MainScreenPane();
        SignInPane signIn = new SignInPane();
        NewJobPane newJob = new NewJobPane();
        HBox btnAndBack = new HBox();

        Button btnCreateUser = new Button("Next");
        Button btnSignIn = new Button("Sign In");
        Button btnBack = new Button("Back");

        Button btnNewJob = new Button("+");//TODO: Put this in a Stack pane
        Button btnCreateJob = new Button("Finish");

        Button btnWrite = new Button("Generate Test Data");

        BorderPane.setAlignment(btnCreateUser, Pos.CENTER);
        BorderPane.setAlignment(btnNewJob, Pos.BOTTOM_CENTER);
        launch.setPadding(new Insets(10));

        btnAndBack.setSpacing(5);
        btnAndBack.setAlignment(Pos.CENTER);
        btnAndBack.getChildren().add(btnBack);

        //Scene
        launch.setCenter(lp);
        Scene scene = new Scene(launch);
        scene.getStylesheets().add(STYLE_SHEET);

        //Load Data
        genTestData(10);
        users=readUsers();
        jobs = readJobs();

        //Display
        stage.setTitle("OddJob");
        stage.getIcons().add(new Image(ICON_PATH));
        stage.setScene(scene);
        stage.show();


        //Actions
        lp.btnWorker.setOnAction(e-> {
            launch.setCenter(new NewUserPane(true));
            btnAndBack.getChildren().clear();
            btnAndBack.getChildren().addAll(btnBack, btnCreateUser);
            launch.setBottom(btnAndBack);
            stage.sizeToScene();
        });
        lp.btnEmployer.setOnAction(e-> {
            launch.setCenter(new NewUserPane(false));
            btnAndBack.getChildren().clear();
            btnAndBack.getChildren().addAll(btnBack, btnCreateUser);
            launch.setBottom(btnAndBack);
            stage.sizeToScene();
        });
        lp.signInLink.setOnAction( e-> {
            launch.setCenter(signIn);
            btnAndBack.getChildren().clear();
            btnAndBack.getChildren().addAll(btnBack,btnSignIn);
            launch.setBottom(btnAndBack);
            stage.sizeToScene();
        });


        btnCreateUser.setOnAction(e->{
            String[] data = ((NewUserPane) launch.getCenter()).getData();
            if (data != null) {
                u = createNewUser(data);
                if(u!=null) {
                    scene.setRoot(main);
                    main.setRight(btnNewJob);
                    jobs = readJobs();
                    main.addJobsToCenter(jobs);
                    stage.sizeToScene();
                } else {
                    ((NewUserPane) launch.getCenter()).errorText.setText("Your email is already associated with an account.");
                }
            }
            stage.sizeToScene();
        });
        btnSignIn.setOnAction(e-> {
            u = signIn.login();
            if (u!=null) {
                scene.setRoot(main);
                main.setRight(btnNewJob);
                jobs = readJobs();
                main.addJobsToCenter(jobs);
                stage.sizeToScene();
            } else {
                signIn.txtErr.setText("Incorrect Username or Password");
            }
        });
        btnBack.setOnAction(e-> {
            launch.setCenter(lp);
            launch.setBottom(null);
            stage.sizeToScene();
        });


        main.btnHome.setOnAction(e-> {
            scene.setRoot(main);
            main.setRight(btnNewJob);
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
            main.setCenter(newJob);
            main.setBottom(btnCreateJob);
            stage.sizeToScene();
        });
        btnCreateJob.setOnAction(e-> {
            String[] arr = newJob.getData(u);
            if(arr !=null) {//check if valid
                //Create job
                Job j = new Job(arr);
                writeJob(j);
                jobs = readJobs();
                main.addJobsToCenter(jobs);
            }
        });

        btnWrite.setOnAction(e->genTestData(10));

//        scene.setOnMouseEntered(e-> {
//            stage.sizeToScene();
//        });
    }

    public User createNewUser(String[] data) {
        if(!isEmailAlreadyAssociated(data[3])) {
            User user = new User(data);
            writeUser(user);
            return user;
        } else {
            System.out.println("User already Exists!");
            return null;
        }
        //Check if user already exists with username/email. (username is already checked on creation)
    }

    public boolean isEmailAlreadyAssociated(String s) {
        ArrayList<User> tmpUsers = readUsers();
        if(tmpUsers!=null) {
            for(User u:tmpUsers) {
                if(s.equals(u.getEmail())) {
                    System.out.println(s+": matches :"+u.getEmail());
                    return true;
                }
            }
        } else {
            System.out.println("tmpUsers was null!");
        }
        return false;
    }

    /**
     * Could be made generic for both Jobs and Users
     * Hardcoded to write to USERS_FILE_PATH file in Defaults.java
     * @param us The user to write to the file.
     */
    public static void writeUser(User us) {
        //Read everything out, then put it all in with new data.
        ArrayList<User> tmpUsers = new ArrayList<>();
        if (new File(USERS_FILE_PATH).exists()) {
            try (FileInputStream fis = new FileInputStream(USERS_FILE_PATH);
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
        try (FileOutputStream fos = new FileOutputStream(USERS_FILE_PATH,false); //append false;
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            //Write everything that was in the file, and then add.
            for(User u: tmpUsers) {
                oos.writeObject(u);
            }
            oos.writeObject(us);
            System.out.println("Successfully wrote User "+us.getName());
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<User> readUsers() {
        ArrayList<User> tmpUsers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(USERS_FILE_PATH);
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
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
            System.out.println("File not found "+ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Job> readJobs() {
        ArrayList<Job> tmpJobs = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("jobs.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            while(true) {
                tmpJobs.add((Job)ois.readObject());
            }
        } catch (EOFException ex){
            //End of file reached
            System.out.println("All jobs read");
        } catch (FileNotFoundException ex) {
            System.out.println("No File to load from\n"+ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tmpJobs;
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
    // - Create other pages
    //      - Earnings
    //          top part w/ graph, then bottom part a tab pane. one for worked, one for posted.
    //          Text below with # of jobs total worked/posted
    //      - More/Settings
    // - add links in job info pane to Creator's profile
    // -
    // - make the job info pane prettier(text wrap and pref width on Text Area.
    // - New job, make Description box smaller, fiddle with sizes of others
    //      -make new pane to select an image from a list of 6 or so.
    // - remove user & Job IDs from the job & job info panes.
    // - remove prints.

//TODO :Actual
// Make Accept Job button do something
//      delete the job from main so others can't take it.
//      Add to your jobs and creator's jobs
// User sees accepted jobs somehow?
//      myJobs tab or something
}
