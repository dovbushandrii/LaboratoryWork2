package appPack;


import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainPageActivity extends Activity {

    private ArrayList<SingleNote> notes;

    public MainPageActivity(Stage stage, String title, ArrayList<SingleNote> notes) {
        this.notes = notes;
        this.activityStage = stage;
        this.title = title;
    }

    @Override
    public Object runActivity() {
        Scene mainScene = this.constructScene(500, 500);
        this.activityStage.setTitle(this.title);
        this.activityStage.setScene(mainScene);
        this.activityStage.show();
        return this.notes;
    }

    @Override
    public Object stopActivity() {
        this.activityStage.close();
        return this.notes;
    }

    private Scene constructScene(double width, double height) {
        VBox vbox = new VBox();
        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        vbox.setAlignment(Pos.TOP_CENTER);

        Parent toolBar = this.constructToolBarLayout();
        Parent mainPage = constructMainLayout();

        vbox.getChildren().setAll(toolBar, mainPage);
        return new Scene(vbox, width, height);
    }

    private Parent constructMainLayout() {

        ScrollPane scrollMenu = new ScrollPane();
        scrollMenu.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        scrollMenu.setFitToWidth(true);

        scrollMenu.setContent(FillScrollMenu());

        return scrollMenu;
    }

    private Parent FillScrollMenu() {
        VBox box = new VBox();
        box.setFillWidth(true);

        for (int i = 0; i < notes.size(); i++) {

            Parent dateAndNoteBox = this.constructDateAndNoteBox(i);

            box.getChildren().add(dateAndNoteBox);
        }
        return box;
    }

    private Parent constructDateAndNoteBox(int i) {
        ContextMenu menu = new ContextMenu();

        MenuItem EditItem = new MenuItem("Edit node");
        EditItem.setOnAction(actionEvent -> {
            EditPageActivity editPageActivity = new EditPageActivity(new Stage(), "Edit note", notes.get(i));
            notes.set(i, (SingleNote) editPageActivity.runActivity());

            cleanList();
            notes = Sorter.sortByDate(notes);
            double currWidth = this.activityStage.getScene().getWidth();
            double currHeight = this.activityStage.getScene().getHeight();
            this.activityStage.setScene(this.constructScene(currWidth, currHeight));

        });

        MenuItem DeleteItem = new MenuItem("Delete node");
        DeleteItem.setOnAction(actionEvent -> {
            DeletePageActivity deletePageActivity = new DeletePageActivity(new Stage(), "Delete note", notes.get(i));
            notes.set(i, (SingleNote) deletePageActivity.runActivity());

            cleanList();
            notes = Sorter.sortByDate(notes);
            double currWidth = this.activityStage.getScene().getWidth();
            double currHeight = this.activityStage.getScene().getHeight();
            this.activityStage.setScene(this.constructScene(currWidth, currHeight));

        });

        menu.getItems().setAll(EditItem, DeleteItem);

        BoundTextArea textArea = new BoundTextArea(notes.get(i).getNoteProperty());
        textArea.setContextMenu(menu);
        textArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        textArea.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                EditPageActivity editPageActivity = new EditPageActivity(new Stage(), "Edit note", notes.get(i));
                notes.set(i, (SingleNote) editPageActivity.runActivity());
            }
            cleanList();
            notes = Sorter.sortByDate(notes);
            double currWidth = this.activityStage.getScene().getWidth();
            double currHeight = this.activityStage.getScene().getHeight();
            this.activityStage.setScene(this.constructScene(currWidth, currHeight));
            ;
        });


        return textArea;
    }

    private Parent constructToolBarLayout() {
        ToolBar bar = new ToolBar();
        bar.setMaxHeight(200);

        Button AddBTN = this.addButtonConstruct();

        Button AboutBTN = this.aboutButtonConstruct();

        bar.getItems().addAll(AddBTN, AboutBTN);

        return bar;
    }

    private Button addButtonConstruct() {
        Button AddBTN = new Button("Add Note");
        AddBTN.setOnAction(actionEvent -> {
            Activity editPageActivity = new EditPageActivity(new Stage(), "Add note", new SingleNote());
            notes.add((SingleNote) editPageActivity.runActivity());
            cleanList();
            notes = Sorter.sortByDate(notes);
            double currWidth = this.activityStage.getScene().getWidth();
            double currHeight = this.activityStage.getScene().getHeight();
            this.activityStage.setScene(this.constructScene(currWidth, currHeight));
        });
        return AddBTN;
    }

    private Button aboutButtonConstruct() {
        Button AboutBTN = new Button("About");
        AboutBTN.setOnAction(actionEvent -> {
            Activity aboutPageActivity = new AboutPageActivity(new Stage(), "About");
            aboutPageActivity.runActivity();
            double currWidth = this.activityStage.getScene().getWidth();
            double currHeight = this.activityStage.getScene().getHeight();
            this.activityStage.setScene(this.constructScene(currWidth, currHeight));
        });
        return AboutBTN;
    }

    private void cleanList() {
        int i = 0;
        while (i < this.notes.size()) {
            if (this.notes.get(i).getNoteProperty().get().equals("")) {
                this.notes.remove(i);
            } else i++;
        }
    }
}
