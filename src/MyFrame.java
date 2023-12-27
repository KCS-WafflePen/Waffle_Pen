import board.MyBoard;

import java.awt.*;

public class MyFrame extends Frame {
    // Constants
    public static final int FRAME_WIDTH=800;
    public static final int FRAME_HEIGHT=600;
    MyBoard mb = new MyBoard();

    public MyFrame(){
        this.add(mb);

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);
    }
}
