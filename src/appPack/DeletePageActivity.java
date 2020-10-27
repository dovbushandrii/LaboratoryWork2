package appPack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeletePageActivity extends Activity{

    private SingleNote note;

    public DeletePageActivity(Stage stage, String title,SingleNote note){
        this.note = note;
        this.activityStage = stage;
        this.title = title;
    }

    @Override
    public Object runActivity(){
        Scene mainScene = this.constructScene();
        this.activityStage.setTitle(this.title);
        this.activityStage.setScene(mainScene);
        this.activityStage.showAndWait();
        return this.note;
    }

    @Override
    public Object stopActivity(){
        this.activityStage.close();
        return this.note;
    }

    private Scene constructScene(){
        Parent layout = this.constructLayout();
        return new Scene(layout, 300,200);
    }

    private Parent constructLayout(){

        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(5);

        Label label = new Label();
        label.setText("Are you sure?");

        Button btnYES = new Button("Yes");
        btnYES.setMaxSize(200,Double.MAX_VALUE);
        btnYES.setOnAction(actionEvent ->  {
            note.getNoteProperty().set("");
            Node source = (Node)  actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });

        Button btnNO = new Button("No");
        btnNO.setMaxSize(200,Double.MAX_VALUE);
        btnNO.setOnAction(actionEvent ->  {
            Node source = (Node)  actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });

        pane.getChildren().addAll(label,btnYES,btnNO);
        return pane;
    }
}