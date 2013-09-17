package com.dat255.Wood;

import com.badlogic.gdx.Game;
import com.dat255.Wood.screens.MainMenuScreen;
import com.dat255.Wood.screens.PlayLevel;

public class WoodGame extends Game {
	
	
	//INSTANCE VARIABLES
	MainMenuScreen mainMenuScreen;
	PlayLevel playLevel;
	
	@Override
	public void create() 
	{
		mainMenuScreen = new MainMenuScreen(this);
		playLevel = new PlayLevel(this);
		
		setScreen(playLevel);
	}

	@Override
	public void dispose() 
	{
		super.dispose();
	}

	@Override
	public void render() 
	{		
		super.render();
	}

	@Override
	public void resize(int width, int height) 
	{
		super.resize(width, height);
	}

	@Override
	public void pause() 
	{
		super.pause();
	}

	@Override
	public void resume() 
	{
		super.resume();
	}
}
