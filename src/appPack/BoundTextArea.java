package appPack;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;


/**
 * Extended TextArea class that
 * implements binding functionality
 * to TextArea.
 * Each BoundTextArea class is
 * bound bidirectionally with
 * specific StringProperty object.
 */
public class BoundTextArea extends TextArea {

    /**
     * Constructs an object of BoundTextArea
     * that bound bidirectionally
     * to bindCouple String.
     *
     * @param bindCouple - StringProperty to bind with.
     */
    public BoundTextArea(StringProperty bindCouple) {
        this.setPrefRowCount(6);
        this.setPrefColumnCount(5);
        this.setWrapText(true);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setText(bindCouple.get());
        this.textProperty().bindBidirectional(bindCouple);
    }
}
