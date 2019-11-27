package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class StreamSocket extends Socket{
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    StreamSocket(InetAddress acceptorHost, int acceptorPort) throws SocketException, IOException {
        socket = new Socket(acceptorHost, acceptorPort);
        setStreams();
    }

    StreamSocket(Socket socket)  throws IOException {
        this.socket = socket;
        setStreams();
    }

    private void setStreams() throws IOException{
        InputStream inStream = socket.getInputStream();
        input = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        output = new PrintWriter(new OutputStreamWriter(outStream));
    }

    public void sendMessage(String message) throws IOException {
        output.println(message);
        output.flush();
    }

    public String receiveMessage() throws IOException {
        String message = input.readLine();
        return message;
    }

    public void close() throws IOException {
        socket.close();
    }
}
