package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private ServerSocket serverSocket;
    public static Socket socket;


    DataOutputStream output;
    DataInputStream input;

    private int PORT = 5000 ;

    public void run()  {

        // Se crea el socket del servidor

        try {
            this.serverSocket = new ServerSocket(PORT);
            System.out.println("---Servidor Iniciado---");



            }catch (IOException e) {
            e.printStackTrace();
            }
    }

    public void startToLisen() throws IOException {

        while (true){

            socket = this.serverSocket.accept();
            new Thread(new Client()).start();
            System.out.println("----Un nuevo Cliente se ha conectado----");


            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());



        }


    }
}
