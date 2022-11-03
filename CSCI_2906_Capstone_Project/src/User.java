package sample;
/**
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Oct/06
 * @created: 2022/Oct/06
 * Class: User
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URL;
import java.time.Period;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class User {
    private int userID;
    private String name;
    private String userName;
    private String password;
    private LocalDate DOB;
    private String gender;
    private String email;
    private ArrayList<String> skills;
    private double employerRating;
    private double workerRating;
    private String profilePicturePath;
    private ArrayList<Job> jobsWorked;
    private ArrayList<Job> jobsCreated;
    private double earnings;
    private double earningGoal;

    private final URL URL_TO_USER_DATA = getClass().getResource("users.txt");
    private final static char ENCODED_DATA_SEPARATOR = '<';


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

    //TODO: Create unique IDs
    private static int generateUniqueID() {
        Random r = new Random();
        return r.nextInt(1000);
    }

    public boolean addToDatabase() {
        try {
//            System.out.println(URL_TO_USER_DATA);
//            System.out.println(URL_TO_USER_DATA.getPath());
            RandomAccessFile raf = new RandomAccessFile("users.dat", "rw");//URL_TO_USER_DATA.getPath()
//            raf.seek(raf.length());//put it at the end of the file
            raf.seek(raf.length());
            raf.writeUTF(encode()+"\n");
//            System.out.println(raf.readInt());
            raf.close();

//            PrintWriter userWrite = new PrintWriter(f);

//            String s = encode();
////            System.out.println(s);
//            userWrite.write(s);
////            userWrite.println("HELLO!");
////            userWrite.print("Text 2");
////            userWrite.println("HELLO!");
//            userWrite.close();
//
//            Scanner userScan = new Scanner(f);
//            System.out.println("First: "+userScan.nextLine());
//            System.out.println("Second"+userScan.nextLine());
//
//            userScan.close();
        } catch(FileNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Experienced unhandled error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String[] getAllData() {
        ArrayList<String> arrLst = new ArrayList<>();

        arrLst.add(userID+"");
        arrLst.add(name);
        arrLst.add(userName);
        arrLst.add(password);
        arrLst.add(DOB.toString());
        arrLst.add(gender);
        arrLst.add(email);

        arrLst.add(employerRating+"");
        arrLst.add(workerRating+"");
        arrLst.add(earnings+"");
        arrLst.add(earningGoal+"");

        arrLst.add(profilePicturePath);

        if(skills != null && !skills.isEmpty()) {
            arrLst.add("SKILLS");
            for (String s : skills) {
                arrLst.add(s);
            }
        }
        if(jobsWorked != null && !jobsWorked.isEmpty()) {
            arrLst.add("JOBS_WORKED");
            for (Job j : jobsWorked) {
                arrLst.add("JobID:" + j.getJobImage());
            }
        }
        if(jobsCreated != null && !jobsCreated.isEmpty()) {
            arrLst.add("JOBS_CREATED");
            for (Job j : jobsCreated) {
                arrLst.add("JobID:" + j.getJobImage());
            }
        }

        String[] arr = new String[arrLst.size()];
        for (int i=0; i<arr.length; i++) {
            arr[i] = arrLst.get(i);
        }
        return arr;
    }

    public String encode() {
        return encode(this);
    }

    public static String encode(User u) {

        StringBuilder s = new StringBuilder();


        String[] arr = u.getAllData();
        for (String str: arr) {
            s.append(str).append(ENCODED_DATA_SEPARATOR);
        }
        return s.toString();
    }

    public static User decode(String string) {
        return null;
    }
}
