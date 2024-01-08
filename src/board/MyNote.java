package board;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class MyNote extends JPanel {
    MyBoard mb = new MyBoard();
    Memo m = new Memo();

    public MyNote() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mb.setSize(800, 600);
        this.add(mb);
        this.add(m);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 1200);
        jFrame.add(new MyNote());
        jFrame.setVisible(true);
    }
}
