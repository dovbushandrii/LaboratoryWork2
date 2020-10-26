package appPack;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class DateLabel extends Label {
    public DateLabel(StringProperty date){
        this.textProperty().bindBidirectional(date);
    }

    public void setCurrentTime(){
        this.textProperty().set("NEW");
    }
}
