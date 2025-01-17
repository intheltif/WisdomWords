import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * The TCP Server portion of the Wisdom Words project. The server reads in a 
 * file containing words of wisdom. Once contacted by the client, sends a
 * random word of wisdom to them.
 *
 * @author Evert Ball
 * @author Garrett Starkey
 *
 * @version 09/20/2019
 */
public class WisdomWordsServTCP {

    /** The port we are connecting to. */
    private static final int PORT = 5555;
    
    /** A constant representing a failed exit */
    private static final int FAILURE = 1;

    /** A constant representing a failed exit */
    private static final int SUCCESS= 0;

    /**
     * The main entry point into the program. Passes off duties to the go
     * method to make this project object oriented.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        WisdomWordsServTCP tcp = new WisdomWordsServTCP();
        tcp.go(args);

        System.exit(SUCCESS);

    } // end main method
    
    /**
     * The method that runs our program.
     *
     * @param args The command line arguments, received from the main method.
     */
    private void go(String[] args) {

        if(args.length != 1) {
            usage();
        }

        // The filename that holds the wise sayings.
        String file = args[0];


        try {
            // Creates the ServerSocket on port 5500
            ServerSocket server = new ServerSocket(PORT);

            // Message to let user know the server is actually doing something.
            System.out.println("For a moment, nothing happened. \nThen, after" +
                               " a second or so, nothing continued to happen.");

            //Continues to listen for requests until socket is closed
            while(!server.isClosed()) {

                // Create a socket for communication
                Socket commSocket = server.accept();

                // Creating streams for the server to communicate with client
                InputStreamReader serverInputStream =
                    new InputStreamReader(commSocket.getInputStream());
                BufferedReader serverReader =
                    new BufferedReader(serverInputStream);
                PrintWriter serverPrintWriter =
                    new PrintWriter(commSocket.getOutputStream(), true);

                // Gets a random saying to send to the client.
                WisdomWords wiseGuy = new WisdomWords(file);
                Random rand = new Random();
                int random_index = rand.nextInt(wiseGuy.getSize());
                String alrightWiseGuy = wiseGuy.getWisdom(random_index);

                // Sends the new number back to the client.
                serverPrintWriter.println(alrightWiseGuy);

                // Closing streams
                serverPrintWriter.close();
                serverReader.close();
                serverInputStream.close();

                // Closing sockets
                commSocket.close();
            } // End while loop.

            server.close();

        } catch(IOException ioe) {

            System.out.println("I/O error occurred...Don't panic.");
            System.exit(FAILURE);

        }  // end try-catch
    } // end go method.

    /**
     * Prints a usage message to stdout.
     */
    private void usage() {
        System.out.println("Usage is: java WisdomWordsServTCP [FILE]");
    } // end usage method

} // end WisdomWordsServTCP class
