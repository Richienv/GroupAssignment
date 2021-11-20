package com.example.richiefx;

import com.sun.media.jfxmedia.MediaPlayer;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


public class ContestantTest extends Application {
    private File myf = new File("Questions.txt");
    private File myf2 = new File("Result.txt");
    private int correctAns = 0;
    private int totQues = 0;
    private int activeQ = 1;
    private Label labName, labQuesNo, labQues, labComradeName;
    private Text labA, labB, labC, labD;
    private RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private Button btnPrev, btnNext, btnComplete;
    private ToggleGroup grpChoices;
    private ImageView imgQues, imgAnsA, imgAnsB, imgAnsC, imgAnsD, imgComrade;
    private Pane mainPane;
    private Pane paneC;
    private Scene mainScene;
    private LogInPage winLogIn;
    private MyFarewell winFarewell;
    private LinkedList<Question> quesList = new LinkedList<Question>();
    private static final Integer STARTTIME = 300;
    private Timeline timeline;
    private Integer timeSeconds = STARTTIME;
    private Label countdown;
    private MediaPlayer joker;

    public void start(Stage mainStage) {
        mainStage.setTitle("ITAJORLALIPAL UNION TEST!");
        mainStage.setResizable(false);
        //Label Positioning and Layout
        labName = new Label("Comrade Name: ");
        labName.setLayoutX(72);
        labName.setLayoutY(36);
        labName.setFont(new Font("Times New Roman", 13));
        labComradeName = new Label("");
        labComradeName.setLayoutX(72);
        labComradeName.setLayoutY(53);
        labComradeName.setFont(new Font("Times New Roman", 16));
        imgComrade = new ImageView();
        imgComrade.setLayoutX(20);
        imgComrade.setLayoutY(30);
        imgComrade.setFitHeight(49);
        imgComrade.setFitWidth(41);
        imgComrade.setImage(null);
        labQuesNo = new Label("");
        labQuesNo.setLayoutX(336);
        labQuesNo.setLayoutY(80);
        labQuesNo.setFont(new Font("Times New Roman", 13));
        labQues = new Label("");
        labQues.setLayoutX(290);
        labQues.setLayoutY(138);
        labQues.setFont(new Font("Times New Roman", 20));
        imgQues = new ImageView();
        imgQues.setLayoutX(325);
        imgQues.setLayoutY(100);
        imgQues.setFitHeight(149);
        imgQues.setFitWidth(179);
        countdown = new Label("");
        countdown.setLayoutX(634);
        countdown.setLayoutY(53);
        countdown.setFont(new Font("Times New Roman", 13));
        labA = new Text("A");
        radChoice1 = new RadioButton("");
        imgAnsA = new ImageView();
        imgAnsA.setFitHeight(100);
        imgAnsA.setFitWidth(150);
        labB = new Text("B");
        radChoice2 = new RadioButton("");
        imgAnsB = new ImageView();
        imgAnsB.setFitHeight(100);
        imgAnsB.setFitWidth(150);
        labC = new Text("C");
        radChoice3 = new RadioButton("");
        imgAnsC = new ImageView();
        imgAnsC.setFitHeight(100);
        imgAnsC.setFitWidth(150);
        labD = new Text("D");
        radChoice4 = new RadioButton("");
        imgAnsD = new ImageView();
        imgAnsD.setFitHeight(100);
        imgAnsD.setFitWidth(150);

        grpChoices = new ToggleGroup();
        radChoice1.setToggleGroup(grpChoices);
        radChoice2.setToggleGroup(grpChoices);
        radChoice3.setToggleGroup(grpChoices);
        radChoice4.setToggleGroup(grpChoices);
//Inserting Objects into PaneC (Test Window)
        paneC = new Pane();
        paneC.setLayoutX(25);
        paneC.setLayoutY(75);
        paneC.getChildren().add(imgQues);
        paneC.getChildren().add(labA);
        paneC.getChildren().add(radChoice1);
        paneC.getChildren().add(imgAnsA);
        paneC.getChildren().add(labB);
        paneC.getChildren().add(radChoice2);
        paneC.getChildren().add(imgAnsB);
        paneC.getChildren().add(labC);
        paneC.getChildren().add(radChoice3);
        paneC.getChildren().add(imgAnsC);
        paneC.getChildren().add(labD);
        paneC.getChildren().add(radChoice4);
        paneC.getChildren().add(imgAnsD);
//Button prev,Next and Complete Layout
        btnPrev = new Button(" Prev ");
        btnPrev.setLayoutX(200);
        btnPrev.setLayoutY(540);
        btnPrev.setDisable(true);
        btnNext = new Button(" Next ");
        btnNext.setLayoutX(285);
        btnNext.setLayoutY(540);
        btnComplete = new Button("Complete!");
        btnComplete.setLayoutX(650);
        btnComplete.setLayoutY(540);
//Calling out Questions from "questions.txt"
        readQuestion();
//Radio choices configuration
        radChoice1.setOnAction(e -> {
            quesList.get(activeQ - 1).setSelected(0, true);
            quesList.get(activeQ - 1).setSelected(1, false);
            quesList.get(activeQ - 1).setSelected(2, false);
            quesList.get(activeQ - 1).setSelected(3, false);

        });
        radChoice2.setOnAction(e -> {
            quesList.get(activeQ - 1).setSelected(0, false);
            quesList.get(activeQ - 1).setSelected(1, true);
            quesList.get(activeQ - 1).setSelected(2, false);
            quesList.get(activeQ - 1).setSelected(3, false);
        });
        radChoice3.setOnAction(e -> {
            quesList.get(activeQ - 1).setSelected(0, false);
            quesList.get(activeQ - 1).setSelected(1, false);
            quesList.get(activeQ - 1).setSelected(2, true);
            quesList.get(activeQ - 1).setSelected(3, false);
        });
        radChoice4.setOnAction(e -> {
            quesList.get(activeQ - 1).setSelected(0, false);
            quesList.get(activeQ - 1).setSelected(1, false);
            quesList.get(activeQ - 1).setSelected(2, false);
            quesList.get(activeQ - 1).setSelected(3, true);
        });
//"Prev" and "Next" button set disability
        if (totQues == 1)
            btnNext.setDisable(true);
        btnNext.setOnAction(e -> {
            activeQ++;
            btnPrev.setDisable(false);
            if (activeQ == totQues)
                btnNext.setDisable(true);
            reloadQues();
        });
        btnPrev.setOnAction(e -> {
            activeQ--;
            btnNext.setDisable(false);
            if (activeQ == 1)
                btnPrev.setDisable(true);
            reloadQues();
        });
        btnComplete.setOnAction(e -> {
            checkComradeAns();
            comradeAns();
            mainStage.hide();
            winFarewell = new MyFarewell();
            winFarewell.setName(labComradeName.getText());
            winFarewell.greetFarewell();
            //Answer Saved
            try {

                PrintWriter pw = new PrintWriter(new
                        FileWriter("Results.txt", true));
                pw.println("");
                pw.close();
            } catch (IOException f) {
                System.out.println("Error");
                f.printStackTrace();
            }
            try {
                PrintWriter pw = new PrintWriter(new
                        FileWriter("ComradesTest.txt", true));
                pw.println("");
                pw.close();
            } catch (IOException f) {
                System.out.println("Error");
                f.printStackTrace();
            }
        });
        //Calling out objects (Labels, Buttons, Images, Timer ) into MainPane
        mainPane = new Pane();
        mainPane.getChildren().add(labName);
        mainPane.getChildren().add(labComradeName);
        mainPane.getChildren().add(labQues);
        mainPane.getChildren().add(labQuesNo);
        mainPane.getChildren().add(countdown);
        mainPane.getChildren().add(paneC);
        mainPane.getChildren().add(btnNext);
        mainPane.getChildren().add(btnPrev);
        mainPane.getChildren().add(btnComplete);
        mainPane.getChildren().add(imgComrade);
        //Calling out all pane objects
        mainScene = new Scene(mainPane, 800, 600);
        mainStage.setScene(mainScene);
        //Calling out Question Types
        reloadQues();
        //Page Navigation
        winLogIn = new LogInPage();
        winLogIn.show();
        winLogIn.setOnHiding(event -> {
            String n = winLogIn.getName();

            labComradeName.setText(n);
            mainStage.show();
            timerStart(mainStage);
            //Calling out Comrade Picture to Test Form
            if (n.equals("Regina")) {
                File communist = new File("data/Regina.jpeg");
                Image reG = new Image(communist.toURI().toString());
                imgComrade.setImage(reG);
            } else if (n.equals("Reuben")) {
                File communist = new File("data/Reuben.jpeg");
                Image reuB = new Image(communist.toURI().toString());
                imgComrade.setImage(reuB);
            } else if (n.equals("Sathish")) {
                File communist = new File("data/Sathish.jpeg");
                Image saT = new Image(communist.toURI().toString());
                imgComrade.setImage(saT);
            } else if (n.equals("Bryan")) {
                File communist = new File("data/Bryan.jpeg");
                Image brY = new Image(communist.toURI().toString());
                imgComrade.setImage(brY);
            } else if (n.equals("Seifeldeen")) {
                File communist = new File("data/Seifeldeen.jpeg");
                Image seF = new Image(communist.toURI().toString());
                imgComrade.setImage(seF);
            }
        });
        //Timer sound
        //String musicFile = "data/hahaha.mp3";
       // Media hahaha = new Media(new File(musicFile).toURI().toString());
       // joker = new MediaPlayer(hahaha);
    }

    //Question Types (Type A, Type B, Type C)
    public void reloadQues() {
        labQuesNo.setText("Question " + Integer.toString(activeQ) + " out of " + totQues);
        labQues.setText(quesList.get(activeQ - 1).getTheQues());
        imgQues.setImage(null);
        imgAnsA.setImage(null);
        imgAnsB.setImage(null);

        imgAnsC.setImage(null);
        imgAnsD.setImage(null);
        //Layout for Type A Questions
        if (quesList.get(activeQ - 1).getType() == 1) {
            radChoice1.setText(quesList.get(activeQ - 1).getChoice(0));
            radChoice2.setText(quesList.get(activeQ - 1).getChoice(1));
            radChoice3.setText(quesList.get(activeQ - 1).getChoice(2));
            radChoice4.setText(quesList.get(activeQ - 1).getChoice(3));
            labA.setLayoutX(300);
            labA.setLayoutY(150);
            radChoice1.setLayoutX(318);
            radChoice1.setLayoutY(136);
            labB.setLayoutX(300);
            labB.setLayoutY(180);
            radChoice2.setLayoutX(318);
            radChoice2.setLayoutY(166);
            labC.setLayoutX(300);
            labC.setLayoutY(210);
            radChoice3.setLayoutX(318);
            radChoice3.setLayoutY(196);
            labD.setLayoutX(300);
            labD.setLayoutY(240);
            radChoice4.setLayoutX(318);
            radChoice4.setLayoutY(226);
        }
        //Layout for Type B Questions
        if (quesList.get(activeQ - 1).getType() == 2) {
            radChoice1.setText(quesList.get(activeQ - 1).getChoice(0));
            radChoice2.setText(quesList.get(activeQ - 1).getChoice(1));
            radChoice3.setText(quesList.get(activeQ - 1).getChoice(2));
            radChoice4.setText(quesList.get(activeQ - 1).getChoice(3));
            //Extracting Type B Question Picture
            File pFile = new File("" +
                    quesList.get(activeQ - 1).getQuesPic());
            Image img = new Image(pFile.toURI().toString());
            imgQues.setImage(img);
            labA.setLayoutX(325);
            labA.setLayoutY(300);
            radChoice1.setLayoutX(340);
            radChoice1.setLayoutY(285);
            labB.setLayoutX(325);
            labB.setLayoutY(330);
            radChoice2.setLayoutX(340);
            radChoice2.setLayoutY(315);
            labC.setLayoutX(325);
            labC.setLayoutY(360);
            radChoice3.setLayoutX(340);
            radChoice3.setLayoutY(345);
            labD.setLayoutX(325);

            labD.setLayoutY(390);
            radChoice4.setLayoutX(340);
            radChoice4.setLayoutY(375);
        }
        //Layout for Type C Questions
        if (quesList.get(activeQ - 1).getType() == 3) {
            radChoice1.setText("");
            radChoice2.setText("");
            radChoice3.setText("");
            radChoice4.setText("");
            //Extracting Type C Answer Images
            File quespicA = new File("" +
                    quesList.get(activeQ - 1).getChoice(0));
            Image imgQuesA = new Image(quespicA.toURI().toString());
            File quespicB = new File("" +
                    quesList.get(activeQ - 1).getChoice(1));
            Image imgQuesB = new Image(quespicB.toURI().toString());
            File quespicC = new File("" +
                    quesList.get(activeQ - 1).getChoice(2));
            Image imgQuesC = new Image(quespicC.toURI().toString());
            File quespicD = new File("" +
                    quesList.get(activeQ - 1).getChoice(3));
            Image imgQuesD = new Image(quespicD.toURI().toString());
            imgAnsA.setImage(imgQuesA);
            imgAnsB.setImage(imgQuesB);
            imgAnsC.setImage(imgQuesC);
            imgAnsD.setImage(imgQuesD);
            labA.setLayoutX(207);
            labA.setLayoutY(150);
            radChoice1.setLayoutX(220);
            radChoice1.setLayoutY(136);
            imgAnsA.setLayoutX(250);
            imgAnsA.setLayoutY(160);
            labB.setLayoutX(507);
            labB.setLayoutY(150);
            radChoice2.setLayoutX(521);
            radChoice2.setLayoutY(136);
            imgAnsB.setLayoutX(551);
            imgAnsB.setLayoutY(160);
            labC.setLayoutX(207);
            labC.setLayoutY(305);
            radChoice3.setLayoutX(220);
            radChoice3.setLayoutY(290);
            imgAnsC.setLayoutX(250);
            imgAnsC.setLayoutY(300);

            labD.setLayoutX(507);
            labD.setLayoutY(305);
            radChoice4.setLayoutX(521);
            radChoice4.setLayoutY(290);
            imgAnsD.setLayoutX(551);
            imgAnsD.setLayoutY(300);
        }
        radChoice1.setSelected(quesList.get(activeQ - 1).getSelected(0));
        radChoice2.setSelected(quesList.get(activeQ - 1).getSelected(1));
        radChoice3.setSelected(quesList.get(activeQ - 1).getSelected(2));
        radChoice4.setSelected(quesList.get(activeQ - 1).getSelected(3));
    }

    //Read Questions from "questions.txt" (Delimiter Setting)
    public void readQuestion() {
        Scanner sfile;
        int type;
        char answer;
        String theQues;
        String choices[] = new String[4];
        String quesPic;
        Question ques;
        try {
            sfile = new Scanner(myf);
            String aLine = sfile.nextLine();
            Scanner sline = new Scanner(aLine);
            totQues = Integer.parseInt(sline.next());
            for (int k = 1; k <= totQues; k++) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                type = Integer.parseInt(sline.next());
                answer = sline.next().charAt(0);
                theQues = sline.next();
                quesPic = "";
                if (type == 2)
                    quesPic = sline.next();
                choices[0] = sline.next();
                choices[1] = sline.next();
                choices[2] = sline.next();
                choices[3] = sline.next();
                sline.close();
                ques = new Question(type, answer, theQues, choices, quesPic);
                quesList.add(ques);
            }
            sfile.close();


        } catch (FileNotFoundException e) {
            System.out.println("File to read " + myf + " not found!");
        }
    }

    //Saving Comrade Answers into "results.txt"
    public void comradeAns() {
        int i = 0;
        int t = 0;
        correctAns = i;
        t = totQues;
        boolean ans = false;
        for (int k = 0; k < totQues; k++) {
            if (quesList.get(k).getSelected(0)) {
                try {
                    PrintWriter pw = new PrintWriter(new
                            FileWriter("Result.txt", true));
                    pw.print("A:");
                    pw.close();
                    i++;
                } catch (IOException e) {
                    System.out.println("Comrade System Failure");
                    e.printStackTrace();
                }
            } else if (quesList.get(k).getSelected(1)) {
                try {
                    PrintWriter pw = new PrintWriter(new
                            FileWriter("Result.txt", true));
                    pw.print("B:");
                    pw.close();
                    i++;
                } catch (IOException e) {
                    System.out.println("Comrade System Failure");
                    e.printStackTrace();
                }
            } else if (quesList.get(k).getSelected(2)) {
                try {
                    PrintWriter pw = new PrintWriter(new
                            FileWriter("Result.txt", true));
                    pw.print("C:");
                    pw.close();

                    i++;
                } catch (IOException e) {
                    System.out.println("Comrade System Failure");
                    e.printStackTrace();
                }
            } else if (quesList.get(k).getSelected(3)) {
                try {
                    PrintWriter pw = new PrintWriter(new
                            FileWriter("Result.txt", true));
                    pw.print("D:");
                    pw.close();
                    i++;
                } catch (IOException e) {
                    System.out.println("Comrade System Failure");
                    e.printStackTrace();
                }
            } else {
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(myf2, true));
                    pw.print("E:");
                    pw.close();
                } catch (IOException e) {
                    System.out.println("Comrade System Failure");
                    e.printStackTrace();
                }
            }
        }
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Result.txt",
                    true));
            i++;
            pw.print("." + "\n");
            pw.close();
        } catch (IOException e) {
            System.out.println("Comrade System Failure");
            e.printStackTrace();
        }
    }
//Get Comrade correct answers (Out of 25 Questions)

    public void checkComradeAns() {
        int i = 0;
        correctAns = i;
        for (int k = 0; k < totQues; k++) {
            if (quesList.get(k).getAns() == 'A') {
                if (quesList.get(k).getSelected(0)) {
                    i++;
                }
            } else if (quesList.get(k).getAns() == 'B') {
                if (quesList.get(k).getSelected(1)) {
                    i++;
                }
            } else if (quesList.get(k).getAns() == 'C') {
                if (quesList.get(k).getSelected(2)) {
                    i++;
                }
            } else if (quesList.get(k).getAns() == 'D') {
                if (quesList.get(k).getSelected(3)) {
                    i++;
                }
            }
        }
        try {
            PrintWriter pw = new PrintWriter(new
                    FileWriter("ComradesTest.txt", true));
            String comradeCredentials = labComradeName.getText() + ":" + i + ":" +
                    "(" + i + " out of " + totQues + ")";
            pw.print(comradeCredentials);
            pw.close();
        } catch (IOException f) {
            System.out.println("Error");
            f.printStackTrace();
        }
    }

    //Timer
    public void timerStart(Stage mainStage) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1),
                countdown);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        timeSeconds = STARTTIME;
        countdown.setText("Time Left:" + formatTimer(timeSeconds).toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            timeSeconds--;
            countdown.setText("Time Left: " +
                    formatTimer(timeSeconds).toString());
            if (timeSeconds <= 30) {
                countdown.setStyle("-fx-font-size: 13pt;-fx-font-family:'Times New Roman';-fx-font-weight:bold;-fx-text-fill:#FF0000;");
                joker.play();
                fadeTransition.play();
                if (timeSeconds <= 0) {
                    timeline.stop();
                    joker.stop();
                    fadeTransition.stop();
                }
            }
        }));
        timeline.playFromStart();
    }

    //Timer Format
    public String formatTimer(int i) {
        int h, m, s;
        if (i >= 0 && i < 60) {
            s = i % 60;
            return s + " sec";
        } else if (i >= 60 && i < 3600) {
            m = i % 3600 / 60;
            s = i % 60;
            return m + " min " + s + " sec";
        } else if (i >= 3600) {
            h = i / 3600;
            m = i % 3600 / 60;
            s = i % 60;
            return h + " hr " + m + " min " + s + " sec";
        }
        return null;
    }
}
