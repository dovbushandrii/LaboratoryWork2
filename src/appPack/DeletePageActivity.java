package appPack;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * DeletePageActivity - class that can create
 * window for the delete of node confirmation.
 * If note is deleted, this Activity returns
 * empty Note that automatically deletes in
 * List of Notes.
 */
public class DeletePageActivity extends Activity {

    /**
     * Size of window that creates this activity.
     */
    private int widthOfScene = 300;
    private int heightOfScene = 200;

    /**
     * @note - result of DeletePageActivity.
     * Can be empty or as it was given in
     * Constructor.
     */
    private final SingleNote note;

    /**
     * Constructs object of DeletePageActivity.
     *
     * @param stage - stage of activity
     * @param title - title of window
     * @param note  - note that can be deleted.
     */
    public DeletePageActivity(Stage stage, String title, SingleNote note) {
        this.note = note;
        this.activityStage = stage;
        this.title = title;
    }

    /**
     * Main method to start activity.
     * If note is deleted, this Activity returns
     * empty Note that automatically deletes in
     * List of Notes.
     * If note is not deleted returns same note
     * as it was given in Constructor.
     *
     * @return - returns result of activity.
     */
    @Override
    public Object runActivity() {
        Scene mainScene = this.constructScene();
        this.activityStage.setTitle(this.title);
        this.activityStage.setScene(mainScene);
        this.activityStage.setResizable(false);
        this.activityStage.showAndWait();
        return this.note;
    }

    /**
     * Closes the window that was created
     * by runActivity() method.
     * If note is deleted, this Activity returns
     * empty Note that automatically deletes in
     * List of Notes.
     * If note is not deleted returns same note
     * as it was given in Constructor.
     *
     * @return - default return of result
     * of activity.
     */
    @Override
    public Object stopActivity() {
        this.activityStage.close();
        return this.note;
    }

    /**
     * Constructs scene with width = widthOfScene,
     * and height = heightOfScene
     * and layout that object constructs
     * by constructLayout() method.
     * That scene will be set on stage that
     * object got by Constructor.
     *
     * @return - returns constructed Scene.
     */
    private Scene constructScene() {
        Parent layout = this.constructLayout();
        return new Scene(layout, this.widthOfScene, this.heightOfScene);
    }

    /**
     * Constructs layout of scene.
     * Contains only text (Label)
     * and buttons "Yes" and "No"
     * to confirm or not deleting of note.
     *
     * @return - returns constructed layout(Parent)
     */
    private Parent constructLayout() {

        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(5);

        Label label = new Label();
        label.setText("\n\nAre you sure?");

        Button btnYES = new Button("Yes");
        btnYES.setMaxSize(200, Double.MAX_VALUE);
        btnYES.setOnAction(actionEvent -> {
            note.getNoteProperty().set("");
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });

        Button btnNO = new Button("No");
        btnNO.setMaxSize(200, Double.MAX_VALUE);
        btnNO.setOnAction(actionEvent -> {
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });

        pane.getChildren().addAll(label, btnYES, btnNO);
        return pane;
    }
}
