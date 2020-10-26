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

    private static ArrayList<SingleNote> notes;
    private ScreenController controller;
    private Integer mainSceneWidth = 500;
    private Integer mainSceneHeight = 500;

    public static void main(String[] args) {
        notes = new ArrayList<>();
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
        return new Scene(vbox,this.mainSceneWidth, this.mainSceneHeight);
    }

    private Parent constructToolBarLayout(){
        ToolBar bar = new ToolBar();
        bar.setMaxHeight(200);

        Button AddBTN = this.addButtonConstruct();

        Button AboutBTN = this.aboutButtonConstruct();

        bar.getItems().addAll(AddBTN,AboutBTN);
        bar.requestFocus();
        return bar;
    }

    private Button addButtonConstruct(){
        Button AddBTN = new Button("Add Note");
        AddBTN.setOnAction(actionEvent -> {
            SingleNote note = new SingleNote();
            notes.add(note);
            controller.updateScene(constructMainScene(),"main");

            Parent mainLayout = EditPageLayout.constructLayout(notes.get(notes.size()-1),controller);
            Scene editScene = new Scene(mainLayout, 500, 500);

            controller.setScene(editScene);
        });
        return AddBTN;
    }

    private Button aboutButtonConstruct(){
        Button AboutBTN = new Button("About");
        AboutBTN.setOnAction(actionEvent -> {

        });
        return AboutBTN;
    }

}
