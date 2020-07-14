package fr.deltadesnoc.somc.launcher.ram.poutinerp;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RamPanel extends Parent{

	RamScene ramScene;
	private static Stage stage;
	private double xOffset = 0;
	private double yOffset = 0;
	
	public RamPanel() {
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
        Scene scene = new Scene(root, 500, 400, Color.TRANSPARENT);
        stage.setScene(scene);
        ramScene = new RamScene();
		root.getChildren().add(ramScene);
		stage.show();

		if(!ramScene.ramfile.exists()){
			try {
				ramScene.ramfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	
}
