
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

    private Scanner input;
    
    /**
     * The main entry point into the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        //TODO finish main.
        this.input = new Scanner(System.in);

        WisdomWordsServUDP udp = new WisdomWordsServUDP();
        udp.go(args);

    } // end main method.

    public void go(String[] args) {
        
        // Print usage message if incorrect number of CL args supplied.
        if(args.length != 1) {
            usage();
        }

    } // end go method.

    public void usage() {
        System.out.println("Usage is: ");
    } // end usage method

} // end WisdomWordsServUDP class
