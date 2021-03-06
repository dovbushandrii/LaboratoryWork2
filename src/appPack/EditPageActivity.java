package appPack;


import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.event.ChangeListener;

/**
 * DeletePageActivity - class that can create
 * window for the editing of node confirmation.
 * Returns edited SingleNote object.
 * In note was not edited, time of last edit
 * wont be updated.
 */
public class EditPageActivity extends Activity {

    /**
     * Size of window that creates this activity.
     */
    private int widthOfScene = 300;
    private int heightOfScene = 200;

    /**
     * @note - result of EditPageActivity.
     * Returns edited note. In note was not edited,
     * time of last edit wont be updated.
     * If note is empty, it will be removed
     * after Activity stop.
     */
    private final SingleNote note;

    /**
     * Constructs an object of EditPageActivity.
     *
     * @param stage - stage of activity
     * @param title - title of window
     * @param note  - note that can be edited.
     */
    public EditPageActivity(Stage stage, String title, SingleNote note) {
        this.note = note;
        this.activityStage = stage;
        this.title = title;
    }

    /**
     * Main method to start activity.
     * As result returns edited note. In note was not edited,
     * time of last edit wont be updated.
     * If note is empty, it will be removed
     * after Activity stop.
     *
     * @return - returns result of activity.
     */
    @Override
    public Object runActivity() {
        Scene mainScene = this.constructScene();
        this.activityStage.setTitle(this.title);
        this.activityStage.setScene(mainScene);
        this.activityStage.showAndWait();
        return note;
    }

    /**
     * Closes the window that was created
     * by runActivity() method.
     */
    @Override
    public Object stopActivity() {
        this.activityStage.close();
        return note;
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
     * Contains only Date&Time of note (Label),
     * Context type of note menu
     * and button "Save"
     * to confirm edit.
     *
     * @return - returns constructed layout(Parent)
     */
    private Parent constructLayout() {
        String oldNote = note.getNoteProperty().get();

        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(5);

        Label label = new Label();
        label.setText(this.note.getTimeString());
        label.setWrapText(true);
        label.setAlignment(Pos.TOP_CENTER);

        BoundTextArea textArea = new BoundTextArea(note.getNoteProperty());
        textArea.positionCaret(note.getNoteProperty().get().length());
        textArea.setPrefRowCount(Integer.MAX_VALUE);
        textArea.setPrefColumnCount(Integer.MAX_VALUE);
        textArea.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                pane.requestFocus();
            }
        });

        ChoiceBox cb = this.constructContextTypeChoiseBox();

        Button btn = new Button("Save");
        btn.setMaxSize(200, Double.MAX_VALUE);
        btn.setOnAction(actionEvent -> {
            if (!oldNote.equals(textArea.getText())) {
                note.setCurrentTime();
            }
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });

        pane.getChildren().addAll(label, textArea, cb, btn);
        return pane;
    }

    /**
     * Constructs ChoiseBox with options for
     * note Context type(WORK,PERSONAL,STUDY)
     *
     * @return - returns constructed choice box
     */
    private ChoiceBox constructContextTypeChoiseBox(){

        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
                "Personal note","Work note","Study note")
        );
        if(this.note.getConType().equals(ContextEnum.PERSONAL)){
            cb.setValue("Personal note");
        }
        else if (this.note.getConType().equals(ContextEnum.WORK)){
            cb.setValue("Work note");
        }
        else if (this.note.getConType().equals(ContextEnum.STUDY)){
            cb.setValue("Study note");
        }
        cb.setOnAction(actionEvent -> {
            if(cb.getValue().equals("Personal note")){
                this.note.setConType(ContextEnum.PERSONAL);
            }
            else if (cb.getValue().equals("Work note")){
                this.note.setConType(ContextEnum.WORK);
            }
            else if (cb.getValue().equals("Study note")){
                this.note.setConType(ContextEnum.STUDY);
            }
        });

        return cb;
    }
}
