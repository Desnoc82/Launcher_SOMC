package fr.deltadesnoc.somc.launcher.ui.components.modal.error;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModalErrorAction extends Parent {

    ModalErrorActionScene modalErrorActionScene;
    private static Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    public ModalErrorAction(){
        stage = new Stage();
        stage.setTitle("");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0);");
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root, 400, 150, Color.TRANSPARENT);
        stage.setScene(scene);
        modalErrorActionScene = new ModalErrorActionScene();
        root.getChildren().add(modalErrorActionScene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

}
