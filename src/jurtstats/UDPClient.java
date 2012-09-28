package jurtstats;

import java.io.*;
import java.net.*;

/**
 *
 * @author masnun
 */
public class UDPClient {

    public String getData(String host, int port) throws Exception {
        // Message and bytes
        String message = "xxxxgetstatus";
        byte[] messageBytes = message.getBytes("UTF-8");
        
        // Special character
        byte specialChar = (byte) 0xff;

        messageBytes[0] = specialChar;
        messageBytes[1] = specialChar;
        messageBytes[2] = specialChar;
        messageBytes[3] = specialChar;

        // IP Address
        InetAddress IPAddress = InetAddress.getByName(host);

        //Socket and Packet
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket sendPacket = new DatagramPacket(messageBytes, messageBytes.length, IPAddress, port);
        socket.send(sendPacket);

        // Set timeout
        socket.setSoTimeout(2000);

        // Recieve data and return
        byte[] data = new byte[1024];
        DatagramPacket recievePacket = new DatagramPacket(data, data.length);
        socket.receive(recievePacket);
        String serverInfo = new String(recievePacket.getData());
        return serverInfo;

    }
}
