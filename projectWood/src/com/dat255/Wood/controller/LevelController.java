package com.dat255.Wood.controller;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

import com.dat255.Wood.model.Level;
import com.dat255.Wood.model.Player;
import com.dat255.Wood.model.Player.State;

public class LevelController {
	
	enum Keys
	{
		LEFT, RIGHT, UP, DOWN
	}
	
	private Level level;
	private Player player;
	
	static HashMap<LevelController.Keys, Boolean> keys = new HashMap<LevelController.Keys, Boolean>();
	
	static
	{
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	};
	
	public LevelController(Level level)
	{
		this.level = level;
		this.player = level.getPlayer();
	}
	
	//Input
	
	public void leftPressed()
	{
		keys.put(Keys.LEFT, true);
	}
	
	public void leftReleased()
	{
		keys.put(Keys.LEFT, false);
	}
	
	public void rightPressed()
	{
		keys.put(Keys.RIGHT, true);
	}
	
	public void rightReleased()
	{
		keys.put(Keys.RIGHT, false);
	}
	
	public void upPressed()
	{
		keys.put(Keys.UP, true);
	}
	
	public void upReleased()
	{
		keys.put(Keys.UP, false);
	}
	
	public void downPressed()
	{
		keys.put(Keys.DOWN, true);
	}
	
	public void downReleased()
	{
		keys.put(Keys.DOWN, false);
	}
	
	public void update(float delta)
	{
		processInput();
		player.update(delta);
	}
	
	
	private void processInput()
	{
		if(keys.get(Keys.LEFT))
		{
			player.setFacingLeft(true);
			player.setState(State.WALKING);
			player.getVelocity().x = -Player.SPEED;
		}
		
		if(keys.get(Keys.RIGHT))
		{
			player.setFacingLeft(false);
			player.setState(State.WALKING);
			player.getVelocity().x = Player.SPEED;
		}
		
		if(keys.get(Keys.UP))
		{
			player.setFacingUp(true);
			player.setState(State.WALKING);
			player.getVelocity().y = Player.SPEED;
		}
		
		if(keys.get(Keys.DOWN))
		{
			player.setFacingUp(false);
			player.setState(State.WALKING);
			player.getVelocity().y = -Player.SPEED;
		}
		
		if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
		(!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT))))
		{
			player.setState(State.IDLE);
			player.getAcceleration().x = 0;
			player.getVelocity().x = 0;
		}
		
		if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
		(!keys.get(Keys.UP) && !(keys.get(Keys.DOWN))))
		{
			player.setState(State.IDLE);
			player.getAcceleration().y = 0;
			player.getVelocity().y = 0;
		}
		
	}
	
}
