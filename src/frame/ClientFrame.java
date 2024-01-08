package frame;

import frame.board.MyBoard;

import javax.swing.*;

public class ClientFrame extends JFrame implements MyFrame{
    public MyBoard mb;

    public ClientFrame(){
        mb = new MyBoard();
        this.add(mb);

        this.setSize(MyFrame.FRAME_WIDTH,MyFrame.FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 통해서도 종료 가능
        this.setVisible(true);
    }
}
