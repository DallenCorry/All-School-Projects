package oddJob;
/*
 * @author: Dallen Corry
 * @version: 1.5
 * @since: 2022/Nov/15
 * @created: 2022/Oct/20
 * Class: NewUserPane
 * */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
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
    PasswordField txtPassword = new PasswordField();
    PasswordField txtConfirmPassword = new PasswordField();
    Text errorText = new Text("");
    Text txtUsernameError = new Text("");

    private final int FIELD_SIZE = 300;
    private final int CURRENT_YEAR = LocalDate.now().getYear();
    private int numDaysInMonth = 31;

    private final String[] GENDERS_LIST = {"Male", "Female", "Prefer not to say", "Other"};
    private final String[] MONTHS = {"January", "February", "March","April", "May","June","July","August","September","October","November","December"};

    NewUserPane(boolean isWorker){
        super();
        cbGender.setEditable(false);
        HBox dates = new HBox();
        dates.getChildren().addAll(cbMonth,cbDay,cbYear);

        setCBValues();

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
        Label email = new Label(" *Email: ", txtEmail);
        Label username = new Label("*Username: ", txtUserName);
        Label usernameTaken = new Label("", txtUsernameError);
        Label password = new Label("*Password: ", txtPassword);
        Label confirm = new Label("*Confirm Password: ", txtConfirmPassword);
        Label error = new Label("", errorText);

        getChildren().addAll(name,DOB,gender,otherGender,email,username,usernameTaken,password,confirm, error);
        for(Node n:getChildren()) {
            ((Label)n).setContentDisplay(ContentDisplay.RIGHT);
        }
        error.setAlignment(Pos.CENTER);

        errorText.setFill(Color.RED);
        otherGender.setVisible(false);
        setAlignment(Pos.CENTER_RIGHT);
        setPadding(new Insets(15));
        setSpacing(5);

        //Handlers
        cbGender.setOnAction(e->
                otherGender.setVisible(cbGender.getSelectionModel().getSelectedItem().equals("Other"))
        );
        cbMonth.setOnAction(e->updateDays());
        cbYear.setOnAction(e-> updateDays());

        txtUserName.setOnKeyTyped(e->{
            if (usernameAlreadyExists(txtUserName.getText())) {
                txtUsernameError.setText("Username " + txtUserName.getText() + " already taken");
            } else if(!isValidUsername(txtUserName.getText())) {
                txtUsernameError.setText("Invalid Username");
            } else {
                txtUsernameError.setText("");
            }
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
        for (int i = CURRENT_YEAR -14; i>= CURRENT_YEAR -110; i--) {
            cbYear.getItems().add(i+"");
        }
    }

    /**
     * Updates the Combobox's values based on selected month and year
     * Accounts for months with 28,30, and 31 days, as well as leap years.
     */
    private void updateDays() {
        String selected=cbDay.getSelectionModel().getSelectedItem();

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
        cbDay.getSelectionModel().select(cbDay.getItems().contains(selected)? selected:"");
    }

    /**
     * Gets the data from the form and stores it in the array in the below-mentioned format.
     * @return data = String[] in the following format:
     *              name, DOB, gender, email, userName, password.
     */
    public String[] getData() {
        String name;
        String DOB;
        String gender;
        String email;
        String userName;
        String password;

        boolean isValid;
        errorText.setText("");
        try {
            isValid = validate();
        }catch (MissingDataException e) {
            errorText.setText(e.getMessage());
            isValid = false;
        }
        if(isValid) {
            name = txtName.getText();
            email = txtEmail.getText();
            userName = txtUserName.getText();
            password = txtPassword.getText();
            gender = cbGender.getSelectionModel().getSelectedItem().equals("Other")? txtOtherGender.getText() : cbGender.getSelectionModel().getSelectedItem();

            String day;
            String month;
            String year;
            if (cbDay.getSelectionModel().getSelectedIndex() < 10) {
                day = "0" + cbDay.getSelectionModel().getSelectedItem();
            } else {
                day = cbDay.getSelectionModel().getSelectedItem();
            }

            if (cbMonth.getSelectionModel().getSelectedIndex() < 10) {
                month = "0" + (cbMonth.getSelectionModel().getSelectedIndex() + 1);
            } else {
                month = "" + (cbMonth.getSelectionModel().getSelectedIndex() + 1);
            }

            year = cbYear.getSelectionModel().getSelectedItem();

            DOB = year+"-"+month+"-"+day;
        } else {
            System.out.println("NewUserPane.getData: not Valid");
            return null;
        }
        return new String[] {name,DOB,gender,email,userName,password};
    }

    /**
     * Validates the job based on all the required fields.
     * @return boolean true if all cases pass. Else, throws MissingDataException.
     * @throws MissingDataException if any data in the form is empty or invalid.
     */
    private boolean validate() throws MissingDataException {
        boolean valid = true;
        if (txtName.getText().equals ("")) {
            throw new MissingDataException("No Name");
        }
        if (cbMonth.getSelectionModel().getSelectedItem() == null) {
            throw new MissingDataException("No Month");
        }
        if (cbDay.getSelectionModel().getSelectedItem() == null) {
            throw new MissingDataException("No Day");
        }
        if (cbYear.getSelectionModel().getSelectedItem() == null) {
            throw new MissingDataException("No Year");
        }
        if (cbGender.getSelectionModel().getSelectedItem() == null) {
            throw new MissingDataException("No Gender");
        }
        if (txtEmail.getText().equals ("")) {
            throw new MissingDataException("No Email");
        }
        if (txtUserName.getText().equals ("")) {
            throw new MissingDataException("No Username");
        }
        if (txtPassword.getText().equals ("")) {
            throw new MissingDataException("No Password");
        }
        if (txtConfirmPassword.getText().equals ("")) {
            throw new MissingDataException("No Password Confirmation");
        }


        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            throw new MissingDataException("Passwords do not match!");
        }
        if (cbGender.getSelectionModel().getSelectedItem()!=null && cbGender.getSelectionModel().getSelectedItem().equals("Other") && txtOtherGender.getText().equals("")) {
            throw new MissingDataException("No Other gender input");
        }
        if (!txtEmail.getText().equals("") && !isValidEmail(txtEmail.getText())) {
            throw new MissingDataException("Not a valid email");
        }
        if (usernameAlreadyExists(txtUserName.getText())) {
            throw new MissingDataException("Username "+txtUserName.getText()+" already Taken");
        }
        if(!isValidUsername(txtUserName.getText())) {
            throw new MissingDataException("invalid Username");
        }

        return valid;
    }

    private boolean isValidEmail(String s) {
        //Regular expression from www.baeldung.com "Email Validation in Java" - by baeldung
        return Pattern.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", s);
    }

    /**
     * validates a username, where there must be only alpha-numeric characters and the underscore character '_'
     * Must be at least 3 characters long, but no more than 30.
     * @param s the username to validate
     * @return true if matching the regex:"[a-zA-Z0-9_]{3,30}"
     */
    private boolean isValidUsername(String s) {
        return Pattern.matches("[a-zA-Z0-9_]{3,30}",s);
    }

    public boolean usernameAlreadyExists(String s) {
        ArrayList<User> tmpUsers = driver.readUsers();
        if (!tmpUsers.isEmpty()) {
            for (User u:tmpUsers) {
                if(s.equals(u.getUserName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
