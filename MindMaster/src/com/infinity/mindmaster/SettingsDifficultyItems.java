package com.infinity.mindmaster;

import android.widget.Spinner;

public class SettingsDifficultyItems {
	
	private String displayText;
	private int iconID;
	private Spinner spinner;
	
	public SettingsDifficultyItems(String displayText,int iconID, Spinner spinner){
		
		super();
		this.displayText = displayText;
		this.iconID = iconID;	
		this.spinner = spinner;
	}
	
	public String getDisplayText(){
		return displayText;
	}
	
	public int getIconID(){
		return iconID;
	}	
	
	public Spinner getSpinner(){
		return spinner;
	}

}
