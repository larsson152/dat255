package com.dat255.Wood.controller;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.dat255.Wood.model.Block;
import com.dat255.Wood.model.GameTimer;
import com.dat255.Wood.model.Level;
import com.dat255.Wood.model.Player;
import com.dat255.Wood.model.Player.State;


/**
 * This controller class is a supervisor for all activities on a level.
 * on a level. It makes sure the timer ticks and the state of the level,
 * whether it is completed or the game is paused.
 *
 */
public class LevelController {

	
	public boolean isPaused;
	public boolean levelWon;
	public boolean gameOver;
	

	enum Keys
	{
		LEFT, RIGHT, UP, DOWN
	}

	private Level level;
	private Player player;
	private float startXpos, startYpos, actionBlockStartXpos, actionBlockStartYpos;
	private Block actionBlock = null;

	static HashMap<LevelController.Keys, Boolean> keys = new HashMap<LevelController.Keys, Boolean>();

	static
	{
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	};

	/**
	 * Constructor for LevelController.
	 * It loads a level and a player and sets all starting
	 * booleans to proper values
	 * @param level The level that will be loaded
	 *
	 */
	
	public LevelController(Level level)
	{
		this.level = level;
		this.player = level.getPlayer();
		isPaused = false;
		levelWon = false;
		gameOver = false;
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
	
	/**
	 * This method is run repeatedly. It increases the timer as long
	 * as the game is not paused or gameover has been activated.
	 * The timer will also only tick if a second has passed. It also
	 * checks for input from the user. It also looks for interactions with
	 * different kinds of blocks.
	 * @param delta Seconds since last frame
	 */

	public void update(float delta)
	{
		if(!isPaused && !gameOver){
			GameTimer.updateFps();
			if(GameTimer.returnTicked() == true){
				level.decrementScore();
				GameTimer.unTick();
			}
			processInput();
			player.update(delta);
			if(actionBlock != null)
			{
				actionBlock.update(delta);
			}	
		}	
		

	}
	
	/**
	* This method moves the player 
	* @param dirX X-coordinate
	* @param dirY Y-coordinate
	*/

	private void movePlayer(int dirX, int dirY)
	{
		player.setState(State.WALKING);
		if(level.getBlocks()[(int) (player.getPosition().x + dirX)][(int) (player.getPosition().y + dirY)].isSlippery())
		{
			player.setState(State.SLIDING);
		}
		player.getVelocity().x = dirX * Player.SPEED;
		player.getVelocity().y = dirY * Player.SPEED;
		startXpos = player.getPosition().x;
		startYpos = player.getPosition().y;
	}

	private void stopPlayer(int incX, int incY)
	{
		if(!(player.getState() == State.SLIDING && level.getBlocks()[(int) (startXpos + incX)][(int) (startYpos + incY)].isSlippery() && !level.getBlocks()[(int) (startXpos + (2 * incX))][(int) (startYpos + (2 * incY))].isSolid()))	
		{
			player.setState(State.IDLE);
			player.getAcceleration().x = 0;
			player.getVelocity().x = 0;
			player.getAcceleration().y = 0;
			player.getVelocity().y = 0;
		}
		player.getPosition().set(new Vector2(startXpos + incX, startYpos + incY));
		startXpos = startXpos + incX;
		startYpos = startYpos + incY;
		
		
	}

	//Determines if the player can move in a specific direction (char d).
	//And if there is a actionBlock in front of him interact with it.
	private boolean canMoveTo(int dX, int dY)
	{
		int deltaX = dX;
		int deltaY = dY;

		if((level.getBlocks()[(int) (player.getPosition().x + deltaX)][(int) player.getPosition().y + deltaY].isMoveable()) && 
				(level.getBlocks()[(int) (player.getPosition().x + (2 * deltaX))][(int) player.getPosition().y + (2 * deltaY)].isSolid() == false))
		{
			actionBlock = level.getBlocks()[(int) (player.getPosition().x + deltaX)][(int) player.getPosition().y + deltaY];
			actionBlock.getVelocity().x = deltaX * Block.SPEED;
			actionBlock.getVelocity().y = deltaY * Block.SPEED;
			actionBlockStartXpos = actionBlock.getPosition().x;
			actionBlockStartYpos = actionBlock.getPosition().y;
			return true;
		}
		//Move if the adjacent block is not solid.
		return (!(level.getBlocks()[(int) (player.getPosition().x + deltaX)][(int) player.getPosition().y + deltaY].isSolid()));

	}
	
	private boolean pushBlockToLiquid(int x, int y){
		if(level.getBlocks()[(int)actionBlockStartXpos+x][(int) actionBlockStartYpos+y].isLiquid()){
			level.getBlocks()[(int)actionBlockStartXpos][(int) actionBlockStartYpos] = new Block(new Vector2(actionBlockStartXpos, actionBlockStartYpos), '0', false, false,false,false);
			level.getBlocks()[(int)actionBlockStartXpos+x][(int) actionBlockStartYpos+y] = new Block(new Vector2(actionBlockStartXpos+x, actionBlockStartYpos+y), '0', false, false,false,false);	
			return true;
		}
		
		return false;
	}

	private void processInput()
	{
		if(player.getState() == Player.State.IDLE)
		{
			if(keys.get(Keys.LEFT))
			{
				player.setFacingDirection(Player.FacingDirection.LEFT);
				if(canMoveTo(-1,0))
				{
					movePlayer(-1,0);
				}
			}

			else if(keys.get(Keys.RIGHT))
			{
				player.setFacingDirection(Player.FacingDirection.RIGHT);
				if(canMoveTo(1,0))
				{
					movePlayer(1,0);
				}
			}

			else if(keys.get(Keys.UP))
			{
				player.setFacingDirection(Player.FacingDirection.UP);
				if(canMoveTo(0,1))
				{
					movePlayer(0,1);
				}
			}

			else if(keys.get(Keys.DOWN))
			{
				player.setFacingDirection(Player.FacingDirection.DOWN);
				if(canMoveTo(0,-1))
				{
					movePlayer(0,-1);
				}
			}
		}

		if(player.getState() != State.IDLE)
		{
			if ((player.getPosition().x - startXpos) > 1)
			{
				stopPlayer(1,0);
				rightReleased();
			}
			else if ((player.getPosition().y - startYpos) > 1)
			{
				stopPlayer(0,1);
				upReleased();
			}
			else if (Math.abs((player.getPosition().x - startXpos)) > 1)
			{
				stopPlayer(-1,0);
				leftReleased();
			}
			else if (Math.abs((player.getPosition().y - startYpos)) > 1)
			{
				stopPlayer(0,-1);
				downReleased();
			}
		}

		if(actionBlock != null)
		{
					
			
			if ((actionBlock.getPosition().x - actionBlockStartXpos) > 1)
			{
				if(pushBlockToLiquid(1,0)==false){
					actionBlock.getVelocity().x = 0;
					level.switchCollisionBlocks((int) actionBlockStartXpos,(int) actionBlockStartYpos,(int) (actionBlockStartXpos + 1),(int) actionBlockStartYpos);
					actionBlock.getPosition().set(new Vector2(actionBlockStartXpos + 1, actionBlockStartYpos));
				}
				actionBlock = null;
				
			}
			else if ((actionBlock.getPosition().y - actionBlockStartYpos) > 1)
			{
				if(pushBlockToLiquid(0,1)==false){
					actionBlock.getVelocity().y = 0;
					level.switchCollisionBlocks((int) actionBlockStartXpos,(int) actionBlockStartYpos,(int) (actionBlockStartXpos ),(int) actionBlockStartYpos + 1);
					actionBlock.getPosition().set(new Vector2(actionBlockStartXpos , actionBlockStartYpos + 1));
				}
				actionBlock = null;
				
			}
			else if (Math.abs((actionBlock.getPosition().y - actionBlockStartYpos)) > 1)
			{
				if(pushBlockToLiquid(0,-1)==false){
					actionBlock.getVelocity().y = 0;
					level.switchCollisionBlocks((int) actionBlockStartXpos,(int) actionBlockStartYpos,(int) (actionBlockStartXpos ),(int) actionBlockStartYpos - 1);
					actionBlock.getPosition().set(new Vector2(actionBlockStartXpos , actionBlockStartYpos - 1));
				}
				actionBlock = null;
				
			}
			else if (Math.abs((actionBlock.getPosition().x - actionBlockStartXpos)) > 1)
			{
				if(pushBlockToLiquid(-1,0)==false){
					actionBlock.getVelocity().x = 0;
					level.switchCollisionBlocks((int) actionBlockStartXpos,(int) actionBlockStartYpos,(int) (actionBlockStartXpos - 1),(int) actionBlockStartYpos);
					actionBlock.getPosition().set(new Vector2(actionBlockStartXpos - 1 , actionBlockStartYpos));
				}
				actionBlock = null;
				
			}
		}

	}

}
