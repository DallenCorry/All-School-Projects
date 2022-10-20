package sample;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewUserPane extends VBox {
    TextField txtName = new TextField();
    ComboBox<String> cbMonth = new ComboBox<>();
    ComboBox<String> cbDay = new ComboBox<>();
    ComboBox<String> cbYear = new ComboBox<>();
    ComboBox<String> cbGender = new ComboBox<>();
    TextField txtEmail = new TextField();
    TextField txtUserName = new TextField();
    TextField txtPassword = new TextField();
    TextField txtConfirmPassword = new TextField();
    Button btnFinish = new Button("Done");
    NewUserPane(){
        super();
        cbGender.setEditable(true);//Let's see if we can select the Other thing here or something
        HBox dates = new HBox();
        dates.getChildren().addAll(cbMonth,cbDay,cbYear);

        Label name = new Label("*Name: ", txtName);
        Label DOB = new Label("*Date of Birth: ", dates);
        Label gender = new Label(" Gender: ", cbGender);
        Label email = new Label(" Email: ", txtEmail);
        Label username = new Label("*Username: ", txtUserName);
        Label password = new Label("*Password: ", txtPassword);
        Label confirm = new Label("*Confirm Password: ", txtConfirmPassword);

//        name.setContentDisplay(ContentDisplay.RIGHT);
//        DOB.setContentDisplay(ContentDisplay.RIGHT);
        getChildren().addAll(name, DOB,gender,email,username,password,confirm);
        for(Node n:getChildren()) {
            ((Label)n).setContentDisplay(ContentDisplay.RIGHT);
        }
    }
}
