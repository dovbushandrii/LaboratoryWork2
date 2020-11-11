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
     * @param primaryStage - main stage of page.
     * @throws Exception - throws file opening Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pair<ArrayList<SingleNote>, ArrayList<SingleNote>> notesAndArchive;
        notesAndArchive = FileManager.readFile("notesDataBase.txt");
        Activity mainActivity = new MainPageActivity(new Stage(), "Notepad/Active Notes", notesAndArchive);
        notesAndArchive = (Pair<ArrayList<SingleNote>, ArrayList<SingleNote>>) mainActivity.runActivity();
        FileManager.writeToFile("notesDataBase.txt", notesAndArchive);
    }
}
