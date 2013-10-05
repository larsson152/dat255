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
	
	private static boolean ticked;
	
	
	/**
	 * This static method updates the timer so it increases by 1
	 * every second.
	 */
	public static void updateFps(){
		currentFps++;
		if(System.currentTimeMillis() - start >= 1000){
			levelTime += 1;
			ticked = true;
			Fps = currentFps;
			currentFps = 0;
			start = System.currentTimeMillis();
			
			
			
			
			
		}
	}
	
	/**
	 * This static boolean method is used in LevelControllers update method.
	 * It makes sure that the score is decreasing by one as the timer updates
	 * and not repeatedly so the score lowers faster than the amount of seconds.
	 *
	 */
	public static void unTick(){
		ticked = false;
	}
	
	/**
	 * This static method returns the "ticked" boolean to see
	 * what state it is in.
	 */
	
	public static boolean returnTicked() {
		
		return ticked;
	}
	
	
	
	

}
