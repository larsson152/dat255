package com.dat255.Wood.controller;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
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
	private float startXpos, startYpos;
	private boolean moving = false;
	
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
		if(keys.get(Keys.LEFT) && (moving == false))
		{
			player.setFacingLeft(true);
			if(level.getBlocks()[(int) (player.getPosition().x - 1)][(int) player.getPosition().y].getBlockId() != 1)
			{
				player.setState(State.WALKING);
				player.getVelocity().x = -Player.SPEED;
				startXpos = player.getPosition().x;
				startYpos = player.getPosition().y;
				moving = true;
			}
		}
		
		else if(keys.get(Keys.RIGHT) && (moving == false))
		{
			player.setFacingLeft(false);
			if(level.getBlocks()[(int) (player.getPosition().x + 1)][(int) player.getPosition().y].getBlockId() != 1)
			{
				player.setState(State.WALKING);
				player.getVelocity().x = Player.SPEED;
				startXpos = player.getPosition().x;
				startYpos = player.getPosition().y;
				moving = true;
			}
		}
		
		else if(keys.get(Keys.UP) && (moving == false))
		{
			player.setFacingUp(true);
			if(level.getBlocks()[(int) (player.getPosition().x)][(int) player.getPosition().y + 1].getBlockId() != 1)
			{
				player.setState(State.WALKING);
				player.getVelocity().y = Player.SPEED;
				startXpos = player.getPosition().x;
				startYpos = player.getPosition().y;
				moving = true;
			}
		}
		
		else if(keys.get(Keys.DOWN) && (moving == false))
		{
			player.setFacingUp(false);
			if(level.getBlocks()[(int) (player.getPosition().x)][(int) player.getPosition().y - 1].getBlockId() != 1)
			{
				player.setState(State.WALKING);
				player.getVelocity().y = -Player.SPEED;
				startXpos = player.getPosition().x;
				startYpos = player.getPosition().y;
				moving = true;
			}
		}		
		
		if ((player.getPosition().x - startXpos) > 1)
		{
			player.setState(State.IDLE);
			player.getAcceleration().x = 0;
			player.getVelocity().x = 0;
			player.getPosition().set(new Vector2(startXpos + 1, startYpos));
			moving = false;
			rightReleased();
		}
		else if ((player.getPosition().y - startYpos) > 1)
		{
			player.setState(State.IDLE);
			player.getAcceleration().y = 0;
			player.getVelocity().y = 0;
			player.getPosition().set(new Vector2(startXpos, startYpos + 1));
			moving = false;
			upReleased();
		}
		else if (Math.abs((player.getPosition().x - startXpos)) > 1)
		{
			player.setState(State.IDLE);
			player.getAcceleration().x = 0;
			player.getVelocity().x = 0;
			player.getPosition().set(new Vector2(startXpos - 1, startYpos));
			moving = false;
			leftReleased();
			
		}
		else if (Math.abs((player.getPosition().y - startYpos)) > 1)
		{
			player.setState(State.IDLE);
			player.getAcceleration().y = 0;
			player.getVelocity().y = 0;
			player.getPosition().set(new Vector2(startXpos, startYpos - 1));
			moving = false;
			downReleased();
		}
		
	}
	
}
