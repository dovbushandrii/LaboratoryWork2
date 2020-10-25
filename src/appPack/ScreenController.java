package appPack;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class ScreenController {
    private HashMap<String,Scene> sceneMap = new HashMap<>();
    private Stage mainStage;

    public ScreenController(Stage mainStage){
        this.mainStage = mainStage;
    }

    public ScreenController(Stage mainStage, Scene main, String key){
        this.mainStage = mainStage;
        mainStage.setScene(main);
        this.sceneMap.put(key,main);
    }

    public ScreenController(Stage mainStage, HashMap<String,Scene> sceneMap, String key){
        this.mainStage = mainStage;
        mainStage.setScene(sceneMap.get(key));
        this.sceneMap = sceneMap;
    }

    public Boolean changeScene(String key){
        mainStage.setScene(sceneMap.get(key));
        return true;
    }

    public Boolean changeScene(Scene newScene, String key){
        mainStage.setScene(newScene);
        this.sceneMap.put(key,newScene);
        return true;
    }

    public void addScene(Scene newScene, String key){
        this.sceneMap.put(key,newScene);
    }

    public void showStage(){
        this.mainStage.show();
    }

    public void removeScene(String key){
        sceneMap.remove(key);
    }

}
