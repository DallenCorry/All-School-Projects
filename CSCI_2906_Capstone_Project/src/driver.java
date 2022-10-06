import javax.sound.sampled.*;
import java.io.*;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.stage.Stage;

public class driver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    /**
     * Entry to run the program
     */
    public static void main(String[] args) {
        LocalDate d = LocalDate.parse("2000-10-01");
        User myUser = new User("Bob", "bob1234", "1234", d);
        System.out.println("Name: "+myUser.name + "\nAge: " + myUser.getAge());
    }


}
