package com.example.oddjob;
/**
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Oct/06
 * @created: 2022/Oct/06
 * Class: User
 * */
import javafx.scene.image.Image;

import java.time.Period;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class User {
    int userID;
    String name;
    private String userName;
    private String password;
    private LocalDate DOB;
    private String gender;
    private String email;
    ArrayList<String> skills;
    double employerRating;
    double workerRating;
    Image profilePicture;
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
        userID = getUniqueID();
    }
    public User(String name, String userName, String password, LocalDate DOB) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.DOB = DOB;
        userID = getUniqueID();
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

    public Image getProfilePicture() {
        return profilePicture;
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

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
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

    //TODO: Create unique IDs
    public static int getUniqueID() {
        Random r = new Random();
        return r.nextInt(1000);
    }
}
