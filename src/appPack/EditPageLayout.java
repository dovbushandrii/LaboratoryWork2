package appPack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class EditPageLayout {
    public static Parent constructLayout(SingleNote note, ScreenController controller){

        String oldNote = note.getNoteProperty().get();

        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);

        DateLabel label = new DateLabel(note.getDateProperty());

        BindedTextArea textArea = new BindedTextArea(note.getNoteProperty());
        textArea.positionCaret(note.getNoteProperty().get().length());
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
            if(oldNote.equals(textArea.getText())){
                textArea.setText("NOT CHANGED");
            }
            else label.setCurrentTime();
            controller.changeScene("main");;
            pane.requestFocus();
        });
        pane.getChildren().addAll(label,textArea,btn);
        return pane;
    }

}
