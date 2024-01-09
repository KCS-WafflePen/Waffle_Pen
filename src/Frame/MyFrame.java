package Frame;

import Frame.board.MyBoard;
import Frame.board.MyNote;

import javax.swing.*;

public class MyFrame extends JFrame {
    // Constants
    public static final int FRAME_WIDTH=800;
    public static final int FRAME_HEIGHT=600;


    public MyFrame(){
        super();
    }

//    public MyFrame(MyNote mn){
//        this.add(mn);
//
//        this.setSize(FRAME_WIDTH, FRAME_HEIGHT + 200);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 통해서도 종료 가능
//        this.setVisible(true);
//    }



//    public static void main(String[] arg){
//        new MyFrame(mb);
//        new MyFrame(mn);
//    }
}
