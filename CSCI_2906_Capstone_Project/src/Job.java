package com.example.oddjob;
/**
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Oct/06
 * @created: 2022/Oct/06
 * Class: Job
 * */
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Random;

public class Job {
    int jobID;
    User creator;
    String title;
    LocalDate datePosted;
    LocalDate dateOfJob;
    double pay;
    boolean payIsHourly;
    double jobTime;
    String description;
    String location;
    int numWorkersWanted;
    String category;
    boolean isActive;
    ArrayList<User> workersApplied;
    Image jobImage;

    public Job(User creator, String title, LocalDate dateOfJob, double pay, boolean payIsHourly, String location) {
        this.creator = creator;
        this.title = title;
        this.dateOfJob = dateOfJob;
        this.pay = pay;
        this.payIsHourly = payIsHourly;
        this.location = location;
        jobID = getUniqueID();
    }

    public Job(User creator, String title, LocalDate dateOfJob, double pay, boolean payIsHourly, double jobTime, String description, String location, int numWorkersWanted, String category) {
        this.creator = creator;
        this.title = title;
        this.dateOfJob = dateOfJob;
        this.pay = pay;
        this.payIsHourly = payIsHourly;
        this.jobTime = jobTime;
        this.description = description;
        this.location = location;
        this.numWorkersWanted = numWorkersWanted;
        this.category = category;
        jobID = getUniqueID();
    }

    public int getJobID() {
        return jobID;
    }

    public User getCreator() {
        return creator;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public LocalDate getDateOfJob() {
        return dateOfJob;
    }

    public double getPay() {
        return pay;
    }

    public boolean isPayIsHourly() {
        return payIsHourly;
    }

    public double getJobTime() {
        return jobTime;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getNumWorkersWanted() {
        return numWorkersWanted;
    }

    public String getCategory() {
        return category;
    }

    public boolean isActive() {
        return isActive;
    }

    public ArrayList<User> getWorkersApplied() {
        return workersApplied;
    }

    public Image getJobImage() {
        return jobImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateOfJob(LocalDate dateOfJob) {
        this.dateOfJob = dateOfJob;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public void setPayIsHourly(boolean payIsHourly) {
        this.payIsHourly = payIsHourly;
    }

    public void setJobTime(double jobTime) {
        this.jobTime = jobTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNumWorkersWanted(int numWorkersWanted) {
        this.numWorkersWanted = numWorkersWanted;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setJobImage(Image jobImage) {
        this.jobImage = jobImage;
    }

    @Override
    public String toString() {
        if(payIsHourly) {
            return title + ": " + jobID + "\nCreated by: " + creator + "\n$" + pay+"/hr, "+jobTime;
        } else {
            return title + ": " + jobID + "\nCreated by: " + creator + "\n$" + pay;
        }
    }

    static String encode(Job job) {
        return null;
    }

    static Job decode(String string) {
        return null;
    }

    public static int getUniqueID() {
        Random r = new Random();
        return r.nextInt(1000);
    }
}
