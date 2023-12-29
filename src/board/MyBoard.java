package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyBoard extends JPanel {
    // variable
    private int currentX;
    private int currentY;
    private String type = "Brush";
    private Color color = Color.BLACK;
    private Boolean press = false;
    private PaintObject preview;
    private List<PaintObject> boardObjectList = new ArrayList<>();
    private final ActionHandler actHandler = new ActionHandler();

    // Panel
    private UtilButtons ubtn = new UtilButtons();
    private DrawButtons dbtn = new DrawButtons();
    private ColorButtons cbtn = new ColorButtons();

    // Label
    private Label typeLabel = new Label(this.type);

    // Construct
    public MyBoard() {
        try {
            makeUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setBackground(Color.PINK);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());
    }//Constructor()


    //Method
    private void makeUI() throws IOException {
        typeLabel.setBackground(this.color);
        typeLabel.setForeground(Color.white);

        ubtn.setLayout(new GridLayout(1, 8));
        ubtn.setPreferredSize(new Dimension(800, 40));

        dbtn.setLayout(new GridLayout(1, 7));
        dbtn.setPreferredSize(new Dimension(800, 40));

        this.setLayout(new BorderLayout());
        this.add("North", ubtn);
        this.add("South", dbtn);
        this.add("East", cbtn);
    }//makeUI

    private void refreshTypeColorLabel() {
        typeLabel.setBackground(color);
        typeLabel.setText(type);
    }

    @Override
    public void paint(Graphics g) {
        if (boardObjectList != null) {
            for (PaintObject obj : boardObjectList) {
                obj.display(g);
            }
        }
        if (preview != null) {
            preview.display(g);
        }
    }//paint


    //--inner
    //Button
    protected class UtilButtons extends JPanel {
        // Button variable
        Button resetButton = new Button("Reset");
        Button exitButton = new Button("Exit");

        // Constructor
        UtilButtons() {
            addEvent();

            // empty space
            this.add(new Panel());
            this.add(new Panel());
            this.add(new Panel());
            this.add(new Panel());
            this.add(new Panel());
            this.add(new Panel());

            this.add(resetButton);
            this.add(exitButton);
        }//Constructor UtilButtons()

        // Method
        private void addEvent() {
            resetButton.addActionListener(actHandler);
            exitButton.addActionListener(actHandler);
        }
    }//class UtilButton

    protected class DrawButtons extends JPanel {
        // Button variable
        Button lineButton = new Button("Line");
        Button brushButton = new Button("Brush");
        Button circleButton = new Button("Circle");
        Button rectangularButton = new Button("Rect");
        Button triangleButton = new Button("Triangle");

        Button redoButton = new Button("Redo");

        Button saveButton = new Button("Save");

        // Constructor
        DrawButtons() {
            addEvent();

            this.add(brushButton);
            this.add(lineButton);
            this.add(circleButton);
            this.add(rectangularButton);
            this.add(triangleButton);
            this.add(redoButton);
            this.add(saveButton);
        }//Constructor DrawButtons()

        //Method
        private void addEvent() {
            brushButton.addActionListener(actHandler);
            lineButton.addActionListener(actHandler);
            circleButton.addActionListener(actHandler);
            rectangularButton.addActionListener(actHandler);
            triangleButton.addActionListener(actHandler);
            redoButton.addActionListener(actHandler);
        }
    }//class DrawButtons

    protected class ColorButtons extends JPanel {
        // 버튼 변수
        Button redButton = new Button("Red");
        Button blackButton = new Button("Black");
        Button blueButton = new Button("Blue");
        Button yellowButton = new Button("Yellow");  // 대소문자 수정
        Button violetButton = new Button("Violet");  // 대소문자 수정
        Button greenButton = new Button("Green");
        Button indigoButton = new Button("Indigo");

        // 생성자
        ColorButtons() {
            addEvent();

            setLayout(new GridLayout(0, 1));

            this.add(redButton);
            this.add(blueButton);
            this.add(yellowButton);
            this.add(violetButton);
            this.add(greenButton);
            this.add(indigoButton);
        }//class ColorButtons()

        private void addEvent() {
            redButton.addActionListener(actHandler);
            blueButton.addActionListener(actHandler);
            blackButton.addActionListener(actHandler);
            yellowButton.addActionListener(actHandler);
            greenButton.addActionListener(actHandler);
            violetButton.addActionListener(actHandler);
            indigoButton.addActionListener(actHandler);
        }
    }//class ColorButtons


    // Handler
    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ubtn.exitButton) {
                System.exit(1);
            } else if (e.getSource() == dbtn.brushButton) {
                MyBoard.this.type = "Brush";
            } else if (e.getSource() == dbtn.lineButton) {
                MyBoard.this.type = "Line";
            } else if (e.getSource() == dbtn.circleButton) {
                MyBoard.this.type = "Circle";
            } else if (e.getSource() == dbtn.rectangularButton) {
                MyBoard.this.type = "Rect";
            } else if (e.getSource() == dbtn.triangleButton) {
                MyBoard.this.type = "Triangle";
            } else if (e.getSource() == dbtn.redoButton) {
                MyBoard.this.boardObjectList.remove(boardObjectList.size() - 1);
                repaint();
            } else if (e.getSource() == ubtn.resetButton) {
                MyBoard.this.boardObjectList.clear();
                repaint();
            } else if (e.getSource() == cbtn.redButton) {
                MyBoard.this.color = Color.red;
            } else if (e.getSource() == cbtn.blueButton) {
                MyBoard.this.color = Color.blue;
            } else if (e.getSource() == cbtn.blackButton) {
                MyBoard.this.color = Color.black;
            } else if (e.getSource() == cbtn.yellowButton) {
                MyBoard.this.color = Color.yellow;
            } else if (e.getSource() == cbtn.violetButton) {
                MyBoard.this.color = new Color(148, 0, 211);  // Violet
            } else if (e.getSource() == cbtn.greenButton) {
                MyBoard.this.color = Color.green;
            } else if (e.getSource() == cbtn.indigoButton) {
                MyBoard.this.color = new Color(75, 0, 130);   // Indigo
            }

            refreshTypeColorLabel();
        }
    }//class ActionHandler


    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            MyBoard.this.press = true;
            MyBoard.this.currentX = e.getX();
            MyBoard.this.currentY = e.getY();
        }//mouse pressed

        @Override
        public void mouseReleased(MouseEvent e) {
            preview = null;
            MyBoard.this.press = false;
            if (MyBoard.this.type.equals("Line")) {
                boardObjectList.add(new Line(currentX, currentY, e.getX(), e.getY(), color));
            } else if (MyBoard.this.type.equals("Circle")) {
                boardObjectList.add(new Circle(currentX, currentY, e.getX(), e.getY(), color));
            } else if (MyBoard.this.type.equals("Rect")) {
                boardObjectList.add(new Rectangular(currentX, currentY, e.getX(), e.getY(), color));
            } else if (MyBoard.this.type.equals("Triangle")) {
                boardObjectList.add(new Triangle(currentX, currentY, e.getX(), e.getY(), color));
            }
            repaint();
        }//mouse released

        @Override
        public void mouseDragged(MouseEvent e) {
            int newX = e.getX();
            int newY = e.getY();
            if (MyBoard.this.press == true) {
                if (type.equals("Brush")) {
                    boardObjectList.add(new Brush(currentX, currentY, newX, newY, color));
                    MyBoard.this.currentX = newX;
                    MyBoard.this.currentY = newY;
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
        }//mouse dragged
    }//class MouseHandler










}//myboard
