package board;

import javax.swing.*;
import java.awt.*;

public class MyNote extends JPanel {
    MyBoard mb = new MyBoard();
    Memo m = new Memo();

    public MyNote() {
        this.setLayout(new BorderLayout());

        // MyBoard와 Memo를 수직으로 나누는 JSplitPane 생성
        JSplitPane boardAndMemoSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mb, m);
        boardAndMemoSplitPane.setResizeWeight(0.5);

        // 위에서 생성한 split pane과 Memo를 수평으로 나누는 JSplitPane 생성
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardAndMemoSplitPane, m);
        mainSplitPane.setResizeWeight(0.5);

        // MyBoard와 위에서 생성한 split pane을 수직으로 나누는 JSplitPane 생성
        JSplitPane finalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mb, mainSplitPane);
        finalSplitPane.setResizeWeight(0.5);

        // 최종적으로 구성된 split pane을 메인 패널에 추가
        this.add(finalSplitPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new MyNote());
        jFrame.setVisible(true);
    }
}
