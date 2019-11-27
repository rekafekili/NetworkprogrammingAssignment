package Client;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        final int MAX_LEN = 1000;

        try {
            DatagramSocket dsoc = new DatagramSocket();
            AcceptThread acThr = new AcceptThread(65001);
            acThr.start();
            // 받을 곳의 주소 생성
            System.out.println("주소를 입력하세요:");
            String hostName = br.readLine();
            if (hostName.length() == 0)
                hostName = "localhost";
            InetAddress ia = InetAddress.getByName(hostName);
            System.out.println("포트번호를 입력하세요:");
            String portNum = br.readLine();
            if (portNum.length() == 0)
                portNum = "7";

            boolean done = false;

            while (!done) {
                System.out.println("Enter a Message :");
                String message = br.readLine();
                if (message.equals("quit")) {
                    done = true;
                }
                DatagramPacket dp = new DatagramPacket(message.getBytes(), message.getBytes().length, ia, Integer.parseInt(portNum));

                dsoc.send(dp);
                byte[] buf = new byte[MAX_LEN];
                dp = new DatagramPacket(buf, MAX_LEN);
                dsoc.receive(dp);
                String rcv = new String(buf);
                System.out.println(rcv);
                if(rcv.substring(0,8).equals("Request:")) {
                    String[] arr = rcv.substring(8).split(",");
                    RequestThread reqThr = new RequestThread(arr[1].trim(), arr[2].trim());
                    reqThr.start();
                }
            }
            dsoc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}