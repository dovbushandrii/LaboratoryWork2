package appPack;

import javafx.stage.Stage;

/**
 * Each Activity object is an activity
 * that implements functionality of
 * specific user interface.
 */

public abstract class Activity {

    /**
     * @title - Title of window
     * that can be created by
     * runActivity().
     */
    protected String title;

    /**
     * @title - main Stage of
     * Activity.
     */
    protected Stage activityStage;

    /**
     * Main function that should be created
     * for starting Activity.
     *
     * @return - returns result of Activity
     */
    public abstract Object runActivity();

    /**
     * Function to close window of Activity
     * and return result is there is one.
     *
     * @return - returns result of Activity
     */
    public abstract Object stopActivity();
}
