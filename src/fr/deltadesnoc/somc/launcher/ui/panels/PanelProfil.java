package fr.deltadesnoc.somc.launcher.ui.panels;

import com.azuriom.azauth.AuthenticationException;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.deltadesnoc.somc.launcher.Main;
import fr.deltadesnoc.somc.launcher.ui.PanelManager;
import fr.deltadesnoc.somc.launcher.ui.panel.Panel;
import fr.deltadesnoc.somc.launcher.utils.LauncherManager;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class PanelProfil extends Panel {

    private GridPane centerPane = new GridPane();
    private PanelLogin panelLogin;

    public PanelProfil(PanelLogin panelLogin){this.panelLogin = panelLogin;}

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        ColumnConstraints menuPaneConstraint = new ColumnConstraints();
        menuPaneConstraint.setHalignment(HPos.LEFT);
        menuPaneConstraint.setMinWidth(300);
        menuPaneConstraint.setMaxWidth(300);
        this.layout.getColumnConstraints().addAll(menuPaneConstraint, new ColumnConstraints());

        GridPane leftBarPane = new GridPane();
        GridPane.setVgrow(leftBarPane, Priority.ALWAYS);
        GridPane.setHgrow(leftBarPane, Priority.ALWAYS);

        Separator rightSeparator = new Separator();
        GridPane.setVgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHalignment(rightSeparator, HPos.RIGHT);
        rightSeparator.setOrientation(Orientation.VERTICAL);
        rightSeparator.setTranslateY(1);
        rightSeparator.setMinWidth(2);
        rightSeparator.setMaxWidth(2);
        rightSeparator.setOpacity(0.30);

        GridPane bottomGridPane = new GridPane();
        GridPane.setVgrow(bottomGridPane, Priority.ALWAYS);
        GridPane.setHgrow(bottomGridPane, Priority.ALWAYS);
        GridPane.setHalignment(bottomGridPane, HPos.LEFT);
        GridPane.setValignment(bottomGridPane, VPos.TOP);
        bottomGridPane.setTranslateY(30);
        bottomGridPane.setMinHeight(40);
        bottomGridPane.setMaxHeight(40);
        bottomGridPane.setMinWidth(300);
        bottomGridPane.setMaxWidth(300);
        showLeftBar(bottomGridPane);
        leftBarPane.getChildren().addAll(rightSeparator, bottomGridPane);

        this.layout.add(leftBarPane,0,0);
        this.layout.add(this.centerPane,1,0);
        GridPane.setHgrow(this.centerPane, Priority.ALWAYS);
        GridPane.setVgrow(this.centerPane, Priority.ALWAYS);
        ScrollPane scrollPane = new ScrollPane();
        GridPane.setHgrow(scrollPane, Priority.ALWAYS);
        GridPane.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.getStylesheets().add(Main.class.getResource("/fr/deltadesnoc/somc/launcher/resources/css/scrollbar.css").toExternalForm());

        GridPane centerPanel = new GridPane();
        GridPane.setHgrow(centerPanel, Priority.ALWAYS);
        GridPane.setVgrow(centerPanel, Priority.ALWAYS);
        centerPanel.setMaxWidth(960);
        centerPanel.setMinWidth(960);
        centerPanel.setMaxHeight(950);
        centerPanel.setMinHeight(950);
        centerPanel.setStyle("-fx-background-color: black; -fx-opacity: 100%");
        profilPanel(centerPanel);

        this.centerPane.getChildren().addAll(scrollPane);
        scrollPane.setContent(centerPanel);
    }

    private void profilPanel(GridPane gridPane){

        Label profil = new Label("PROFIL USTILISATEUR");
        GridPane.setVgrow(profil, Priority.ALWAYS);
        GridPane.setHgrow(profil, Priority.ALWAYS);
        GridPane.setValignment(profil, VPos.TOP);
        profil.setTranslateX(30);
        profil.setTranslateY(20);
        profil.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 30px;");

        Separator profilSeparator = new Separator();
        GridPane.setVgrow(profilSeparator, Priority.ALWAYS);
        GridPane.setHgrow(profilSeparator, Priority.ALWAYS);
        GridPane.setValignment(profilSeparator, VPos.TOP);
        profilSeparator.setTranslateX(30);
        profilSeparator.setTranslateY(70);
        profilSeparator.setMinWidth(350);
        profilSeparator.setMaxWidth(350);
        profilSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 70%;");

        ImageView skinHead = new ImageView("https://minotar.net/avatar/" + LauncherManager.getAuthInfos().getUsername() + "/200.png");
        GridPane.setVgrow(skinHead, Priority.ALWAYS);
        GridPane.setHgrow(skinHead, Priority.ALWAYS);
        GridPane.setValignment(skinHead, VPos.TOP);
        skinHead.setTranslateX(30);
        skinHead.setTranslateY(125);
        skinHead.setStyle("-fx-opacity: 100%");

        Label pseudoLabel = new Label("PSEUDO");
        pseudoLabel.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 24px;");
        GridPane.setVgrow(pseudoLabel, Priority.ALWAYS);
        GridPane.setHgrow(pseudoLabel, Priority.ALWAYS);
        GridPane.setValignment(pseudoLabel, VPos.CENTER);
        pseudoLabel.setTranslateX(30);

        Separator pseudoSeparator = new Separator();
        GridPane.setVgrow(pseudoSeparator, Priority.ALWAYS);
        GridPane.setHgrow(pseudoSeparator, Priority.ALWAYS);
        GridPane.setValignment(pseudoSeparator, VPos.CENTER);
        pseudoSeparator.setTranslateX(30);
        pseudoSeparator.setTranslateY(20);
        pseudoSeparator.setMinWidth(300);
        pseudoSeparator.setMaxWidth(300);
        pseudoSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 70%;");

        Rectangle pseudo = new Rectangle();
        pseudo.setHeight(50);
        pseudo.setWidth(300);
        GridPane.setVgrow(pseudo, Priority.ALWAYS);
        GridPane.setHgrow(pseudo, Priority.ALWAYS);
        GridPane.setValignment(pseudo, VPos.CENTER);
        pseudo.setTranslateX(30);
        pseudo.setTranslateY(70);
        pseudo.setFill(Color.rgb(25,25,25,1));

        Label pseudoText = new Label(LauncherManager.getAuthInfos().getUsername());
        pseudoText.setStyle("-fx-font-size: 22px; -fx-text-fill: white");
        GridPane.setVgrow(pseudoText, Priority.ALWAYS);
        GridPane.setHgrow(pseudoText, Priority.ALWAYS);
        GridPane.setValignment(pseudoText, VPos.CENTER);
        pseudoText.setTranslateX(32);
        pseudoText.setTranslateY(72);

        Label rankLabel = new Label("GRADE");
        rankLabel.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 24px;");
        GridPane.setVgrow(rankLabel, Priority.ALWAYS);
        GridPane.setHgrow(rankLabel, Priority.ALWAYS);
        GridPane.setValignment(rankLabel, VPos.CENTER);
        rankLabel.setTranslateX(620);

        Separator rankSeparator = new Separator();
        GridPane.setVgrow(rankSeparator, Priority.ALWAYS);
        GridPane.setHgrow(rankSeparator, Priority.ALWAYS);
        GridPane.setValignment(rankSeparator, VPos.CENTER);
        rankSeparator.setTranslateX(620);
        rankSeparator.setTranslateY(20);
        rankSeparator.setMinWidth(300);
        rankSeparator.setMaxWidth(300);
        rankSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 70%;");

        Rectangle rank = new Rectangle();
        rank.setHeight(50);
        rank.setWidth(300);
        GridPane.setVgrow(rank, Priority.ALWAYS);
        GridPane.setHgrow(rank, Priority.ALWAYS);
        GridPane.setValignment(rank, VPos.CENTER);
        rank.setTranslateX(620);
        rank.setTranslateY(70);
        rank.setFill(Color.rgb(25,25,25,1));

        Label rankText = new Label();
        try {
            rankText.setText(LauncherManager.getAuthenticator().verify(LauncherManager.getAuthInfos().getAccessToken()).getRole().getName());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rankText.setStyle("-fx-font-size: 22px; -fx-text-fill: white");
        GridPane.setVgrow(rankText, Priority.ALWAYS);
        GridPane.setHgrow(rankText, Priority.ALWAYS);
        GridPane.setValignment(rankText, VPos.CENTER);
        rankText.setTranslateX(622);
        rankText.setTranslateY(72);

        Label mailLabel = new Label("EMAIL");
        mailLabel.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 24px;");
        GridPane.setVgrow(mailLabel, Priority.ALWAYS);
        GridPane.setHgrow(mailLabel, Priority.ALWAYS);
        GridPane.setValignment(mailLabel, VPos.CENTER);
        mailLabel.setTranslateX(30);
        mailLabel.setTranslateY(160);

        Separator mailSeparator = new Separator();
        GridPane.setVgrow(mailSeparator, Priority.ALWAYS);
        GridPane.setHgrow(mailSeparator, Priority.ALWAYS);
        GridPane.setValignment(mailSeparator, VPos.CENTER);
        mailSeparator.setTranslateX(30);
        mailSeparator.setTranslateY(180);
        mailSeparator.setMinWidth(300);
        mailSeparator.setMaxWidth(300);
        mailSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 70%;");

        Rectangle mail = new Rectangle();
        mail.setHeight(50);
        mail.setWidth(300);
        GridPane.setVgrow(mail, Priority.ALWAYS);
        GridPane.setHgrow(mail, Priority.ALWAYS);
        GridPane.setValignment(mail, VPos.CENTER);
        mail.setTranslateX(30);
        mail.setTranslateY(230);
        mail.setFill(Color.rgb(25,25,25,1));

        Label mailText = new Label();
        try {
            mailText.setText(LauncherManager.getAuthenticator().verify(LauncherManager.getAuthInfos().getAccessToken()).getEmail());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mailText.setStyle("-fx-font-size: 22px; -fx-text-fill: white");
        GridPane.setVgrow(mailText, Priority.ALWAYS);
        GridPane.setHgrow(mailText, Priority.ALWAYS);
        GridPane.setValignment(mailText, VPos.CENTER);
        mailText.setTranslateX(32);
        mailText.setTranslateY(232);

        Label oldLabel = new Label("ANCIENNETE");
        oldLabel.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 24px;");
        GridPane.setVgrow(oldLabel, Priority.ALWAYS);
        GridPane.setHgrow(oldLabel, Priority.ALWAYS);
        GridPane.setValignment(oldLabel, VPos.CENTER);
        oldLabel.setTranslateX(620);
        oldLabel.setTranslateY(160);

        Separator oldSeparator = new Separator();
        GridPane.setVgrow(oldSeparator, Priority.ALWAYS);
        GridPane.setHgrow(oldSeparator, Priority.ALWAYS);
        GridPane.setValignment(oldSeparator, VPos.CENTER);
        oldSeparator.setTranslateX(620);
        oldSeparator.setTranslateY(180);
        oldSeparator.setMinWidth(300);
        oldSeparator.setMaxWidth(300);
        oldSeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 70%;");

        Rectangle old = new Rectangle();
        old.setHeight(50);
        old.setWidth(300);
        GridPane.setVgrow(old, Priority.ALWAYS);
        GridPane.setHgrow(old, Priority.ALWAYS);
        GridPane.setValignment(old, VPos.CENTER);
        old.setTranslateX(620);
        old.setTranslateY(230);
        old.setFill(Color.rgb(25,25,25,1));

        Label oldText = new Label();
        try {
            oldText.setText("Le " + LauncherManager.getAuthenticator().verify(LauncherManager.getAuthInfos().getAccessToken()).getCreatedAt().toString().substring(8, 10) + "-" + LauncherManager.getAuthenticator().verify(LauncherManager.getAuthInfos().getAccessToken()).getCreatedAt().toString().substring(5, 7) + "-" + LauncherManager.getAuthenticator().verify(LauncherManager.getAuthInfos().getAccessToken()).getCreatedAt().toString().substring(0, 4) + " à " + LauncherManager.getAuthenticator().verify(LauncherManager.getAuthInfos().getAccessToken()).getCreatedAt().toString().substring(11, 19));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        oldText.setStyle("-fx-font-size: 22px; -fx-text-fill: white");
        GridPane.setVgrow(oldText, Priority.ALWAYS);
        GridPane.setHgrow(oldText, Priority.ALWAYS);
        GridPane.setValignment(oldText, VPos.CENTER);
        oldText.setTranslateX(622);
        oldText.setTranslateY(232);

        Label moneyLabel = new Label("MONEY");
        moneyLabel.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 24px;");
        GridPane.setVgrow(moneyLabel, Priority.ALWAYS);
        GridPane.setHgrow(moneyLabel, Priority.ALWAYS);
        GridPane.setValignment(moneyLabel, VPos.CENTER);
        moneyLabel.setTranslateX(30);
        moneyLabel.setTranslateY(320);

        Separator moneySeparator = new Separator();
        GridPane.setVgrow(moneySeparator, Priority.ALWAYS);
        GridPane.setHgrow(moneySeparator, Priority.ALWAYS);
        GridPane.setValignment(moneySeparator, VPos.CENTER);
        moneySeparator.setTranslateX(30);
        moneySeparator.setTranslateY(340);
        moneySeparator.setMinWidth(300);
        moneySeparator.setMaxWidth(300);
        moneySeparator.setStyle("-fx-background-color: #fff; -fx-opacity: 70%;");

        Rectangle money = new Rectangle();
        money.setHeight(50);
        money.setWidth(300);
        GridPane.setVgrow(money, Priority.ALWAYS);
        GridPane.setHgrow(money, Priority.ALWAYS);
        GridPane.setValignment(pseudo, VPos.CENTER);
        money.setTranslateX(30);
        money.setTranslateY(390);
        money.setFill(Color.rgb(25,25,25,1));

        Label moneyText = new Label();
        try {
            moneyText.setText(String.valueOf(LauncherManager.getAuthenticator().verify(LauncherManager.getAuthInfos().getAccessToken()).getMoney()));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        moneyText.setStyle("-fx-font-size: 22px; -fx-text-fill: white");
        GridPane.setVgrow(moneyText, Priority.ALWAYS);
        GridPane.setHgrow(moneyText, Priority.ALWAYS);
        GridPane.setValignment(moneyText, VPos.CENTER);
        moneyText.setTranslateX(32);
        moneyText.setTranslateY(392);

        gridPane.getChildren().addAll(profil, profilSeparator, skinHead,
                pseudoLabel, pseudoSeparator, pseudo, pseudoText,
                rankLabel, rankSeparator, rank, rankText,
                mailLabel, mailSeparator, mail, mailText,
                oldLabel, oldSeparator, old, oldText,
                moneyLabel, moneySeparator, money, moneyText);

    }

    private void showLeftBar(GridPane gridPane){

        Button returnButton = new Button("Retour");
        returnButton.setStyle("-fx-background-color: #5FECA7; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 18px; -fx-text-fill: black; -fx-text-alignment: center");
        GridPane.setVgrow(returnButton, Priority.ALWAYS);
        GridPane.setHgrow(returnButton, Priority.ALWAYS);
        GridPane.setValignment(returnButton, VPos.TOP);
        GridPane.setHalignment(returnButton, HPos.CENTER);
        MaterialDesignIconView back = new MaterialDesignIconView(MaterialDesignIcon.ARROW_LEFT);
        back.setSize("40px");
        back.setFill(Color.BLACK);
        returnButton.setGraphic(back);
        returnButton.setMinHeight(50);
        returnButton.setMaxHeight(50);
        returnButton.setMinWidth(250);
        returnButton.setMaxWidth(250);
        returnButton.setTranslateY(20);
        returnButton.setOnMouseClicked(e -> this.panelManager.showPanel(new PanelHomeTalymio(panelLogin)));

        Button disconnectButton = new Button("Se Deconnecter");
        disconnectButton.setStyle("-fx-background-color: #5FECA7; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 18px; -fx-text-fill: black; ");
        GridPane.setVgrow(disconnectButton, Priority.ALWAYS);
        GridPane.setHgrow(disconnectButton, Priority.ALWAYS);
        GridPane.setValignment(disconnectButton, VPos.BOTTOM);
        GridPane.setHalignment(disconnectButton, HPos.CENTER);
        MaterialDesignIconView disconnect = new MaterialDesignIconView(MaterialDesignIcon.LOGOUT);
        disconnect.setSize("40px");
        disconnect.setFill(Color.BLACK);
        disconnectButton.setGraphic(disconnect);
        disconnectButton.setMinHeight(50);
        disconnectButton.setMaxHeight(50);
        disconnectButton.setMinWidth(250);
        disconnectButton.setMaxWidth(250);
        disconnectButton.setTranslateY(600);
        disconnectButton.setOnMouseClicked(e -> {
            try {
                LauncherManager.getAuthenticator().logout(LauncherManager.getAuthInfos().getAccessToken());
                this.panelManager.showPanel(new PanelLogin());
            } catch (AuthenticationException authenticationException) {
                authenticationException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        gridPane.getChildren().addAll(returnButton, disconnectButton);

    }
}
