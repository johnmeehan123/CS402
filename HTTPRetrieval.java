import java.util.regex.*;

import java.io.*;
import java.net.*;

public class HTTPRetrieval {

	//initialize a counter
	static int count = 0;
	
	//This method uses a GET request to retrieve the pay-load of a HTML page
	public static String getHTML(String urlToRead) throws Exception {
      
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
      
		//This reads the HTML pay-load line by line and appends it to a string, while there is still content to read
		while ((line = rd.readLine()) != null) {
        		
				result.append(line);
		}
		
		
		rd.close();
		//return the result as a String
		return result.toString();
   }
   
   //This method takes in a String of text and matches occurances of a specific string
   public static String printMatches(String text, String regex) {
	    
	   //take in regular expression (in our case our string)
	   Pattern pattern = Pattern.compile(regex);
	   //take in the text
	   Matcher matcher = pattern.matcher(text);
	   String result= new String();
	   
	   //while we find a match
	   while (matcher.find()) {
	     
		   //add the index of where it ends to a list and increment a counter(this is used later so we know how big our array and for loop will have to be
		   result+=matcher.end() + ",";
	       count++;
	        
	   }
	   //return the string of indices
	   return result;
	
   }

   public static void main(String[] args) throws Exception{
	  
       //This is the webpage we want to retrieve, call the getHTML method on it
	   String orignalHTML = getHTML("http://www.cs.nuim.ie/currentstudents");
	   //This is where we find matches, find matches of "href=http" throughout our webpage
	   String a= printMatches(orignalHTML, "href=\"http");
	   //create a string array of size count
	   String[] lm = new String[count];
	   for(int i=0;i<count;i++){
     
		   String[] temp;
		   //Put occurances of a into a string array separetly
		   temp=a.split(",");
		   //index as a string
		   String stuff= temp[i];
		   //parse this to an int
		   int q= Integer.parseInt(stuff);
		   //new string temp1 = substrings between q(each integer where a "href" ends) and the length of the HTML file
		   String temp1=orignalHTML.substring(q,orignalHTML.length());
		   //for each index i, create a substring between i and the next occurance of ", this is how we know we have reached the end of the link
		   lm[i]= temp1.substring(0,temp1.indexOf("\""));
     
	   }
	   
	   String s= "";
	   for(int i=0;i<count;i++)  
	   {
		   //create a list of links with http at the start, escape each so we have them on a nice ordered manner
		   s+="http"+lm[i]+"\r\n";
	   } 
     
	   //Print your links to Link.txt
	   try (PrintWriter out = new PrintWriter ("Links.txt")){
		   out.println(s);
	   }
	   //Print your entire HTML Payload to HTMLPatLoad.txt
	   try (PrintWriter out = new PrintWriter ("HTMLPayload.txt")){
		   out.println(orignalHTML);
	   }
   }
}
