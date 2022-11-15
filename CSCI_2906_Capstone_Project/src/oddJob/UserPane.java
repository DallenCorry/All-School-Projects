package oddJob;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import static oddJob.Defaults.*;

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

    HBox workedStars = new HBox();
    HBox postedStars = new HBox();


    UserPane(User u) {
        super();
        name.setText("Name "+u.getName());
        profile = new Image(u.getProfilePicturePath());
        if (u.getJobsCreated() != null) {
            jobsPosted.setText("Jobs Posted: "+u.getJobsCreated().size());
        } else {
            jobsPosted.setText("Jobs Posted: 0");
        }
        if (u.getJobsCreated() != null) {
            jobsWorked.setText("Jobs Worked: "+u.getJobsWorked().size());
        } else {
            jobsWorked.setText("Jobs Worked: 0");
        }
        userID.setText("ID: "+u.getUserID());
        userName.setText("Username: "+u.getUserName());
        age.setText("Age: " + u.getAge()+"");
        gender.setText("Gender: "+u.getGender());
        email.setText("Email: "+ u.getEmail());
        email.setWrappingWidth(DEFAULT_WIDTH/2-5.0);
        ImageView profPic = new ImageView(profile);

        additionalInfo.setVisible(false);
        additionalInfo.getChildren().addAll(userName,userID,age,gender);
        getChildren().addAll(name,profPic,jobsWorked,workedStars,jobsPosted,postedStars,email, btnMore, additionalInfo);
        setAlignment(Pos.TOP_CENTER);

        btnMore.setOnAction(e-> {
            additionalInfo.setVisible(true);
            btnMore.setVisible(false);
        });
    }
    UserPane(User u, boolean isOwner) {
        this(u);
        btnMore.setVisible(isOwner);
    }
}
