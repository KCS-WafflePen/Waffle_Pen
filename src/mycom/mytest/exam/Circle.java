package mycom.mytest.exam;

import java.awt.*;

public class Circle extends BoardObject{
    public Circle(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }
    @Override
    public void display(Graphics g) {
        g.setColor(color);
        g.drawOval(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
