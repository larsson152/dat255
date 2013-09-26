package com.dat255.Wood.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
//Use this or the normal one?

public class Level {

	//An array of blocks that our Level will consist off.

	//The player
	private Player player;
	private Block[][] collisionLayer;

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

		collisionLayer = new Block[16][16];
		FileHandle file = Gdx.files.internal("data/levels/level1.txt");
		String text = file.readString();

		for(int x=0;x<16;x++){						
			for(int y=0;y<16;y++){
				char c = text.charAt(x+y*16+y*2);

				if(c == '1'){
					//Nytt väggblock.
					collisionLayer[x][y]= new Block(new Vector2(x,y), 1);
				}
				else if(c == '2')
				{
					collisionLayer[x][y]= new Block(new Vector2(x,y), 2);
				}
				
				else if(c == 's'){
					//Spelarens skapas på denna startposition samt blocket spelaren startar på är ett tomt block.
					player = new Player(new Vector2(x,y));
					collisionLayer[x][y] = new Block(new Vector2(x,y), 0);
				}
				else
				{
					//Nytt "tomt" block.
					collisionLayer[x][y] = new Block(new Vector2(x,y), 0);
				}

			}
		}
	}
	
	public void switchCollisionBlocks(int x1, int y1, int x2, int y2)
	{
		Block temp = collisionLayer[x1][y1];
		Block temp2 = collisionLayer[x2][y2];
		
		collisionLayer[x1][y1] = temp2;
		collisionLayer[x2][y2] = temp;
	}
	

}
