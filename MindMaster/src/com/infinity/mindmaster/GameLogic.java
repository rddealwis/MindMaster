package com.infinity.mindmaster;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
	
	public ArrayList randomNumbers = new ArrayList( );
	public ArrayList userInput = new ArrayList( );
	public int gameLevel=4;
	public GameLogic() {
		
		
	}
	
	public void GenerateRandomValues(int amount){
		
		//amount=4;
		randomNumbers.removeAll(randomNumbers);
		Random rand = new Random();
		for (int generateCount = 0; generateCount < amount; generateCount++) {
			
			randomNumbers.add(rand.nextInt((amount - 1) + 1) + 1);			
		}	    
	}
	
	public void GetNextRandomValue(){
		
		
	}
	
	public boolean CheckRandomValue() {				
		
		return false;		
	}
	
	public void PrintRandomValues(){
		
		for (int index = 0; index < randomNumbers.size(); index++)
			System.out.println(randomNumbers.get(index));
	}
	
	public void GetUserInput(){
		
		Scanner reader = new Scanner(System.in);
		userInput.removeAll(userInput);
		for (int index = 0; index < randomNumbers.size(); index++)
		{
			System.out.println("Enter the number " +(index+1)+": ");
			userInput.add(reader.nextInt());
		}
	}
	
	public boolean CheckUserInput(){		
		for (int index = 0; index < randomNumbers.size(); index++) {
			if (randomNumbers.get(index) != userInput.get(index)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean GamePlay(){
		while (true) {
			System.out.println("Level "+(gameLevel-3));
			GenerateRandomValues(gameLevel++);
			PrintRandomValues();
			System.out.println();
			System.out.println("Printing over");
			System.out.println();
			GetUserInput();
			if (!CheckUserInput()) {
				return false;
			}
		}		
	}
	
	public static void main(String[] args){
		
		GameLogic gl=new GameLogic();
		if(!gl.GamePlay()){
			System.out.println("Game over");
		}		
	}
}
