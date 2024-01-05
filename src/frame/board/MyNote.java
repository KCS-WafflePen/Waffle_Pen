package frame.board;

import frame.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;

public class MyNote implements ActionListener {
    JFrame f; // JFrame 객체
    JMenuBar mb; // 메뉴바
    JMenu file, edit; // 파일 및 편집 메뉴
    JMenuItem cut, copy, paste, selectAll; // 편집 메뉴 아이템들
    JTextArea ta; // 텍스트 에어리어
    JMenuItem i_save, i_load; // 파일 메뉴 아이템들

    MyNote() {
        // JFrame 및 각종 GUI 구성 요소 초기화
        f = new JFrame("자바 메모장");
        cut = new JMenuItem("잘라내기");
        copy = new JMenuItem("복사");
        paste = new JMenuItem("붙이기");
        selectAll = new JMenuItem("모두 선택");
        i_save = new JMenuItem("저장");
        i_load = new JMenuItem("열기");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        i_save.addActionListener(this);
        i_load.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("파일");
        edit = new JMenu("편집");

        // 각 메뉴에 아이템 추가
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        file.add(i_save);
        file.add(i_load);

        mb.add(file);
        mb.add(edit);

        // 텍스트 에어리어 및 메뉴바를 프레임에 추가
        ta = new JTextArea();
        ta.setBounds(5, 5, 360, 320);
        f.add(mb);
        f.add(ta);
        f.setJMenuBar(mb);

        // 기타 GUI 설정
        f.setLayout(null);
        f.setSize(MyFrame.FRAME_WIDTH, MyFrame.FRAME_HEIGHT);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        // 메뉴 아이템에 대한 이벤트 처리

        // "잘라내기" 메뉴 아이템 선택 시
        if (e.getSource() == cut)
            ta.cut();

        // "붙이기" 메뉴 아이템 선택 시
        if (e.getSource() == paste)
            ta.paste();

        // "복사" 메뉴 아이템 선택 시
        if (e.getSource() == copy)
            ta.copy();

        // "모두 선택" 메뉴 아이템 선택 시
        if (e.getSource() == selectAll)
            ta.selectAll();

        // "저장" 메뉴 아이템 선택 시
        if (e.getSource() == i_save) {
            FileDialog fd = new FileDialog(f, "저장", FileDialog.SAVE);
            fd.setVisible(true);

            String path = fd.getDirectory() + fd.getFile();

            if (fd.getFile() == null) return;

            try {
                FileWriter fw = new FileWriter(path);
                String s = ta.getText();
                fw.write(s);
                fw.close();
            } catch (Exception e1) {
                System.out.println("저장 오류: " + e1);
            }
        }

        // "열기" 메뉴 아이템 선택 시
        if (e.getSource() == i_load) {
            FileDialog fd = new FileDialog(f, "열기", FileDialog.LOAD);
            fd.setVisible(true);

            String path = fd.getDirectory() + fd.getFile();

            String s = "";

            if (fd.getFile() == null) return;

            try {
                FileReader fr = new FileReader(path);

                int k;

                while (true) {
                    k = fr.read();
                    if (k == -1) break;
                    s += (char) k;
                }

                fr.close();

            } catch (Exception e2) {
                System.out.println("오류: " + e2);
            }

            ta.setText(s);
        }
    }

    public static void main(String[] args) {
        new MyNote();
    }
}