package appPack;

import javafx.application.Application;
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
    private static ArrayList<String> notes;
    private ScreenController controller;

    public static void main(String[] args) {
        notes = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            notes.add("Andrey");
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Notepad/Bloknot");

        Parent mainLayout = this.constructMenuLayout(this.notes);
        Scene mainScene = new Scene(mainLayout, Double.MAX_VALUE, Double.MAX_VALUE);

        this.controller = new ScreenController(primaryStage);
        this.controller.addScene(mainScene,"main");
        this.controller.changeScene("main");
        this.controller.showStage();
    }

    private Parent constructEditLayout(final Integer index){

        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);

        Parent bar = this.constructToolBarLayout();

        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(Integer.MAX_VALUE);
        textArea.setPrefColumnCount(15);
        textArea.setText(notes.get(index));
        textArea.setWrapText(true);
        textArea.positionCaret(notes.get(index).length());
        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ESCAPE){
                    pane.requestFocus();
                }
            }
        });

        Button btn = new Button("Save");
        pane.getChildren().addAll(bar,textArea,btn);
        btn.setMaxSize(200,Double.MAX_VALUE);
        btn.setOnAction(actionEvent ->  {
            notes.set(index,textArea.getText());
            controller.removeScene("edit");
            Parent mainLayout = this.constructMenuLayout(this.notes);
            Scene mainScene = new Scene(mainLayout, Double.MAX_VALUE, Double.MAX_VALUE);
            controller.changeScene(mainScene,"main");
            pane.requestFocus();
        });

        return pane;
    }

    private Parent constructMenuLayout(ArrayList<String> notes){
        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);

        Parent bar = this.constructToolBarLayout();

        ScrollPane scrollMenu = new ScrollPane();
        scrollMenu.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        scrollMenu.setFitToWidth(true);

        VBox box = new VBox();
        box.setFillWidth(true);
        ArrayList<Node> arr = new ArrayList<>();

        for(int i = 0; i < notes.size(); i++){

            Parent mainLayout = this.constructEditLayout(i);
            Scene editScene = new Scene(mainLayout, Double.MAX_VALUE, Double.MAX_VALUE);

            TextArea textArea = new TextArea();
            textArea.setPrefRowCount(6);
            textArea.setPrefColumnCount(5);
            textArea.setText(notes.get(i));
            textArea.setWrapText(true);
            textArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    controller.changeScene(editScene,"edit");
                    controller.removeScene("main");
                    pane.requestFocus();
                }
            });
            textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if(keyEvent.getCode() == KeyCode.ESCAPE){
                        pane.requestFocus();
                    }
                }
            });
            arr.add(textArea);
        }

        box.getChildren().setAll(arr);

        scrollMenu.setContent(box);

        pane.getChildren().setAll(bar,scrollMenu);

        return pane;
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
