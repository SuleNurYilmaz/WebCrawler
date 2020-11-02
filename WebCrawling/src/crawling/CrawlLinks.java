package crawling;

import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class CrawlLinks {
	
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private static final int MAX_PAGES_TO_SEARCH = 10;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();
    private Set<String> AllLinks = new HashSet<String>();

	
    private void CrawlHelp(String Url) {
    	 try
         {
             Connection connection = Jsoup.connect(Url).userAgent(USER_AGENT);
             Document htmlDocument = connection.get();
             Elements linksOnPage = htmlDocument.select("a[href]");
             System.out.println("\nLinklerin arandýðý Url: "+ Url);
             System.out.println("\nFound (" + linksOnPage.size() + ") links");
             for(Element link : linksOnPage)
             {
                 this.pagesToVisit.add(link.absUrl("href"));
             }
         }catch(IOException ioe){
        	 
         }
    }
    
    public void Crawl(String Url) {
    	System.out.printf("Toplam %d kadar Url'de Url Taramasý yapýlacaktýr. ",MAX_PAGES_TO_SEARCH);
    	while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
        {
    		String currentUrl;
    		if(this.pagesToVisit.isEmpty())
            {
                currentUrl = Url;
                this.pagesVisited.add(Url);
            }
    		else
            {
                currentUrl = this.nextUrl();
            }
    		CrawlHelp(currentUrl);	
        }
    	System.out.println("Gezilen Url'ler: \n");
    	for(var x: pagesVisited) {
    		
    		System.out.println(x);
    	}
    	System.out.println("Toplanan Url Sayýsý (tekrarlý): " +pagesToVisit.size());
    	AllLinks.addAll(pagesToVisit);
    	System.out.println("Toplanan Url Sayýsý (tekrarsýz): "+AllLinks.size());
    }
    	
    	
    private String nextUrl() {	
    	String nextUrl;
	    do{
	    nextUrl = this.pagesToVisit.remove(0);
	    } while(this.pagesVisited.contains(nextUrl));
	    this.pagesVisited.add(nextUrl);
	    return nextUrl;
    }

}
