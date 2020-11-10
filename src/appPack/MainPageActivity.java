package appPack;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

public class MainPageActivity extends Activity {

    /**
     * Size of window that creates this activity.
     */
    private int widthOfScene = 500;
    private int heightOfScene = 500;

    /**
     * @notes - List of SingleNote objects
     * Contains all notes that shown on page.
     */
    private ArrayList<SingleNote> notes;

    /**
     * @archive - List of SingleNote objects
     * Contains all archived notes.
     */
    private ArrayList<SingleNote> archive;

    /**
     * @active - List of SingleNote objects
     * Contains all active notes.
     */
    private ArrayList<SingleNote> active;

    /**
     * Types of notes shown on page
     * (ALL,WORK,PERSONAL,STUDY)
     */
    private ContextEnum contextType;

    /**
     * Constructs an object of EditPageActivity.
     *
     * @param stage           - stage of activity
     * @param title           - title of window
     * @param notesAndArchive - list on active notes
     */
    public MainPageActivity(Stage stage, String title, Pair<ArrayList<SingleNote>, ArrayList<SingleNote>> notesAndArchive) {
        this.active = notesAndArchive.getKey();
        this.notes = this.active;
        this.activityStage = stage;
        this.title = title;
        this.archive = notesAndArchive.getValue();
        this.contextType = ContextEnum.ALL;
    }

    /**
     * Main method to start activity.
     * As result returns ArrayList<SingleNote>
     *
     * @return - returns result of activity.
     */
    @Override
    public Object runActivity() {
        Scene mainScene = this.constructScene(widthOfScene, heightOfScene);
        this.activityStage.setTitle(this.title);
        this.activityStage.setScene(mainScene);
        this.activityStage.show();
        if (this.activityStage.getTitle().equals("Notepad/Active Notes")) {
            return new Pair(this.active, this.archive);
        } else {
            return new Pair(this.archive, this.active);
        }
    }

    /**
     * Closes the window that was created
     * by runActivity() method.
     */
    @Override
    public Object stopActivity() {
        this.activityStage.close();
        if (this.activityStage.getTitle().equals("Notepad/Active Notes")) {
            return new Pair(this.active, this.archive);
        } else {
            return new Pair(this.archive, this.active);
        }
    }

    /**
     * Constructs scene with
     * layout that object constructs
     * by constructLayout() method.
     * That scene will be set on stage that
     * object got by Constructor.
     *
     * @param width  - width of created scene
     * @param height - height of created scene
     * @return - returns created scene
     */
    private Scene constructScene(double width, double height) {
        VBox vbox = new VBox();
        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        vbox.setAlignment(Pos.TOP_CENTER);

        Parent toolBar = this.constructToolBarLayout();
        Parent mainPage = constructMainLayout();

        vbox.getChildren().setAll(toolBar, mainPage);
        return new Scene(vbox, width, height);
    }

    /**
     * Deletes empty notes
     * and updates stage.
     */
    private void reconstructStage() {
        cleanList();
        notes = Sorter.sortByDateAndContextType(this.active, this.contextType);
        double currWidth = this.activityStage.getScene().getWidth();
        double currHeight = this.activityStage.getScene().getHeight();
        this.activityStage.setScene(this.constructScene(currWidth, currHeight));
    }

    /**
     * Constructs layout of scene.
     * Contains toolbar and list with
     * notes.
     *
     * @return - returns constructed layout(Parent)
     */
    private Parent constructMainLayout() {

        ScrollPane scrollMenu = new ScrollPane();
        scrollMenu.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        scrollMenu.setFitToWidth(true);

        scrollMenu.setContent(FillScrollMenu());

        return scrollMenu;
    }

    /**
     * Constructs ScrollMenu with active notes.
     *
     * @return - returns ScrollPane(Parent).
     */
    private Parent FillScrollMenu() {
        VBox box = new VBox();
        box.setFillWidth(true);

        for (int i = 0; i < notes.size(); i++) {

            Parent dateAndNoteBox = this.constructNoteBox(i);

            box.getChildren().add(dateAndNoteBox);
        }
        return box;
    }

    /**
     * Constructs element of ScrollMenu -
     * NoteBox.
     * Has custom ContextMenu: Edit,Archive,Delete note.
     *
     * @param i - index of note in @notes
     * @return - returns HBox and Note.
     */
    private Parent constructNoteBox(int i) {
        ContextMenu menu = new ContextMenu();

        MenuItem EditItem = new MenuItem("Edit note");
        EditItem.setOnAction(actionEvent -> {
            EditPageActivity editPageActivity = new EditPageActivity(new Stage(), "Edit note", notes.get(i));
            notes.set(i, (SingleNote) editPageActivity.runActivity());

            reconstructStage();
        });

        MenuItem ArchiveItem = new MenuItem("Archive/Unarchive note");
        ArchiveItem.setOnAction(actionEvent -> {

            archive.add(notes.get(i));
            notes.set(i, new SingleNote(""));

            reconstructStage();
        });

        MenuItem DeleteItem = new MenuItem("Delete note");
        DeleteItem.setOnAction(actionEvent -> {
            DeletePageActivity deletePageActivity = new DeletePageActivity(new Stage(), "Delete note", notes.get(i));
            notes.set(i, (SingleNote) deletePageActivity.runActivity());

            reconstructStage();
        });

        menu.getItems().setAll(EditItem, ArchiveItem, DeleteItem);

        BoundTextArea textArea = new BoundTextArea(notes.get(i).getNoteProperty());
        textArea.setContextMenu(menu);
        textArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        textArea.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                EditPageActivity editPageActivity = new EditPageActivity(new Stage(), "Edit note", notes.get(i));
                notes.set(i, (SingleNote) editPageActivity.runActivity());
            }
            reconstructStage();
        });


        return textArea;
    }

    /**
     * Constructs ToolBar with AddNote button
     * and About info-button.
     *
     * @return - returns constructed ToolBar(Parent)
     */
    private Parent constructToolBarLayout() {
        ToolBar bar = new ToolBar();
        bar.setMaxHeight(200);

        Button AddBTN = this.addButtonConstruct();

        Button SwtchBTN = this.switchButtonConstruct();

        Button AboutBTN = this.aboutButtonConstruct();

        ChoiceBox conTypeCB = this.constructContextTypeChoiseBox();

        bar.getItems().addAll(AddBTN, SwtchBTN, AboutBTN, conTypeCB);

        return bar;
    }


    /**
     * Constructs Add button for ToolBar.
     *
     * @return - returns constructed Button.
     */
    private Button addButtonConstruct() {
        Button AddBTN = new Button("Add Note");
        AddBTN.setOnAction(actionEvent -> {
            Activity editPageActivity = new EditPageActivity(new Stage(), "Add note", new SingleNote());
            notes.add((SingleNote) editPageActivity.runActivity());
            reconstructStage();
        });
        return AddBTN;
    }

    /**
     * Constructs switch between archive and
     * active notes button for ToolBar.
     * Just simply switches notes list andg
     * archive list.
     *
     * @return - returns constructed Button.
     */
    private Button switchButtonConstruct() {
        Button AddBTN = new Button("Switch Active/Archive");
        AddBTN.setOnAction(actionEvent -> {
            if (this.activityStage.getTitle().equals("Notepad/Active Notes")) {
                this.activityStage.setTitle("Notepad/Archive");
            } else {
                this.activityStage.setTitle("Notepad/Active Notes");
            }
            ArrayList<SingleNote> copy = this.active;
            this.active = this.archive;
            this.archive = copy;
            reconstructStage();
        });
        return AddBTN;
    }

    /**
     * Constructs About button for ToolBar.
     *
     * @return - returns constructed Button.
     */
    private Button aboutButtonConstruct() {
        Button AboutBTN = new Button("About");
        AboutBTN.setOnAction(actionEvent -> {
            Activity aboutPageActivity = new AboutPageActivity(new Stage(), "About");
            aboutPageActivity.runActivity();
            reconstructStage();
        });
        return AboutBTN;
    }

    /**
     * Constructs ChoiseBox with options for
     * notes that shown on page.
     * May be shown:
     * All notes,Personal notes,
     * Work notes, Study notes.
     *
     * @return - returns constructed choice box
     */
    private ChoiceBox constructContextTypeChoiseBox() {

        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
                "All notes", "Personal notes", "Work notes", "Study notes")
        );

        if (this.contextType.equals(ContextEnum.PERSONAL)) {
            cb.setValue("Personal notes");
        } else if (this.contextType.equals(ContextEnum.WORK)) {
            cb.setValue("Work notes");
        } else if (this.contextType.equals(ContextEnum.STUDY)) {
            cb.setValue("Study notes");
        } else if (this.contextType.equals(ContextEnum.ALL)) {
            cb.setValue("All notes");
        }

        cb.setOnAction(actionEvent -> {
            if (cb.getValue().equals("Personal notes")) {
                this.contextType = ContextEnum.PERSONAL;
            } else if (cb.getValue().equals("Work notes")) {
                this.contextType = ContextEnum.WORK;
            } else if (cb.getValue().equals("Study notes")) {
                this.contextType = ContextEnum.STUDY;
            } else if (cb.getValue().equals("All notes")) {
                this.contextType = ContextEnum.ALL;
            }
            this.reconstructStage();
        });

        return cb;
    }


    /**
     * Deletes empty notes from @active.
     */
    private void cleanList() {
        int i = 0;
        while (i < this.active.size()) {
            if (this.active.get(i).getNoteProperty().get().equals("")) {
                this.active.remove(i);
            } else i++;
        }
    }
}
