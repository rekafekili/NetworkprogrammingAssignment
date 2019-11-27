package Server;

import java.net.*;

public class ClientInfo {
    private String message;
    private InetAddress mIP;
    private int mPort;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setmIP(InetAddress mIP) {
        this.mIP = mIP;
    }

    public InetAddress getmIP() {
        return mIP;
    }

    public int getmPort() {
        return mPort;
    }

    public void setmPort(int mPort) {
        this.mPort = mPort;
    }

    public ClientInfo(String msg, InetAddress ip, int port) {
        this.message = msg;
        this.mIP = ip;
        this.mPort = port;
    }
}