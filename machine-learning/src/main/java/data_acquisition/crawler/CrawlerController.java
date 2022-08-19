package data_acquisition.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlerController {
	
	public static void main(String[] args) throws Exception {
		 int numberOfCrawlers = 2; //This controls the number of threads used for the crawl
	    CrawlConfig config = new CrawlConfig(); 
	    String crawlStorageFolder = "data"; //The location where crawl data is stored
	    
	    config.setCrawlStorageFolder(crawlStorageFolder);
	    config.setPolitenessDelay(500); //How many seconds to pause between requests
	    config.setMaxDepthOfCrawling(2); //How deep the crawl will go
	    config.setMaxPagesToFetch(20); //How many pages to fetch
	    config.setIncludeBinaryContentInCrawling(false); //How many pages to fetch
	    
	    /*
	     * RobotstxtConfig and RobotstxtServer classes used to handle robot.txt files.
		  * These files contain instructions that are intended to be read by a web crawler. 
		  * They provide direction to help a crawler to do a better job such as specifying which parts
		  * of a site should not be crawled
	     */
	    PageFetcher pageFetcher = new PageFetcher(config);
	    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
	    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
	    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

	    controller.addSeed("https://en.wikipedia.org/wiki/Bishop_Rock,_Isles_of_Scilly");

	    controller.start(SampleCrawler.class, numberOfCrawlers);
	}
}
