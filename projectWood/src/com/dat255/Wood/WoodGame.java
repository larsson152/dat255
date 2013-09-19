package com.dat255.Wood;

import com.badlogic.gdx.Game;
import com.dat255.Wood.screens.PlayLevel;
import com.dat255.Wood.screens.Splash;

public class WoodGame extends Game {
	
	
	//INSTANCE VARIABLES
	Splash splashScreen;
	public PlayLevel playLevel;
	
	@Override
	public void create() 
	{
		splashScreen = new Splash(this);
		playLevel = new PlayLevel(this);
		
		setScreen(splashScreen);
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
