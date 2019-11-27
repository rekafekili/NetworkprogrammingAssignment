package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class AcceptThread extends Thread {
    private int port;
    private ServerSocket svrSocket;

    AcceptThread(int port) throws IOException {
        this.port = port;
        this.svrSocket = new ServerSocket(this.port);
        System.out.println("TCP Accepting Thread Start...");
    }

    @Override
    public void run(){
        try {
            StreamSocket streamSocket = new StreamSocket(svrSocket.accept());
            System.out.println("Connection Accepted");
            Scanner sc = new Scanner(System.in);
            streamSocket.receiveMessage();
            streamSocket.sendMessage(sc.next());
            if(sc.next().trim().equals("quit")){
                streamSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
