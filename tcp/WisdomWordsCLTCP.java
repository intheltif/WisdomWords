public class WisdomWordsCLTCP {

    /** A constant for the number of expected command line arguemnts */
    private static final int EXPECTED_ARGS = 2;
    
    /** A constant to represent a failed exit */
    private static final int FAILURE = 1;

    /** The first command line argument */
    private static final int FIRST_ARG = 0;
    
    /** The second command line argument */
    private static final int SECOND_ARG = 1;
    
    private static final String PORT_ERR = "Port must be a numerical value. Try again.";

    public static void main(String[] args) {
        // Do stuff

        String host = args[FIRST_ARG];
        try {
            int port = Integer.parseInt(args[SECOND_ARG]);
        } catch(NumberFormatException nfe) {
            System.out.println(PORT_ERR);
            System.exit(FAILURE);
        }

        // Printing confirmation of host and port connection.
        System.out.println("Connecting to " + host + 
                           " on port " + port.toString() + "...");

    } // end main method.

} // end WisdomWordsCLTCP class
