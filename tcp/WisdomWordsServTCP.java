public class WisdomWordsServTCP {

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

        String file = args[1];

    } // end go method.

    private void usage() {
        System.out.println("Usage is: java WisdomWordsServTCP [FILE_TO_MERGE]");
    } // end usage method

} // end WisdomWordsServTCP class
