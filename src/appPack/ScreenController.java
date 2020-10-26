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

    public void updateScene(Scene scene, String key){
        if(sceneMap.get(key) != null){
            sceneMap.remove(key);
        }
        sceneMap.put(key,scene);
    }

    public void showStage() {
        this.mainStage.show();
    }


}
