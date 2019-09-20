import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * The TCP Client portion of the Wisdom Words project. The client contacts
 * the server, the server responds with a word of wisdom, and the client
 * prints this word of wisdom to stdout.
 *
 * @author Evert Ball
 * @author Garrett Starkey
 *
 * @version 09/20/2019
 */
public class WisdomWordsCLTCP {

    /** A constant for the number of expected command line arguments */
    private static final int EXPECTED_ARGS = 2;
    
    /** A constant to represent a failed exit */
    private static final int FAILURE = 1;

    /** A constant to represent a successful exit */
    private static final int SUCCESS = 0;

    /** The first command line argument */
    private static final int FIRST_ARG = 0;
    
    /** The second command line argument */
    private static final int SECOND_ARG = 1;

    /** A constant string to print out when we encounter an IOException. */
    private static final String IO_ERROR = "I/O error...Something went wrong.";

    private static final String PORT_ERROR = "Port must be a numerical value." +
            " Try again.";

    /**
     * The main entry point into the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        if(args.length != EXPECTED_ARGS) {
            System.out.println("Usage is: java WisdomWordsCLTCP [HOST] [PORT]");
        }

        //Initializing variables
        InetAddress host;
        int port;

        try{
            host = InetAddress.getByName(args[FIRST_ARG]);
            port = Integer.parseInt(args[SECOND_ARG]);

            // Printing confirmation of host and port connection.
            System.out.println("Connecting to " + host +
                    " on port " + port + "...");

            // Creating client socket
            Socket client = new Socket(host, port);

            // Creating streams for the client to communicate with the server.
            InputStreamReader clientInputStream =
                new InputStreamReader(client.getInputStream());
            BufferedReader clientReader = 
                new BufferedReader(clientInputStream);
            PrintWriter clientPrintWriter =
                new PrintWriter(client.getOutputStream(), true);


            // Contact the server for a saying.
            clientPrintWriter.println("");


            // Receive wisdom word and print it to the screen.
            String response = clientReader.readLine();
    
            System.out.println("\n" + response);

            // Closing streams.
            clientInputStream.close();
            clientReader.close();
            clientPrintWriter.close();

            //Closing socket
            client.close();

        } catch(IOException ioe) {
            System.out.println(IO_ERROR);
            System.exit(FAILURE);
        } catch(NumberFormatException nfe) {
            System.out.println(PORT_ERROR);
            System.exit(FAILURE);
        } catch(SecurityException se) {
            System.out.println("Security Error: " + se.getMessage());
            System.exit(FAILURE);
        }

        System.exit(SUCCESS);
        

    } // end main method.

} // end WisdomWordsCLTCP class
