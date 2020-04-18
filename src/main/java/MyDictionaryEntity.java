import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MyDictionaryEntity {

    /**
     * The key is the word itself;
     * The value is the meanings.
     */
    private boolean hasInitialised = false;
    private Map<String, String> map = new ConcurrentHashMap<>();

    /**
     * Feed the Map by reading and parsing the file.
     * @param filepath path to find the file.
     * @throws IOException if failed to load and parse the file.
     */
    public void feedMapFromFile(String filepath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filepath);
        Map<String, String> mapFromFile = Files.lines(path)
                .filter(s -> s.matches("^\\w+:\\w+"))
                .collect(Collectors.toMap(k -> k.split(":")[0], v -> v.split(":")[1]));

        hasInitialised = true;
    }

    /**
     * Add an entry to the map. TODO: Add validation according to the assignment spec.
     * @param key the "word"
     * @param value the "meaning"
     */
    public DictionaryQueryResponseEntity createEntry(String key, String value) {
        DictionaryQueryResponseEntity rsp = new DictionaryQueryResponseEntity();
        if(hasInitialised && key != null) {
            map.put(key, value);
            rsp.setSuccessOrNot(true);
        } else {
            rsp.setSuccessOrNot(false);
        }
        return rsp;
    }

    /**
     * Add an entry to the map.
     * TODO: Add validation according to the assignment spec. Form the DictionaryQueryResponseEntity.
     * @param key the word to delete
     */
    public DictionaryQueryResponseEntity deleteEntry(String key) {
        if(hasInitialised && key != null) {
            map.remove(key);
        }

        //TODO: generate the response and return it
        return null;
    }

    /**
     * Get the meaning of the word. Note: the caller need to check null.
     * TODO: Add validation according to the assignment spec. Form the DictionaryQueryResponseEntity.
     * @param key the word
     * @return the meaning of the given word.
     */
    public DictionaryQueryResponseEntity getMeaning(String key) {
        String meaning = null;
        if(hasInitialised && key != null) {
            meaning = map.get(key);
        }

        //TODO: generate the response and return it
        return null;
    }
}
