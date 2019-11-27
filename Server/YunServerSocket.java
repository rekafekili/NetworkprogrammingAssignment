package Server;

import java.io.IOException;
import java.net.*;
import java.net.SocketException;

public class YunServerSocket extends DatagramSocket {
    private final int MAX_LEN = 1000;

    public YunServerSocket(int port) throws SocketException {
        super(port);
    }

    public void sendMessage(InetAddress ipaddr, int port, String message) throws IOException {
        String sendMessage = message;
        byte[] sendBuffer = sendMessage.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ipaddr, port);

        this.send(sendPacket);
    }

    public ClientInfo receivePacket() throws IOException {
        byte[] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, MAX_LEN);

        this.receive(receivePacket);

        String receiveMessage = new String(receiveBuffer);

        ClientInfo client = new ClientInfo(receiveMessage, receivePacket.getAddress(), receivePacket.getPort());
        return client;
    }
}