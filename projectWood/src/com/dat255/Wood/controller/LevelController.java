package com.dat255.Wood.controller;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.dat255.Wood.model.Block;
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
	private float startXpos, startYpos, actionBlockStartXpos, actionBlockStartYpos;
	private boolean moving = false;
	private Block actionBlock = null;

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
		if(actionBlock != null)
		{
			actionBlock.update(delta);
		}

	}

	//Determines if the player can move in a specific direction (char d).
	//And if there is a actionBlock in front of him interact with it.
	private boolean canMoveTo(char d)
	{
		int deltaX = 0;
		int deltaY = 0;

		switch (d)
		{
		
		//Left
		case 'l':
			deltaX = -1;
			deltaY = 0;
			break;
			
		//Right
		case 'r':
			deltaX = 1;
			deltaY = 0;
			break;

		//Up
		case 'u':
			deltaX = 0;
			deltaY = 1;
			break;
			
		//Down
		case 'd':
			deltaX = 0;
			deltaY = -1;
			break;

		default:
			return false;
		}
		//If the block the player wants to move to is a pushBlock and the block behind it is neither a wall or another pushblock.
		if(	(level.getBlocks()[(int) (player.getPosition().x + deltaX)][(int) player.getPosition().y + deltaY].getBlockId() == 2) && 
				(level.getBlocks()[(int) (player.getPosition().x + (2 * deltaX))][(int) player.getPosition().y + (2 * deltaY)].getBlockId() != 1) && 
				(level.getBlocks()[(int) (player.getPosition().x + (2 * deltaX))][(int) player.getPosition().y + (2 * deltaY)].getBlockId() != 2))
		{
			actionBlock = level.getBlocks()[(int) (player.getPosition().x + deltaX)][(int) player.getPosition().y + deltaY];
			actionBlock.getVelocity().x = deltaX * Block.SPEED;
			actionBlock.getVelocity().y = deltaY * Block.SPEED;
			actionBlockStartXpos = actionBlock.getPosition().x;
			actionBlockStartYpos = actionBlock.getPosition().y;
			return true;
		}
		//Move of the adjacent block is neither a wall or pushBlock(Because from the earlier statement we get to know if there is a wall behind the pushblock or not.).
		return ((level.getBlocks()[(int) (player.getPosition().x + deltaX)][(int) player.getPosition().y + deltaY].getBlockId() != 1) &&
				(level.getBlocks()[(int) (player.getPosition().x + deltaX)][(int) player.getPosition().y + deltaY].getBlockId() != 2));
	}

	private void processInput()
	{
		if(keys.get(Keys.LEFT) && (moving == false))
		{
			player.setFacingLeft(true);
			if(canMoveTo('l'))
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
			if(canMoveTo('r'))
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
			if(canMoveTo('u'))
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
			if(canMoveTo('d'))
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

		if(actionBlock != null)
		{
			if ((actionBlock.getPosition().x - actionBlockStartXpos) > 1)
			{
				actionBlock.getVelocity().x = 0;
				level.switchCollisionBlocks((int) actionBlockStartXpos,(int) actionBlockStartYpos,(int) (actionBlockStartXpos + 1),(int) actionBlockStartYpos);
				actionBlock.getPosition().set(new Vector2(actionBlockStartXpos + 1, actionBlockStartYpos));
				actionBlock = null;
			}
			else if ((actionBlock.getPosition().y - actionBlockStartYpos) > 1)
			{
				actionBlock.getVelocity().y = 0;
				level.switchCollisionBlocks((int) actionBlockStartXpos,(int) actionBlockStartYpos,(int) (actionBlockStartXpos ),(int) actionBlockStartYpos + 1);
				actionBlock.getPosition().set(new Vector2(actionBlockStartXpos , actionBlockStartYpos + 1));
				actionBlock = null;
			}
			else if (Math.abs((actionBlock.getPosition().y - actionBlockStartYpos)) > 1)
			{
				actionBlock.getVelocity().y = 0;
				level.switchCollisionBlocks((int) actionBlockStartXpos,(int) actionBlockStartYpos,(int) (actionBlockStartXpos ),(int) actionBlockStartYpos - 1);
				actionBlock.getPosition().set(new Vector2(actionBlockStartXpos , actionBlockStartYpos - 1));
				actionBlock = null;
			}
			else if (Math.abs((actionBlock.getPosition().x - actionBlockStartXpos)) > 1)
			{
				actionBlock.getVelocity().x = 0;
				level.switchCollisionBlocks((int) actionBlockStartXpos,(int) actionBlockStartYpos,(int) (actionBlockStartXpos - 1),(int) actionBlockStartYpos);
				actionBlock.getPosition().set(new Vector2(actionBlockStartXpos - 1 , actionBlockStartYpos));
				actionBlock = null;
			}
		}

	}

}
