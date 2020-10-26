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

    public static void main(String[] args) {
        notes = new ArrayList<>();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Activity mainActivity = new MainPageActivity(primaryStage,"Notepad/Bloknot",this.notes);
        mainActivity.runActivity();
    }
}
