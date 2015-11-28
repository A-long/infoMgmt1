import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StopWords {

	ArrayList<String> stopwords =  new ArrayList<String>();
	String file;

	public StopWords(){
		file = "C:\\Users\\Chewie\\Dropbox\\College\\Info management\\stopwords.txt";
	}
	
	public StopWords(String filePath){
		file = filePath;
	}
	
	public String removeStopWords(String doc){
		
		this.readStopWordsFile(file);
		
		List<String> str = new LinkedList<String>(Arrays.asList(doc.split("[., ]")));
		String removed ="";
		

		for (int i = 0; i<stopwords.size(); i++){
			
			if (str.contains(stopwords.get(i))){		
				str.remove(stopwords.get(i));
			}		
		}
		
		for (int i =0;i<str.size();i++){
			removed = removed + str.get(i) + " ";
		}
		
		return removed;
	}
	
	public void readStopWordsFile(String file){
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			int i = 0;
			while ((line = reader.readLine()) != null) {				
				stopwords.add(line);
				i++;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
