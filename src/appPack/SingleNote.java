package appPack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

/**
 * Class that describes notes in
 * this application.
 * Contains @note,
 * and @time of last update.
 */

public class SingleNote {

    private final StringProperty note;
    /**
     * @time - time of last update.
     */
    private LocalDateTime time;

    /**
     * Constructs empty note.
     */
    public SingleNote() {
        this.note = new SimpleStringProperty("");
        this.time = LocalDateTime.now();
    }

    /**
     * Constructs note.
     */
    public SingleNote(String note) {
        this.note = new SimpleStringProperty(note);
        this.time = LocalDateTime.now();
    }


    public StringProperty getNoteProperty() {
        return this.note;
    }

    public LocalDateTime getLocalDateTime() {
        return this.time;
    }

    /**
     * Returns String object with time
     * in standard format
     * yyyy/mm/dd hh/mm/ss
     *
     * @return - returns String object
     */
    public String getTimeString() {
        return StringFromDateConstructor.dateAndTimeToString(this.time);
    }

    /**
     * Sets time of last
     * update to current time.
     */
    public void setCurrentTime() {
        this.time = LocalDateTime.now();
    }

}
