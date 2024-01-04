import board.MainPage;

import java.awt.*;

public class MainFrame extends Frame {
    // Constants
    public static final int FRAME_WIDTH=400;
    public static final int FRAME_HEIGHT=200;
    MainPage mp = new MainPage();

    public MainFrame(){



        this.add(mp);

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);
    }
}
