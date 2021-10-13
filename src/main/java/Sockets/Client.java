package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {

    //Host del servidor
    String HOST = "Localhost";
    //PORT del servidor
    int PORT = 5000;
    DataOutputStream output;
    DataInputStream input;



    public Client () throws IOException{

        try {

            Socket socket = new Socket(HOST, PORT);

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());


            while(true){




                // Envio de mensajes y objetos que necesiten estar actualizando constantemente




            }





        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {

    }

}
