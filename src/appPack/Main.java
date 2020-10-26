package appPack;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;



public class Main extends Application {

    private static ArrayList<StringProperty> notes;
    private ScreenController controller;

    public static void main(String[] args) {
        notes = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            StringProperty line = new SimpleStringProperty("Andrey" + Integer.toString(i));
            notes.add(line);
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Notepad/Bloknot");

        this.controller = new ScreenController(primaryStage);
        this.controller.addScene(this.constructMainScene(),"main");
        this.controller.changeScene("main");
        this.controller.showStage();
    }

    private Scene constructMainScene(){
        VBox vbox = new VBox();
        vbox.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        vbox.setAlignment(Pos.TOP_CENTER);

        Parent toolBar = this.constructToolBarLayout();
        Parent mainPage = MainPageLayout.constructLayout(this.notes,this.controller);

        vbox.getChildren().setAll(toolBar,mainPage);
         return new Scene(vbox, 500,500);
    }

    private Parent constructToolBarLayout(){
        ToolBar bar = new ToolBar();
        bar.setMaxHeight(200);
        Button SettingsBTN = new Button("Settings");
        SettingsBTN.setOnAction(actionEvent -> {
            bar.requestFocus();
        });
        Button AboutBTN = new Button("About");
        AboutBTN.setOnAction(actionEvent -> {
            bar.requestFocus();
        });

        bar.getItems().addAll(SettingsBTN,AboutBTN);
        bar.requestFocus();
        return bar;
    }
}
