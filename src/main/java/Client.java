

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
//import java.net.SocketTimeoutException;
import java.util.Random;

public class Client {
	static private Random r = new Random();
    public static void main(String[] args) {
        //Retrieve commandline arguments
        if(args.length != 2) {
            System.out.println("ERROR: YOU NEED TO POPULATE 2 COMMAND LINE ARGUMENTS: server-address AND server-port");
            System.out.println("ERROR: client Start Failure!");
            return;
        }
        String serverAddress = args[0];
        String serverPort = args[1];
        int server_port = Integer.parseInt(serverPort);
        
        /**
         * TODO: copy and paste from somewhere on the Google.
         * Send the {@link DictionaryQueryRequestEntity} and receive the {@link DictionaryQueryResponseEntity}
         */
        try {
            
        	//Create a client socket
            Socket socket = new Socket(serverAddress, server_port);
            
            //get keywords to send to server
            OutputStream os = socket.getOutputStream();
            //transfer to PrintWriter
            
            PrintWriter pw = new PrintWriter(os);

            //refresh and send request to server
            pw.flush();
            //stop the output
            socket.shutdownOutput();
            
            //get the response from server
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("Response from server ：" + info);
            }
            
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException ex) {
            //Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }


        /**
         * TODO: copy and paste from somewhere on the Google.
         * Send the {@link DictionaryQueryRequestEntity} and receive the {@link DictionaryQueryResponseEntity}
         */

    }

}
