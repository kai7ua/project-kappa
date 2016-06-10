import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static final float WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    static GraphicsContext graphics;
    static float G = 0.03f;

    static List<IBody> objectPool = new ArrayList<>();
    static boolean isRunning = false;
    boolean rightPressed = false, leftPressed = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("project-kappa");

        Pane rootNode = new Pane();

        Scene gameScene = new Scene(rootNode, WINDOW_WIDTH, WINDOW_HEIGHT );

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                for (IBody obj : objectPool) {
                    if (obj instanceof Controlable) {
                        Controlable o = (Controlable) obj;
                        o.RightButtonPressed();
                    }
                }
                rightPressed = true;
            }

            if (event.getCode() == KeyCode.LEFT) {
                for (IBody obj : objectPool) {
                    if (obj instanceof Controlable) {
                        Controlable o = (Controlable) obj;
                        o.LeftButtonPressed();
                    }
                }
                leftPressed = true;
            }

            if (event.getCode() == KeyCode.UP) {
                for (IBody obj : objectPool) {
                    if (obj instanceof Controlable) {
                        Controlable o = (Controlable) obj;
                        o.JumpButtonPressed();
                    }
                }
            }
        });

        gameScene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if(!leftPressed) {
                    for (IBody obj : objectPool) {
                        if (obj instanceof Controlable) {
                            Controlable o = (Controlable) obj;
                            o.RightButtonReleased();
                        }
                    }
                }
                rightPressed = false;
            }

            if (event.getCode() == KeyCode.LEFT) {
                if(!rightPressed) {
                    for (IBody obj : objectPool) {
                        if (obj instanceof Controlable) {
                            Controlable o = (Controlable) obj;
                            o.LeftButtonReleased();
                        }
                    }
                }
                leftPressed = false;
            }
        });

        Canvas gameCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);

        graphics = gameCanvas.getGraphicsContext2D();

        rootNode.getChildren().add(gameCanvas);

        primaryStage.setScene(gameScene);
        primaryStage.show();

        isRunning = true;

        new GraphicsLoop();
        new PhysicThread();
        Block downBlock = new Block(20,400,400,10);
        Block leftBlock = new Block(20, 100, 10, 310);
        Block rightBlock = new Block(420, 100, 10, 310);
        //Block upBlock =new Block(20, 100, 400, 10);
        new Player(130,130,50,50);

    }

    public void stop(){
        isRunning = false;
    }


}
