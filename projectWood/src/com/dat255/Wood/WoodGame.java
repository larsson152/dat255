package com.dat255.Wood;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.dat255.Wood.model.GameClient;
import com.dat255.Wood.model.Player;
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
		gameScreen = new GameScreen();
		//client = new GameClient(new Player(new Vector2(4,2)));
		splashScreen = new SplashScreen(this);
		//updateScore();
		
		setScreen(splashScreen);
	}
	
	public void playGame()
	{
		setScreen(gameScreen);
	}

	public void updateScore(){
		
		client.getClient().sendTCP(null);
		
	}
	
	public void setName(String name){
		this.name = name;
	}
}
