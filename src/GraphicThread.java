import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;


class GraphicThread extends Thread{
    private GraphicsContext graphics = Main.graphics;

    GraphicThread(){
        super();
        this.start();
    }

    @Override
    public void run(){
        while (Main.isRunning) {
            graphics.setFill(Color.WHITE);
            graphics.fillRect(0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
            graphics.setFill(Color.BLUE);
            for (IBody obj : Main.objectPool) {
                if (obj instanceof Updateable) {
                    ((Updateable) obj).Update();
                }
                obj.draw();
            }

            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
