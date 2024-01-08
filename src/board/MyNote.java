package board;

import javax.swing.*;
import java.awt.*;

public class MyNote extends JPanel {
    MyBoard mb = new MyBoard();
    Memo m = new Memo();

    public MyNote() {
        mb.setPreferredSize(new Dimension(800, 600));
        m.setPreferredSize(new Dimension(800, 150));

        this.setLayout(new FlowLayout());
        this.add(mb);
        this.add(m);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 900);
        jFrame.setMinimumSize(new Dimension(800, 750));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new MyNote());
        jFrame.setVisible(true);
    }
}
