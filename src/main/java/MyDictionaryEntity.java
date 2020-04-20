import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.Path;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

//import java.util.stream.Collectors;

public class MyDictionaryEntity {

    /**
     * The key is the word itself;
     * The value is the meanings.
     */
    private boolean hasInitialised = false;
    
    //private HashMap<String, List<String>> map;
    
    //private Map<String, String> map = new ConcurrentHashMap<>();
    
    private Map<String,List<String>> map = new ConcurrentHashMap<String,List<String>>();
    List<String> putlist=new ArrayList<>();
    

    /**
     * Feed the Map by reading and parsing the file.
     * @param filepath path to find the file.
     * @throws IOException if failed to load and parse the file.
     */
    public void feedMapFromFile(String filepath) throws IOException {
    	
    	filepath = "src/dictionary_system/DictionaryFeedFile";
    	
        //Path path = FileSystems.getDefault().getPath(filepath);
        
        // Copy and paste from https://stackoverflow.com/questions/29061782/java-read-txt-file-to-hashmap-split-by ,not tested
        //Map<String, String> mapFromFile = Files.lines(path)
        		
        		
        String line;	
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        while ((line = reader.readLine()) != null)
        {
        	String[] parts = line.split(":", 2);
        	//String[] subParts = parts[1];
        	
        	if (parts.length >= 2)
        	{
        		String key = parts[0];
        		
        		String[] val = parts[1].split(";");
        		
        		int list_len = val.length;
        		
        		for(int i=0;i<list_len;i++){
        			putlist.add(val[i]);
       			
        		}
        		
        		map.put(key,putlist);
        			
        		for (Entry<String, List<String>> in: map.entrySet()){
                    System.out.println("key===  "+in.getKey()+"     values=  "+in.getValue());
                }
        	    
        	    //map.put(key, value);
        	 } else {
        		System.out.println("ignoring line: " + line);
        	        }
        }
        
        for (String key : map.keySet())
        {
            System.out.println(key + ":" + map.get(key));
        }
        reader.close();
        		
                //.filter(s -> s.matches("^\\w+:\\w+"))
                //.collect(Collectors.toMap(k -> k.split(":")[0], v -> v.split(":")[1]));

        hasInitialised = true;
        
        
    }

    /**
     * Add an entry to the map. TODO: Add validation according to the assignment spec.
     * @param key the "word"
     * @param value the "meaning"
     */
    public DictionaryQueryResponseEntity createEntry(String key, List<String> values) {
    	
        DictionaryQueryResponseEntity rsp = new DictionaryQueryResponseEntity();
        
        if(hasInitialised && key != null) {
        	
        	Iterator<String> keys = map.keySet().iterator();
        	
    		while(keys.hasNext()){
    			String target_key = (String)keys.next();
    			if(key.equals(target_key)){
    				System.out.println("This word has existed in the dictionary");
    			}else {
    				
    				map.put(key, values);
    	            
    	            rsp.setSuccessOrNot(true);
    				}
    			}
    
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
    	
    	DictionaryQueryResponseEntity rsp = new DictionaryQueryResponseEntity();
    	
        if(hasInitialised && key != null) {
        	
        	Iterator<String> find_keys = map.keySet().iterator();
        	
        	while(find_keys.hasNext()){
        		
    			String remove_key = (String)find_keys.next();
    			
    			if(key.equals(remove_key)){
    				
    				map.remove(key); 
    				rsp.setSuccessOrNot(true);
    				System.out.println("This word has removed in the dictionary");
    				
    			}else {
    				rsp.setSuccessOrNot(false);
    				System.out.println("This word is not existed in the dictionary");
    				}
    		}

        }
        else {
        	rsp.setSuccessOrNot(false);
        	System.out.println("Sorry, this word can not be removed!");
        	
        }
        //TODO: generate the response and return it
        return rsp;
    }

    /**
     * Get the meaning of the word. Note: the caller need to check null.
     * TODO: Add validation according to the assignment spec. Form the DictionaryQueryResponseEntity.
     * @param key the word
     * @return the meaning of the given word.
     */
    public DictionaryQueryResponseEntity getMeanings(String key) {
    	
    	DictionaryQueryResponseEntity rsp = new DictionaryQueryResponseEntity();
    	
    	List<String> meanings =new ArrayList<>();
        //String meaning = null;
        if(hasInitialised && key != null) {
        	
        	if(map.containsKey(key)){
        		
        		//if word is existed in map
        		meanings = map.get(key);
        		rsp.setSuccessOrNot(true);
        		
        	}else{
        		rsp.setSuccessOrNot(false);
        		System.out.println("This word is not existed in this dictionary");
        		
        	}
            
            
            
        }
        else {
        	rsp.setSuccessOrNot(false);
        	System.out.println("This word is not existed in this dictionary");
        	System.out.println("Intial dictionary may not be downloaded");
        }
        

        //TODO: generate the response and return it
        return rsp;
    }
}