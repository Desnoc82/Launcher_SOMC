package fr.deltadesnoc.somc.launcher.utils;

import com.azuriom.azauth.AuthenticationException;
import com.azuriom.azauth.AzAuthenticator;
import fr.deltadesnoc.somc.launcher.ui.components.modal.error.ModalErrorAction;
import fr.deltadesnoc.somc.launcher.ui.panels.PanelLogin;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;

import java.io.IOException;

public class LauncherManager {

    private static String onUpdate = "false";

    private static AuthInfos authInfos;

    private static AzAuthenticator authenticator ;

    public static void auth(String username, String password) throws AuthenticationException, IOException {
        setOnUpdate("false");
        setAuthenticator(new AzAuthenticator("https://talymio.fr"));
        authInfos = getAuthenticator().authenticate(username, password, AuthInfos.class);
        if(!getAuthenticator().verify(getAuthInfos().getAccessToken()).isBanned()){
            PanelLogin.isConnected = true;
        }else{
            new ModalErrorAction();
        }
    }

    public static void setOnUpdate(String onUpdate) {
        LauncherManager.onUpdate = onUpdate;
    }
    public static String getOnUpdate() {
        return onUpdate;
    }

    public static void setAuthInfos(AuthInfos authInfos) {
        LauncherManager.authInfos = authInfos;
    }

    public static AuthInfos getAuthInfos() {
        return authInfos;
    }

    public static void setAuthenticator(AzAuthenticator authenticator) {
        LauncherManager.authenticator = authenticator;
    }

    public static AzAuthenticator getAuthenticator() {
        return authenticator;
    }
}
