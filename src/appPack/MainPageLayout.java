package appPack;

import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainPageLayout{
    public static Parent constructLayout(ArrayList<SingleNote> notes, ScreenController controller){

        ScrollPane scrollMenu = new ScrollPane();
        scrollMenu.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        scrollMenu.setFitToWidth(true);

        scrollMenu.setContent(FillScrollMenu(notes,controller));

        return scrollMenu;
    }

    private static Parent FillScrollMenu(ArrayList<SingleNote> notes, ScreenController controller){
        VBox box = new VBox();
        box.setFillWidth(true);

        for(int i = 0; i < notes.size(); i++){

            Parent mainLayout = EditPageLayout.constructLayout(notes.get(i),controller);
            Scene editScene = new Scene(mainLayout, 500, 500);

            BindedTextArea textArea = new BindedTextArea(notes.get(i).getNoteProperty());
            textArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    controller.setScene(editScene);
                    box.requestFocus();
                }
            });
            box.getChildren().add(textArea);
        }

        return box;
    }
}
