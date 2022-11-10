package oddJob;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserPane extends VBox {
    Text name = new Text();
    Image profile;
    Text jobsWorked = new Text();
    Text jobsPosted = new Text();

    Button btnMore = new Button("More");
    VBox additionalInfo = new VBox();
    Text userName = new Text();
    Text userID = new Text();
    Text age = new Text();
    Text gender = new Text();
    Text email = new Text();


    UserPane(User u) {
        super();

        getChildren().add(new Text("Hello World!"));
    }
}
