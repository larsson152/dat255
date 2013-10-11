package com.dat255.Wood.model;

//A class that runs in the background and manages time. Requires no constructor.

/**
 * @author Lucas
 * This class represents the timer in the game. It can be called from anywhere
 * and does not require a constructor. 
 */

public class GameTimer {
	private static int currentFps = 0;
	public static int Fps; //actual frames per second
	private static long start = 0;
	public static int levelTime = 0;
	
	
	
	
	/**
	 * This static method updates the timer so it increases by 1
	 * every second.
	 */
	public static void updateFps(){
		currentFps++;
		if(System.currentTimeMillis() - start >= 1000){
			if(!(levelTime >= 1000)){
				levelTime += 1;
				Fps = currentFps;
				currentFps = 0;
				start = System.currentTimeMillis();
			}else{
				levelTime = 1000;
			}
		}
	}
	
	
	
	
	
	public static long getTime(){
		return levelTime;
	}
	
	public static void resetLevelTime(){
		levelTime = 0;
	}
	

}
