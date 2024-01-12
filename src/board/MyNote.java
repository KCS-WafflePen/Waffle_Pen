package Frame.board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyNote extends MyBoard {
    private final TextPanel txtp = new TextPanel();
    private final NoteActionHandler noteActHandler = new NoteActionHandler();

    private final UtilButtonPanel utilButtonPanel = (UtilButtonPanel) getUtilButtonPanel();

    // Construct
    public MyNote() {
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());

        JPanel spanel = new JPanel(new BorderLayout());
        txtp.setPreferredSize(new Dimension(800, 200));
        spanel.add("Center", txtp);
        spanel.add("South", utilButtonPanel);

        this.add("North", getDrawButtonPanel());
        this.add("East", getColorButtonPanel());
        this.add("Center", getDrawPaintPanel());

        this.add("South", spanel);
        this.add("North", getDrawButtonPanel());
        this.add("East", getColorButtonPanel());
        this.add("Center", getDrawPaintPanel());
    }


    //--inner
    protected class TextPanel extends JPanel {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("파일");
        JMenuItem i_save = new JMenuItem("저장");
        JMenuItem i_load = new JMenuItem("열기");
        JTextArea textarea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textarea);

        TextPanel() {
            addEvent();

            file.add(i_save);
            file.add(i_load);

            menubar.add(file);

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
            if (e.getSource() == utilButtonPanel.saveButton) {

            }
//              이벤트 작동 함수 작성할 것.
//                if (e.getSource() == cut)
//                    this.textarea.cut();
        }
    }

    @Override
    protected void saveImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Check if the file has a .png extension, if not, add it
            if (!filePath.toLowerCase().endsWith(".png")) {
                fileToSave = new File(filePath + ".png");
            }

            try {
                BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = image.createGraphics();
                this.paint(g2d);
                g2d.dispose();

                // Save the image
                ImageIO.write(image, "PNG", fileToSave);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
