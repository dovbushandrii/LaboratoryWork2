package appPack;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

public class BindedTextArea extends TextArea {
    public BindedTextArea(StringProperty bindCouple){
        this.setPrefRowCount(6);
        this.setPrefColumnCount(5);
        this.setWrapText(true);
        this.setText(bindCouple.get());
        this.textProperty().bindBidirectional(bindCouple);
    }
}
