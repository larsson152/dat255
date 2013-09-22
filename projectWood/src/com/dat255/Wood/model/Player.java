package com.dat255.Wood.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	public enum State{
		IDLE, WALKING, DYING
	}
	
	
	//INSTANCE VARIABLES
	//NO REAL VALUES SET YET
	public static final float SPEED = 2f;
	public static final float SIZE = 0.5f;
	
	private Vector2 position; //Position Vector with X and Y components.
	private Vector2 acceleration; //Acceleration Vector with X and Y components.
	private Vector2 velocity; //Velocity Vector with X and Y components.
	private Rectangle bounds; //Rectangle representing the players bounding box (collision box) with Height and Width (and X and Y position).
	State state; //State, the players current state.
	
	//FACING DIRECTION variable here
	boolean facingLeft = true;
	boolean facingUp = true;
	
	
	
	//CONSTRUCTOR
	public Player(Vector2 position)
	{
		this.position = position;
		this.acceleration = new Vector2();
		this.velocity = new Vector2();
		this.bounds = new Rectangle(0,0,SIZE,SIZE); //New rectangle with X position 0 and Y position 0 and Width = SIZE and Height = SIZE. (x,y,width,height)
		this.state = State.IDLE; //Initiating state to the default state IDLE.
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public void setState(State newState)
	{
		this.state = newState;
	}
	
	public void update(float delta)
	{
		position.add(velocity.cpy().scl(delta));
	}
	
	public void setFacingLeft(boolean facingLeft)
	{
		this.facingLeft = facingLeft;
	}
	
	public void setFacingUp(boolean facingUp)
	{
		this.facingUp = facingUp;
	}
	
	public Vector2 getAcceleration()
	{
		return acceleration;
	}
	
	public Vector2 getVelocity()
	{
		return velocity;
	}

}
