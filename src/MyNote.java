import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

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

        // 각 메뉴 및 메뉴 아이템 초기화
        cut = new JMenuItem("잘라내기");
        copy = new JMenuItem("복사");
        paste = new JMenuItem("붙이기");
        selectAll = new JMenuItem("모두 선택");
        i_save = new JMenuItem("저장");
        i_load = new JMenuItem("열기");

        // 메뉴 아이템에 액션 리스너 등록
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        i_save.addActionListener(this);
        i_load.addActionListener(this);

        // 각 메뉴에 아이템 추가
        file = new JMenu("파일");
        edit = new JMenu("편집");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        file.add(i_save);
        file.add(i_load);

        // 메뉴바에 메뉴 추가
        mb = new JMenuBar();
        mb.add(file);
        mb.add(edit);

        // 텍스트 에어리어 초기화
        ta = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ta);

        // 패널 초기화 및 컴포넌트 추가
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(mb, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // JFrame에 패널 추가
        f.add(panel);

        // 기타 GUI 설정
        f.setSize(400, 400);
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
