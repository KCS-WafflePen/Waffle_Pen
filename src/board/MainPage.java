package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainPage extends JPanel {
    private final EnterPanel ep = new EnterPanel();
    private final CodePanel cp = new CodePanel();

    private final NewRoom nr = new NewRoom();

    public MainPage() {
        try {
            makeUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void makeUI() throws IOException {
        this.setLayout(new BorderLayout());
        this.add("North", ep);
        this.add("Center",cp);
        this.add("South",nr);
        //this.add("South",sp);
    }

    private class EnterPanel extends JPanel {
        JButton TeacherButton = new JButton("강사");
        JButton StudentButton = new JButton("학생");

        EnterPanel() {
            addEvent();

            TeacherButton.setSize(100, 50);
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 80));
            this.add(TeacherButton);
            this.add(StudentButton);
        }

        private void addEvent() {
            TeacherButton.addActionListener(new ActionHandler());
            StudentButton.addActionListener(new ActionHandler());
        }
    }

    private class CodePanel extends JPanel {
        JTextArea InviteCode = new JTextArea("초대코드 생성");

        public void setInviteCodeText(String text) {
            InviteCode.setText(text);
        }

        CodePanel(){
            this.add(InviteCode);
        }
    }

    private class NewRoom extends JPanel {
        JButton enterButton = new JButton("강의방 개설");

        NewRoom() {
            enterButton.setSize(100, 50);
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 80));

            // 처음에는 강의방 개설 버튼을 숨김
            enterButton.setVisible(false);

            this.add(enterButton);
        }
    }



    // Handler
// Handler
    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ep.TeacherButton) {
                EnterCode ec = new EnterCode();
                ec.setPwdLength(8);
                String inviteCode = ec.excuteGenerate();
                cp.setInviteCodeText(inviteCode);

                // 강의방 개설 버튼을 보이도록 설정
                nr.enterButton.setVisible(true);
            } else if (e.getSource() == ep.StudentButton) {
                // Handle join button action
            } else if (e.getSource() == nr.enterButton) {

            }
        }
    }



}


