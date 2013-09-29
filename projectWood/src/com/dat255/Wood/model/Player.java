package com.dat255.Wood.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	public enum State{
		IDLE, WALKING, DYING;

		State(){
			
		}
	}
	
	//FACING DIRECTIONS
	public enum FacingDirection
	{
		LEFT, RIGHT, UP, DOWN;
		
		FacingDirection(){
			
		}
	}
	
	
	//INSTANCE VARIABLES
	public static final float SPEED = 2f;
	public static final float SIZE = 1f;
	
	private String name;
	private int score;
	private Vector2 position; //Position Vector with X and Y components.
	private Vector2 acceleration; //Acceleration Vector with X and Y components.
	private Vector2 velocity; //Velocity Vector with X and Y components.
	private Rectangle bounds; //Rectangle representing the players bounding box (collision box) with Height and Width (and X and Y position).
	State state; //State, the players current state.
	public FacingDirection direction;
	
	
	
	
	//CONSTRUCTOR
	public Player(Vector2 position)
	{
		this.position = position;
		this.acceleration = new Vector2();
		this.velocity = new Vector2();
		this.bounds = new Rectangle(0,0,SIZE,SIZE); //New rectangle with X position 0 and Y position 0 and Width = SIZE and Height = SIZE. (x,y,width,height)
		this.state = State.IDLE; //Initiating state to the default state IDLE.
		this.direction = FacingDirection.DOWN;
	}
	
	//Empty contructor for Serialization
	public Player(){
		
	}

	
	//Returns the players bounding box.
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	//Returns the players position.
	public Vector2 getPosition()
	{
		return position;
	}
	
	//Changes the players state to newState.
	public void setState(State newState)
	{
		this.state = newState;
	}
	
	//Moves the players position by the velocity times the time since last frame, in x and y. (we only use either only x or only y)
	public void update(float delta)
	{
		position.add(velocity.cpy().scl(delta));
	}
	
	//Sets the players direction which it faces. (The renderer will use this to know which texture to draw.)
	public void setFacingLeft(boolean facingLeft)
	{
		if(facingLeft)
		{
			this.direction = FacingDirection.LEFT;
		}
		else if(!facingLeft)
		{
			this.direction = FacingDirection.RIGHT;
		}
	}
	
	//Sets the players direction which it faces. (The renderer will use this to know which texture to draw.)
	public void setFacingUp(boolean facingUp)
	{
		if(facingUp)
		{
			this.direction = FacingDirection.UP;
		}
		else if(!facingUp)
		{
			this.direction = FacingDirection.DOWN;
		}		
	}
	
	//Returns the player acceleration vector.
	public Vector2 getAcceleration()
	{
		return acceleration;
	}
	
	//Returns the players velocity vector.
	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return score;
	}

}
