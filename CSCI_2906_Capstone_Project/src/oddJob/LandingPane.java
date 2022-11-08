package oddJob;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This Class is the first pane seen when loading the app.
 * It is centered withing the LaunchScreen Pane
 */
public class LandingPane extends VBox {
    HBox buttons = new HBox();
    Hyperlink signInLink = new Hyperlink("Sign In");
    Button btnWorker = new Button("Looking for Work");
    Button btnEmployer = new Button("Looking for Help");

    LandingPane() {
        super();
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        btnWorker.setPrefWidth(100);
        btnWorker.setWrapText(true);
        btnWorker.setTextAlignment(TextAlignment.CENTER);
        btnEmployer.setPrefWidth(100);
        btnEmployer.setWrapText(true);
        btnEmployer.setTextAlignment(TextAlignment.CENTER);
//        link.setTextAlignment(TextAlignment.CENTER);

        buttons.getChildren().addAll(btnWorker, new Text("or"), btnEmployer);
        getChildren().addAll( new Text("Are you..."), buttons, new Text("Already have an account?"), signInLink);
        setPadding(new Insets(50,20,50,20));
        setAlignment(Pos.CENTER);
        setSpacing(20);

        btnWorker.setOnAction(e -> {
            System.out.println("New Worker");
        });
        btnEmployer.setOnAction(e->{
            System.out.println("new Employer");
        });
    }
}
