import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weighting {
        
       	private int DocNum;			// Number of Documents
        private int TermDocs;		// Number of Documents that contain a Term t
        private int DocTermNum;		// Number of times Term t occurs in Document d
        
        public void Weight () {
        	
        	TFIDFWeight (IDFWeight(), TFWeight());
        }
        
        public float IDFWeight () {
			
        	float IDF;
        	IDF = (float) (Math.log(DocNum/TermDocs)/Math.log(10));
        	return IDF;
        }

        public float TFWeight () {
        	
        	float TF;
        	if (DocTermNum>0) {       	
        		TF = (float) (1 + (Math.log(DocTermNum)/Math.log(10)));
        	}
        	else {
        		TF = 0;
        	}
        	return TF;
        }
        
        public float TFIDFWeight (float IDFWeight, float TFWeight) {
			
        	float TFIDF;
        	TFIDF = (IDFWeight * TFWeight);
        	return TFIDF;
        }
        
        public int DocCount (String[] Document) {
        	
        	int dcount;
        	System.out.println(Document.length);    	
        	return DocNum;
        	
        }
        
        public int DocTermCount(String phrase, String[] Document) {
            int count = 0;
            
            
            for (int i=0; i<30; i++) {
            	System.out.println(Document[i]);
                if (Document[i].contains(phrase)){
                    count++;
                }
            }
        	System.out.println(phrase + " occurs " + count + " times."); 
            return count;
        }
public void TermDocCount (String phrase, String qrys) {
        	
        	int i = 0;
        	Pattern p = Pattern.compile(phrase);
        	Matcher m = p.matcher( qrys );
        	while (m.find()) {
        	    i++;
        	}
        	System.out.println(i);
        	this.DocTermNum = i;
        }
}