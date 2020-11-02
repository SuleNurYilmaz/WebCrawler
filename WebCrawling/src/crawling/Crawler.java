package crawling;

import java.io.IOException;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class Crawler {
		
		private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

	    
	    public void GetInfoLinks(String Url) {
	    	
	    	try {
	        Connection connection = Jsoup.connect(Url).userAgent(USER_AGENT);
	        Document htmlDocument = connection.get();
	        //this.htmlDocument = htmlDocument;
	        Elements linksOnPage = htmlDocument.select("a[href][title]");
	        System.out.println("Found (" + linksOnPage.size() + ") links");
	        System.out.println("Sayfadaki Tüm Linkler: \n");
	        for(Element link : linksOnPage) {
	        	System.out.println(link.attr("title"));
	        	System.out.println(link.absUrl("href"));
	        }
	    	}catch(IOException ioe){
	    		System.out.println("Hata:"+ ioe);
	    	}
	    }
	    
	    public void GetInfoText(String Url) {
	    	try {
	    		System.out.println("\nSayfa Ýçeriði\n");
		        Connection connection = Jsoup.connect(Url).userAgent(USER_AGENT);
		        Document htmlDocument = connection.get();
		        System.out.println(htmlDocument.text()+ "\n");
	    	}catch(IOException ioe){
    		System.out.println("Hata:"+ ioe);
	    	}
    	}
     
	    
	}
