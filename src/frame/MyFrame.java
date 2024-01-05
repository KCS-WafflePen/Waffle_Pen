package frame;

import javax.swing.*;

public class MyFrame extends JFrame {
    // Constants
    public static final int FRAME_WIDTH=800;
    public static final int FRAME_HEIGHT=600;
    MyBoard mb = new MyBoard();

    public MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 통해서도 종료 가능
        this.setVisible(true);
    }
}
