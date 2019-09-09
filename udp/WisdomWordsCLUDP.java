import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WisdomWordsCLUDP {
    private static final int SUCCESS = 0;
    private static final int FAILURE = 1;
    
    public static void main(String[] args) {
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            System.out.println("Connecting to " + host + " on port " + port);


            /**Creating a buffer*/
            byte[] buffer = new byte[0];
            DatagramPacket send = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
            DatagramSocket receive = new DatagramSocket();
            receive.send(send);


            /**Deals with retrieving the data*/
            byte[] retrieve = new byte[256];
            DatagramPacket incoming = new DatagramPacket(retrieve, retrieve.length);
            receive.receive(incoming);
            String incomingTwo = new String(incoming.getData(), incoming.getOffset(), incoming.getLength());
            System.out.println(incomingTwo);


        } catch (IOException ioe) {
            System.out.println("You have entered an incorrect address " + ioe);
            System.exit(FAILURE);
        } catch (NumberFormatException input) {
            System.out.println("You have entered an incorrect port number " + input);
            System.exit(FAILURE);
        }
    }
} // end WisdomWordsCLUDP class
