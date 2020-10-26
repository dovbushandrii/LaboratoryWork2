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
import javafx.scene.input.MouseButton;
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
        primaryStage.show();
    }

    private Scene constructMainScene(){
        VBox vbox = new VBox();
        vbox.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        vbox.setAlignment(Pos.TOP_CENTER);

        Parent toolBar = this.constructToolBarLayout();
        Parent mainPage = constructMainLayout();

        vbox.getChildren().setAll(toolBar,mainPage);
        return new Scene(vbox,this.mainSceneWidth, this.mainSceneHeight);
    }

    public Parent constructMainLayout(){

        ScrollPane scrollMenu = new ScrollPane();
        scrollMenu.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        scrollMenu.setFitToWidth(true);

        scrollMenu.setContent(FillScrollMenu());

        return scrollMenu;
    }

    private Parent FillScrollMenu(){
        VBox box = new VBox();
        box.setFillWidth(true);

        for(int i = 0; i < notes.size(); i++){
            ScreenController copy = this.controller;
            SingleNote note = notes.get(i);

            BindedTextArea textArea = new BindedTextArea(notes.get(i).getNoteProperty());
            textArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                        Parent layout = EditPageLayout.constructLayout(note);
                        Scene editScene = new Scene(layout, 300, 200);
                        controller.showNewStage(editScene, "Edit note");
                        cleanList();
                        controller.setScene(constructMainScene());
                    }
                    else if(mouseEvent.getButton() == MouseButton.MIDDLE){
                        Parent layout = DeletePageLayout.constructLayout(note);
                        Scene deleteScene = new Scene(layout, 300, 200);
                        controller.showNewStage(deleteScene, "Delete note");
                        cleanList();
                        controller.setScene(constructMainScene());
                    }
                    box.requestFocus();
                }
            });

            box.getChildren().add(textArea);
        }
        return box;
    }


    private Parent constructToolBarLayout(){
        ToolBar bar = new ToolBar();
        bar.setMaxHeight(200);

        Button AddBTN = this.addButtonConstruct();

        Button AboutBTN = this.aboutButtonConstruct();

        bar.getItems().addAll(AddBTN,AboutBTN);

        return bar;
    }

    private Button addButtonConstruct(){
        Button AddBTN = new Button("Add Note");
        AddBTN.setOnAction(actionEvent -> {
            SingleNote note = new SingleNote();
            notes.add(note);

            Parent mainLayout = EditPageLayout.constructLayout(notes.get(notes.size()-1));
            Scene editScene = new Scene(mainLayout, 300, 200);
            controller.showNewStage(editScene,"Adding");
            cleanList();
            controller.setScene(constructMainScene());
        });
        return AddBTN;
    }

    private Button aboutButtonConstruct(){
        Button AboutBTN = new Button("About");
        AboutBTN.setOnAction(actionEvent -> {

        });
        return AboutBTN;
    }

    private void cleanList(){
        int i = 0;
        while(i < this.notes.size()){
            if(this.notes.get(i).getNoteProperty().get().equals("")){
                this.notes.remove(i);
            }
            else i++;
        }
    }
}
