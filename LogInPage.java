package com.example.richiefx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LogInPage extends Stage {

    Scene logInScene,greetingScene;
        private TextField  passW2;
        private PasswordField passW;
        private Label uName, pW, pWDet,memName, memCount;
        private Label n1, n2, n3, n4, n5;
        private ImageView reGi, reuBn,saTh,brYn,seiF,backImg,memberPic,sovietLogo,sovietLogo2;
        private CheckBox showPw;
        private Button btnLogIn,beGin;
        private File members = new File("data/candidates.txt");
        ChoiceBox<String> userName = new ChoiceBox<>();
        private int totmember = 0;
        private int verify = 0;
        private Text invalidPass,welC, Commname, counTry,warning1,warning2;
        private String user = "";
        private String password = "";
        private String checkBox = "" ;
        //private MediaPlayer mdPlayer;
        private static String currComrade;

    //Password Verification
    public void verifyPass () {
        Scanner sfile;
        String id;
        try {
            sfile = new Scanner(members);
            String aLine = sfile.nextLine();
            Scanner sline = new Scanner(aLine);
            totmember = Integer.parseInt(sline.next());
            for (int k = 1; k <= totmember; k++) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                id = sline.next();
                user = sline.next();
                password = sline.next();
                if (user.equals(userName.getValue())) {
                    verify = 1;
                    if (password.equals(passW.getText())) {
                        verify = 2;
                    }
                } else {
                    invalidPass.setText("Invalid Password! Please try again...");
                }
                sline.close();
            }
            sfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE " + members + " DIMINISHED!");
        }
    }

    //Export Members data for the drop down list
    public void dropDownMembers() {
        Scanner memberfile;
        String name;
        try {
            memberfile = new Scanner(members);
            String aLine = memberfile.nextLine();
            Scanner sline = new Scanner(aLine);
            totmember = Integer.parseInt(sline.next());
            for (int k = 1; k <= totmember; k++) {
                aLine = memberfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                name = sline.next();
                user = sline.next();
                password = sline.next();
                checkBox = user;
                userName.getItems().add(checkBox);
                sline.close();
            }
            memberfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE" + members + " DIMINISHED!");
        }
    }

    public String getName(){
        currComrade = userName.getSelectionModel().getSelectedItem();
        return currComrade;
    }

    //Exporting Comrade initial Country
    public String getCountry(){
        File comRades = new File("data/comradeDetails.txt");
        Scanner comradeFile;
        String country;
        String name;
        String selectedName = getName();
        String err = "You are an Immigrant";
        try {
            comradeFile = new Scanner(comRades);
            while (comradeFile.hasNextLine()){
                String aLine = comradeFile.nextLine();
                Scanner sLine = new Scanner(aLine);
                sLine.useDelimiter(":");
                while (sLine.hasNext()){
                    country = sLine.next();
                    name = sLine.next();
                    if (selectedName.equals(name)){
                        return country;
                    }
                }sLine.close();
            }comradeFile.close();
        }catch (Exception e){
            System.out.println("File" + comRades + "Does not Exist");
        }return err;
    }
        //LOGIN PAGE
        public void LogInPage() {
            this.setTitle("ITAJORLALIPAL CITIZENSHIP TEST");
            btnLogIn = new Button("Log In");
            btnLogIn.setLayoutX(377);
            btnLogIn.setLayoutY(530);
            btnLogIn.setOnAction(event -> {
                verifyPass();
                if (verify == 2) {
                    this.setScene(greetingScene);
                   // mdPlayer.play();
                    memName.setText(getName());
                    memCount.setText(getCountry());
                    File communist = new File("data/" + memName.getText() + ".jpeg");
                    Image comrade = new Image(communist.toURI().toString());
                    memberPic.setImage(comrade);
                }
            });


            //Password Protection and visibility
            showPw = new CheckBox("Show Password");
            showPw.setLayoutX(530);
            showPw.setLayoutY(467);
            showPw.setStyle("-fx-font-size: 9pt");
            showPw.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (showPw.isSelected()) {
                        passW2.setText(passW.getText());
                        passW2.setVisible(true);
                        passW.setVisible(false);
                        return;
                    } else {
                        passW.setText(passW2.getText());
                        passW.setVisible(true);
                        passW2.setVisible(false);
                    }
                }
            });


            //Members Images and Layouts
            reGi = new ImageView();
            reGi.setLayoutX(138);
            reGi.setLayoutY(86);
            reGi.setFitHeight(88);
            reGi.setFitWidth(90);
            reuBn = new ImageView();
            reuBn.setLayoutX(360);
            reuBn.setLayoutY(86);
            reuBn.setFitHeight(88);
            reuBn.setFitWidth(90);
            saTh = new ImageView();
            saTh.setLayoutX(590);
            saTh.setLayoutY(86);
            saTh.setFitHeight(88);
            saTh.setFitWidth(90);
            brYn = new ImageView();
            brYn.setLayoutX(245);
            brYn.setLayoutY(220);
            brYn.setFitHeight(88);
            brYn.setFitWidth(90);
            seiF = new ImageView();
            seiF.setLayoutX(485);
            seiF.setLayoutY(220);
            seiF.setFitHeight(88);
            seiF.setFitWidth(90);

            //Background Image Sizing
            backImg = new ImageView();
            backImg.setFitHeight(617);
            backImg.setFitWidth(811);


            //Members Pictures and Path Location
            File rFile = new File("data/Regina.jpeg");
            Image reG = new Image(rFile.toURI().toString());
            reGi.setImage(reG);
            File reFile = new File("data/Reuben.jpeg");
            Image reuB = new Image(reFile.toURI().toString());
            reuBn.setImage(reuB);
            File saFile = new File("data/Sathish.jpeg");
            Image saT = new Image(saFile.toURI().toString());
            saTh.setImage(saT);
            File brFile = new File("data/Bryan.jpeg");
            Image brY = new Image(brFile.toURI().toString());
            brYn.setImage(brY);
            File seFile = new File("data/Seifeldeen.jpeg");
            Image seF = new Image(seFile.toURI().toString());
            seiF.setImage(seF);
            File bFile = new File("data/original.gif");
            Image bgImg = new Image(bFile.toURI().toString());
            backImg.setImage(bgImg);

            //Music File
            //String musicFile = "data/AP_Audio.mp3";
           // Media sound = new Media(new File(musicFile).toURI().toString());
            //mdPlayer = new MediaPlayer(sound);

            //Members Name and layouts
            n1 = new Label("RICHIE");
            n1.setLayoutX(104);
            n1.setLayoutY(190);
            n2 = new Label("MICHAEL");
            n2.setLayoutX(345);
            n2.setLayoutY(190);
            n3 = new Label("RANGGA");
            n3.setLayoutX(580);

            n3.setLayoutY(190);
            n4 = new Label("SHARUMATY");
            n4.setLayoutX(210);
            n4.setLayoutY(320);
            n5 = new Label("JORDAN");
            n5.setLayoutX(465);
            n5.setLayoutY(320);

            //Username and Password labels and layouts
            uName = new Label("Username: ");
            uName.setLayoutX(269);
            uName.setLayoutY(440);
            pW = new Label("Password: ");
            pW.setLayoutX(271);
            pW.setLayoutY(468);

            //Username and Password text fields and Password field
            userName.setPrefWidth(167);
            userName.setLayoutX(345);
            userName.setLayoutY(433);
            passW = new PasswordField();
            passW.setLayoutX(345);
            passW.setLayoutY(463);
            passW2 = new TextField();
            passW2.setLayoutX(345);
            passW2.setLayoutY(463);

            //Password Notice Label, Layouts and Style
            pWDet = new Label("**Password: (Your Name in Hexadecimal)**");
            pWDet.setLayoutX(558);
            pWDet.setLayoutY(548);
            pWDet.setStyle("-fx-font-size: 8pt;-fx-font-weight:bold;");


            //Invalid Password Alert
            invalidPass = new Text();
            invalidPass.setText("");
            invalidPass.setFill(Color.RED);
            invalidPass.setLayoutX(326);
            invalidPass.setLayoutY(512);
            dropDownMembers();


            //Log in Page Display
            Group logInPage = new
                    Group(backImg, userName, passW2, passW, pW, pWDet, showPw, uName, invalidPass, n1, n2, n3, n4, n5, brYn, reGi, reuBn, saTh, seiF, btnLogIn);
            logInScene = new Scene(logInPage, 811, 617);
            this.setScene(logInScene);
            this.show();

            //Greeting Scene
            //Text Labels Positioning and Font Size
            welC = new Text("WELCOME COMRADE!");
            welC.setLayoutX(200);
            welC.setLayoutY(76);
            welC.setFont(new Font("Times New Roman",38));
            warning1 = new Text("NOTE: YOU NEED TO ANSWER 25 QUESTIONS!");
            warning1.setLayoutX(245);
            warning1.setLayoutY(475);
            warning1.setFont(new Font("Times New Roman",15));
            warning2 = new Text( "'HONOUR YOUR NEW FATHERLAND'");
            warning2.setLayoutX(280);
            warning2.setLayoutY(530);
            warning2.setFont(new Font("Times New Roman",15));
            Commname = new Text("Name :");
            Commname.setLayoutX(315);
            Commname.setLayoutY(173);
            Commname.setStyle("-fx-font-size: 20pt");
            counTry = new Text("Country :");
            counTry.setLayoutX(288);
            counTry.setLayoutY(210);
            counTry.setStyle("-fx-font-size: 20pt");

            //Members name and country
            memName = new Label();
            memName.setLayoutX(420);
            memName.setLayoutY(148);
            memName.setStyle("-fx-font-size: 20pt");
            memCount = new Label();
            memCount.setLayoutX(420);
            memCount.setLayoutY(185);
            memCount.setStyle("-fx-font-size: 20pt");

            //Comrade Picture and Logo Positioning
            memberPic = new ImageView();
            memberPic.setLayoutX(89);
            memberPic.setLayoutY(135);
            memberPic.setFitHeight(176);
            memberPic.setFitWidth(185);
            memberPic.setImage(null);
            sovietLogo = new ImageView();
            sovietLogo.setLayoutX(25);
            sovietLogo.setLayoutY(15);
            sovietLogo.setFitHeight(77);
            sovietLogo.setFitWidth(88);
            sovietLogo2 = new ImageView();
            sovietLogo2.setLayoutX(685);
            sovietLogo2.setLayoutY(15);
            sovietLogo2.setFitHeight(77);
            sovietLogo2.setFitWidth(88);

            //Soviet Union Logos and Positioning
            File soviet = new File("data/ussr.gif");
            Image comm = new Image(soviet.toURI().toString());
            sovietLogo.setImage(comm);
            File soviet2 = new File("data/ussr.gif");
            Image comm2 = new Image(soviet2.toURI().toString());
            sovietLogo2.setImage(comm2);

            //Begin Text Button and it's function
            beGin = new Button("Begin Test!");
            beGin.setLayoutX(369);
            beGin.setLayoutY(400);
            beGin.setPrefWidth(99);
            beGin.setPrefHeight(37);
            beGin.setOnAction(event -> {
                //mdPlayer.stop();
                this.hide();
            });

            //Greeting Comrade Page Display
            Group greetingComrade = new
                    Group(welC,Commname,counTry,memName,memCount,memberPic,sovietLogo,sovietLogo2,beGin,warning1,warning2);
            this.setTitle("WELCOME COMRADES!");
            greetingScene = new Scene(greetingComrade,800,600);


        }
}