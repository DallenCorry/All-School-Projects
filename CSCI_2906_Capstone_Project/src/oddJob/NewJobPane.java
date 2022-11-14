package oddJob;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.format.DateTimeParseException;

import static oddJob.Defaults.*;

public class NewJobPane extends ScrollPane {
    VBox content = new VBox();

    TextField title = new TextField();//Limit characters
    DatePicker date = new DatePicker();//Make it only future dates
    TextField pay = new TextField();//make it only #s
    RadioButton hourly = new RadioButton("Hourly");
    RadioButton flat = new RadioButton("Flat Rate");
    ToggleGroup payRate = new ToggleGroup();
    TextField location = new TextField();
    TextField time = new TextField();
    ComboBox<String> workers = new ComboBox<>();
    ComboBox<String> category = new ComboBox<>();//set char limit
    Button btnImage = new Button("Add Image");
    TextArea description = new TextArea();

    Text txtErr = new Text("");
    String imagePath = "";

    /**/
    NewJobPane() {
        super();
        Label lblTitle = new Label("*Title: ",title);
        Label lblDate = new Label("*Date of Job: ", date);
        Label lblPay = new Label("*Pay: $", pay);
        HBox payButtons = new HBox(hourly, flat);
        Label lblLocation = new Label("*Location: ",location);
        Label lblTime = new Label("Time it will take: ",time);
        Label lblWorkers = new Label("Number of people who can do the Job", workers);
        Label lblCategory = new Label("Category: ", category);
        Label lblBtnImage = new Label("Job Image: ", btnImage);
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

        workers.getItems().addAll("1","2","3","4","5","6","7","8","9","10+");
        workers.getSelectionModel().select(0);
        workers.setEditable(false);
        category.getItems().addAll("","Yard Work", "Construction","Animal Care", "Babysitting", "Manual Labor", "Electronic help", "Repairs-electric", "Repairs-plumbing");
        category.setEditable(true);

        payButtons.setSpacing(10);

        content.getChildren().addAll(lblTitle,lblDate,lblPay,payButtons,lblLocation,lblTime,lblWorkers,
                lblCategory,lblBtnImage,lblDescription,txtErr);

        content.setPrefSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        content.setSpacing(10);
        content.setPadding(new Insets(5,5,5,5));
        setContent(content);
    }

    /**
     *
     * @return data = String[] in the following format:
     *             userID, title, dateOfJob, pay, payIsHourly, location, jobTime, description,
     *             numWorkersWanted, category, jobImagePath
     */
    public String[] getData(User u) {
        boolean isValid;
        txtErr.setText("");
        try {
            isValid = isValid(u);
        }catch (MissingDataException e) {
            txtErr.setText(e.getMessage());
            isValid = false;
        }
        if (isValid) {
            String[] data = new String[11];
            data[0] = u.getUserID()+"";
            data[1] = title.getText();
            data[2] = date.getValue().toString();
            data[3] = pay.getText();
            data[4] = (hourly.isSelected() ? "true" : "false");
            data[5] = location.getText();
            data[6] = time.getText();
            data[7] = description.getText();
            data[8] = (workers.getSelectionModel().getSelectedIndex() + 1) + "";
            data[9] = category.getSelectionModel().getSelectedItem();
            data[10] = imagePath;

            return data;
        } else {
            return null;
        }
    }

    public boolean isValid(User u) throws MissingDataException {
        if (u == null) {
            throw new MissingDataException("Error getting User Data");
        }
        if (title.getText().equals("")) {
            throw new MissingDataException("No Title set");
        }
        try {
            if (date.getValue() == null) {
                throw new MissingDataException("No Date selected");
            }
        } catch (DateTimeParseException e) {
            throw new MissingDataException("Bad Date");
        }
        if (pay.getText().equals("")) {
            throw new MissingDataException("No Pay set");
        }
        if (location.getText().equals("")) {
            throw new MissingDataException("No Location set");
        }
        if(time.getText().equals("")) {
            time.setText("0");
        }
        //Check formatting
        try {
            Double.parseDouble(pay.getText());
        } catch (NumberFormatException e) {
            throw new MissingDataException("Pay must be a #");
        }
        try {
            Double.parseDouble(time.getText());
        } catch (NumberFormatException e) {
            throw new MissingDataException("Time must be a #");
        }
        return true;
    }

}
