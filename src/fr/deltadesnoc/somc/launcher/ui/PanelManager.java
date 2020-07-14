package fr.deltadesnoc.somc.launcher.ui;

import fr.arinonia.arilibfx.AriLibFX;
import fr.arinonia.arilibfx.ui.utils.ResizeHelper;

import fr.deltadesnoc.somc.launcher.SOLauncher;
import fr.deltadesnoc.somc.launcher.ui.panel.IPanel;
import fr.deltadesnoc.somc.launcher.ui.panels.includes.TopPanel;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {

    private final SOLauncher soLauncher;
    private final Stage stage;
    private GridPane layout;
    private TopPanel topPanel = new TopPanel();
    private GridPane centerPanel = new GridPane();

    public PanelManager(SOLauncher soLauncher, Stage stage) {
        this.soLauncher = soLauncher;
        this.stage = stage;
    }

    public void init(){
        this.stage.setTitle("SOMC - Service Of Media Company");
        this.stage.setMinWidth(1280);
        this.stage.setWidth(1280);
        this.stage.setMinHeight(720);
        this.stage.setHeight(720);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.centerOnScreen();
        this.stage.show();

        this.layout = new GridPane();
        this.layout.setStyle(AriLibFX.setResponsiveBackground("https://www.zupimages.net/up/20/28/q0ai.jpg"));
        this.stage.setScene(new Scene(this.layout));

        RowConstraints topPanelConstraints = new RowConstraints();
        topPanelConstraints.setValignment(VPos.TOP);
        topPanelConstraints.setMinHeight(25);
        topPanelConstraints.setMaxHeight(25);
        this.layout.getRowConstraints().addAll(topPanelConstraints, new RowConstraints());
        this.layout.add(this.topPanel.getLayout(), 0, 0);
        this.topPanel.init(this);

        this.layout.add(this.centerPanel, 0, 1);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);
        ResizeHelper.addResizeListener(this.stage);

    }

    public void showPanel(IPanel panel){
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
        panel.onShow();
    }


    public Stage getStage() { return stage; }
    public SOLauncher getPCLauncher() { return soLauncher; }
    public TopPanel getTopPanel() { return topPanel; }
}
