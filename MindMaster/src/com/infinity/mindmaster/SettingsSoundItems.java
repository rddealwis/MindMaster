package com.infinity.mindmaster;

import android.widget.ToggleButton;

public class SettingsSoundItems {
	
	private String displayText;
	private int iconID;
	private ToggleButton tglBtn;
	
	public SettingsSoundItems(String displayText,int iconID, ToggleButton tglBtn){
		
		super();
		this.displayText = displayText;
		this.iconID = iconID;	
		this.tglBtn = tglBtn;
	}
	
	public String getDisplayText(){
		return displayText;
	}
	
	public int getIconID(){
		return iconID;
	}	
	
	public ToggleButton getToggleButton(){
		return tglBtn;
	}

}
