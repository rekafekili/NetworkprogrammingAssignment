package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RequestThread extends Thread{
    private InetAddress ipaddr;
    private int port;

    public RequestThread(String addr, String port) throws UnknownHostException {
        this.ipaddr = InetAddress.getByName(addr.substring(1));
        this.port = Integer.parseInt(port);
        System.out.println("TCP Requesting Thread Start...");
    }

    @Override
    public void run(){
        try {
            StreamSocket streamSocket = new StreamSocket(ipaddr, port);
            System.out.println("Connection Requested");
            Scanner sc = new Scanner(System.in);
            streamSocket.sendMessage(sc.next());
            if(sc.next().trim().equals("quit")){
                streamSocket.close();
            }
            streamSocket.receiveMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
