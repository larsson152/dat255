package com.dat255.Wood;


import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.dat255.Wood.model.GameClient;
import com.dat255.Wood.model.HighScore;
import com.dat255.Wood.screens.GameScreen;
import com.dat255.Wood.screens.SplashScreen;


public class WoodGame extends Game {

	//INSTANCE VARIABLES
	
	//SCREENS
	//SplashScreen splashScreen;
	//MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;
	private SplashScreen splashScreen;
	private String name;
	private static ArrayList<HighScore> scoreList;
	
	//From libgdx wiki: Method called once when the application is created.
	@Override
	public void create() {
		scoreList = new ArrayList<HighScore>();
		gameScreen = new GameScreen(1,this);
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
	}
	
	public void playGame()
	{
		setScreen(gameScreen);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
		
	public static void setScoreList(ArrayList<HighScore> scoreList) {
		WoodGame.scoreList = scoreList;
	}
	
	public ArrayList<HighScore> getScoreList() {
		return scoreList;
	}


}
