package fr.deltadesnoc.somc.launcher.ui.components.modal.question;

import fr.deltadesnoc.somc.launcher.ui.components.modal.error.ModalErrorAction;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ModalQuestionProfilScene extends Parent {

    public ModalQuestionProfilScene(){
        Rectangle rectangle = new Rectangle();
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setWidth(400);
        rectangle.setHeight(150);
        rectangle.setFill(Color.rgb(59, 59, 59));
        rectangle.setArcHeight(105);
        rectangle.setArcWidth(50);

        Label label = new Label("Voulez-vous vraiment\n" + "vous déconnecter ?");
        label.setTranslateX(100);
        label.setTranslateY(10);
        label.setStyle("-fx-text-fill: rgb(35,194,182); -fx-font-size: 25px; -fx-text-alignment: center");

        Button close = new Button("Fermer");
        close.setMinWidth(100);
        close.setMaxWidth(100);
        close.setMinHeight(40);
        close.setMaxHeight(40);
        close.setTranslateX(150);
        close.setTranslateY(80);
        close.setStyle("-fx-background-color: #5FECA7; -fx-border-radius: 0px; -fx-background-insets: 0; -fx-font-size: 18px; -fx-text-fill: black; ");
        close.setOnMouseEntered(e -> close.setCursor(Cursor.HAND));
        close.setOnMouseClicked(e -> ModalErrorAction.getStage().close());

        getChildren().addAll(rectangle, label, close);
    }

}

