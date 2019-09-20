import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * The UDP Client portion of the Wisdom Words project. The client contacts
 * the server, the server responds with a word of wisdom, and the client
 * prints this word of wisdom to stdout.
 *
 * @author Evert Ball
 * @author Garrett Starkey
 *
 * @version 09/20/2019
 */
public class WisdomWordsCLUDP {
    /** A constant to represent a successful exit */
    private static final int SUCCESS = 0;

    /** A constant to represent a failed exit */
    private static final int FAILURE = 1;

    /** A constant string to print out when we encounter an IOException. */
    private static final String IO_ERROR = "I/O error...Something went wrong.";

    /** String to print out when we encounter a NumberFormatException. */
    private static final String PORT_ERROR = "Port must be a numerical value." +
            " Try again.";

    /**
     * The main entry point into our program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            System.out.println("Connecting to " + host + " on port " + port);


            // Creating a buffer
            byte[] buffer = new byte[0];
            DatagramPacket send = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
            DatagramSocket receive = new DatagramSocket();
            receive.send(send);


            // Deals with retrieving the data
            byte[] retrieve = new byte[256];
            DatagramPacket incoming = new DatagramPacket(retrieve, retrieve.length);
            receive.receive(incoming);
            String incomingTwo = new String(incoming.getData(), incoming.getOffset(), incoming.getLength());
            System.out.println(incomingTwo);


        } catch (IOException ioe) {
            System.out.println(IO_ERROR);
            System.exit(FAILURE);
        } catch (NumberFormatException input) {
            System.out.println(PORT_ERROR);
            System.exit(FAILURE);
        } // end try-catch

        System.exit(SUCCESS);

    } // end main method
} // end WisdomWordsCLUDP class
