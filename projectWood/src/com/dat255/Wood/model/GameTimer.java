package com.dat255.Wood.model;

//A class that runs in the background and manages time. Requires no constructor.

public class GameTimer {
	private static int currentFps = 0;
	public static int Fps; //actual frames per second
	private static long start = 0;
	public static int levelTime = 0;
	
	private static boolean ticked;
	
	//Every second this method adds 1 to the current leveltimer.
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
	
	public static void unTick(){
		ticked = false;
	}
	


	public static boolean returnTicked() {
		
		return ticked;
	}
	
	
	
	

}
