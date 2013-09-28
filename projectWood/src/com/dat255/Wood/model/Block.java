package com.dat255.Wood.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


//WORKING NAME FOR CLASS
public class Block {
	
	//INSTANCE VARIABLES
	public static final float SIZE = 1f;
	
	private Vector2 position;
	private Rectangle bounds;
	
	//CONSTRUCTOR
	public Block(Vector2 position)
	{
		this.position = position;
		this.bounds = new Rectangle(0,0,SIZE,SIZE); //x,y,width,height
	}
	
	//Returns the bounding box of the block.
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}

}
