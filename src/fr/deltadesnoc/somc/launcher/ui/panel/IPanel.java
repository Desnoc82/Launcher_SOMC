package fr.deltadesnoc.somc.launcher.ui.panel;

import fr.deltadesnoc.somc.launcher.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {

    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();

}
