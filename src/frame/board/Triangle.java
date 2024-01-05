package frame.board;

import java.awt.*;

public class Triangle extends PaintObject {
    public Triangle(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public void display(Graphics g) {
        g.setColor(color);
        int[] xArr = {x1, (x1 + x2)/2, x2};
        int[] yArr = {y2, y1, y2};
        g.drawPolygon(xArr, yArr, 3);
    }
}
