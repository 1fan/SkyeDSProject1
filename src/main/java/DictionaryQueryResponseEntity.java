import java.io.Serializable;

public class DictionaryQueryResponseEntity implements Serializable {

    private String meanings;
    private boolean successOrNot;

    public String getMeanings() {
    	
        return meanings;
    }

    public void setMeanings(String meanings) {
        this.meanings = meanings;
    }

    public boolean isSuccessOrNot() {
        return successOrNot;
    }

    public void setSuccessOrNot(boolean successOrNot) {
        this.successOrNot = successOrNot;
    }
    
    
    
}
