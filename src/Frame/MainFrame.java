package Frame;

import Server.Server;

import java.awt.*;

public class MainFrame extends Frame {
    // Constants
    public static final int FRAME_WIDTH=400;
    public static final int FRAME_HEIGHT=200;

    public MainFrame(){
        this.add(new MainPage());
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);
    }
}
