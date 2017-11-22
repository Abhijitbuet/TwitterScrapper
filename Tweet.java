package main;

public class Tweet {
private String text;
private String username;
private int numberOfFollowers;
private int totalFollowing;
private String type;
private String dateTime;
private int favourites;
private String url;
private String replyToURL;
private String searchKey;
private int numberOfRetweets;
public Tweet(String text, String username, int numberOfFollowers, int totalFollowing, String type, String dateTime,
		int favourites, String url,String replyToURL, String key, int numberOfRetweets) {
	super();
	this.text = text;
	this.username = username;
	this.numberOfFollowers = numberOfFollowers;
	this.totalFollowing = totalFollowing;
	this.type = type;
	this.dateTime = dateTime;
	this.favourites = favourites;
	this.url = url;
	this.replyToURL = replyToURL;
	this.searchKey=key;
	this.numberOfRetweets = numberOfRetweets;
}

public int getNumberOfRetweets() {
	return numberOfRetweets;
}

public void setNumberOfRetweets(int numberOfRetweets) {
	this.numberOfRetweets = numberOfRetweets;
}

public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getNumberOfFollowers() {
	return numberOfFollowers;
}
public void setNumberOfFollowers(int numberOfFollowers) {
	this.numberOfFollowers = numberOfFollowers;
}
public int getTotalFollowing() {
	return totalFollowing;
}
public void setTotalFollowing(int totalFollowing) {
	this.totalFollowing = totalFollowing;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getDateTime() {
	return dateTime;
}
public void setDateTime(String dateTime) {
	this.dateTime = dateTime;
}
public int getFavourites() {
	return favourites;
}
public void setFavourites(int favourites) {
	this.favourites = favourites;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}

public String getSearchKey() {
	return searchKey;
}
public void setSearchKey(String searchKey) {
	this.searchKey = searchKey;
}

public String getReplyToURL() {
	return replyToURL;
}

public void setReplyToURL(String replyToURL) {
	this.replyToURL = replyToURL;
}

@Override
public String toString() {
	return  text + "," + username + "," + numberOfFollowers
			+ "," + totalFollowing + "," + type + "," + dateTime + ","
			+ favourites + "," + url+","+replyToURL+ ","+searchKey+" ,"+numberOfRetweets ;
}


}
