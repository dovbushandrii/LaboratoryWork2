package appPack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * AboutPageActivity - class that can create
 * info-window with quick tips of Notepad/Bloknot
 * usage.
 */
public class AboutPageActivity extends Activity {

    /**
     * Size of window that creates this activity.
     */
    private int widthOfScene = 300;
    private int heightOfScene = 200;

    /**
     * Constructs an object of class.
     *
     * @param stage - window of activity.
     * @param title - title of activity.
     */
    public AboutPageActivity(Stage stage, String title) {
        this.activityStage = stage;
        this.title = title;
    }

    /**
     * Main method to run activity.
     * Creates a window using the stage and
     * title that object got in Constructor.
     * Returns null, because there is no
     * changes in here.
     *
     * @return -  default return of result
     * of activity.
     */
    @Override
    public Object runActivity() {
        Scene mainScene = this.constructScene();
        this.activityStage.setTitle(this.title);
        this.activityStage.setResizable(false);
        this.activityStage.setScene(mainScene);
        this.activityStage.showAndWait();
        return null;
    }

    /**
     * Closes the window that was created
     * by runActivity() method.
     * Returns null, because there is no
     * changes in here.
     *
     * @return - default return of result
     * of activity.
     */
    @Override
    public Object stopActivity() {
        this.activityStage.close();
        return null;
    }

    /**
     * Constructs scene with width = widthOfScene,
     * and height = heightOfScene
     * and layout that object constructs
     * by constructLayout() method.
     * That scene will be set on stage that
     * object got by Constructor.
     *
     * @return - returns constructed Scene.
     */
    private Scene constructScene() {
        Parent layout = this.constructLayout();
        return new Scene(layout, this.widthOfScene, this.heightOfScene);
    }

    /**
     * Constructs layout of scene.
     * Contains only text (Label) with tips
     * and one button "Got it" that closes
     * this activity.
     *
     * @return - returns constructed layout(Parent)
     */
    private Parent constructLayout() {

        VBox layout = new VBox();
        layout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(5);
        layout.setPadding(new Insets(5));

        Label label = new Label();
        String text = "Short instructions:\n" +
                "To add note - use Add note button.\n" +
                "Use secondary button click on note to " +
                "use context menu for editing and deleting note.\n" +
                "To edit note you can also use primary mouse button click" +
                "on note tha you want to edit.\n" +
                "Empty notes will be deleted automatically.\n\n" +
                "Done by Andrii Dovbush, K-29";

        label.setText(text);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);


        Button btn = new Button("Get it!");
        btn.setMaxSize(200, Double.MAX_VALUE);
        btn.setOnAction(actionEvent -> {
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });


        layout.getChildren().addAll(label, btn);

        return layout;
    }
}
