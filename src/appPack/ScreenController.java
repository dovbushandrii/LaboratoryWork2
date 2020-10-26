package appPack;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class ScreenController {
    private HashMap<String, Scene> sceneMap = new HashMap<>();
    private Stage mainStage;


    public ScreenController(Stage mainStage){
        this.mainStage = mainStage;
    }
    public ScreenController(Stage mainStage, Scene main, String key) {
        this.mainStage = mainStage;
        mainStage.setScene(main);
        this.sceneMap.put(key, main);
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

    public void showStage() {
        this.mainStage.show();
    }


}
