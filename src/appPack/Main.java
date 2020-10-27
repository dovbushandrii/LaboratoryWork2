package appPack;

import javafx.application.Application;
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
