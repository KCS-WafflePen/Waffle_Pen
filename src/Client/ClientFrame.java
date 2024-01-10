package Client;

import Frame.MyFrame;
import Frame.board.MyBoard;
import Frame.board.MyNote;

public class ClientFrame extends MyFrame{
    MyNote mn = new MyNote();

    public ClientFrame(){
        super();
        this.add(mn);

        this.setSize(MyFrame.FRAME_WIDTH,MyFrame.FRAME_HEIGHT + 200);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 통해서도 종료 가능
        this.setVisible(true);
    }
}
