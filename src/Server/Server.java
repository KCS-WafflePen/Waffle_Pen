package Server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerFrame frame;

    public static void main(String[] args) {
        new Server().startServer();
    }

    //Method
    private static void startServer() {
        int port = 8000;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            frame = new ServerFrame();
            System.out.println("Sever statrted...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected" + clientSocket.getInetAddress());

                new Thread(new ClientConnectionHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//startServer

    //send Draw Painting Panel Image to client
    private static void sendPaintingImage(Socket socket) throws AWTException {
        try {
            BufferedImage captureImage = frame.mb.getDrawPaintPanelImage();

            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            ImageIO.write(captureImage, "png", bos);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//sendPaintingImage


    //--inner
    // Socket Handler for 1:n connection
    private static class ClientConnectionHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientConnectionHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        // Method to handle connection
        @Override
        public void run() {
            try {
                while (true) {
                    sendPaintingImage(clientSocket);
                    Thread.sleep(1000); // 전송 대기
                }
            } catch (AWTException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//run
    }//class ClientHandler
}
