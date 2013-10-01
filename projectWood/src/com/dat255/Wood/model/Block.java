package com.dat255.Wood.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


//WORKING NAME FOR CLASS
public class Block {
	
	//INSTANCE VARIABLES
	public static final float SPEED = 2.5f;
	public static final float SIZE = 1f;
	
	private char blockId;
	private Vector2 position;
	private Vector2 velocity; //Velocity Vector with X and Y components.
	private Rectangle bounds;
	
	private boolean moveable = false;
	private boolean solid = false;
	
	//CONSTRUCTOR
	public Block(Vector2 position, char blockId, boolean moveable, boolean solid)
	{
		this.position = position;
		this.velocity = new Vector2();
		this.bounds = new Rectangle(0,0,SIZE,SIZE); //x,y,width,height
		this.blockId = blockId;
		this.moveable = moveable;
		this.solid = solid;
	}
	
	//Returns the bounding box of the block.
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	//Returns the blocks current position.
	public Vector2 getPosition()
	{
		return position;
	}
	
	//Returns the blocks id.
	public int getBlockId()
	{
		return blockId;
	}
	
	//Returns the current Velocity Vector2.
	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	//Updates the Blocks position in regards to the velocity it travels.
	public void update(float delta)
	{
		position.add(velocity.cpy().scl(delta));
	}
	
	//Returns whether the Block is moveable or not.
	public boolean isMoveable()
	{
		return moveable;
	}
	
	//Returns whether the Block is solid or not.
	public boolean isSolid()
	{
		return solid;
	}

}
