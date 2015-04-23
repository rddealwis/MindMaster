package com.infinity.mindmaster;

import android.widget.ToggleButton;

public class SettingsSoundItems {
	
	private int displayText;
	private int iconID;
	private ToggleButton tglBtn;
	
	public SettingsSoundItems(int displayText,int iconID, ToggleButton tglBtn){
		
		super();
		this.displayText = displayText;
		this.iconID = iconID;	
		this.tglBtn = tglBtn;
	}
	
	public int getDisplayText(){
		return displayText;
	}
	
	public int getIconID(){
		return iconID;
	}	
	
	public ToggleButton getToggleButton(){
		return tglBtn;
	}
}
