package appPack;

import javafx.stage.Stage;

public abstract class Activity {
    protected String title;
    protected Stage activityStage;
    public abstract Object runActivity();
    public abstract Object stopActivity();
}
