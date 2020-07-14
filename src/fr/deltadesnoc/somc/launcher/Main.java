package fr.deltadesnoc.somc.launcher;

import fr.arinonia.arilibfx.AriLibFX;
import fr.arinonia.arilibfx.utils.AriLogger;
import fr.deltadesnoc.somc.launcher.utils.LauncherManager;
import javafx.application.Application;
import javafx.scene.image.Image;

import javax.swing.*;

public class Main {

    public static AriLogger logger;

    public static void main(String[] args) {
        AriLibFX.setResourcePath("/fr/deltadesnoc/somc/launcher/resources/image/");

        logger = new AriLogger("SOMC");

        try {
            Class.forName("javafx.application.Application");
            Application.launch(FxApplication.class, args);
        }catch (ClassNotFoundException e){
            logger.warn("JavaFx not found :cry:");
            JOptionPane.showMessageDialog(null, "Une erreur avec Java à été détectée.\n" + e.getMessage() + "\nNot Found", "Erreur Java", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static Image loadImage(String image) {
        return new Image(Main.class.getResourceAsStream(AriLibFX.getResourcePath() + "/" + image + ".png"));
    }

}
