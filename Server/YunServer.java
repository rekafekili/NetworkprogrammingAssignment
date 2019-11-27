package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

public class YunServer {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Port Number : ");
        int port = scanner.nextInt();

        try {
            YunServerSocket serverSocket = new YunServerSocket(port);
            YunServerVector serverTable = new YunServerVector();
            while (true) {
                // register client
                ClientInfo client = serverSocket.receivePacket();
                commandClient(serverSocket, client, serverTable);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void commandClient(YunServerSocket serverSocket, ClientInfo client, YunServerVector serverTable) throws IOException {
        String sendMSG = null;
        String message = client.getMessage();
        String command = message.substring(0,4);

        if(message.substring(0,7).equals("connect")){ // command : find
            String findingID = message.substring(7).trim();
            if(serverTable.containID(findingID)==-1){
                sendMSG = "!!! NOT FOUND : " + findingID;
            }
            else{
                InetAddress sendIP = serverTable.getmIP(serverTable.containID(findingID));
                int sendPort = 65001;
                sendMSG = "Request:" + findingID + "," + sendIP + "," + sendPort;
            }
        }

        else if(command.equals("view")){    // command : view
            sendMSG = serverTable.viewTable();
        }

        else if(command.equals("quit")){    // command : quit
            serverTable.removeVectorElement(serverTable.containIP(client.getmIP()));
            sendMSG = "!!! Disconnected with Server !!!";
            System.out.println(serverTable.viewTable());
        }

        else{   // not command, this is ID
            if(serverTable.containID(message)>-1){   // duplicate
                serverTable.removeVectorElement(serverTable.containID(message));
                serverTable.putVectorElement(client.getMessage().trim(), client.getmIP(), client.getmPort());  // update data
            }
            else {
                serverTable.putVectorElement(client.getMessage().trim(), client.getmIP(), client.getmPort());
                System.out.println(serverTable.viewTable());
            }
            sendMSG = "--- SUCCESS >> Register to Server ---\n" +
                    "> Server Commands >>>>>>>>>>>>>>>>>>>>>\n" +
                    "1. view : return other clients connected with server\n" +
                    "2. connect <id> : return partner client ip address\n" +
                    "3. quit : disconnect with server\n" +
                    ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
        }
        serverSocket.sendMessage(client.getmIP(), client.getmPort(), sendMSG);
    }
}