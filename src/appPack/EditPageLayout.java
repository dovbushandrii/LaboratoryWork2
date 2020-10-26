package appPack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditPageLayout {
    public static Parent constructLayout(SingleNote note){

        String oldNote = note.getNoteProperty().get();

        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(5);

        DateLabel label = new DateLabel(note.getDateProperty());

        BindedTextArea textArea = new BindedTextArea(note.getNoteProperty());
        textArea.positionCaret(note.getNoteProperty().get().length());
        textArea.setPrefRowCount(Integer.MAX_VALUE);
        textArea.setPrefColumnCount(Integer.MAX_VALUE);
        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ESCAPE){
                    pane.requestFocus();
                }
            }
        });


        Button btn = new Button("Save");
        btn.setMaxSize(200,Double.MAX_VALUE);
        btn.setOnAction(actionEvent ->  {
            if(!oldNote.equals(textArea.getText())){
                label.setCurrentTime();
            }
            Node source = (Node)  actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });

        pane.getChildren().addAll(label,textArea,btn);
        return pane;
    }

}
