package Frame;

import Client.Client;
import Server.Server;
//import frame.DataMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainPage extends JPanel {

    private final EnterPanel ep = new EnterPanel();
    private final CodePanel cp = new CodePanel();
    private final NewRoom nr = new NewRoom();

    MyFrame mf = new MyFrame();
    DataMan dataMan = new DataMan();

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
        this.add("Center", cp);
        this.add("South", nr);
    }

    private class EnterPanel extends JPanel {

        ImageIcon teacher = new ImageIcon("./ButtonImg/teacher04.png");
        ImageIcon student = new ImageIcon("./ButtonImg/student04.png");
        JButton TeacherButton = new JButton(teacher);
        JButton StudentButton = new JButton(student);

        EnterPanel() {
            addEvent();

            this.setLayout(new FlowLayout(FlowLayout.CENTER));  // 변경: 중앙 정렬
            this.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

            TeacherButton.setPreferredSize(new Dimension(100,80));
            StudentButton.setPreferredSize(new Dimension(100,80));

            this.add(TeacherButton);
            this.add(new JPanel());
            this.add(StudentButton);
        }

        private void addEvent() {
            TeacherButton.addActionListener(new ActionHandler());
            StudentButton.addActionListener(new ActionHandler());
        }
    }

    private class CodePanel extends JPanel {
        JTextField InviteCode = new JTextField();

        CodePanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));  // 변경: 중앙 정렬
            this.add(InviteCode);
            InviteCode.setText("초대코드 생성");
            InviteCode.setEnabled(false);
        }

        public void setInviteCode(String code) {
            InviteCode.setText(code);
        }
    }

    private class NewRoom extends JPanel {
        JButton createButton = new JButton("강의방 개설");

        NewRoom() {
            createButton.setSize(100, 50);
            this.setLayout(new FlowLayout(FlowLayout.CENTER));  // 변경: 중앙 정렬
            this.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

            createButton.addActionListener(new ActionHandler());

            createButton.setVisible(false);

            this.add(createButton);
        }
    }

    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ep.TeacherButton) {
                EnterCode ec = new EnterCode();
                ec.setPwdLength(8);
                String inviteCode = ec.excuteGenerate();

                cp.setInviteCode(inviteCode);
                cp.InviteCode.setEnabled(true);

                nr.createButton.setVisible(true);

                ep.TeacherButton.setEnabled(false);
                ep.StudentButton.setEnabled(false);
            } else if (e.getSource() == nr.createButton) {
                //초대 코드를 데이터베이스에 저장
                dataMan.saveInviteCodeToDatabase(cp.InviteCode.getText(), "localhost", 8000);
                //Server.startServer();
                Server s = new Server();
                s.startServer();
                //mf.setVisible(true);
            } else if (e.getSource() == ep.StudentButton) {
                new Client();

                ep.TeacherButton.setEnabled(false);
                String studentInputCode = JOptionPane.showInputDialog("코드를 입력하세요:");

                cp.setInviteCode(studentInputCode);

                System.out.println(studentInputCode);

                 //데이터베이스에서 초대 코드 확인
                if (dataMan.isInviteCodeValid(studentInputCode)) {
                    //mf.setVisible(true);
                    //new Client().startClient();
                } else {
                    JOptionPane.showMessageDialog(null, "초대코드를 확인하세요");
                    System.exit(1);
                }
            }
        }
    }
}
