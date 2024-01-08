package board;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Memo extends JPanel implements ActionListener {
    JMenuBar mb; // 메뉴바
    JMenu file, edit; // 파일 및 편집 메뉴
    JMenuItem cut, copy, paste, selectAll; // 편집 메뉴 아이템들
    JTextArea ta; // 텍스트 에어리어
    JMenuItem i_save, i_load; // 파일 메뉴 아이템들

    public Memo() {
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

        file = new JMenu("파일");
        edit = new JMenu("편집");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        file.add(i_save);
        file.add(i_load);

        mb = new JMenuBar();
        mb.add(file);
//        mb.add(edit);

        ta = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ta);

        // 메모 패널 크기를 고정
        this.setPreferredSize(new Dimension(400, 200));

        this.setLayout(new BorderLayout());
        this.add(mb, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        // 메뉴 아이템에 대한 이벤트 처리
        if (e.getSource() == cut)
            ta.cut();
        else if (e.getSource() == paste)
            ta.paste();
        else if (e.getSource() == copy)
            ta.copy();
        else if (e.getSource() == selectAll)
            ta.selectAll();

        // 이하 저장 및 열기 기능 주석 처리
    }
}
