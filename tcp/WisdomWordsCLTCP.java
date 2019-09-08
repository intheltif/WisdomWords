import org.omg.PortableInterceptor.SUCCESSFUL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class WisdomWordsCLTCP {

    /** A constant for the number of expected command line arguemnts */
    private static final int EXPECTED_ARGS = 2;
    
    /** A constant to represent a failed exit */
    private static final int FAILURE = 1;

    /** A constant to represent a successful exit */
    private static final int SUCCESS = 1;

    /** The first command line argument */
    private static final int FIRST_ARG = 0;
    
    /** The second command line argument */
    private static final int SECOND_ARG = 1;
    
    private static final String PORT_ERR = "Port must be a numerical value. Try again.";

    public static void main(String[] args) {
        InetAddress host;
        int port;

        try{
            host = InetAddress.getByName(args[FIRST_ARG]);
            port = Integer.parseInt(args[SECOND_ARG]);

            // Printing confirmation of host and port connection.
            System.out.println("Connecting to " + host +
                    " on port " + Integer.toString(port) + "...");

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
            System.out.println(response);

            // Closing streams.
            clientInputStream.close();
            clientReader.close();
            clientPrintWriter.close();

            //Closing socket
            client.close();

            //Ending message.
            System.out.println("\nThank yeens for getting your daily dose of Mountain Wisdom. Y'all come back now, ya hear?");


        } catch(IOException ioe) {
            System.out.println("I/O error...Something went wrong.");
            System.exit(FAILURE);
        } catch(NumberFormatException nfe) {
            System.out.println("Port must be a numerical value. Try again.");
            System.exit(FAILURE);
        } catch(SecurityException se) {
            System.out.println("Security Error: " + se.getMessage());
            System.exit(FAILURE);
        }

        System.exit(SUCCESS);
        

    } // end main method.

} // end WisdomWordsCLTCP class
