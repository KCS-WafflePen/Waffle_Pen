package frame.board;

import java.awt.*;

public abstract class PaintObject {
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected Color color;

    public PaintObject(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public abstract void display(Graphics g);
}
