package appPack;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutPageActivity extends Activity{

    public AboutPageActivity(Stage stage, String title){
        this.activityStage = stage;
        this.title = title;
    }

    @Override
    public Object runActivity(){
        Scene mainScene = this.constructScene();
        this.activityStage.setTitle(this.title);
        this.activityStage.setScene(mainScene);
        this.activityStage.showAndWait();
        return null;
    }

    @Override
    public Object stopActivity(){
        this.activityStage.close();
        return null;
    }

    private Scene constructScene(){
        Parent layout = this.constructLayout();
        return new Scene(layout, 300,300);
    }

    private Parent constructLayout(){

        VBox pane = new VBox();
        pane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(5);

        Label label = new Label();
        String text = "\n\nShort instructions:\n" +
                "To add note - use Add note button\n" +
                "To delete note - click middle button on " +
                "note that should be deleted\n\n" +
                "Empty notes will be deleted automatically\n\n" +
                "Done by Andrii Dovbush, K-29";

        label.setText(text);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);


        Button btn= new Button("Get it!");
        btn.setMaxSize(200,Double.MAX_VALUE);
        btn.setOnAction(actionEvent ->  {
            Node source = (Node)  actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });

        pane.getChildren().addAll(label,btn);
        return pane;
    }
}
