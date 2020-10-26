package appPack;

import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class EditPageLayout {
    public static Parent constructLayout(StringProperty note, ScreenController controller){
        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);

        BindedTextArea textArea = new BindedTextArea(note);
        textArea.positionCaret(note.get().length());
        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ESCAPE){
                    pane.requestFocus();
                }
            }
        });

        Button btn = new Button("Save");
        pane.getChildren().addAll(textArea,btn);
        btn.setMaxSize(200,Double.MAX_VALUE);
        btn.setOnAction(actionEvent ->  {
            controller.changeScene("main");;
            pane.requestFocus();
        });

        return pane;
    }

}
