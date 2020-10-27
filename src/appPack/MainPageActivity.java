package appPack;


import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainPageActivity extends Activity{

    private ArrayList<SingleNote> notes;

    public MainPageActivity(Stage stage, String title,ArrayList<SingleNote> notes){
        this.notes = notes;
        this.activityStage = stage;
        this.title = title;
    }

    @Override
    public Object runActivity(){
        Scene mainScene = this.constructScene();
        this.activityStage.setTitle(this.title);
        this.activityStage.setScene(mainScene);
        this.activityStage.show();
        return this.notes;
    }

    @Override
    public Object stopActivity(){
        this.activityStage.close();
        return this.notes;
    }

    private Scene constructScene() {
        VBox vbox = new VBox();
        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        vbox.setAlignment(Pos.TOP_CENTER);

        Parent toolBar = this.constructToolBarLayout();
        Parent mainPage = constructMainLayout();

        vbox.getChildren().setAll(toolBar, mainPage);
        return new Scene(vbox, 500,500);
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
            int index = i;
            BindedTextArea textArea = new BindedTextArea(notes.get(i).getNoteProperty());
            textArea.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    EditPageActivity editPageActivity = new EditPageActivity(new Stage(),"Edit note",notes.get(index));
                    notes.set(index,(SingleNote)editPageActivity.runActivity());
                }
                else if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                    DeletePageActivity deletePageActivity = new DeletePageActivity(new Stage(),"Delete note",notes.get(index));
                    notes.set(index,(SingleNote)deletePageActivity.runActivity());
                }
                cleanList();
                notes = Sorter.sortByDate(notes);
                activityStage.setScene(constructScene());
                box.requestFocus();
            });

            box.getChildren().add(textArea);
        }
        return box;
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
            EditPageActivity editPageActivity = new EditPageActivity(new Stage(),"Add note",new SingleNote());
            notes.add((SingleNote)editPageActivity.runActivity());
            cleanList();
            notes = Sorter.sortByDate(notes);
            this.activityStage.setScene(this.constructScene());
        });
        return AddBTN;
    }

    private Button aboutButtonConstruct() {
        Button AboutBTN = new Button("About");
        AboutBTN.setOnAction(actionEvent -> {

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
