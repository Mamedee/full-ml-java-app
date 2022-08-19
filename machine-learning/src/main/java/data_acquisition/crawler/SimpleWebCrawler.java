package data_acquisition.crawler;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SimpleWebCrawler {
	private final String topic;
   private final String startingURL;
   private final String urlLimiter;
   private final int pageLimit = 20; //The maximum number of pages to retrieve
   private final ArrayList<String> visitedList = new ArrayList<>(); //The ArrayList containing pages that have already been visited
   private final ArrayList<String> pageList = new ArrayList<>(); //An ArrayList containing the URLs of the pages of interest

   public SimpleWebCrawler() {
       startingURL = "https://en.wikipedia.org/wiki/Bishop_Rock,_Isles_of_Scilly"; // The URL of the first page
       urlLimiter = "Bishop_Rock"; //A string that must be contained in a link before it will be followed
       topic = "shipping route"; //The keyword that needs to be in a page for the page to be accepted
       visitPage(startingURL);
   }

   public void visitPage(String url) {
       if (pageList.size() >= pageLimit) {
           return;
       }
       if (visitedList.contains(url)) {
      	 System.out.println("[ALREADY VISITED] " + url);
       } else {
           visitedList.add(url);
           try {
               Document doc = Jsoup.connect(url).get();
               if (doc.text().contains(topic)) {
                   System.out.println((pageList.size() + 1) + ": [" + url + "]");
                   //System.out.println("\t[TEXT] " + doc.text()); 
                   pageList.add(url);

                   // Process page links
                   Elements questions = doc.select("a[href]");
                   for (Element link : questions) {
                       if (link.attr("href").contains(urlLimiter)) {
                           visitPage(link.attr("abs:href"));
                       }
                   }
               }
           } catch (Exception ex) {
               ex.printStackTrace();
           }
       }
   }

   public static void main(String[] args) {
       new SimpleWebCrawler();
   }
}
