import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static final float WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    public static GraphicsContext graphics;

    public static List<IBody> objectPool = new ArrayList<>();
    public static boolean isRunning = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("project-kappa");

        Pane rootNode = new Pane();

        Scene gameScene = new Scene(rootNode, WINDOW_WIDTH, WINDOW_HEIGHT );

        Canvas gameCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);

        graphics = gameCanvas.getGraphicsContext2D();

        rootNode.getChildren().add(gameCanvas);

        primaryStage.setScene(gameScene);
        primaryStage.show();

        isRunning = true;
        GraphicThread graphicThread = new GraphicThread();
        Player player = new Player(0,0,50,50);
        player.setSpeed(1f, 1f);
    }

    public void stop(){
        isRunning = false;
    }
}
