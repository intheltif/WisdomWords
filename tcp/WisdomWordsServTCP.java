import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class WisdomWordsServTCP {

    /** The port we are connecting to. */
    private static final int PORT = 5500;
    
    /** A constant representing a failed exit */
    private static final int FAILURE = 1;

    /** A constant representing a failed exit */
    private static final int SUCCESS= 0;

    public static void main(String[] args) {

        //TODO finish main method

        WisdomWordsServTCP tcp = new WisdomWordsServTCP();
        tcp.go(args);

    } // end main method
    
    /**
     * The method that runs our program.
     *
     * @param args The command line arguments.
     */
    public void go(String[] args) {

        if(args.length != 1) {
            usage();
        }

        // The filename that holds the wise sayings.
        String file = args[0];

        // Gets a random saying to send to the client.
        WisdomWords wiseGuy = new WisdomWords(file);
        Random rand = new Random();
        int random_index = rand.nextInt(wiseGuy.getSize());
        String alrightWiseGuy = wiseGuy.getWisdom(random_index);
        System.out.println("The wise words are: " + alrightWiseGuy);

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

    private void usage() {
        System.out.println("Usage is: java WisdomWordsServTCP [FILE]");
    } // end usage method

} // end WisdomWordsServTCP class
