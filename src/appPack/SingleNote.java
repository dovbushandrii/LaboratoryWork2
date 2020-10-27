package appPack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class SingleNote {
    private final StringProperty note;
    private LocalDateTime time;

    public SingleNote(){
        this.note = new SimpleStringProperty("");
        this.time = LocalDateTime.now();
    }

    public SingleNote(String note){
        this.note = new SimpleStringProperty(note);
        this.time = LocalDateTime.now();
    }

    public StringProperty getNoteProperty(){
        return this.note;
    }

    public String getTime() {
        return StringFromDateConstructor.dateAndTimeToString(this.time);
    }

    public void setCurrentTime(){
        this.time = LocalDateTime.now();
    }

}
