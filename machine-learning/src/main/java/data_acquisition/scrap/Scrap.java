package data_acquisition.scrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrap {
	
	
	public static void main(String[] args) {
		String html = "<html>\n"
				+ "<head><title>Example Document</title></head>\n"
				+ "<body>\n"
				+ "<p>The body of the document</p>\n"
				+ "Interesting Links:\n"
				+ "<br>\n"
				+ "<a href=\"https://en.wikipedia.org/wiki/Data_science\">" 
				+ "DataScience</a>\n"
				+ "<br>\n"
				+ "<a href=\"https://en.wikipedia.org/wiki/Jsoup\">" 
				+ "Jsoup</a>\n"
				+ "<br>\n"
				+ "Images:\n"
				+ "<br>\n"
				+ " <img src=\"eyechart.jpg\" alt=\"Eye Chart\"> \n"
				+ "</body>\n"
				+ "</html>";
		Document document = Jsoup.parse(html);
		String title = document.title();
		
		System.out.println("Title: " + title);
		Elements element = document.select("body");
		System.out.println("\nText: " + element.text());
		
		/* Recuperar imagens
		Elements images = document.select("img[src$=.png]");
		for (Element image : images) {
			System.out.println("\nImage: " + image);
		}
		*/
		
		/* Recuperar links
		Elements links = document.select("a[href]");
		for (Element link : links) {
			System.out.println("Link: " + link.attr("href") + " Text: " + link.text());
		}
		*/
	}
}
