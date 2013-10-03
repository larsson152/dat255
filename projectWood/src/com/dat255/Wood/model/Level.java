﻿package com.dat255.Wood.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

public class Level {

	//The player
	private Player player;
	
	//An array containing all Blocks.
	private Block[][] collisionLayer;
	private float levelScore;

	//Get functions

	//Get the blocks
	public Block[][] getBlocks()
	{
		return collisionLayer;
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
		setLevelScore(20);
		collisionLayer = new Block[16][16];
		FileHandle file = Gdx.files.internal("data/levels/level1.txt");
		String text = file.readString();

		for(int x=0;x<16;x++){						
			for(int y=0;y<16;y++){
				char c = text.charAt(x+y*16+y*2);

				if(c == '1'){
					//New WallBlock.
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, true,false,false);
				}
				else if(c == '2')
				{
					//New PushBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, true, true,false,false);
				}
				else if(c == '3')
				{
					//New WaterBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,true,false);
				}
				else if(c == '4')
				{
					//New LavaBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,true,false);
				}
				else if(c == '5')
				{
					//New SandBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,false);
				}
				else if(c == '6')
				{
					//New IceBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,true);
				}
				else if(c == 'K')
				{
					//New KeyBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,true);
				}
				else if(c == 'H')
				{
					//New KeyHoleBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,true);
				}
				else if(c == 'T')
				{
					//New GreenTeleportBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,true);
				}
				else if(c == 't')
				{
					//New RedTeleportBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,true);
				}
				else if(c == 'G')
				{
					//New GoalBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,false);
				}
				else if(c == 's'){
					//The player is initialized to start at this position, and an empty block is added to the array as the player always start on a empty block.
					player = new Player(new Vector2(x,y));
					collisionLayer[x][y] = new Block(new Vector2(x,y), '0', false, false,false,false);
				}
				else
				{
					//New Empty Block
					collisionLayer[x][y] = new Block(new Vector2(x,y), '0', false, false,false,false);
				}

			}
		}
	}
	
	//Swaps two blocks in the current level. 
	public void switchCollisionBlocks(int x1, int y1, int x2, int y2)
	{
		Block temp = collisionLayer[x1][y1];
		Block temp2 = collisionLayer[x2][y2];
		
		collisionLayer[x1][y1] = temp2;
		collisionLayer[x2][y2] = temp;
	}
	

	
	public void setLevelScore(float s){
		levelScore = s;				//Sets the current worth of the level.
	}
	
	public void decrementScore(){
		if(levelScore > 0){
			levelScore--;
		}else{
			levelScore = 0;
		}
	}

	public float getLevelScore() {
		return levelScore;	
	}
	


}
