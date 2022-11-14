package oddJob;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        userName.setText("Usesrname "+u.getUserName());
        age.setText(u.getAge()+"");
        ImageView profPic = new ImageView(profile);

        additionalInfo.setVisible(false);
        additionalInfo.getChildren().addAll(userName,userID,age,gender,email);
        getChildren().addAll(name,profPic,jobsWorked,workedStars,jobsPosted,postedStars, btnMore, additionalInfo);

        btnMore.setOnAction(e-> {
            additionalInfo.setVisible(true);
            btnMore.setVisible(false);
        });
    }
}
