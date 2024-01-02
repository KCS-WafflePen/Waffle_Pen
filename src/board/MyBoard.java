package board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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

        this.add("South", ubtnp);
        this.add("North", dbtnp);
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
        JButton saveButton = new JButton("저장");
        JButton exitButton = new JButton("나가기");

        // Constructor
        UtilButtons() {
            addEvent();

            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 80));

            this.add(saveButton);
            this.add(exitButton);
        }//Constructor UtilButtons()

        // Method
        private void addEvent() {

            exitButton.addActionListener(actHandler);
            saveButton.addActionListener(actHandler);
        }
    }//class UtilButton

    protected class DrawButtonPanel extends JPanel {

        //저장 이미지
        private BufferedImage canvas;

        // Button Image
        ImageIcon Brush = new ImageIcon("./ButtonImg/brush.png");
        ImageIcon Line = new ImageIcon("./ButtonImg/line.png");
        ImageIcon Circle = new ImageIcon("./ButtonImg/circle.png");
        ImageIcon triangle = new ImageIcon("./ButtonImg/triangle.png");
        ImageIcon rectangle = new ImageIcon("./ButtonImg/rectangle.png");
        ImageIcon back = new ImageIcon("./ButtonImg/back.png");
        ImageIcon reset = new ImageIcon("./ButtonImg/reset.png");

        // Button variable
        JButton lineButton = new JButton(Line);
        JButton brushButton = new JButton(Brush);
        JButton circleButton = new JButton(Circle);
        JButton rectangularButton = new JButton(rectangle);
        JButton triangleButton = new JButton(triangle);

        JButton redoButton = new JButton(back);

        JButton resetButton = new JButton(reset);

        // Constructor
        DrawButtonPanel() {
            addEvent();

            // Button design
            brushButton.setOpaque(false);
            brushButton.setBorder(null);
            lineButton.setOpaque(false);
            lineButton.setBorder(null);
            circleButton.setOpaque(false);
            circleButton.setBorder(null);
            rectangularButton.setOpaque(false);
            rectangularButton.setBorder(null);
            triangleButton.setOpaque(false);
            triangleButton.setBorder(null);
            redoButton.setOpaque(false);
            redoButton.setBorder(null);
            resetButton.setOpaque(false);
            resetButton.setBorder(null);

            this.add(brushButton);
            this.add(lineButton);
            this.add(circleButton);
            this.add(rectangularButton);
            this.add(triangleButton);
            this.add(new JPanel());
            this.add(new JPanel());
            this.add(redoButton);
            this.add(new JPanel());
            this.add(resetButton);

        }//Constructor DrawButtons()

        // DrawPanel 내용을 PNG 파일로 저장하는 메서드
        private void saveAsImage(File file) throws IOException {
            BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            // 배경을 흰색으로 설정
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

            // 그림을 그린다
            super.paintComponent(g2d);

            // 저장
            ImageIO.write(image, "png", file);
        }


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

        // Button Image
        ImageIcon red = new ImageIcon("./ButtonImg/red.png");
        ImageIcon yellow = new ImageIcon("./ButtonImg/yellow.png");
        ImageIcon orange = new ImageIcon("./ButtonImg/orange.png");
        ImageIcon green = new ImageIcon("./ButtonImg/green.png");
        ImageIcon blue = new ImageIcon("./ButtonImg/blue.png");
        ImageIcon purple = new ImageIcon("./ButtonImg/purple.png");
        ImageIcon gray = new ImageIcon("./ButtonImg/gray.png");
        ImageIcon dark = new ImageIcon("./ButtonImg/black.png");

        JButton redButton = new JButton(red);
        JButton yellowButton = new JButton(yellow);
        JButton orangeButton = new JButton(orange);
        JButton greenButton = new JButton(green);
        JButton blueButton = new JButton(blue);
        JButton purpleButton = new JButton(purple);
        JButton grayButton = new JButton(gray);
        JButton darkButton = new JButton(dark);

        // Constructor
        ColorButtonPanel(){
            addEvent();

            this.setLayout(new GridLayout(0, 1));

            // Button design
            redButton.setOpaque(false);
            redButton.setBorder(null);
            yellowButton.setOpaque(false);
            yellowButton.setBorder(null);
            orangeButton.setOpaque(false);
            orangeButton.setBorder(null);
            greenButton.setOpaque(false);
            greenButton.setBorder(null);
            blueButton.setOpaque(false);
            blueButton.setBorder(null);
            purpleButton.setOpaque(false);
            purpleButton.setBorder(null);
            grayButton.setOpaque(false);
            grayButton.setBorder(null);
            darkButton.setOpaque(false);
            darkButton.setBorder(null);

            this.add(redButton);
            this.add(yellowButton);
            this.add(orangeButton);
            this.add(greenButton);
            this.add(blueButton);
            this.add(purpleButton);
            this.add(grayButton);
            this.add(darkButton);
        }//class ColorButtons()

        private void addEvent() {
            redButton.addActionListener(actHandler);
            yellowButton.addActionListener(actHandler);
            orangeButton.addActionListener(actHandler);
            greenButton.addActionListener(actHandler);
            blueButton.addActionListener(actHandler);
            purpleButton.addActionListener(actHandler);
            grayButton.addActionListener(actHandler);
            darkButton.addActionListener(actHandler);
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
            Graphics2D g2d = (Graphics2D) g;

            // 선 두께 설정 (5로 설정, 필요에 따라 조절 가능)
            g2d.setStroke(new BasicStroke(3));

            if (boardObjectList != null) {
                for (PaintObject obj : boardObjectList) {
                    obj.display(g2d);
                }
            }
            if (preview != null) {
                preview.display(g2d);
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
            } else if (e.getSource() == ubtnp.saveButton) {
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
                        BufferedImage image = new BufferedImage(dpp.getWidth(), dpp.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = image.createGraphics();
                        dpp.paint(g2d);
                        g2d.dispose();

                        // Save the image
                        ImageIO.write(image, "PNG", fileToSave);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
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
            } else if (e.getSource() == cbtnp.yellowButton) {
                MyBoard.this.color = Color.yellow;
            } else if (e.getSource() == cbtnp.orangeButton) {
                MyBoard.this.color = new Color(252, 196, 25);
            } else if (e.getSource() == cbtnp.greenButton) {
                MyBoard.this.color = new Color(0, 153, 0);
            } else if (e.getSource() == cbtnp.blueButton) {
                MyBoard.this.color = Color.blue;
            } else if (e.getSource() == cbtnp.purpleButton) {
                MyBoard.this.color = new Color(153, 51, 153);
            } else if (e.getSource() == cbtnp.grayButton) {
                MyBoard.this.color = new Color(102, 102, 102);
            } else if (e.getSource() == cbtnp.darkButton) {
                MyBoard.this.color = Color.black;
            }
            refreshTypeColorLabel();
        }
    }//class ActionHandler

}//myboard
