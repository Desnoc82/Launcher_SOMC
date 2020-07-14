package fr.deltadesnoc.somc.launcher.ui.panels;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.deltadesnoc.somc.launcher.Main;
import fr.deltadesnoc.somc.launcher.ram.talymio.RamManagerTalymio;
import fr.deltadesnoc.somc.launcher.ram.talymio.RamPanelTalymio;
import fr.deltadesnoc.somc.launcher.ui.PanelManager;
import fr.deltadesnoc.somc.launcher.ui.components.ABigDownloadBar;
import fr.deltadesnoc.somc.launcher.ui.components.modal.error.ModalErrorAction;
import fr.deltadesnoc.somc.launcher.ui.panel.Panel;
import fr.deltadesnoc.somc.launcher.utils.FxAlert;
import fr.deltadesnoc.somc.launcher.utils.LauncherManager;
import fr.deltadesnoc.somc.launcher.utils.json.Config;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.openlauncherlib.util.ProcessLogManager;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.supdate.exception.BadServerResponseException;
import fr.theshark34.supdate.exception.BadServerVersionException;
import fr.theshark34.supdate.exception.ServerDisabledException;
import fr.theshark34.supdate.exception.ServerMissingSomethingException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PanelHomeTalymio extends Panel {

    private PanelLogin panelLogin;
    private GridPane centerPane = new GridPane();
    private ABigDownloadBar aBigDownloadBar;
    private final static boolean COMPATIBLE = System.getProperty("sun.arch.data.model").equals("64");
    public static final GameVersion SO_VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
    public static final GameInfos SO_INFOS = new GameInfos("talymio", SO_VERSION, new GameTweak[] { GameTweak.FORGE });
    public static final File DIR = SO_INFOS.getGameDir();
    public static File ramText = new File(DIR, "ram.properties");
    private SUpdate sUpdate = new SUpdate("https://talymio.fr/talymio", DIR);

    public PanelHomeTalymio(PanelLogin panelLogin){this.panelLogin = panelLogin;}

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        RowConstraints menuPaneConstraints = new RowConstraints();
        menuPaneConstraints.setValignment(VPos.CENTER);
        menuPaneConstraints.setMinHeight(120);
        menuPaneConstraints.setMaxHeight(120);
        this.layout.getRowConstraints().addAll(menuPaneConstraints, new RowConstraints());

        GridPane topBarPane = new GridPane();
        GridPane.setVgrow(topBarPane, Priority.ALWAYS);
        GridPane.setHgrow(topBarPane, Priority.ALWAYS);

        Separator rightSeparator = new Separator();
        GridPane.setVgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setHgrow(rightSeparator, Priority.ALWAYS);
        GridPane.setValignment(rightSeparator, VPos.CENTER);
        GridPane.setHalignment(rightSeparator, HPos.CENTER);
        rightSeparator.setOrientation(Orientation.HORIZONTAL);
        rightSeparator.setMinHeight(2);
        rightSeparator.setMaxHeight(2);
        rightSeparator.setOpacity(0.70);

        GridPane topGridPane = new GridPane();
        GridPane.setVgrow(topGridPane, Priority.ALWAYS);
        GridPane.setHgrow(topGridPane, Priority.ALWAYS);
        GridPane.setHalignment(topGridPane, HPos.LEFT);
        GridPane.setValignment(topGridPane, VPos.TOP);
        topGridPane.setMinHeight(60);
        topGridPane.setMaxHeight(60);
        topGridPane.setMinWidth(1280);
        topGridPane.setMaxWidth(1280);
        showServerBar(topGridPane);
        topBarPane.getChildren().addAll(rightSeparator, topGridPane);

        this.layout.add(topBarPane,0,0);
        this.layout.add(this.centerPane,0,1);
        GridPane.setHgrow(this.centerPane, Priority.ALWAYS);
        GridPane.setVgrow(this.centerPane, Priority.ALWAYS);
        ScrollPane scrollPane = new ScrollPane();
        GridPane.setHgrow(scrollPane, Priority.ALWAYS);
        GridPane.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.getStylesheets().add(Main.class.getResource("/fr/deltadesnoc/somc/launcher/resources/css/scrollbar.css").toExternalForm());

        GridPane centerPanel = new GridPane();
        GridPane.setHgrow(centerPanel, Priority.ALWAYS);
        GridPane.setVgrow(centerPanel, Priority.ALWAYS);
        centerPanel.setMaxWidth(1260);
        centerPanel.setMinWidth(1260);
        centerPanel.setMaxHeight(780);
        centerPanel.setMinHeight(780);
        showServerDescBar(centerPanel);

        this.centerPane.getChildren().addAll(scrollPane);
        scrollPane.setContent(centerPanel);

    }

    private void showServerBar(GridPane gridPane){

        Rectangle rectangle = new Rectangle();
        rectangle.setStyle("-fx-background-color: black");
        rectangle.setWidth(1280);
        rectangle.setHeight(60);

        ImageView server1 = new ImageView(Main.loadImage("icon"));
        GridPane.setVgrow(server1, Priority.ALWAYS);
        GridPane.setHgrow(server1, Priority.ALWAYS);
        GridPane.setValignment(server1, VPos.CENTER);
        server1.setTranslateX(20);
        server1.setTranslateY(-5);
        server1.setFitWidth(40);
        server1.setFitHeight(40);
        server1.setOnMouseEntered(e -> server1.setCursor(Cursor.HAND));

        ImageView server2 = new ImageView(Main.loadImage("icon2"));
        GridPane.setVgrow(server2, Priority.ALWAYS);
        GridPane.setHgrow(server2, Priority.ALWAYS);
        GridPane.setValignment(server2, VPos.CENTER);
        server2.setTranslateX(100);
        server2.setTranslateY(-5);
        server2.setFitWidth(40);
        server2.setFitHeight(37.5);
        server2.setOnMouseClicked(e -> {
            if(!LauncherManager.getOnUpdate().equalsIgnoreCase("true")){
                this.panelManager.showPanel(new PanelHomePoutineRP(panelLogin));
            }else {
                new ModalErrorAction();
            }
        });
        server2.setOnMouseEntered(e -> server2.setCursor(Cursor.HAND));

        Separator blueLeftSeparator = new Separator();
        GridPane.setVgrow(blueLeftSeparator, Priority.ALWAYS);
        GridPane.setHgrow(blueLeftSeparator, Priority.ALWAYS);
        blueLeftSeparator.setOrientation(Orientation.HORIZONTAL);
        blueLeftSeparator.setMinWidth(40);
        blueLeftSeparator.setMaxWidth(40);
        blueLeftSeparator.setMinHeight(4);
        blueLeftSeparator.setMaxHeight(4);
        blueLeftSeparator.setTranslateX(20);
        blueLeftSeparator.setTranslateY(25);
        blueLeftSeparator.setStyle("-fx-background-color: rgb(5,179,242); -fx-border-width: 3 3 3 0; -fx-border-color: rgb(5,179,242);");

        Rectangle player = new Rectangle();
        GridPane.setVgrow(player, Priority.ALWAYS);
        GridPane.setHgrow(player, Priority.ALWAYS);
        GridPane.setValignment(player, VPos.CENTER);
        GridPane.setHalignment(player, HPos.RIGHT);
        player.setWidth(200);
        player.setHeight(50);
        player.setTranslateX(-20);
        player.setStyle("-fx-opacity: 0%");
        player.setOnMouseClicked(e -> {
            this.panelManager.showPanel(new PanelProfil(panelLogin));
        });

        ImageView skinHead = new ImageView("https://minotar.net/avatar/" + LauncherManager.getAuthInfos().getUsername() + "/40.png");
        GridPane.setVgrow(skinHead, Priority.ALWAYS);
        GridPane.setHgrow(skinHead, Priority.ALWAYS);
        GridPane.setValignment(skinHead, VPos.CENTER);
        GridPane.setHalignment(skinHead, HPos.RIGHT);
        skinHead.setTranslateX(-175);

        Label pseudoSkin = new Label(LauncherManager.getAuthInfos().getUsername());
        GridPane.setVgrow(pseudoSkin, Priority.ALWAYS);
        GridPane.setHgrow(pseudoSkin, Priority.ALWAYS);
        GridPane.setValignment(pseudoSkin, VPos.CENTER);
        GridPane.setHalignment(pseudoSkin, HPos.RIGHT);
        pseudoSkin.setTranslateX(-40);
        pseudoSkin.setStyle("-fx-text-alignment: center; -fx-font-size: 20px; -fx-text-fill: #5FECA7");

        gridPane.getChildren().addAll(rectangle, server1, server2, blueLeftSeparator, pseudoSkin, skinHead, player);
    }

    private void showServerDescBar(GridPane gridPane){

        ImageView logo = new ImageView(Main.loadImage("talymio"));
        GridPane.setHgrow(logo, Priority.ALWAYS);
        GridPane.setVgrow(logo, Priority.ALWAYS);
        GridPane.setHalignment(logo, HPos.CENTER);
        GridPane.setValignment(logo, VPos.TOP);
        logo.setFitWidth(540);
        logo.setFitHeight(303);

        Label desc = new Label("Talymio est un launcher PvP/Faction en 1.12.2 fondé en 2020.\n" + "Actuellement en Version 1, Talymio est un serveur où réside une communauté hors du commun.\n" + "Un univers unique ou le farm est le pillier du serveur.\n" + "Un Staff présent et à l'écoute sera là pour vous guider durant votre aventure !\n" + "Êtes vous prêt à devenir le meilleur ?");
        GridPane.setHgrow(desc, Priority.ALWAYS);
        GridPane.setVgrow(desc, Priority.ALWAYS);
        GridPane.setHalignment(desc, HPos.CENTER);
        desc.setStyle("-fx-text-alignment: center; -fx-font-size: 18px; -fx-text-fill: white");

        Button installButton = new Button();
        if(BarAPI.getNumberOfFileToDownload() != 0){
            installButton.setText("Mise à jour");
        }else{
            installButton.setText("Lancer le Jeu");
        }
        GridPane.setVgrow(installButton, Priority.ALWAYS);
        GridPane.setHgrow(installButton, Priority.ALWAYS);
        GridPane.setHalignment(installButton, HPos.CENTER);
        installButton.setMinWidth(400);
        installButton.setMaxWidth(400);
        installButton.setMinHeight(50);
        installButton.setMaxHeight(50);
        installButton.setStyle("-fx-background-color: #5FECA7; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 20px; -fx-text-fill: black; ");
        installButton.setTranslateY(150);
        MaterialDesignIconView install = new MaterialDesignIconView(MaterialDesignIcon.DOWNLOAD);
        install.setSize("20px");
        install.setFill(Color.BLACK);
        installButton.setGraphic(install);
        installButton.setOnMouseClicked(e -> {
            LauncherManager.setOnUpdate("true");
            System.out.println(LauncherManager.getOnUpdate());
            sUpdate.getServerRequester().setRewriteEnabled(true);
            sUpdate.addApplication(new FileDeleter());

            Thread t = new Thread(() -> {
                try {
                    sUpdate.start();
                    LauncherManager.setOnUpdate("true");
                }catch (BadServerResponseException | ServerDisabledException | BadServerVersionException | ServerMissingSomethingException | IOException e1) {
                    Platform.runLater(() -> {
                        FxAlert alert = new FxAlert(Alert.AlertType.ERROR, "ElderTale Launcher | Erreur", "Une erreur est survenue", "Si l'erreur perciste veuillez contacter un membre du staff !", e1, "Code d'erreur : 003");
                        alert.setIcon("logo");
                        alert.setImage("logo", 100, 100);
                        alert.show();
                    });
                    e1.printStackTrace();
                    return;
                }
                try {
                    ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(SO_INFOS, GameFolder.BASIC, LauncherManager.getAuthInfos());
                    profile.getVmArgs().addAll(COMPATIBLE ? ramText.exists() ? Integer.parseInt(RamManagerTalymio.getRam()) > 0 ? Arrays.asList("-Xms1G","-Xmx"+ RamManagerTalymio.getRam()+"G") : Arrays.asList("-Xms1G","-Xmx2G") : Arrays.asList("-Xms1G","-Xmx2G") : Arrays.asList("-Xms512M","-Xmx1024M"));
                    ExternalLauncher launcher = new ExternalLauncher(profile);
                    Process p = launcher.launch();
                    ProcessLogManager manager = new ProcessLogManager(p.getInputStream(), new File(DIR, "logs.txt"));
                    manager.start();
                    try {
                        Thread.sleep(5000L);
                        System.exit(0);
                        p.waitFor();
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.exit(0);
                } catch (LaunchException e1) {
                    FxAlert alert = new FxAlert(Alert.AlertType.ERROR, "ElderTale Launcher | Erreur", "Une erreur est survenue", "Si l'erreur perciste veuillez contacter un membre du staff !", e1, "Code d'erreur : 004");
                    alert.setIcon("logo");
                    alert.setImage("logo", 100, 100);
                    alert.show();
                    e1.printStackTrace();
                }
            });
            t.start();
            Timeline tl = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                int val;
                int max;
                int valMb;
                int maxMb;
                long lastDownloaded = 0;
                @Override
                public void handle(ActionEvent event) {
                    if (BarAPI.getNumberOfFileToDownload() != 0) {
                        this.val = ((int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000L));
                        this.max = ((int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000L));

                        this.valMb = (int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000000L);
                        this.maxMb = ((int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000000L));

                        aBigDownloadBar.setProgress(val, max);

                        long downloaded = BarAPI.getNumberOfTotalDownloadedBytes();
                        long inSecond = downloaded - lastDownloaded;
                        lastDownloaded = downloaded;
                        double speed = inSecond / 1000D;

                        aBigDownloadBar.setInfoLabel("Téléchargement: " + this.valMb + " / " + this.maxMb + " MB @ " + speed + "Kb/s");
                    }
                }
            }));
            tl.setCycleCount(Timeline.INDEFINITE);
            tl.play();
        });
        installButton.setOnMouseEntered(e -> installButton.setCursor(Cursor.HAND));

        Button ramButton = new Button();
        GridPane.setVgrow(ramButton, Priority.ALWAYS);
        GridPane.setHgrow(ramButton, Priority.ALWAYS);
        GridPane.setHalignment(ramButton, HPos.CENTER);
        MaterialDesignIconView settingsIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS);
        settingsIcon.setSize("40px");
        settingsIcon.setFill(Color.BLACK);
        ramButton.setStyle("-fx-background-color: #5FECA7; -fx-border-color: #5FECA7; -fx-border-radius: 2px");
        ramButton.setTranslateX(250);
        ramButton.setTranslateY(150);
        ramButton.setMinWidth(50);
        ramButton.setMaxWidth(50);
        ramButton.setMinHeight(50);
        ramButton.setMaxHeight(50);
        ramButton.setGraphic(settingsIcon);
        ramButton.setOnMouseClicked(e -> new RamPanelTalymio());
        ramButton.setOnMouseEntered(e -> ramButton.setCursor(Cursor.HAND));

        aBigDownloadBar = new ABigDownloadBar();
        GridPane.setVgrow(aBigDownloadBar, Priority.ALWAYS);
        GridPane.setHgrow(aBigDownloadBar, Priority.ALWAYS);
        GridPane.setHalignment(aBigDownloadBar, HPos.CENTER);
        aBigDownloadBar.setTranslateY(-100);

        gridPane.getChildren().addAll(logo, desc, installButton, ramButton, aBigDownloadBar);

    }
}
