package appPack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SingleNote {
    private StringProperty note;
    private StringProperty date;

    public SingleNote(){
        this.note = new SimpleStringProperty("");
        this.date = new SimpleStringProperty(StringFromDateConstructor.fullCurrentDateAndTime());
    }

    public SingleNote(String note){
        this.note = new SimpleStringProperty(note);
        this.date = new SimpleStringProperty(StringFromDateConstructor.fullCurrentDateAndTime());
    }

    public StringProperty getNoteProperty(){
        return this.note;
    }

    public StringProperty getDateProperty(){
        return this.date;
    }

}
