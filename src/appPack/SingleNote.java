package appPack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SingleNote {
    private StringProperty note;
    private StringProperty date;

    public SingleNote(String note){
        this.note = new SimpleStringProperty(note);
        this.date = new SimpleStringProperty("0");
        this.setCurrentTime();
    }

    public StringProperty getNoteProperty(){
        return this.note;
    }

    public StringProperty getDateProperty(){
        return this.date;
    }

    public void setCurrentTime(){
        this.date.set("0.0.0.0.0.0");
    }
}
