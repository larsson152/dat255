package com.dat255.Wood;

import com.badlogic.gdx.Game;
import com.dat255.Wood.screens.GameScreen;
import com.dat255.Wood.screens.SplashScreen;


public class WoodGame extends Game {

	//INSTANCE VARIABLES
	
	//SCREENS
	//SplashScreen splashScreen;
	//MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;
	SplashScreen splashScreen;
	
	//From libgdx wiki: Method called once when the application is created.
	@Override
	public void create() {
		gameScreen = new GameScreen();
		splashScreen = new SplashScreen(this);
		setScreen(splashScreen);
	}
	
	public void playGame()
	{
		setScreen(gameScreen);
	}

}
