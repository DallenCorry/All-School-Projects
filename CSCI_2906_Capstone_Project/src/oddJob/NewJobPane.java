package oddJob;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.concurrent.Callable;

import static oddJob.Defaults.*;

public class NewJobPane extends ScrollPane {
    VBox content = new VBox();

    TextField title = new TextField();
    DatePicker date = new DatePicker();
    TextField pay = new TextField();
    RadioButton hourly = new RadioButton("Hourly");
    RadioButton flat = new RadioButton("Flat Rate");
    ToggleGroup payRate = new ToggleGroup();
    TextField location = new TextField();
    TextField time = new TextField();
    ComboBox<Integer> workers = new ComboBox<>();
    ComboBox<String> category = new ComboBox<>();
    Button btnImage = new Button("Add Image");
    TextArea description = new TextArea();
    Button done = new Button("Finish");
    Text txtErr = new Text("");
    String imagePath = "";

    /**/
    NewJobPane(User u) {
        super();
        Label lblTitle = new Label("*Title: ",title);
        Label lblDate = new Label("*Date of Job: ", date);
        Label lblPay = new Label("*Pay: ", pay);
        HBox payButtons = new HBox(hourly, flat);
        Label lblLocation = new Label("*Location: ",location);
        Label lblTime = new Label("Time it will take: ",time);
        Label lblWorkers = new Label("Number of people who can do the Job", workers);
        Label lblCategory = new Label("Category: ", category);
        Label lblBtnImage = new Label("Job Image", btnImage);
        Label lblDescription = new Label("Description", description);

        lblTitle.setContentDisplay(ContentDisplay.RIGHT);
        lblDate.setContentDisplay(ContentDisplay.RIGHT);
        lblPay.setContentDisplay(ContentDisplay.RIGHT);
        lblLocation.setContentDisplay(ContentDisplay.RIGHT);
        lblTime.setContentDisplay(ContentDisplay.RIGHT);
        lblWorkers.setContentDisplay(ContentDisplay.RIGHT);
        lblCategory.setContentDisplay(ContentDisplay.RIGHT);
        lblBtnImage.setContentDisplay(ContentDisplay.RIGHT);

        lblDescription.setContentDisplay(ContentDisplay.BOTTOM);

        hourly.setToggleGroup(payRate);
        hourly.setSelected(true);
        flat.setToggleGroup(payRate);
        flat.setSelected(false);

        workers.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        workers.setEditable(false);
        category.getItems().addAll("Yard Work", "");
        category.setEditable(true);

//        String[] data = getData(u);
//        data[0] = u.getUserID()+"";
//        Job j = new Job(data);
//        j.addToDatabase();

        content.getChildren().addAll(lblTitle,lblDate,lblPay,payButtons,lblLocation,lblTime,lblWorkers,
                lblCategory,lblBtnImage,lblDescription,done,txtErr);

        content.setPrefSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        content.setSpacing(10);
        content.setPadding(new Insets(5,5,5,5));
        setContent(content);
    }

    /**
     *
     * @return data = String[] in the following format:
     *             userID, title, dateOfJob, pay, payIsHourly, jobTime, description, location,
     *             numWorkersWanted, category, jobImagePath
     */
    public String[] getData(User u) {
        String[] data = new String[11];
//        data[0] = u.getUserID()+"";
        data[1] = title.getText();
        data[2] = date.getValue().toString();
        data[3] = pay.getText();
        data[4] = (hourly.isSelected()?"true":"false");
        data[5] = time.getText();
        data[6] = description.getText();
        data[7] = location.getText();
        data[8] = (workers.getSelectionModel().getSelectedIndex()+1)+"";
        data[9] = category.getSelectionModel().getSelectedItem();
        data[10] = imagePath;
        return data;
    }

    public boolean isValid() {

        return true;
    }

}
