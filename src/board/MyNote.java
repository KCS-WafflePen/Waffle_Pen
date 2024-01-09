package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyNote extends MyBoard {
    private final TextPanel txtp = new TextPanel();
    private final NoteActionHandler noteActHandler = new NoteActionHandler();

    // Construct
    public MyNote() {


        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());

        JPanel spanel = new JPanel();
        spanel.add(txtp);
        //south.add(getUtilButtonPanel());

        this.add("South", spanel);
        this.add("North", getDrawButtonPanel());
        this.add("East", getColorButtonPanel());
        this.add("Center", getDrawPaintPanel());
    }


    //--inner
    protected class TextPanel extends JPanel {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("파일");
        JMenu edit = new JMenu("편집");
        JMenuItem i_save = new JMenuItem("저장");
        JMenuItem i_load = new JMenuItem("열기");
        JTextArea textarea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textarea);

        TextPanel() {
            addEvent();

            file.add(i_save);
            file.add(i_load);

            menubar.add(file);
            menubar.add(edit);

            this.setLayout(new BorderLayout());
            this.add(menubar, BorderLayout.NORTH);
            this.add(scrollPane, BorderLayout.CENTER);
        }

        private void addEvent(){
            i_save.addActionListener(noteActHandler);
        }
    }//class

    class NoteActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

//              이벤트 작동 함수 작성할 것.
//                if (e.getSource() == cut)
//                    this.textarea.cut();
        }
    }


}
