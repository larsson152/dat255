package com.dat255.Wood.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;



/**
 * This class represents a level in the game. It will contain 
 * the player and a certain amounts of blocks with various attributes.
 * A level is also worth a certain amount of points.
 *
 */
public class Level {

	//The player
	private Player player;
	
	//An array containing all Blocks.
	private Block[][] collisionLayer;
	private float levelScore;

	

	
	
	/**
	 * This method returns an array of blocks
	 * @return An array of blocks.
	 *
	 */
	public Block[][] getBlocks()
	{
		return collisionLayer;
	}

	/**
	 * This method returns the player for the game.
	 * @return The player
	 *
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * The constructor immediately creates a level with the 
	 * createLevel method. (see below)
	 *
	 */
	public Level()
	{
		createLevel();
	}

	/**
	 * This method creates our level. It reads a level from a textfile
	 * and generates the level accordingly. It sets up the players location,
	 * blocks of various kinds and the worth of the level in score.
	 *
	 */
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
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,false);
				}
				else if(c == 'H')
				{
					//New KeyHoleBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,false);
				}
				else if(c == 'T')
				{
					//New GreenTeleportBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,false);
				}
				else if(c == 't')
				{
					//New RedTeleportBlock
					collisionLayer[x][y]= new Block(new Vector2(x,y), c, false, false,false,false);
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
	
	
	/**
	 * This method switches place of two blocks in the level.
	 * @param x1 The x-coordinate of the first block
	 * @param y1 The y-coordinate of the first block
	 * @param x2 The x-coordinate of the second block
	 * @param y2 The y-coordinate of the second block
	 *
	 */
	public void switchCollisionBlocks(int x1, int y1, int x2, int y2)
	{
		Block temp = collisionLayer[x1][y1];
		Block temp2 = collisionLayer[x2][y2];
		
		collisionLayer[x1][y1] = temp2;
		collisionLayer[x2][y2] = temp;
	}
	
	/**
	 * This method sets the current worth of a level.
	 * @s The value we want the level to be worth
	 *
	 */
	public void setLevelScore(float s){
		levelScore = s;				 
	}
	
	/**
	 * This method decrements the levelscore of the level. If the levelscore is 0
	 * the decrementation does nothing.
	 *
	 */
	public void decrementScore(){
		if(levelScore > 0){
			levelScore--;
		}
	}
	
	/**
	 * This method returns the current worth of the level.
	 * @return The current levelscore
	 *
	 */
	public float getLevelScore() {
		return levelScore;	
	}
	


}
