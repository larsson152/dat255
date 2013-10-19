package com.dat255.Wood.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


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
	private boolean liquid = false;
	private boolean slippery = false;
	
	//CONSTRUCTOR
	/**
	 * @param position Position of the block as a Vector2.
	 * @param blockId Id for the block used to create it.
	 * @param moveable 
	 * @param solid 
	 * @param liquid 
	 * @param slippery
	 */
	public Block(Vector2 position, char blockId, boolean moveable, boolean solid, boolean liquid, boolean slippery)
	{
		this.position = position;
		this.velocity = new Vector2();
		this.bounds = new Rectangle(0,0,SIZE,SIZE); //x,y,width,height
		this.blockId = blockId;
		this.moveable = moveable;
		this.solid = solid;
		this.liquid = liquid;
		this.slippery = slippery;
	}
	
	/**
	 * @return the bounding box of the block.
	 */
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	/**
	 * @return the blocks current position.
	 */
	public Vector2 getPosition()
	{
		return position;
	}
	
	/**
	 * @return the blocks id.
	 */
	public char getBlockId()
	{
		return blockId;
	}
	
	/**
	 * @return the current Velocity Vector2.
	 */
	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	/**
	 * Updates the Blocks position in regards to the velocity it travels.
	 * @param delta Time elapsed since last update
	 */
	public void update(float delta)
	{
		position.add(velocity.cpy().scl(delta));
	}
	
	/**
	 * @return True if the block is movable, else false
	 */
	public boolean isMoveable()
	{
		return moveable;
	}
	
	/**
	 * @return True if the block is solid, else false
	 */
	public boolean isSolid()
	{
		return solid;
	}
	
	/**
	 * @return True if the block is liquid, else false
	 */
	public boolean isLiquid()
	{
		return liquid;
	}

	/**
	 * @return True if the block is slippery, else false
	 */
	public boolean isSlippery()
	{
		return slippery;
	}
}
