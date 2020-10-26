package appPack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeletePageLayout {
    public static Parent constructLayout(SingleNote note){

        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(5);

        Label label = new Label();
        label.setText("Are you sure?");

        StringProperty thisNote = new SimpleStringProperty();
        thisNote.bindBidirectional(note.getNoteProperty());
        Button btnYES = new Button("Yes");
        btnYES.setMaxSize(200,Double.MAX_VALUE);
        btnYES.setOnAction(actionEvent ->  {
            thisNote.set("");
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
