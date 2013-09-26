package com.dat255.Wood.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


//WORKING NAME FOR CLASS
public class Block {
	
	//INSTANCE VARIABLES
	public static final float SPEED = 2.5f;
	public static final float SIZE = 1f;
	
	private int blockId;
	private Vector2 position;
	private Vector2 velocity; //Velocity Vector with X and Y components.
	private Rectangle bounds;
	
	//CONSTRUCTOR
	public Block(Vector2 position, int blockId)
	{
		this.position = position;
		this.velocity = new Vector2();
		this.bounds = new Rectangle(0,0,SIZE,SIZE); //x,y,width,height
		this.blockId = blockId;
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
	
	public int getBlockId()
	{
		return blockId;
	}
	
	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	public void update(float delta)
	{
		position.add(velocity.cpy().scl(delta));
	}

}
