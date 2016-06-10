import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

class Block{
    protected float x,y;
    protected float w,h;
    private boolean isSolid;
    protected Color color;

    public static List<Block> Blocks = new ArrayList<>();

    Block(float x, float y, float w, float h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        Blocks.add(this);
    }


    public boolean isIntersected(Block anotherBlock){
        return (Math.abs(anotherBlock.y + anotherBlock.h/2 - y - h/2) < (anotherBlock.h / 2 + h / 2))
                && (Math.abs(anotherBlock.x + anotherBlock.w/2 - x - w/2) < (anotherBlock.w / 2 + w / 2));
    }

    public boolean isIntersected(float x, float y, float w, float h){
        return (Math.abs(y + h/2 - this.y - this.h/2) < (h / 2 + this.h / 2))
                && (Math.abs(x + w/2 - this.x - this.w/2) < (w / 2 + this.w / 2));
    }

    public void draw(){
        Main.graphics.setFill(color);
        Main.graphics.fillRect(x, y, w, h);
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y){
        setX(x);
        setY(y);
    }
}
