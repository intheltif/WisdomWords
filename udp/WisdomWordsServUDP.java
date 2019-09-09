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

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(6666);
            while(!ds.isClosed()){
                byte[] buffer = new byte[256];
                DatagramPacket DP = new DatagramPacket(buffer, 256);
                ds.receive(DP);
                
                
                WisdomWords ww = new WisdomWords(args[0]);
                Random random = new Random();
                int rand = random.nextInt(ww.getSize());

                String phrase = ww.getWisdom(rand);
                byte[] outBuff = phrase.getBytes();
                InetAddress hostName = DP.getAddress();
                int port = DP.getPort();

                DatagramPacket packetToClient = new DatagramPacket(outBuff, outBuff.length, hostName, port);
                ds.send(packetToClient);
            }
            ds.close();
    
        } catch(IOException ioe) {
            System.out.println("You have encountered an error " + ioe);
        }
    }
}

