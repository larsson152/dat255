package com.dat255.Wood.controller;

import com.badlogic.gdx.Input.TextInputListener;
import com.dat255.Wood.WoodGame;
import com.dat255.Wood.model.HighScore;
import com.dat255.Wood.screens.LevelSelect;
import com.dat255.Wood.screens.MainMenu;

/**
 * Used to get the input from username dialog box.
 * @author Patrik Larsson
 *
 */
public class UserTextInputListener implements TextInputListener {
	
	private WoodGame game;
	
	public UserTextInputListener(WoodGame game){
		this.game = game;
	}
	
	   @Override
	   public void input (String text) {
		   game.setName(text);
		   new HighScore(text).send();
		   game.setScreen(new LevelSelect(game));
	   }

	   @Override
	   public void canceled () {
		   game.setScreen(new MainMenu(game));
	   }
	}
