package fr.deltadesnoc.somc.launcher.ui.panels;

import com.azuriom.azauth.AuthenticationException;
import fr.deltadesnoc.somc.launcher.Main;
import fr.deltadesnoc.somc.launcher.ui.PanelManager;
import fr.deltadesnoc.somc.launcher.ui.panel.Panel;
import fr.deltadesnoc.somc.launcher.utils.LauncherManager;
import fr.deltadesnoc.somc.launcher.utils.json.Config;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PanelLogin extends Panel {

    private String username;
    private String password;
    TextField usernameField;
    PasswordField passwordField;
    public static boolean isConnected = false;
    private File json = new File(PanelHomeTalymio.DIR.getAbsolutePath().toString() + "\\coordonnee.json");

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        GridPane loginPanel = new GridPane();
        GridPane mainPane = new GridPane();
        GridPane rightPane = new GridPane();

        loginPanel.setMaxWidth(780);
        loginPanel.setMinWidth(780);
        loginPanel.setMaxHeight(380);
        loginPanel.setMinHeight(380);

        GridPane.setHgrow(loginPanel, Priority.ALWAYS);
        GridPane.setVgrow(loginPanel, Priority.ALWAYS);
        GridPane.setHalignment(loginPanel, HPos.CENTER);
        GridPane.setValignment(loginPanel, VPos.CENTER);

        loginPanel.add(mainPane, 0, 0);
        loginPanel.add(rightPane, 1, 0);

        GridPane.setHgrow(mainPane, Priority.ALWAYS);
        GridPane.setVgrow(mainPane, Priority.ALWAYS);
        GridPane.setHgrow(rightPane, Priority.ALWAYS);
        GridPane.setVgrow(rightPane, Priority.ALWAYS);

        mainPane.setStyle("-fx-background-color: #181818; -fx-opacity: 100%");
        mainPane.setMinWidth(530);
        rightPane.setStyle("-fx-background-color: #181818; -fx-opacity: 100%");

        Button connectButton = new Button("Se connecter");
        GridPane.setVgrow(connectButton, Priority.ALWAYS);
        GridPane.setHgrow(connectButton, Priority.ALWAYS);
        GridPane.setHalignment(connectButton, HPos.CENTER);
        GridPane.setValignment(connectButton, VPos.CENTER);
        connectButton.setMinWidth(200);
        connectButton.setMinHeight(40);
        connectButton.setTranslateY(-80);
        connectButton.setStyle("-fx-background-color: #5FECA7; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 18px; -fx-text-fill: black; ");
        connectButton.setOnMouseClicked(e -> {
            try {
                LauncherManager.auth(usernameField.getText(), passwordField.getText());
                if(isConnected){
                    this.panelManager.showPanel(new PanelHomeTalymio(this));
                    Config.save(usernameField.getText(), passwordField.getText());
                }else{
                    return;
                }
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        connectButton.setOnMouseEntered(e -> {
            connectButton.setCursor(Cursor.HAND);
        });

        Button registerHere = new Button("S'inscrire ici");
        GridPane.setVgrow(registerHere, Priority.ALWAYS);
        GridPane.setHgrow(registerHere, Priority.ALWAYS);
        GridPane.setHalignment(registerHere, HPos.CENTER);
        GridPane.setValignment(registerHere, VPos.CENTER);
        registerHere.setMinWidth(200);
        registerHere.setMinHeight(40);
        registerHere.setTranslateY(-20);
        registerHere.setStyle("-fx-background-color: #5FECA7; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 18px; -fx-text-fill: black; ");
        registerHere.setOnMouseClicked(e -> {
            openURL("https://talymio.fr/user/register");
        });
        registerHere.setOnMouseEntered(e -> {
            registerHere.setCursor(Cursor.HAND);
        });

        Button forgotPassword = new Button("Mot de Passe Oublié ?");
        GridPane.setVgrow(forgotPassword, Priority.ALWAYS);
        GridPane.setHgrow(forgotPassword, Priority.ALWAYS);
        GridPane.setHalignment(forgotPassword, HPos.CENTER);
        GridPane.setValignment(forgotPassword, VPos.CENTER);
        forgotPassword.setMinWidth(170);
        forgotPassword.setMinHeight(40);
        forgotPassword.setTranslateY(40);
        forgotPassword.setStyle("-fx-background-color: #5FECA7; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 18px; -fx-text-fill: black; ");
        forgotPassword.setOnMouseClicked(e -> {
            openURL("https://talymio.fr/user/password/reset");
        });
        forgotPassword.setOnMouseEntered(e -> {
            forgotPassword.setCursor(Cursor.HAND);
        });

        ImageView discord = new ImageView(Main.loadImage("discord"));
        GridPane.setVgrow(discord, Priority.ALWAYS);
        GridPane.setHgrow(discord, Priority.ALWAYS);
        GridPane.setHalignment(discord, HPos.RIGHT);
        GridPane.setValignment(discord, VPos.BOTTOM);
        discord.setTranslateX(-20);
        discord.setTranslateY(-10);
        discord.setOnMouseClicked(e -> {
            openURL("https://discord.gg/jfGcvnZ");
        });
        discord.setOnMouseEntered(e -> {
            discord.setCursor(Cursor.HAND);
        });

        ImageView twitter = new ImageView(Main.loadImage("twitter"));
        GridPane.setVgrow(twitter, Priority.ALWAYS);
        GridPane.setHgrow(twitter, Priority.ALWAYS);
        GridPane.setHalignment(twitter, HPos.LEFT);
        GridPane.setValignment(twitter, VPos.BOTTOM);
        twitter.setTranslateX(20);
        twitter.setTranslateY(-15);
        twitter.setOnMouseClicked(e -> {
            openURL("https://twitter.com/SOMC_FR");
        });
        twitter.setOnMouseEntered(e -> {
            twitter.setCursor(Cursor.HAND);
        });

        rightPane.getChildren().addAll(connectButton, registerHere, forgotPassword, discord, twitter);
        this.layout.getChildren().add(loginPanel);

        Label connectLabel = new Label("SE CONNECTER !");
        GridPane.setVgrow(connectLabel, Priority.ALWAYS);
        GridPane.setHgrow(connectLabel, Priority.ALWAYS);
        GridPane.setValignment(connectLabel, VPos.TOP);
        connectLabel.setTranslateY(27);
        connectLabel.setTranslateX(37.5);
        connectLabel.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 20px;");

        Separator connectSeparator = new Separator();
        GridPane.setVgrow(connectSeparator, Priority.ALWAYS);
        GridPane.setHgrow(connectSeparator, Priority.ALWAYS);
        GridPane.setValignment(connectSeparator, VPos.TOP);
        connectSeparator.setTranslateY(60);
        connectSeparator.setTranslateX(37.5);
        connectSeparator.setMinWidth(325);
        connectSeparator.setMaxWidth(325);
        connectSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 50%;");

        Label usernameLabel = new Label("Nom d'utilisateur");
        GridPane.setVgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setHgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setValignment(usernameLabel, VPos.TOP);
        GridPane.setHalignment(usernameLabel, HPos.LEFT);
        usernameLabel.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 18px;");
        usernameLabel.setTranslateX(37.5);
        usernameLabel.setTranslateY(110);

        usernameField = new TextField();
        GridPane.setVgrow(usernameField, Priority.ALWAYS);
        GridPane.setHgrow(usernameField, Priority.ALWAYS);
        GridPane.setHalignment(usernameField, HPos.LEFT);
        GridPane.setValignment(usernameField, VPos.TOP);
        usernameField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5;");
        usernameField.setMaxWidth(325);
        usernameField.setMaxHeight(40);
        usernameField.setTranslateX(37.5);
        usernameField.setTranslateY(140);
        setUsername(usernameField.getText());

        Separator usernameSeparator = new Separator();
        GridPane.setVgrow(usernameSeparator, Priority.ALWAYS);
        GridPane.setHgrow(usernameSeparator, Priority.ALWAYS);
        GridPane.setValignment(usernameSeparator, VPos.TOP);
        usernameSeparator.setTranslateY(181);
        usernameSeparator.setTranslateX(37.5);
        usernameSeparator.setMaxWidth(325);
        usernameSeparator.setMinWidth(325);
        usernameSeparator.setMaxHeight(1);
        usernameSeparator.setMinHeight(1);
        usernameSeparator.setStyle("-fx-opacity: 40%");

        Label passwordLabel = new Label("Mot de passe");
        GridPane.setVgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setHgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setHalignment(passwordLabel, HPos.LEFT);
        GridPane.setValignment(passwordLabel, VPos.TOP);
        passwordLabel.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 18px;");
        passwordLabel.setTranslateX(37.5);
        passwordLabel.setTranslateY(200);

        passwordField = new PasswordField();
        GridPane.setVgrow(passwordField, Priority.ALWAYS);
        GridPane.setHgrow(passwordField, Priority.ALWAYS);
        GridPane.setHalignment(passwordField, HPos.LEFT);
        GridPane.setValignment(passwordField, VPos.TOP);
        passwordField.setStyle("-fx-background-color: #1e1e1e; -fx-font-size: 16px; -fx-text-fill: #e5e5e5;");
        passwordField.setMaxWidth(325);
        passwordField.setMaxHeight(40);
        passwordField.setTranslateX(37.5);
        passwordField.setTranslateY(230);
        setPassword(passwordField.getText());

        if(json.exists()){
            if(Config.getInfo2("pseudo") != null) {
                if (Config.getInfo2("password") != null) {
                    usernameField.setText(Config.getInfo("pseudo"));
                    passwordField.setText(Config.getInfo("password"));
                }
            }
        }

        Separator passwordSeparator = new Separator();
        GridPane.setVgrow(passwordSeparator, Priority.ALWAYS);
        GridPane.setHgrow(passwordSeparator, Priority.ALWAYS);
        GridPane.setValignment(passwordSeparator, VPos.TOP);
        passwordSeparator.setTranslateY(271);
        passwordSeparator.setTranslateX(37.5);
        passwordSeparator.setMaxWidth(325);
        passwordSeparator.setMinWidth(325);
        passwordSeparator.setMaxHeight(1);
        passwordSeparator.setMinHeight(1);
        passwordSeparator.setStyle("-fx-opacity: 40%");

        ImageView logo = new ImageView(Main.loadImage("logo"));
        logo.setFitWidth(160);
        logo.setFitHeight(160);
        logo.setTranslateX(-15);
        GridPane.setVgrow(logo, Priority.ALWAYS);
        GridPane.setHgrow(logo, Priority.ALWAYS);
        GridPane.setHalignment(logo, HPos.RIGHT);
        GridPane.setValignment(logo, VPos.CENTER);

        mainPane.getChildren().addAll(connectLabel, connectSeparator, usernameLabel, usernameField,usernameSeparator,passwordLabel,passwordField,
                passwordSeparator, logo);
    }

    private void openURL(String url){
        try {
            Desktop.getDesktop().browse(new URI(url));
        }catch (URISyntaxException | IOException e){
            Main.logger.warn(e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
