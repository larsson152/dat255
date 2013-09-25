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
				char c = text.charAt(x*16+y+x*2);
				String cc= new String(""+c);

				if(cc.equals("1")){
					collisionLayer[x][y]= new Block(new Vector2(x,y));
				}
				
				if(cc.equals("s")){
					player = new Player(new Vector2(x,y));
				}

			}
		}	
	}

}
