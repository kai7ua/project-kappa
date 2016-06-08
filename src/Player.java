public class Player implements IBody,Updateable,Controlable {

    private float x, y;
    private float w, h;
    private float xs, ys;
    private final float HS = 10;
    private final float G = 1;
    public boolean isRunning;
    public boolean isJumping;
    public boolean isFalling;


    public Player(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        Main.objectPool.add(this);
    }

    @Override
    public void draw() {
        Main.graphics.fillRect(x, y , w, h);
    }

    @Override
    public boolean isIntersected(IBody body) {
        return body.getX() < x + w && body.getY() < y + h && body.getX() + body.getW() > x && body.getY() + body.getH() > y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getW() {
        return w;
    }

    @Override
    public float getH() {
        return h;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y){
        setX(x);
        setY(y);
    }

    public void setSpeed(float _xs, float _ys){
        xs = _xs;
        ys = _ys;
    }

    @Override
    public void Update() {
        for(IBody obj: Main.objectPool){
            if (obj == this)
                continue;

            if (isIntersected(obj)){
                ys = -15;
            }
        }

        x += xs;
        y += ys;
        ys += Main.G;
    }

    @Override
    public void RightButtonPressed() {
        xs = 2;
    }

    @Override
    public void LeftButtonPressed() {
        xs = -2;
    }

    @Override
    public void LeftButtonReleased() {
        xs = 0;
    }

    @Override
    public void RightButtonReleased() {
        xs = 0;
    }
}
