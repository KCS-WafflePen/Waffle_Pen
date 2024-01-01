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
    private final UtilButtons ubtnp = new UtilButtons();
    private final DrawButtonPanel dbtnp = new DrawButtonPanel();
    private final ColorButtonPanel cbtnp = new ColorButtonPanel();
    private final DrawPaintPanel dpp = new DrawPaintPanel();

    // Label
    private Label typeLabel = new Label(this.type);

    // Construct
    public MyBoard() {
        try {
            makeUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//Constructor()


    //Method
    private void makeUI() throws IOException {
        typeLabel.setBackground(this.color);
        typeLabel.setForeground(Color.white);

        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());

        this.add("North", ubtnp);
        this.add("South", dbtnp);
        this.add("East", cbtnp);
        this.add("Center", dpp);
    }//makeUI

    private void refreshTypeColorLabel() {
        typeLabel.setBackground(color);
        typeLabel.setText(type);
    }


    //--inner
    //Button
    protected class UtilButtons extends JPanel {
        // Button variable
        JButton saveButton = new JButton("Save");
        JButton exitButton = new JButton("Exit");

        // Constructor
        UtilButtons() {
            addEvent();

            this.add(saveButton);
            this.add(exitButton);
        }//Constructor UtilButtons()

        // Method
        private void addEvent() {
            exitButton.addActionListener(actHandler);
        }
    }//class UtilButton

    protected class DrawButtonPanel extends JPanel {
        // Button variable
        JButton lineButton = new JButton("Line");
        JButton brushButton = new JButton("Brush");
        JButton circleButton = new JButton("Circle");
        JButton rectangularButton = new JButton("Rect");
        JButton triangleButton = new JButton("Triangle");

        JButton redoButton = new JButton("Redo");
        JButton resetButton = new JButton("Reset");

        // Constructor
        DrawButtonPanel() {
            addEvent();

            this.add(brushButton);
            this.add(lineButton);
            this.add(circleButton);
            this.add(rectangularButton);
            this.add(triangleButton);
            this.add(redoButton);
            this.add(resetButton);
        }//Constructor DrawButtons()

        //Method
        private void addEvent() {
            brushButton.addActionListener(actHandler);
            lineButton.addActionListener(actHandler);
            circleButton.addActionListener(actHandler);
            rectangularButton.addActionListener(actHandler);
            triangleButton.addActionListener(actHandler);
            redoButton.addActionListener(actHandler);
            resetButton.addActionListener(actHandler);
        }
    }//class DrawButtonPanel

    protected class ColorButtonPanel extends JPanel {
        // Button variable
        JButton redButton = new JButton("Red");
        JButton blueButton = new JButton("Blue");
        JButton blackButton = new JButton("Black");

        // Constructor
        ColorButtonPanel(){
            addEvent();

            this.add(redButton);
            this.add(blueButton);
            this.add(blackButton);
        }//class ColorButtons()

        private void addEvent() {
            redButton.addActionListener(actHandler);
            blueButton.addActionListener(actHandler);
            blackButton.addActionListener(actHandler);
        }
    }//class ColorButtonPanel

    protected class DrawPaintPanel extends JPanel {

        DrawPaintPanel(){
            this.setBackground(Color.white);

            this.addMouseListener(new MouseHandler());
            this.addMouseMotionListener(new MouseHandler());
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

        // Mouse Handler for draw paint panel
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
    }

    // Handler
    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ubtnp.exitButton) {
                System.exit(1);
            } else if (e.getSource() == dbtnp.brushButton) {
                MyBoard.this.type = "Brush";
            } else if (e.getSource() == dbtnp.lineButton) {
                MyBoard.this.type = "Line";
            } else if (e.getSource() == dbtnp.circleButton) {
                MyBoard.this.type = "Circle";
            } else if (e.getSource() == dbtnp.rectangularButton) {
                MyBoard.this.type = "Rect";
            } else if (e.getSource() == dbtnp.triangleButton) {
                MyBoard.this.type = "Triangle";
            } else if (e.getSource() == dbtnp.redoButton) {
               MyBoard.this.boardObjectList.remove(boardObjectList.size() - 1);
                repaint();
            } else if (e.getSource() == dbtnp.resetButton) {
                MyBoard.this.boardObjectList.clear();
                repaint();
            } else if (e.getSource() == cbtnp.redButton) {
                MyBoard.this.color = Color.red;
            } else if (e.getSource() == cbtnp.blueButton) {
                MyBoard.this.color = Color.blue;
            } else if (e.getSource() == cbtnp.blackButton) {
                MyBoard.this.color = Color.black;
            }
            refreshTypeColorLabel();
        }
    }//class ActionHandler

}//myboard
