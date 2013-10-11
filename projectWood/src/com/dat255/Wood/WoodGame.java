package com.dat255.Wood;


import com.badlogic.gdx.Game;
import com.dat255.Wood.model.GameClient;
import com.dat255.Wood.screens.GameScreen;
import com.dat255.Wood.screens.SplashScreen;


public class WoodGame extends Game {

	//INSTANCE VARIABLES
	
	//SCREENS
	//SplashScreen splashScreen;
	//MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;
	private SplashScreen splashScreen;
	private GameClient client;
	private String name;
	
	//From libgdx wiki: Method called once when the application is created.
	@Override
	public void create() {
		client = new GameClient();
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
}
