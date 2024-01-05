package Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    String serverAddress = "localhost";
    int serverPort = 8000;
    JFrame frame;
    JLabel imgLabel;
    private Thread clientThread;

    public static void main(String[] args) {
        new Client().startClient();
    }

    //Construct
    public void startClient() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imgLabel = new JLabel();
        frame.getContentPane().add(imgLabel);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        clientThread = new Thread(new ServerConnectionHandler());
        clientThread.start();
    }


    private BufferedImage imageReceive(Socket socket) {
        try {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            return ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateImageLabel(BufferedImage image) {
        ImageIcon icon = new ImageIcon(image);
        imgLabel.setIcon(icon);
        imgLabel.repaint();
    }

    public void stopClient() {
        if (frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }

        if (clientThread != null && clientThread.isAlive()) {
            clientThread.interrupt();
        }
    }//stopClient

    //--inner
    // Socket Handler for show server img and drawing
    private class ServerConnectionHandler implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket(serverAddress, serverPort);
                System.out.println("Connected to server");

                SwingUtilities.invokeLater(() -> frame.setVisible(true));

                while (socket.isConnected()) {
                    BufferedImage receivedImage = imageReceive(socket);
                    System.out.println(receivedImage);

                    if (receivedImage != null) {
                        SwingUtilities.invokeLater(() -> updateImageLabel(receivedImage));
                    }
                }

                stopClient();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                stopClient();
            }
        }
    }
}
