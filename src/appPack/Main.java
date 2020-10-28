package appPack;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overload of start method of JavaFx
     * Runs MainPage Activity.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<SingleNote> notes = new ArrayList<>();
        ArrayList<SingleNote> archive = new ArrayList<>();
        Pair<ArrayList<SingleNote>,ArrayList<SingleNote>> notesAndArchive = new Pair<>(notes,archive);
        Activity mainActivity = new MainPageActivity(primaryStage, "Notepad/Active Notes", notesAndArchive);
        mainActivity.runActivity();
    }
}
