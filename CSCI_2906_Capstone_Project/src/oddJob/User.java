package oddJob;
/*
 * @author: Dallen Corry
 * @version: 1.1
 * @since: 2022/Nov/14
 * @created: 2022/Oct/06
 * Class: User
 * */

import java.io.*;
import java.time.Period;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static oddJob.Defaults.*;


public class User implements Serializable {
    private final int userID;
    private String name;
    private final String userName;
    private final String password;
    private final LocalDate DOB;
    private String gender;
    private String email;
    private ArrayList<String> skills;
    private double employerRating;
    private double workerRating;
    private String profilePicturePath = NO_PROFILE_PIC;
    private ArrayList<Job> jobsWorked;
    private ArrayList<Job> jobsCreated;
    private double earnings;
    private double earningGoal;

    //Constructors
    public User() {
        name = null;
        userName = null;
        password=null;
        DOB=null;
        userID = generateUniqueID();
    }
    public User(String name, String userName, String password, LocalDate DOB) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.DOB = DOB;
        userID = generateUniqueID();
    }
    public User(String name, String userName,String password, LocalDate DOB, String gender, String email) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.DOB = DOB;
        this.gender = gender;
        this.email = email;
        userID = generateUniqueID();
    }

    /**
     * String[] data in the following format:
     *      name, DOB, gender, email, userName, password.
     *
     */
    public User(String[] data) {
        name = data[0];
        DOB = LocalDate.parse(data[1]);
        gender = data[2];
        email = data[3];
        userName = data[4];
        password = data[5];

        userID = generateUniqueID();

    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public double getEmployerRating() {
        return employerRating;
    }

    public double getWorkerRating() {
        return workerRating;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public ArrayList<Job> getJobsWorked() {
        return jobsWorked;
    }

    public ArrayList<Job> getJobsCreated() {
        return jobsCreated;
    }

    public double getEarnings() {
        return earnings;
    }

    public double getEarningGoal() {
        return earningGoal;
    }

    public int getAge() {
        if (DOB == null) {
            return -1;
        }
        return Period.between(DOB, LocalDate.now()).getYears();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public void setEarningGoal(double earningGoal) {
        this.earningGoal = earningGoal;
    }

    public void updateRatings() {
        employerRating = avgRating(this, "employer");
        workerRating = avgRating(this, "worker");
    }

    public void addSkill(String newSkill) {
        skills.add(newSkill);
    }

    public void addJobWorked(Job newJob) {
        jobsWorked.add(newJob);
    }

    public void addJobCreated(Job newJob) {
        jobsCreated.add(newJob);
    }

    @Override
    public String toString() {
        return "Name: "+name+"\nAge: "+getAge()+"\nGender: "+gender;
    }

    //TODO: calculate an average rating based on reviews.
    public static double avgRating(User user, String type) {
        double avg;
        if (type.equals("employer")) {
            avg = user.getJobsCreated().size()/5;
        } else {
            avg = user.getJobsWorked().size()/5;
        }
        return avg;
    }

    private static int generateUniqueID() {
        int tempID = new Random().nextInt(1000);
        boolean isUnique = false;
        ArrayList<User> tmpUsers = driver.readUsers();
        if (!tmpUsers.isEmpty()) {
            while (!isUnique) {
                for (User u : tmpUsers) {
                    if (u.getUserID() == tempID) {
                        isUnique = false;
                        tempID = new Random().nextInt(1000);
                        break;
                    }
                    isUnique = true;
                }
            }
        }
        return tempID;
    }
}
