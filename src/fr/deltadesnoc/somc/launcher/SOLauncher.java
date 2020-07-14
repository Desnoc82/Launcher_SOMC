package fr.deltadesnoc.somc.launcher;


import com.azuriom.azauth.AuthenticationException;
import fr.deltadesnoc.somc.launcher.ui.PanelManager;
import fr.deltadesnoc.somc.launcher.ui.panels.PanelHomeTalymio;
import fr.deltadesnoc.somc.launcher.ui.panels.PanelLogin;
import fr.deltadesnoc.somc.launcher.utils.LauncherManager;
import fr.deltadesnoc.somc.launcher.utils.json.Config;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SOLauncher {

    private PanelManager panelManager;
    private PanelLogin panelLogin;
    private File json = new File(PanelHomeTalymio.DIR.getAbsolutePath().toString() + "\\coordonnee.json");

    public void init(Stage stage){

        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();

        if(json.exists()){
            if(Config.getInfo2("pseudo") != null) {
                if (Config.getInfo2("password") != null) {
                    try {
                        LauncherManager.auth(Config.getInfo("pseudo"), Config.getInfo("password"));
                        if(panelLogin.isConnected){
                            this.panelManager.showPanel(new PanelHomeTalymio(panelLogin));
                        }
                    } catch (AuthenticationException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            this.panelManager.showPanel(new PanelLogin());
        }

    }

}
