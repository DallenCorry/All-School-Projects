package oddJob;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class SignInPane extends VBox {
    TextField txtUsername = new TextField();
    TextField txtPassword = new TextField();
    Text txtErr = new Text("");

    private final URL URL_TO_USER_DATA = getClass().getResource("users.txt");

    SignInPane() {
        super();
        Label username = new Label("Username:", txtUsername);
        username.setContentDisplay(ContentDisplay.RIGHT);
        Label password = new Label("Password: ", txtPassword);
        password.setContentDisplay(ContentDisplay.RIGHT);

        getChildren().addAll(username, password, txtErr);
        setSpacing(15);
        setPadding(new Insets(45,15,45,15));
        setAlignment(Pos.CENTER_LEFT);
    }

    public boolean login() {
//        try {
//            //Get the data from stored users
//            String username = txtUsername.getText();
//            String password = txtPassword.getText();
////            File f = new File(URL_TO_USER_DATA.getPath());
////            Scanner input = new Scanner(f);
////            String s = input.nextLine();
////
////            String[] temp = s.split(" ");
////            for (String str:temp) {
////                System.out.println(str);
////            }
//
//            //Check against the database and see if there's a match
//            txtErr.setText("Bad!");
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
        return true;
    }
}
