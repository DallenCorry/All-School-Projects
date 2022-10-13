package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LandingPage extends VBox {
    HBox buttons = new HBox();
    Hyperlink link = new Hyperlink("Sign In");
    Button worker  = new Button("Looking for Work");
    Button employer = new Button("Looking for Help");

    LandingPage() {
        super();
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        worker.setPrefWidth(100);
        worker.setWrapText(true);
        worker.setTextAlignment(TextAlignment.CENTER);
        employer.setPrefWidth(100);
        employer.setWrapText(true);
        employer.setTextAlignment(TextAlignment.CENTER);
//        link.setTextAlignment(TextAlignment.CENTER);

        buttons.getChildren().addAll(worker, new Text("or"), employer);
        getChildren().addAll( new Text("Are you..."), buttons, new Text("Already have an account?"), link);
        setPadding(new Insets(50,20,50,20));
        setAlignment(Pos.CENTER);
        setSpacing(25);
    }
}
