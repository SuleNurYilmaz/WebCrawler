package crawling;



public class Test {
public static void main (String[] arg) {
		
		Crawler crawler = new Crawler();
		CrawlLinks crawler_2 = new CrawlLinks();
		crawler.GetInfoText("https://www.hurriyetemlak.com/");	//Sayfadaki i�erikler(text)
		crawler.GetInfoLinks("https://www.hurriyetemlak.com/");	//Sayfadaki linkler
		crawler_2.Crawl("https://www.hurriyetemlak.com/");	//verilen sayfadan ba�layarak t�m sayfadaki linkleri tarama
	}

}
