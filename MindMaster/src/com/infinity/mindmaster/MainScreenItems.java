package com.infinity.mindmaster;

public class MainScreenItems {
	
	private int displayText;
	private int iconID;
	
	public MainScreenItems(int displayText,int iconID){
		
		super();
		this.displayText = displayText;
		this.iconID = iconID;	
	}
	
	public int getDisplayText(){
		return displayText;
	}
	
	public int getIconID(){
		return iconID;
	}	
}
