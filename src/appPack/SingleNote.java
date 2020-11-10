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
     * Type of note: Personal, Work, Study
     * Default: Personal
     */
    private ContextEnum conType;

    /**
     * Constructs empty note.
     */
    public SingleNote() {
        this.note = new SimpleStringProperty("");
        this.time = LocalDateTime.now();
        this.conType = ContextEnum.PERSONAL;
    }

    /**
     * Constructs note.
     */
    public SingleNote(String note) {
        this.note = new SimpleStringProperty(note);
        this.time = LocalDateTime.now();
        this.conType = ContextEnum.PERSONAL;
    }


    public StringProperty getNoteProperty() {
        return this.note;
    }

    public LocalDateTime getLocalDateTime() {
        return this.time;
    }

    public ContextEnum getConType() {
        return this.conType;
    }

    public void setConType(ContextEnum type) {
        this.conType = type;
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
