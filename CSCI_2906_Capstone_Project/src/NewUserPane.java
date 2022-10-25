package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.regex.Pattern;

public class NewUserPane extends VBox {
    TextField txtName = new TextField();
    ComboBox<String> cbMonth = new ComboBox<>();
    ComboBox<String> cbDay = new ComboBox<>();
    ComboBox<String> cbYear = new ComboBox<>();
    ComboBox<String> cbGender = new ComboBox<>();
    TextField txtOtherGender = new TextField();
    TextField txtEmail = new TextField();
    TextField txtUserName = new TextField();
    TextField txtPassword = new TextField();
    TextField txtConfirmPassword = new TextField();
    Text errorText = new Text("");
    Button btnFinish = new Button("Done");

    private final int FIELD_SIZE = 300;
    private final int CUURENT_YEAR = LocalDate.now().getYear();
    private int numDaysInMonth = 31;

    private final String[] GENDERS_LIST = {"Male", "Female", "Prefer not to say", "Other"};
    private final String[] MONTHS = {"January", "February", "March","April", "May","June","July","August","September","October","November","December"};
    NewUserPane(){
        super();
        cbGender.setEditable(false);
        HBox dates = new HBox();
        dates.getChildren().addAll(cbMonth,cbDay,cbYear);

        setCBValues();

        //set things for Individual fields
        txtName.setPrefWidth(FIELD_SIZE);
        txtEmail.setPrefWidth(FIELD_SIZE);
        txtUserName.setPrefWidth(FIELD_SIZE);
        txtPassword.setPrefWidth(FIELD_SIZE);
        txtConfirmPassword.setPrefWidth(FIELD_SIZE);

        cbGender.setPrefWidth(FIELD_SIZE);
        txtOtherGender.setPrefWidth(FIELD_SIZE-50);
        cbMonth.setPrefWidth(FIELD_SIZE*3/8);
        cbDay.setPrefWidth ( FIELD_SIZE*2/8);
        cbYear.setPrefWidth (FIELD_SIZE*3/8);

        Label name = new Label("*Name: ", txtName);
        Label DOB = new Label("*Date of Birth: ", dates);
        Label gender = new Label("*Gender: ", cbGender);
        Label otherGender = new Label("Gender: ",txtOtherGender);
        Label email = new Label(" Email: ", txtEmail);
        Label username = new Label("*Username: ", txtUserName);
        Label password = new Label("*Password: ", txtPassword);
        Label confirm = new Label("*Confirm Password: ", txtConfirmPassword);
        Label error = new Label("", errorText);

        getChildren().addAll(name, DOB,gender,otherGender,email,username,password,confirm, error);
        for(Node n:getChildren()) {
            ((Label)n).setContentDisplay(ContentDisplay.RIGHT);
        }
        error.setAlignment(Pos.CENTER);

        otherGender.setVisible(false);
        setAlignment(Pos.CENTER_RIGHT);
        setPadding(new Insets(15));

        //Handlers
        cbGender.setOnAction(e-> {
            otherGender.setVisible(cbGender.getSelectionModel().getSelectedItem().equals("Other"));
        });
        cbMonth.setOnAction(e->{
            updateDays();
        });
        cbYear.setOnAction(e-> {
            updateDays();
        });
    }

    private void setCBValues() {
        cbGender.getItems().clear();
        cbMonth.getItems().clear();
        cbYear.getItems().clear();
        cbDay.getItems().clear();

        for (String s:GENDERS_LIST) {
            cbGender.getItems().add(s);
        }
        for (String s:MONTHS) {
            cbMonth.getItems().add(s);
        }
        for (int i = 1; i <= numDaysInMonth; i++) {
            cbDay.getItems().add(i+"");
        }
        for (int i=CUURENT_YEAR-14; i>=CUURENT_YEAR-110; i--) {
            cbYear.getItems().add(i+"");
        }
    }

    private void updateDays() {
        String tempYear = cbYear.getSelectionModel().getSelectedItem();
        int selectedYear = Integer.parseInt(tempYear != null ? tempYear:"2000");

        int tempMonth = cbMonth.getSelectionModel().getSelectedIndex();
        int selectedMonth = tempMonth<=0 ? 1 : tempMonth + 1;

        YearMonth temp = YearMonth.of(selectedYear, selectedMonth);
        numDaysInMonth = temp.lengthOfMonth();

        cbDay.getItems().clear();
        for (int i = 1; i <= numDaysInMonth; i++) {
            cbDay.getItems().add(i+"");
        }
    }

    public String[] getData() throws MissingDataException {
        String name = "";
        String DOB = "";
        String gender = "";
        String email = "";
        String userName = "";
        String password = "";

        if(validate()) {

            String day;
            if (cbDay.getSelectionModel().getSelectedItem() == null) {
                day = "00";
                throw (new MissingDataException("No Day Set"));
            } else if (cbDay.getSelectionModel().getSelectedIndex() < 10) {
                day = "0" + cbDay.getSelectionModel().getSelectedItem();
            } else {
                day = cbDay.getSelectionModel().getSelectedItem();
            }
        } else {
            errorText.setText("Invalid something");
        }




        return new String[] {name,DOB,gender,email,userName,password};
    }

    private boolean validate() {
        boolean valid = true;
        if (txtName.getText().equals ("")) {
            valid = false;
        }
        if (cbMonth.getSelectionModel().getSelectedItem() == null) {
            valid = false;
        }
        if (cbDay.getSelectionModel().getSelectedItem() == null) {
            valid = false;
        }
        if (cbYear.getSelectionModel().getSelectedItem() == null) {
            valid = false;
        }
        if (cbGender.getSelectionModel().getSelectedItem() == null) {
            valid = false;
        }
        if (txtUserName.getText().equals ("")) {
            valid = false;
        }
        if (txtPassword.getText().equals ("")) {
            valid = false;
        }
        if (txtConfirmPassword.getText().equals ("")) {
            valid = false;
        }


        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            valid = false;
        }
        if (cbGender.getSelectionModel().getSelectedItem()!=null && cbGender.getSelectionModel().getSelectedItem().equals("Other") && txtOtherGender.getText().equals("")) {
            valid = false;
        }
        if (!isValidEmail(txtEmail.getText())) {
            valid = false;
        }
        return valid;
    }

    private boolean isValidEmail(String s) {
        //Regular expression from www.baeldung.com "Email Validation in Java" - by baeldung
        return Pattern.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", s);
    }

    public String test() {
        errorText.setText(validate()+"");
        return cbDay.getSelectionModel().getSelectedItem();
    }
}
