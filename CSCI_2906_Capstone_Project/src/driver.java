package com.example.oddjob;
/**
 * @author: Dallen Corry
 * @version: 1.0
 * @since: 2022/Oct/06
 * @created: 2022/Oct/06
 * Class: driver
 * */
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class driver extends Application {

    User u;

    @Override
    public void start(Stage stage) throws Exception {
        //Create Objects
        BorderPane main = new BorderPane();
        Scene scene = new Scene(main);
        ScrollPane center = new ScrollPane();
        VBox homePane = new VBox();
        VBox earningsPane = new VBox();
        VBox profilePane = new VBox();
        VBox morePane = new VBox();
        HBox topBar = new HBox();
        VBox bottomBar = new VBox();
        HBox buttons = new HBox();
        Button btnHome = new Button("Home");
        Button btnEarnings = new Button("Earn");
        Button btnProfile = new Button("Prof");
        Button btnMore = new Button("More");

        ArrayList<JobPane> jobsArray = new ArrayList<JobPane>();

        //Set Object
        //top
        topBar.getChildren().add(new Text("OddJob"));
        //center
        createNewUser(LocalDate.parse("2000-01-01"));
        jobsArray.add(new JobPane(new Job(u,"Title",LocalDate.now(), 10.0, false, "")));
        jobsArray.add(new JobPane(new Job(u,"Title2",LocalDate.now(), 10.0, false, "")));
        jobsArray.add(new JobPane(new Job(u,"Title3",LocalDate.now(), 10.0, false, "")));
        homePane.getChildren().addAll(jobsArray);
        center.setContent(homePane);
        //Bottom
        buttons.getChildren().addAll(btnHome, btnEarnings, btnProfile, btnMore);
        bottomBar.getChildren().addAll(new Button ("+"), buttons);
        //main
        main.setTop(topBar);
        main.setCenter(center);
        main.setBottom(bottomBar);

        //Actions


        //Display
        center.setPrefHeight(300);
        stage.setTitle("OddJob");
        stage.setScene(scene);
        stage.show();
    }

    public void createNewUser(LocalDate date) {
        u = new User("Bob", "bob1234","12345", date);
    }
    /**
     * Entry to run the program
     */
    public static void main(String[] args) {launch(args);}
    //TODO:
    // - Create JobPane with image and text
    // - Create JobInfoPane with image and text and buttons
    // - Create UserProfilePane with image, stars, text, and buttons
    // - Create CSS page and link it
    // - Create other pages
    //      - Landing
    //          2 buttons(work or post?), and hyperlink to sign in
    //      - Create user
    //          form with text areas
    //      - Login
    //          2 text areas
    //      - Home
    //      - Earnings
    //          top part w/ graph, then bottom part a tab pane. one for worked, one for posted. Text below with # of jobs total worked/posted
    //      - Profile (custom class??)
    //      - More/Settings
    // - images for buttons
    // - create logo
    // - floating button/button in the corner +
    // -
    // - Make encoding and decoding
    // - Store users and jobs somewhere


//        VBox scrollData = new VBox();
//        Button btNew =new Button("Create New");
//        Button btView = new Button("View Created User");
//        Text txtUser = new Text("");
//
//        //Set Objects
//        scrollData.getChildren().addAll(btNew, btView, txtUser);
//        ScrollPane pane = new ScrollPane(scrollData);
//        Scene scene = new Scene(pane);
//
//        //Actions
//        btNew.setOnAction(e -> {
//            LocalDate date = LocalDate.parse("2000-01-10");
//            createNewUser(date);
//
//        });
//
//        btView.setOnAction(e-> {
//            if (u == null) {
//                txtUser.setText("No user Created");
//            } else {
//                txtUser.setText("ID: " + u.userID + "\n" + u.toString());
//            }
//        });
}
