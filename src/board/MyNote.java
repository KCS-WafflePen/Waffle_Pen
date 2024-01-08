package board;

import javax.swing.*;
import java.awt.*;

public class MyNote extends JPanel {
    MyBoard mb = new MyBoard();
    Memo m = new Memo();

    public MyNote() {
        this.setLayout(new BorderLayout());

        // Create a JSplitPane with horizontal orientation
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mb, m);

        // Set the initial size distribution (50% each)
        splitPane.setResizeWeight(0.5);

        // Add the split pane to the main panel
        this.add(splitPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new MyNote());
        jFrame.setVisible(true);
    }
}
