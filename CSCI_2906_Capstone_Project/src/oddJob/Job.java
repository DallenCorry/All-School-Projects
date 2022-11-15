package oddJob;
/*
 * @author: Dallen Corry
 * @version: 1.4
 * @since: 2022/Nov/15
 * @created: 2022/Oct/06
 * Class: Job
 * */
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Random;

import static oddJob.Defaults.*;

public class Job implements Serializable {
    private int jobID;
    private User creator;
    private String title;
    private LocalDate datePosted;
    private LocalDate dateOfJob;
    private double pay;
    private boolean payIsHourly;
    private double jobTime;
    private String description;
    private String location;
    private int numWorkersWanted;
    private String category;
    private boolean isActive;
    private ArrayList<User> workersApplied;
    private String jobImagePath = "";

    public Job() {
        this(new User("Admin", "", "",null), "none",null,0.0,false,null);
    }
    public Job(User creator, String title, LocalDate dateOfJob, double pay, boolean payIsHourly, String location) {
        this.creator = creator;
        this.title = title;
        this.dateOfJob = dateOfJob;
        this.pay = pay;
        this.payIsHourly = payIsHourly;
        this.location = location;

        jobID = getUniqueID();
        jobImagePath = IMAGE_NOT_FOUND;
        datePosted = LocalDate.now();
        isActive = true;
    }

    public Job(User creator, String title, LocalDate dateOfJob, double pay, boolean payIsHourly, double jobTime, String description, String location, int numWorkersWanted, String category) {
        this(creator,title,dateOfJob,pay,payIsHourly,location);
        this.jobTime = jobTime;
        this.description = description;
        this.numWorkersWanted = numWorkersWanted;
        this.category = category;

        jobID = getUniqueID();
        jobImagePath = IMAGE_NOT_FOUND;
        datePosted = LocalDate.now();
        isActive = true;
    }

    /**
     *
     * @param data = String[] in the following format:
     *             userID, title, dateOfJob, pay, payIsHourly, location, jobTime, description,
     *             numWorkersWanted, category, jobImagePath
     */
    public Job(String[] data){
        creator = getUserFromID(Integer.parseInt(data[0]));//Somehow get the user with ID
        title = data[1];
        dateOfJob = LocalDate.parse(data[2]);
        pay = Double.parseDouble(data[3]);
        payIsHourly = Boolean.parseBoolean(data[4]);
        location = data[5];
        jobTime = Double.parseDouble(data[6]);
        description = data[7];
        numWorkersWanted = Integer.parseInt(data[8]);
        category = data[9];
        jobImagePath = data[10];

        datePosted = LocalDate.now();
        jobID = getUniqueID();
        isActive = true;
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

    public String getJobImagePath() {
        return jobImagePath;
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

    public void setActive(boolean isActive) {
        this.isActive=isActive;
    }

    public void setJobImagePath(String jobImagePath) {
        this.jobImagePath = jobImagePath;
    }

    @Override
    public String toString() {
        if(payIsHourly) {
            return title + ": " + "\nCreated by: " + creator.getName() + "\n$" + pay+"/hr,  ~"+jobTime+" hours"+ "\nLocation: "+location;
        } else {
            return title + ": " + "\nCreated by: " + creator.getName() + "\n$" + pay + "\nLocation: "+location;
        }
    }

    /**
     * Searches the jobs stored and returns a random ID that isn't in use by any Job.
     * @return int a unique 3 digit ID.
     */
    public static int getUniqueID() {
        int tempID = new Random().nextInt(1000);
        boolean isUnique = false;
        ArrayList<Job> tmpUsers = driver.readJobs();
        if (!tmpUsers.isEmpty()) {
            while (!isUnique) {
                for (Job j : tmpUsers) {
                    if (j.getJobID() == tempID) {
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
