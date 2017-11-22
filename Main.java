package main;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.conf.*;

public class Main {
	private static final String USERNAME = "Username: ";
	private static final String RETWEETS = "Retweets: ";
	private static final String TEXT = "Text: ";
	private static final String MENTIONS = "Mentions: ";
	private static final String HASHTAGS = "Hashtags: ";
	public static ConfigurationBuilder getConfigurationBuilder() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("RBxMfIfuRdSN21ITQlPtn9g5R");
		cb.setOAuthConsumerSecret("gYRWAak3GjNNaZ5RcpufJIxl1gnpLolLbvF0N8xuliUXlvzlCL");
		cb.setOAuthAccessToken("786133089512665088-lXbc32Vh2oKmvCEf2kcgwY9geo5lC2A");
		cb.setOAuthAccessTokenSecret("0Kpe1T3SuqP1Z1SAtbMDUXYW2TtHag2MtoWTJonBLHppQ");
		cb.setJSONStoreEnabled(true);
	//	cb.set
		return cb;
	}
	public static void createFormat(XSSFWorkbook workbook, XSSFSheet sheet, String key) throws IOException {

		Row row = sheet.createRow(0);

		int columnCount = 0;

		row.createCell(++columnCount).setCellValue("Username");
		row.createCell(++columnCount).setCellValue("Number of followers");
		row.createCell(++columnCount).setCellValue("Number of people following");

		row.createCell(++columnCount).setCellValue("Tweet text");

		row.createCell(++columnCount).setCellValue("Type");
		row.createCell(++columnCount).setCellValue("Date and time");

		row.createCell(++columnCount).setCellValue("Favourites");

		row.createCell(++columnCount).setCellValue("Retweets");

		row.createCell(++columnCount).setCellValue("Key");

		row.createCell(++columnCount).setCellValue("URL");

		row.createCell(++columnCount).setCellValue("Reply to status URL");


		try (FileOutputStream outputStream = new FileOutputStream("Twitter" + key + ".xlsx")) {
			workbook.write(outputStream);

		}

	}
	public static void main(String[] args) throws Exception {
		
		TwitterCriteria criteria = null;
		GitTweet t = null;
		ArrayList<String>keywords = new ArrayList<String>();
		
		Scanner scanner = new Scanner(new File("twitter.txt"));
		while(scanner.hasNext()){
			String key = scanner.next().substring(1);
			keywords.add(key);
		//	System.out.println(key);
		}
		scanner.close();
		for(String key: keywords){
			System.out.println(key);
		criteria = TwitterCriteria.create()
				.setQuerySearch(key)
				.setSince("2017-02-03");
		List<Long> tweetIDs = TweetManager.getTweetsIDs(criteria);
		System.out.println(tweetIDs.size());
		if(tweetIDs.size()>0){
			
			manipulateByIDs(tweetIDs, key);

		}
		}
		
	}
	public static void writeData(XSSFWorkbook workbook, XSSFSheet sheet, Tweet tweet, int rowIndex)
			throws IOException {

		Row row = sheet.createRow(rowIndex);

		int columnCount = 0;

		row.createCell(++columnCount).setCellValue(tweet.getUsername());
		
		row.createCell(++columnCount).setCellValue(tweet.getNumberOfFollowers());
		row.createCell(++columnCount).setCellValue(tweet.getTotalFollowing());
		
		
		row.createCell(++columnCount).setCellValue(tweet.getText());
	
		row.createCell(++columnCount).setCellValue(tweet.getType());
		
		row.createCell(++columnCount).setCellValue(tweet.getDateTime());
		
		row.createCell(++columnCount).setCellValue(tweet.getFavourites());
	
		row.createCell(++columnCount).setCellValue(tweet.getNumberOfRetweets());
		row.createCell(++columnCount).setCellValue(tweet.getSearchKey());
		
		row.createCell(++columnCount).setCellValue(tweet.getUrl());
		
		row.createCell(++columnCount).setCellValue(tweet.getReplyToURL());

		try (FileOutputStream outputStream = new FileOutputStream("Twitter" + tweet.getSearchKey() + ".xlsx")) {
			workbook.write(outputStream);

		}

	}
	public static void manipulateByIDs(List<Long> ids, String keyword) throws Exception {

		TwitterFactory tf = new TwitterFactory(getConfigurationBuilder().build());
		Twitter twitter = tf.getInstance();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(keyword);
		createFormat(workbook, sheet, keyword);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		int rowIndex = 1;
		for (Long id : ids) {
			
			
			

			Thread.sleep(700);
			
		
			

				
			 Status status = twitter.showStatus(id);
				
				User user = status.getUser();
				String username = user.getScreenName();

				int numberOfFollowers = user.getFollowersCount();
				int numberOfFollowing = user.getFriendsCount();
				String tweetText = status.getText();
				
				
				tweetText = tweetText.replaceAll("[\\t\\n\\r]+"," ");
				int favourites = status.getRetweetCount();
				String URL = "https://twitter.com/statuses/" + status.getId();
				String type = "tweet";
				UserMentionEntity[] entities = status.getUserMentionEntities();
				String replyToURL = "";
				if (entities.length > 0)
					type = "mention";
				if (status.getInReplyToStatusId() > 0)
				{
					type = "reply";
					replyToURL = "https://twitter.com/statuses/" + status.getInReplyToStatusId();					
				}
				Date date = status.getCreatedAt();
				
				String dateTime = df.format(date);
				System.out.println(dateTime);
				System.out.println(rowIndex);
				int numberOfRetweets = status.getRetweetCount();
				
				
			
				Tweet tweet = new Tweet(tweetText, username, numberOfFollowers, numberOfFollowing, type, dateTime,
						favourites, URL, replyToURL, keyword, numberOfRetweets);

				writeData(workbook, sheet, tweet, rowIndex++);
				
			
			
		
				}

	}
}