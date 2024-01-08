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
        Dimension dimension = new Dimension(800, 800);
        jFrame.setSize(dimension);
        jFrame.setMinimumSize(dimension);
        jFrame.setMaximumSize(dimension);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new MyNote());
        jFrame.setVisible(true);
    }
}
