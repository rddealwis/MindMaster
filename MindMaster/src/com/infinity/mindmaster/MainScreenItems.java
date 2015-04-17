package com.infinity.mindmaster;

public class MainScreenItems {
	
	private String displayText;
	private int iconID;
	
	public MainScreenItems(String displayText,int iconID){
		
		super();
		this.displayText = displayText;
		this.iconID = iconID;	
	}
	
	public String getDisplayText(){
		return displayText;
	}
	
	public int getIconID(){
		return iconID;
	}	


}
