package com.infinity.mindmaster;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
	
	public ArrayList randomNumbers = new ArrayList( );
	
	public GameLogic() {
		
		
	}
	
	public void GenerateRandomValues(int amount){
		
		amount=4;
		Random rand = new Random();
		for (int generateCount = 0; generateCount < amount; generateCount++) {
			
			randomNumbers.add(rand.nextInt((amount - 1) + 1) + amount);
			
		}
	    
	}
	
	public void GetNextRandomValue(){
		
		
	}
	
	public boolean CheckRandomValue() {		
		
		
		return false;		
	}

}
