import java.io.Serializable;

public class DictionaryQueryRequestEntity implements Serializable {

    private String word;
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

    public void setMeanings(String meanings) {
        this.meanings = meanings;
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
