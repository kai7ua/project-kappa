import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

class Player extends Block implements Updateable {
    float sx = 0, sy = 0;
    boolean isTouchingH = false;

    Player(float x, float y, float w, float h, Color color) {
        super(x, y, w, h, color);
    }

    void KeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.RIGHT) {
            sx = 1;
        }

        if (event.getCode() == KeyCode.LEFT) {
            sx = -1;
        }

        if (event.getCode() == KeyCode.UP) {
            sy = -3;
        }
    }


    void KeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.RIGHT) {
            if (sx > 0) {
                sx = 0;
            }
        }

        if (event.getCode() == KeyCode.LEFT) {
            if (sx < 0) {
                sx = 0;
            }
        }
    }

    public void Update() {
        isTouchingH = false;

        for (Block block : Blocks) {
            if (block == this)
                continue;

            Direction dir = getNextStepIntersectionDir(block);

            if (dir == Direction.DOWN || dir == Direction.UP) {
                sy = 0;
            }

            if (dir == Direction.RIGHT || dir == Direction.LEFT) {
                isTouchingH = true;
            }

            if (dir == Direction.RIGHT_DOWN || dir == Direction.LEFT_DOWN || dir == Direction.RIGHT_UP || dir == Direction.LEFT_UP) {
                sy = 0;
                isTouchingH = true;
            }
        }

        if (!isTouchingH) {
            x += sx;
        }

        y += sy;
        sy += Main.G;

    }

    @Override
    public void draw() {
        super.draw();
    }

    void setSpeed(float x, float y) {
        sy = y;
        sx = x;
    }

    public Direction getNextStepIntersectionDir(Block block) {
        float nextX = x + sx;
        float nextY = y + sy;
        if (block.isIntersected(nextX, y, w, h)) {
            if (sx < 0) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        } else if (block.isIntersected(x, nextY, w, h)) {
            if (sy < 0) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        } else if (block.isIntersected(nextX, nextY, w, h)) {

            float dy = Math.abs(block.y - y) - block.h / 2 - h / 2;
            float dx = Math.abs(block.x - x) - block.w / 2 - w / 2;
            float tx = dx / sx;
            float ty = dy / sy;

            if (tx > ty) {
                if (sx < 0) {
                    return Direction.LEFT;
                } else {
                    return Direction.RIGHT;
                }
            } else if (ty < tx) {
                if (sy < 0) {
                    return Direction.UP;
                } else {
                    return Direction.DOWN;
                }
            } else {
                if (sx < 0 && sy < 0) {
                    return Direction.LEFT_UP;
                } else if (sx > 0 && sy > 0) {
                    return Direction.RIGHT_DOWN;
                } else if (sx > 0 && sy < 0) {
                    return Direction.RIGHT_UP;
                } else {
                    return Direction.LEFT_DOWN;
                }
            }
        }
        return Direction.NONE;
    }
}