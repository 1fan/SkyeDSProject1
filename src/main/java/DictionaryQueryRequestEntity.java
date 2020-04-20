
import java.io.Serializable;
import java.util.List;

public class DictionaryQueryRequestEntity implements Serializable {

    private String word;
    private List<String> set_meanings;
    private String meanings;
    private OPERATION operation;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeanings() {
    	
    	
    			
        return meanings;
    }

    public List<String> setMeanings(List<String> set_meanings) {
        
    	
    	return set_meanings;
    }

    public OPERATION getOperation() {
        return operation;
    }

    public void setOperation(OPERATION operation) {
        this.operation = operation;
    }

    protected static enum  OPERATION {
        ADD,
        DELETE,
        QUERY,
        TERMINATE;
    }
}