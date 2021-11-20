package com.example.richiefx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyFarewell extends Stage {
    private Label congrats1, congrats2, labReview, labAnswers;
    private Text name, results, percent;
    private Button exit;
    private ImageView soviet;
    private ComboBox commAns, testAns;
    private int totalQues;
    private File allAnswers = new File("data/result.txt");
    private File allComrades = new File("data/comradesTest.txt");
    private File comradeDetails = new File("data/comradeDetails.txt");
    private File questionAns = new File("data/questions.txt");


    public MyFarewell() {
        //Results form Labels, Images and Buttons Layouts
        congrats1 = new Label("CONGRATULATIONS COMRADE!");
        congrats1.setLayoutX(295);
        congrats1.setLayoutY(111);
        congrats1.setFont(new Font("Times New Roman", 18));

        congrats2 = new Label("YOU HAVE COMPLETED THE TEST!");
        congrats2.setLayoutX(305);
        congrats2.setLayoutY(135);
        congrats2.setFont(new Font("Times New Roman", 16));

        soviet = new ImageView();
        soviet.setLayoutX(400);
        soviet.setLayoutY(24);
        soviet.setFitHeight(59);
        soviet.setFitWidth(50);

        //Communist Logo Image Extraction
        File communism = new File("data/hammersickle.png");
        javafx.scene.image.Image comm = new Image(communism.toURI().toString());
        soviet.setImage(comm);

        labReview = new Label("REVIEW YOUR ANSWERS: ");
        labReview.setLayoutX(202);
        labReview.setLayoutY(283);
        labReview.setFont(new Font("Times New Roman", 14));

        labAnswers = new Label("ANSWER SHEET: ");
        labAnswers.setLayoutX(265);
        labAnswers.setLayoutY(319);
        labAnswers.setFont(new Font("Times New Roman", 14));

        exit = new Button("EXIT");
        exit.setLayoutX(397);
        exit.setLayoutY(469);
        exit.setOnAction(event -> {
            this.close();
        });

        name = new Text("");
        name.setLayoutX(380);
        name.setLayoutY(194);
        name.setFont(new Font("Times New Roman", 15));
        name.setStyle("-fx-font-weight:bold;");

        results = new Text("");
        results.setLayoutX(380);
        results.setLayoutY(218);
        results.setFont(new Font("Times New Roman", 15));
        results.setStyle("-fx-font-weight:bold;");

        percent = new Text("");
        percent.setLayoutX(380);
        percent.setLayoutY(242);
        percent.setFont(new Font("Times New Roman", 15));
        percent.setStyle("-fx-font-weight:bold;");

        commAns = new ComboBox();
        commAns.setPrefWidth(131);
        commAns.setLayoutX(383);
        commAns.setLayoutY(278);

        testAns = new ComboBox();
        testAns.setPrefWidth(131);
        testAns.setLayoutX(383);
        testAns.setLayoutY(315);

        //Calling out Objects into Results form
        Group farewell = new Group(congrats1, congrats2, labAnswers, labReview, soviet, exit, commAns, testAns,name,results,percent);
        Scene winFarewell = new Scene(farewell, 800, 600);
        this.setTitle("RESULTS!");
        this.setResizable(false);
        this.setScene(winFarewell);
    }

    public void greetFarewell(){
        totalQues = totalQuestions();
        comradeAns();
        correctAnswers();
        this.show();
    }
    public void setName(String n){
        name.setText("COMRADE NAME: "+ n);
    }
    //Get Total Correct Answers from Comrade Answers from comradeTest.txt (Delimiter Setting)
    public int totalCorrectAns(int c) {
        Scanner sfile;
        String aLine;
        Scanner sline;
        int correctAns = 0;
        try {
            sfile = new Scanner(allComrades);

            while (sfile.hasNextLine()) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                while (sline.hasNextLine()) {
                    sline.next();
                    correctAns = Integer.parseInt(sline.next()); sline.next();
                }
                sline.close();
            }
            sfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("FILE " + allComrades + " IS DEMINISHED!"); }
        return correctAns;
    }


    //Get Total Questions from results.txt
    public int totalQuestions() {
        Scanner sfile;
        int totalQuestions = 0;
        try {
            sfile = new Scanner(questionAns);
            totalQuestions = Integer.parseInt(sfile.nextLine());
            sfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE" + questionAns + " IS DIMINISHED!");
        }
        return totalQuestions;
    }
    //Get Correct Answers from Comrades (Delimiter Setting) from results.txt
    public void correctAnswers() {
        Scanner sfile;
        String aLine;
        Scanner sline;
        char quesAns;
        int quesNo = 1;
        int type;
        totalQues = 25;

        try {
            sfile = new Scanner(questionAns);
            sfile.nextLine();
            for (int k = 1; k <= totalQues; k++) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                while (sline.hasNextLine()) {
                    type = Integer.parseInt(sline.next()); quesAns = sline.next().charAt(0);
                    if (type == 2)
                        sline.next();
                    sline.next();
                    sline.next();
                    sline.next();
                    sline.next();
                    sline.next();
                    String s = quesNo + ". " + quesAns;
                    testAns.getItems().add(s);
                    quesNo++;
                }
                sline.close();
            }
            sfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("FILE " + testAns + " DIMINISHED"); }
    }


    //Get Comrade Answers, configurate Results and Percentage from results.txt (Delimiter Setting)
    public void comradeAns() {
        int choice;
        choice = comradePosition();

        int correctTotal;
        int score;
        correctTotal = totalCorrectAns(choice);
        score = correctTotal*100/totalQues;
        results.setText("RESULTS: " + correctTotal + " Out Of " + totalQues);
        percent.setText( "PERCENT: " + score + "%");

        Scanner sfile;
        int totquestions = totalQues;
        String comradeAns;
        String aLine;
        Scanner sline;

        try {
            sfile = new Scanner(allAnswers);


            while (sfile.hasNextLine()){
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                commAns.getItems().clear();
                sline.useDelimiter(":");
                for (int k = 1; k <= totquestions; k++) {
                    comradeAns = sline.next();
                    String s = k + ". " + comradeAns;
                    commAns.getItems().add(s);
                }
                sline.close();
            }
            sfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("FILE " + allAnswers + " DIMINISHED!"); }
    }


    //Get comrade position from text file (comradeTest.txt)
    public int comradePosition() {
        Scanner sfile;
        String aLine;
        Scanner sline;
        int comradePos = 1;
        String comradeName = "";
        try {
            sfile = new Scanner(allComrades);
            while (sfile.hasNextLine()) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                while (sline.hasNextLine()) {
                    comradeName = sline.next();
                    sline.next();
                    sline.next();
                    if ( (comradeName.equals(name.getText())) )  {
                        break;
                    }
                    comradePos++;
                }
                sline.close();
                if ( (comradeName.equals(name.getText())) ) {
                    break;
                } }
            sfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("FILE" + allComrades + " DIMINISHED!"); }
        return comradePos;
    }
}