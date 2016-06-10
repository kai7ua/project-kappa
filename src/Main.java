import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    static final float WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    static GraphicsContext graphics;
    static float G = 0.03f;
    public static Player player = null;
    static boolean isRunning = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("project-kappa");

        Pane rootNode = new Pane();

        Scene gameScene = new Scene(rootNode, WINDOW_WIDTH, WINDOW_HEIGHT );



        player = new Player(130,130,50,50, Color.BLUE);

        gameScene.setOnKeyPressed(player::KeyPressed);

        gameScene.setOnKeyReleased(player::KeyReleased);

        Canvas gameCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);

        graphics = gameCanvas.getGraphicsContext2D();

        rootNode.getChildren().add(gameCanvas);

        primaryStage.setScene(gameScene);
        primaryStage.show();

        isRunning = true;
        new GraphicThread();
        new PhysicThread();
        new Block(20,400,400,10, Color.AZURE);
        new Block(20, 100, 10, 310, Color.AZURE);
        new Block(420, 100, 10, 310, Color.AZURE);
        //new Block(20, 100, 400, 10);
    }

    public void stop(){
        isRunning = false;
    }


}
