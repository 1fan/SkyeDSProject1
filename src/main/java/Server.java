import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) {

        //Retrieve commandline arguments
        if(args.length != 2) {
            System.out.println("ERROR: YOU NEED TO POPULATE 2 COMMAND LINE ARGUMENTS: port AND dictionary-file");
            System.out.println("ERROR: Server Start Failure!");
            return;
        }

        int port = Integer.parseInt(args[0]);
        String filePath = args[1];

        //TODO: Add validation here - what if the port or filepath is invalid?
        
        File file=new File(filePath);
        
        if(file.isFile() &file.exists()) {
        	
        	MyDictionaryEntity dictionary = new MyDictionaryEntity();
            try {
                dictionary.feedMapFromFile(filePath);
            }catch (IOException e) {
                System.out.println("ERROR: Server failed to load dictionary from the given file.");
                System.out.println("ERROR: Server Start Failure!");
                e.printStackTrace();
                return;
                
            }
                
            ServerSocket serverSocket = null;
            Socket socket = null;

                try {
                    serverSocket = new ServerSocket(port);//create socket
                    
                    
                } catch (IOException error) {
                    error.printStackTrace();

                }
                while (true) {
                    try {
                        socket = serverSocket.accept();//listen the clients
                    } catch (IOException er) {
                        System.out.println("I/O error: " + er);
                    }
                    // new thread for a client
                    new DictQueryThread(socket, dictionary).start();
                }   
        	
            
        }else 
        {
        	
        	 System.out.println("ERROR: Dictionary failed to be downloaded from server.");
        	 System.out.println("Please check your dictionary's name");
        	 
             
        }


        
    }
}