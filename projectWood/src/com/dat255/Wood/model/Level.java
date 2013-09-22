package com.dat255.Wood.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
//Use this or the normal one?

public class Level {
	
	//An array of blocks that our Level will consist off.
	private Array<Block> blocks;
	
	//The player
	private Player player;
	
	//Get functions
	
	//Get the blocks
	public Array<Block> getBlocks()
	{
		return blocks;
	}
	
	//Get the player
	public Player getPlayer()
	{
		return player;
	}
	
	//CONSTRUCTOR
	public Level()
	{
		createLevel();
	}
	
	//Creates a for now test Level
	private void createLevel()
	{
		player = new Player(new Vector2(8,2));
		
		blocks = new Array<Block>();
		
		blocks.add(new Block(new Vector2(8,1)));
	}

}
