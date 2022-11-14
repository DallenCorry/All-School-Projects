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
import java.util.ArrayList;
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

    public User login() {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        ArrayList<User> tmpUsers = driver.readUsers();
        for(User user:tmpUsers) {
            System.out.println("U:"+user.getUserName()+" P: "+user.getPassword());
            System.out.println("U:"+userName        +  " P: "+password);
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("No user found.");
        return null;
    }
}
