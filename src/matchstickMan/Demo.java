package matchstickMan;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
public class Demo extends Application
{
    public void start(Stage stage)
    {
        BorderPane bp = new BorderPane();
        MatchstickMan role = new MatchstickMan(true, Color.BLACK);
        Shield shield = new Shield(role);
        bp.setCenter(role);
        Scene s = new Scene(bp);
        stage.setScene(s);
        stage.setTitle("Demo");
        stage.show();
        s.setOnKeyPressed(e ->
        {
            role.init();
            role.enable("Shield");
        });
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
