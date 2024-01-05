package Client;

import frame.MyFrame;
import frame.board.MyBoard;

import javax.swing.*;

public class ClientFrame extends MyFrame{
    MyBoard mb = new MyBoard();

    public ClientFrame(){
        super();
        this.add(mb);

        this.setSize(MyFrame.FRAME_WIDTH,MyFrame.FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 통해서도 종료 가능
        this.setVisible(true);
    }
}
