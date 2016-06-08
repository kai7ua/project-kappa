import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static final float WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    public static GraphicsContext graphics;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ganjubassed");

        Pane rootNode = new Pane();

        Scene gameScene = new Scene(rootNode, WINDOW_WIDTH, WINDOW_HEIGHT );

        Canvas gameCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);

        graphics = gameCanvas.getGraphicsContext2D();

        rootNode.getChildren().add(gameCanvas);

        primaryStage.setScene(gameScene);
        primaryStage.show();

        new Block(50, 50, 80, 50).draw();
        new Player(120, 120, 80, 100).draw();
    }
}
