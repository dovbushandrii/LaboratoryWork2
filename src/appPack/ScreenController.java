package appPack;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;

public class ScreenController {
    private HashMap<String, Scene> sceneMap = new HashMap<>();
    private Stage mainStage;


    public ScreenController(Stage mainStage){
        this.mainStage = mainStage;
    }

    public void setScene(Scene newScene) {
        mainStage.setScene(newScene);
    }

    public void addScene(Scene newScene, String key) {
        this.sceneMap.put(key, newScene);
    }

    public Boolean changeScene(String key) {
        if(sceneMap.get(key) != null){
            mainStage.setScene(sceneMap.get(key));
            return true;
        }
        return false;
    }


    public void showNewStage(Scene newScene, String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(newScene);
        stage.setAlwaysOnTop(true);
        stage.requestFocus();
        stage.showAndWait();
    }


}
