import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphicsLoop extends AnimationTimer {
    private GraphicsContext graphics = Main.graphics;

    GraphicsLoop() {
        this.start();
    }

    @Override
    public void handle(long now) {
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        graphics.setFill(Color.DARKBLUE);

        for (IBody obj : Main.objectPool) {
            obj.draw();
        }
    }
}

