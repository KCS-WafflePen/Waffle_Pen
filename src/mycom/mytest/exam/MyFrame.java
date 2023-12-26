package mycom.mytest.exam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends Frame{
    // Constants
    public static final int LINE_SIZE = 5;
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    // variable
    private Image backgroundImage;
    private int currentX;
    private int currentY;
    private String type = "Brush";
    private Color color = Color.black;
    private Boolean press = false;

    private List<BoardObject> boardObjectList = new ArrayList<>();

    private BoardObject preview;
    // UI
    // Panel
    private Panel northPanel = new Panel();
    private Panel southPanel = new Panel();

    // Label
    private Label typeLabel = new Label(this.type);

    // Button
    private Button brushButton = new Button("Brush");
    private Button lineButton = new Button("Line");
    private Button circleButton = new Button("Circle");
    private Button rectangularButton = new Button("Rect");
    private Button triangleButton = new Button("Triangle");
    private Button redButton = new Button("Red");
    private Button blueButton  = new Button("Blue");
    private Button blackButton = new Button("Black");
    private Button redoButton = new Button("Redo");
    private Button resetButton = new Button("Reset");
    private Button exitButton = new Button("Exit");
    public MyFrame() {
        makeEvent();
        try {
            makeUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setSize(MyFrame.FRAME_WIDTH, MyFrame.FRAME_HEIGHT);
        this.setVisible(true);
    }
    private void makeEvent() {
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());
        exitButton.addActionListener(new ActionHandler());
        brushButton.addActionListener(new ActionHandler());
        lineButton.addActionListener(new ActionHandler());
        circleButton.addActionListener(new ActionHandler());
        rectangularButton.addActionListener(new ActionHandler());
        triangleButton.addActionListener(new ActionHandler());
        redButton.addActionListener(new ActionHandler());
        blueButton.addActionListener(new ActionHandler());
        blackButton.addActionListener(new ActionHandler());
        redoButton.addActionListener(new ActionHandler());
        resetButton.addActionListener(new ActionHandler());
    }

    private void makeUI() throws IOException{
        setBackground();

        typeLabel.setBackground(this.color);
        typeLabel.setForeground(Color.white);

        northPanel.add(typeLabel);
        northPanel.add(exitButton);
        southPanel.add(brushButton);
        southPanel.add(lineButton);
        southPanel.add(circleButton);
        southPanel.add(rectangularButton);
        southPanel.add(triangleButton);
        southPanel.add(redButton);
        southPanel.add(blueButton);
        southPanel.add(blackButton);
        southPanel.add(redoButton);
        southPanel.add(resetButton);

        this.add("North", northPanel);
        this.add("South", southPanel);
    }

    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exitButton) {
                System.exit(1);
            } else if (e.getSource() == brushButton) {
                MyFrame.this.type = "Brush";
            } else if (e.getSource() == lineButton) {
                MyFrame.this.type = "Line";
            } else if (e.getSource() == circleButton) {
                MyFrame.this.type = "Circle";
            } else if (e.getSource() == rectangularButton) {
                MyFrame.this.type = "Rect";
            } else if (e.getSource() == triangleButton) {
                MyFrame.this.type = "Triangle";
            } else if (e.getSource() == redButton) {
                MyFrame.this.color = Color.red;
            } else if (e.getSource() == blueButton) {
                MyFrame.this.color = Color.blue;
            } else if (e.getSource() == blackButton) {
                MyFrame.this.color = Color.black;
            } else if (e.getSource() == redoButton) {
                MyFrame.this.boardObjectList.remove(boardObjectList.size() - 1);
                repaint();
            } else if (e.getSource() == resetButton) {
                MyFrame.this.boardObjectList.clear();
                repaint();
            }
            refreshTypeColorLabel();
        }
    }

    class MouseHandler implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            MyFrame.this.press = true;
            MyFrame.this.currentX = e.getX();
            MyFrame.this.currentY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            preview = null;
            MyFrame.this.press = false;
            if (MyFrame.this.type.equals("Line")) {
                boardObjectList.add(new Line(currentX, currentY, e.getX(), e.getY(), color));
            } else if (MyFrame.this.type.equals("Circle")) {
                boardObjectList.add(new Circle(currentX, currentY, e.getX(), e.getY(), color));
            } else if (MyFrame.this.type.equals("Rect")) {
                boardObjectList.add(new Rectangular(currentX, currentY, e.getX(), e.getY(), color));
            } else if (MyFrame.this.type.equals("Triangle")) {
                boardObjectList.add(new Triangle(currentX, currentY, e.getX(), e.getY(), color));
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int newX = e.getX();
            int newY = e.getY();
            if (MyFrame.this.press == true) {
                if (type.equals("Brush")) {
                    boardObjectList.add(new Brush(currentX, currentY, newX, newY, color));
                    MyFrame.this.currentX = newX;
                    MyFrame.this.currentY = newY;
                } else if (type.equals("Line")) {
                    preview = new Line(currentX, currentY, newX, newY, color);
                } else if (type.equals("Circle")) {
                    preview = new Circle(currentX, currentY, newX, newY, color);
                } else if (type.equals("Rect")) {
                    preview = new Rectangular(currentX, currentY, newX, newY, color);
                } else if (type.equals("Triangle")) {
                    preview = new Triangle(currentX, currentY, newX, newY, color);
                }
            }
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
    @Override
    public void paint(Graphics g) {
        if (boardObjectList != null) {
            for (BoardObject obj : boardObjectList) {
                obj.display(g);
            }
        }
        if (preview != null) {
            preview.display(g);
        }

    }
    public void setBackground() throws IOException {
        backgroundImage = ImageIO.read(new File("/Users/ljy/Downloads/paintboard/src/mycom/mytest/exam/Pixabay로부터 입수된 csk님의 이미지 입니다.jpg"));
    }
    private void refreshTypeColorLabel() {
        typeLabel.setBackground(color);
        typeLabel.setText(type);
    }
}
