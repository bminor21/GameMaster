package com.bminor.gamemaster;

public class Game {

	private int id;
	private String title;
	private String developer;
	private String platform;
	
	public Game() {}

	public Game(int id, String title, String developer, String platform) {
		this.id = id;
		this.title = title;
		this.developer = developer;
		this.platform = platform;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", developer=" + developer + ", platform=" + platform + "]";
	}
	
	

}
