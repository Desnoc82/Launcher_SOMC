package fr.deltadesnoc.somc.launcher;

import fr.arinonia.arilibfx.AriLibFX;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FxApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(Main.loadImage("logo"));
        new SOLauncher().init(stage);

    }

}
