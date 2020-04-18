import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class DictQueryThread extends Thread {
    private Socket socket;
    private MyDictionaryEntity dictionary;

    public DictQueryThread(Socket clientSocket, MyDictionaryEntity dictionary) {
        this.socket = clientSocket;
        this.dictionary = dictionary;
    }

    public void run() {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;
        try {
            is = new ObjectInputStream(socket.getInputStream());
            os = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            DictionaryQueryResponseEntity rsp = null;
            try {
                try {
                    DictionaryQueryRequestEntity req = (DictionaryQueryRequestEntity) is.readObject();
                    if(req != null && req.getOperation() != null) {
                        switch (req.getOperation()) {
                            case ADD:
                                rsp = dictionary.createEntry(req.getWord(), req.getMeanings());
                                break;
                            case DELETE:
                                rsp = dictionary.deleteEntry(req.getWord());
                                break;
                            case QUERY:
                                rsp = dictionary.getMeaning(req.getWord());
                                break;
                            case TERMINATE:
                                socket.close();
                                break;
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                if(rsp != null) {
                    os.writeObject(rsp);
                }

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}