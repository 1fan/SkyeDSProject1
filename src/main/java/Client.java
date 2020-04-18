public class Client {

    public static void main(String[] args) {
        //Retrieve commandline arguments
        if(args.length != 2) {
            System.out.println("ERROR: YOU NEED TO POPULATE 2 COMMAND LINE ARGUMENTS: server-address AND server-port");
            System.out.println("ERROR: client Start Failure!");
            return;
        }
        String serverAddress = args[0];
        String serverPort = args[1];

        /**
         * TODO: copy and paste from somewhere on the Google.
         * Send the {@link DictionaryQueryRequestEntity} and receive the {@link DictionaryQueryResponseEntity}
         */

    }

}
