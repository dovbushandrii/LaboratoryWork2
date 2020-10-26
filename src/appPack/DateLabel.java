package appPack;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class DateLabel extends Label {
    public DateLabel(StringProperty date){
        this.textProperty().bindBidirectional(date);
        this.setWrapText(true);
        this.setAlignment(Pos.TOP_CENTER);
    }

    public void setCurrentTime(){
        this.textProperty().set(StringFromDateConstructor.fullCurrentDateAndTime());
    }

    public void setDeletedTime() {
        this.textProperty().set("deleted");
    }
}
