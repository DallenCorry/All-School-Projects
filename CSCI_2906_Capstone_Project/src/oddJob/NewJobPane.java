package oddJob;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class NewJobPane extends VBox {
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

    /*data = String[] in the following format:
     *             userID, title, dateOfJob, pay, payIsHourly, jobTime, description, location,
     *             numWorkersWanted, category, jobImagePath*/
    NewJobPane(User u) {
        super();
        getChildren().addAll(title, date, pay, hourly, flat, location, time, workers,category,btnImage,description,done,txtErr);
        hourly.setToggleGroup(payRate);
        flat.setToggleGroup(payRate);

        String[] data = new String[5];
        data[0] = u.getUserID()+"";
        data[1] = title.getText();
//        Job j = new Job(data);
//        j.addToDatabase();
    }

}
