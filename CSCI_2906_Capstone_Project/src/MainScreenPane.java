package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MainScreenPane extends BorderPane {
    Button btnHome = new Button("Home");
    Button btnEarnings = new Button("Earn");
    Button btnProfile = new Button("Prof");
    Button btnMore = new Button("More");
    MainScreenPane() {
        super();
        StackPane logo = new StackPane(new ImageView(new Image("OddJobLogo.png")));
        logo.setAlignment(Pos.CENTER);
        logo.setPadding(new Insets(5,5,5,5));
        setTop(logo);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnHome, btnEarnings, btnProfile, btnMore);
        buttons.setSpacing(5);
        buttons.setPadding(new Insets(5,5,5,5));
        buttons.setAlignment(Pos.CENTER);
        setBottom(buttons);

        setPadding(new Insets(5,15,5,15));
    }
}
