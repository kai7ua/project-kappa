public class Player implements IBody,Updateable,Controlable {

    private float x, y;
    private float w, h;
    private float xs, ys;
    private Direction dir;
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
        if(body.getX() == x + w || body.getY() == y + h || body.getX() + body.getW() == x || body.getY() + body.getH() == y){
            return false;
        }

        if(body.getX() < x + w && body.getY() < y + h && body.getX() + body.getW() > x && body.getY() + body.getH() > y){
            return true;
        }

        return false;
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

        isFalling = (ys > 0);
        isJumping = (ys < 0);
        isRunning = (xs != 0);

        if(!isRunning){
            dir = Direction.NONE;
        } else {
            dir = xs > 0? Direction.RIGHT: Direction.LEFT;
        }

        for(IBody obj: Main.objectPool){

            if (obj == this)
                continue;

            if(isFalling && new Block(x + xs + w/2, y + ys, 0, h, false).isIntersected(obj)){
                setSpeed(xs, obj.getY() - y - h);
            }

            if(isJumping && new Block(x + xs + w/2, y + ys, 0, h, false).isIntersected(obj)){
                setSpeed(xs, obj.getY() + obj.getH() - y);
            }

            if(dir.equals(Direction.RIGHT) && new Block(x + xs, y + ys + h/2, w, 0, false).isIntersected(obj)){
                setSpeed(obj.getX() - x - w, ys);
            }

            if(dir.equals(Direction.LEFT) && new Block(x + xs, y + ys + h/2, w, 0, false).isIntersected(obj)){
                setSpeed(obj.getX() + obj.getW() - x, ys);
            }

        }


        x += xs;
        y += ys;
        ys += Main.G;
    }

    @Override
    public void RightButtonPressed() {
        xs = 5;
    }

    @Override
    public void LeftButtonPressed() {
        xs = -5;
    }

    @Override
    public void LeftButtonReleased() {
        xs = 0;
    }

    @Override
    public void RightButtonReleased() {
        xs = 0;
    }

    @Override
    public void JumpButtonPressed() {
        ys = -10;
    }

    public Block getNextPosPlayer(){
        return new Block(x + xs, y + ys, w, h, false);
    }
}
