import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;


class GraphicThread extends AnimationTimer{
    private GraphicsContext graphics = Main.graphics;

    GraphicThread(){
        super();
        this.start();
    }

    @Override
    public void handle(long now) {
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);


        for (Block obj : Block.Blocks) {
            obj.draw();
        }
    }

}
