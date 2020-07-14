package fr.deltadesnoc.somc.launcher.ram.poutinerp;

import com.sun.management.OperatingSystemMXBean;
import fr.deltadesnoc.somc.launcher.ram.talymio.RamManagerTalymio;
import fr.deltadesnoc.somc.launcher.ram.talymio.RamPanelTalymio;
import fr.deltadesnoc.somc.launcher.ui.panels.PanelHomePoutineRP;
import fr.deltadesnoc.somc.launcher.utils.LauncherManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class RamScene extends Parent{

	public File ramfile = new File(PanelHomePoutineRP.PO__DIR, "ram.properties");

	public RamScene() {

		if(!ramfile.exists()){
			try {
				ramfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Rectangle rectangle = new Rectangle();
		rectangle.setX(0);
		rectangle.setY(0);
		rectangle.setWidth(500);
		rectangle.setHeight(400);
		rectangle.setFill(Color.rgb(59, 59, 59));
		rectangle.setArcHeight(105);
		rectangle.setArcWidth(50);

		Rectangle bar = new Rectangle();
		bar.setX(4);
		bar.setY(-1);
		bar.setWidth(492);
		bar.setHeight(40);
		bar.setFill(Color.rgb(30, 30, 30));
		bar.setArcHeight(50);
		bar.setArcWidth(50);

		Label label = new Label();
		label.setText(ramfile.exists() ? RamManagerTalymio.getRam().toString() + "Go" : "1Go");
		label.setLayoutX(198);
		label.setLayoutY(80);
		label.setStyle("-fx-text-fill: #5FECA7; -fx-font-size: 64px;");
		Slider slider = new Slider();
		slider.setPrefWidth(400.0d);
		slider.setPrefHeight(20.0d);
		slider.setLayoutX(50);
		slider.setLayoutY(250);
		slider.setMin(1.0d);
		slider.setMax(getPhysicalMemoryBytes());
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,Number oldValue, Number newValue) {
				label.setText(newValue.intValue() + "Go");
				RamManagerTalymio.setRam(newValue.intValue() + "Go");

			}
		});
		slider.setValue(ramfile.exists() ? Integer.parseInt(RamManagerTalymio.getRam()) : 1);
		slider.setStyle("-fx-control-inner-background: #5FECA7;");

		Line line = new Line();
		line.setStartX(400.0f);
		line.setStartY(200.0f);
		line.setEndX(487.0f);
		line.setEndY(34.0f);
		line.setStrokeWidth(2);
		line.setStroke(Color.rgb(30, 30, 30));

		Line line2 = new Line();
		line2.setStartX(14.0f);
		line2.setStartY(34.0f);
		line2.setEndX(87.0f);
		line2.setEndY(200);
		line2.setStrokeWidth(2);
		line2.setStroke(Color.rgb(30, 30, 30));

		Line line3 = new Line(400,200,86,200);
		line2.setStrokeWidth(2);
		line2.setStroke(Color.rgb(30, 30, 30));

		Button button = new Button("Valider");
		button.setPrefSize(120, 40);
		button.setStyle("-fx-background-color: #5FECA7; -fx-text-fill: rgb(42, 42, 42); -fx-font-size: 18px");
		button.setLayoutX(198);
		button.setLayoutY(300);
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				button.setStyle("-fx-background-color: rgb(75, 75, 75); -fx-text-fill: #5FECA7; -fx-font-size: 18px");
			}

		});
		button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				button.setStyle("-fx-background-color: #5FECA7; -fx-text-fill: rgb(42, 42, 42); -fx-font-size: 18px");
			}

		});
		button.setOnAction((e)->{
			RamPanelTalymio.getStage().close();
		});
		getChildren().addAll(rectangle, slider, bar, line, line2, line3, label, button);

	}


	public static final long getPhysicalMemoryBytes() {
		return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize()  / (1024 * 1024 * 1024);
	}

}
