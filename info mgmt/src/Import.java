import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Import {
	
		public static void main(String args[]) throws IOException {
		
			List<String> Qrys = readQueryFile("C:\\Users\\Chewie\\Documents\\med\\MED.QRY");
			List<String> Docs = readQueryFile("C:\\Users\\Chewie\\Documents\\med\\MED.ALL");
			String[] relJudge = readRelevanceJudgements("C:\\Users\\Chewie\\Documents\\med\\MED.REL");
			
			StopWords s = new StopWords();
			
			long time = System.currentTimeMillis();
			
			for(int i = 0; i< Qrys.size(); i++){
				Qrys.set(i, s.removeStopWords(Qrys.get(i)));
				System.out.println("qry : "  + i);
			}
			for(int i = 0; i< Docs.size(); i++){
				Docs.set(i, s.removeStopWords(Docs.get(i)));
				System.out.println("docs : "  + i);
			}
			
			System.out.println("Time Taken = " + (System.currentTimeMillis() - time));
			
			System.out.println(Docs.get(2));
			
//			Weighting w = new Weighting();
//			w.DocCount(Qrys);
//			w.DocTermCount("the", Qrys);

//			for (int i=0;i<Qrys.length;i++) if (!(Qrys[i] == null)) System.out.println("Query " + i + ": " + Qrys[i]);
//			for (int i=0;i<Docs.length;i++) if (!(Docs[i] == null)) System.out.println("Documents " + i + ": " + Docs[i]);
//			for (int i=0;i<relJudge.length;i++) if (!(relJudge[i] == null)) System.out.println("Relevance Judgement " + i + ": " + relJudge[i]);
		}
	
		public static String [] readRelevanceJudgements(String filePath) {
			
			String [] relevanceJudgementFiles = new String[700];
			String line = null;
			int i=0;
	    	   
			try {
				FileReader fileReader = new FileReader(filePath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				while((line = bufferedReader.readLine()) != null) {
					relevanceJudgementFiles[i]=line;
	        	   	i++;
				}   
				bufferedReader.close();         
			}
			catch(FileNotFoundException ex) {
				System.out.println("Can't open the file: '" + filePath + "'");                
			}
			catch(IOException ex) {
	           System.out.println("Error reading the file '" + filePath + "'");                  	 
			} 
			return relevanceJudgementFiles;
		}
	
		public static ArrayList<String> readQueryFile(String filePath) throws IOException {
	  
			ArrayList<String> importedQueries = new ArrayList<String>();
			//!FIX directory on new machine
			File queryfile = new File(filePath);
		   
			@SuppressWarnings("resource")
			final BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(queryfile), "UTF8"));

			String str;
			int queryCount = 0;
			boolean skipLine = false;
	       
			while ((str = in.readLine()) != null) {    	   
				if (str.contains(".I ")) { //new query - skip next line and initialise appropriate string array index.
					skipLine = true;
					importedQueries.add( "");
					queryCount++;
				}	      
				else {   
					if (!skipLine) {
						System.out.println("adding : " + queryCount);
						importedQueries.set(queryCount-1, importedQueries.get(queryCount-1) + str);  
					}	    	   
					else {
						skipLine = false; 
					}	       		
				}
			}      
			return importedQueries;	
		}
}