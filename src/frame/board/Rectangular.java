package frame.board;

import java.awt.*;

public class Rectangular extends PaintObject {
    public Rectangular(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public void display(Graphics g) {
        g.setColor(color);
        int xDiff = x2 - x1;
        int yDiff = y2 - y1;
        if (xDiff > 0 && yDiff > 0) {
            g.drawRect(x1, y1, xDiff, yDiff);
        } else if (xDiff < 0 && yDiff > 0) {
            g.drawRect(x2, y1, xDiff * (-1), yDiff);
        } else if (xDiff > 0 && yDiff < 0) {
            g.drawRect(x1, y2, xDiff, yDiff * (-1));
        } else {
            g.drawRect(x2, y2, xDiff * (-1), yDiff * (-1));
        }

    }
}