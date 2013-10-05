package com.dat255.Wood.controller;

import com.badlogic.gdx.Input.TextInputListener;
import com.dat255.Wood.WoodGame;
import com.dat255.Wood.model.HighScore;
import com.dat255.Wood.screens.MainMenu;

public class UserTextInputListener implements TextInputListener {
	
	private WoodGame game;
	
	public UserTextInputListener(WoodGame game){
		this.game = game;
	}
	
	   @Override
	   public void input (String text) {
		   game.setName(text);
		   new HighScore(text).sendHighScore();
		   game.playGame();
	   }

	   @Override
	   public void canceled () {
		   game.setScreen(new MainMenu(game));
	   }
	}
