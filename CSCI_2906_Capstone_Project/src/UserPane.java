package sample;

import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserPane extends VBox {
    UserPane() {
        super();
        getChildren().add(new Text("Hello World!"));
    }
}
