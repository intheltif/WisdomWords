import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 * The UDP Server portion of the Wisdom Words project. The server reads in a 
 * file containing words of wisdom. Once contacted by the client, sends a
 * random word of wisdom to them.
 *
 * @author Evert Ball
 * @author Garrett Starkey
 *
 * @version 09/20/2019
 */
public class WisdomWordsServUDP {

    /** The port we are connecting to. */
    private static final int PORT = 6666;

    /** The maximum size of the buffer */
    private static final int MAX_SIZE = 256;
    
    /** A constant representing a failed exit */
    private static final int FAILURE = 1;

    /** A constant representing a failed exit */
    private static final int SUCCESS= 0;

    /** The first command line argument */
    private static final int FIRST_ARG = 0;
    
    /**
     * The main entry point into the program. Creates the buffer and datagram
     * packet that will be sent back and forth between the server and client.
     *
     * @param The command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Create new socket connected to the port we will be using
            DatagramSocket ds = new DatagramSocket(PORT);

            // Continue to accept requests until the socket is closed
            while(!ds.isClosed()){
                
                // Receive initial communication with the client
                byte[] buffer = new byte[MAX_SIZE];
                DatagramPacket DP = new DatagramPacket(buffer, MAX_SIZE);
                ds.receive(DP);
                
                // Randomly grabs some words of wisdom
                WisdomWords ww = new WisdomWords(args[FIRST_ARG]);
                Random random = new Random();
                int rand = random.nextInt(ww.getSize());
                
                // Create the objects needed to send the data to the client
                String phrase = ww.getWisdom(rand);
                byte[] outBuff = phrase.getBytes();
                InetAddress hostName = DP.getAddress();
                int port = DP.getPort();
                
                // Finally, send the wisdom to the client
                DatagramPacket packetToClient = new DatagramPacket(outBuff, outBuff.length, hostName, port);
                ds.send(packetToClient);
            }

            // Close the socket.
            ds.close();
    
        } catch(IOException ioe) {
            System.out.println("You have encountered an error " + ioe);
        } // end try-catch
    } // end main method
} // end WisdomWordsServUDP class

