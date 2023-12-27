package board;

import java.awt.*;

public class Brush extends PaintObject{
    public Brush(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }
    @Override
    public void display(Graphics g) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
