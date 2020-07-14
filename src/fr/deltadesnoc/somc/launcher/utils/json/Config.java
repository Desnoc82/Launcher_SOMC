package fr.deltadesnoc.somc.launcher.utils.json;

import com.google.gson.*;
import fr.deltadesnoc.somc.launcher.ui.panels.PanelHomePoutineRP;
import fr.deltadesnoc.somc.launcher.ui.panels.PanelHomeTalymio;
import fr.deltadesnoc.somc.launcher.ui.panels.PanelLogin;

import java.io.*;

public class Config {

    private String usernameJSON;
    private String passwordJSON;
    private static PanelLogin panelLogin;

    public static void save(String username, String password){

        Gson gson = new GsonBuilder().create();

        Coordonnee coordonnee = createCoordonneeObject(username, password);

        String json = gson.toJson(coordonnee);

        try (FileWriter writer = new FileWriter(PanelHomeTalymio.DIR.getAbsolutePath().toString() + "\\coordonnee.json")) {
            gson.toJson(coordonnee, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(PanelHomePoutineRP.PO__DIR.getAbsolutePath().toString() + "\\coordonnee.json")) {
            gson.toJson(coordonnee, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getInfo(String info) {

        try(Reader reader = new FileReader(PanelHomePoutineRP.PO__DIR.getAbsolutePath().toString() + "\\coordonnee.json")){
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            return String.valueOf(jsonObject.get(info)).replaceAll("\"", "");
        }catch (IOException e){
            e.getMessage();
        }
        return "";
    }

    public static JsonElement getInfo2(String info) {
        JsonElement element = new JsonArray();
        try(Reader reader = new FileReader(PanelHomePoutineRP.PO__DIR.getAbsolutePath().toString() + "\\coordonnee.json")){
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            return jsonObject.get(info);
        }catch (IOException e){
            e.getMessage();
        }
        return element;
    }

    private static Coordonnee createCoordonneeObject(String username, String password){

        Coordonnee coordonnee = new Coordonnee();

        coordonnee.setPseudo(username);
        coordonnee.setPassword(password);

        return coordonnee;
    }

    public void setUsernameJSON(String usernameJSON) {
        this.usernameJSON = usernameJSON;
    }

    public void setPasswordJSON(String passwordJSON) {
        this.passwordJSON = passwordJSON;
    }

    public String getUsernameJSON() {
        return usernameJSON;
    }

    public String getPasswordJSON() {
        return passwordJSON;
    }
}
