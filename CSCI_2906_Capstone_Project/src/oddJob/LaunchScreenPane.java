package oddJob;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import static oddJob.Defaults.*;

public class LaunchScreenPane extends BorderPane {
    LaunchScreenPane(){
        super();
        StackPane logo = new StackPane(new ImageView(new Image(LOGO_PATH)));
        logo.setPadding(new Insets(10));
        logo.setAlignment(Pos.CENTER);
        setTop(logo);


        StackPane text = new StackPane(new Text("Copyright OddJob© 2022"));
        text.setPadding(new Insets(5));
        text.setAlignment(Pos.CENTER);
        setBottom(text);

    }
}
