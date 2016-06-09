class Block implements IBody{
    private float x,y;
    private float w,h;
    private boolean isSolid;

    Block(float x, float y, float w, float h, boolean isSolid) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.isSolid = isSolid;
        if (isSolid) {
            Main.objectPool.add(this);
        }
    }

    @Override
    public void draw() {
        Main.graphics.fillRect(x,y,w,h);
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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

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

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y){
        setX(x);
        setY(y);
    }
}
