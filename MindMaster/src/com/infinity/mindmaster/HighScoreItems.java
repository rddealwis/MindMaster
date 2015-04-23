package com.infinity.mindmaster;

public class HighScoreItems {

	private String playerName;
	private String playerScore;
	
	public HighScoreItems(String playerName,String playerScore){
		
		super();
		this.playerName = playerName;
		this.playerScore = playerScore;	
	}
	
	public String getDisplayText(){
		return playerName;
	}
	
	public String getIconID(){
		return playerScore;
	}		
}
