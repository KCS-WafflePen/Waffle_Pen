package mycom.mytest.exam;


import javax.swing.*;
import java.awt.event.*;

public class MyNote implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu;
    JMenuItem cutItem, copyItem, pasteItem, selectAllItem;
    JTextArea textArea;


    // UI초기화 및 구성.
    MyNote() {
        frame = new JFrame("Java 메모장");
        cutItem = new JMenuItem("잘라내기");
        copyItem = new JMenuItem("복사");
        pasteItem = new JMenuItem("붙이기");
        selectAllItem = new JMenuItem("모두선택");
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        selectAllItem.addActionListener(this);

        // 상단바 구성
        menuBar = new JMenuBar();
        fileMenu = new JMenu("파일");
        editMenu = new JMenu("편집기");

        // 편집기 메뉴에 항목들 추가
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(selectAllItem);


        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // 텍스트 위치 생성 및 크기 지정.
        textArea = new JTextArea();
        textArea.setBounds(5, 5, 360, 320);
        frame.add(menuBar);
        frame.add(textArea);
        frame.setJMenuBar(menuBar);

        // 프레임 레이아웃 설정 및 크기
        frame.setLayout(null);
        frame.setSize(400, 400);
        frame.setVisible(true);

        // 닫기 버튼 동작 //방금 코드와 Generalizton
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        // 메뉴 기능의 동작 설정
        if (e.getSource() == cutItem)
            textArea.cut();

        if (e.getSource() == pasteItem)
            textArea.paste();

        if (e.getSource() == copyItem)
            textArea.copy();

        if (e.getSource() == selectAllItem)
            textArea.selectAll();
    }

    // Main 코드
    public static void main(String[] args) {
        new MyNote();
    }
}