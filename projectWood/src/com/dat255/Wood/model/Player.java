package com.dat255.Wood.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This class represents the player ingame.
 */

public class Player {
	
	public enum State{
		IDLE, WALKING, DEAD, SLIDING;

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
	
	private String name; //The player should have the option to enter username before playing
	private int score;	// The total score for the player
	private Vector2 position; //Position Vector with X and Y components.
	private Vector2 acceleration; //Acceleration Vector with X and Y components.
	private Vector2 velocity; //Velocity Vector with X and Y components.
	private Rectangle bounds; //Rectangle representing the players bounding box (collision box) with Height and Width (and X and Y position).
	State state; //State, the players current state.
	public FacingDirection direction;
	
	private float stateTime = 0; //Variable to know how far in one "step" the player has walked
	
	private boolean hasKey;  //if true if player has picked up a key
	
	
	/**
	 * Constructor for the player.
	 * @param position A vector with a position
	 */
	
	
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

	
	/**Returns the players bounding box.
	 * @return bounds The bounding box
	 */
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	/**Returns the players position.
	 * @return position The player position
	 */
	public Vector2 getPosition()
	{
		return position;
	}
	
	/**Changes the players state to newState.
	 * @param newState The state to be set
	 */
	public void setState(State newState)
	{
		this.state = newState;
	}
	
	/**Moves the players position by the velocity times the time since last frame, in x and y. (we only use either only x or only y)
	 * @param delta Time since last frame
	 */
	public void update(float delta)
	{
		stateTime = stateTime + delta;
		position.add(velocity.cpy().scl(delta));
	}
	
	/**Sets the players direction which it faces. (The renderer will use this to know which texture to draw.)
	 * @param facingDirection The direction we switch to
	 */
	public void setFacingDirection(FacingDirection facingDirection)
	{
		this.direction = facingDirection;
	}
	
	/**Returns the player acceleration vector.
	 * @return acceleration The acceleration of the player
	 */
	public Vector2 getAcceleration()
	{
		return acceleration;
	}
	
	/**Returns the players velocity vector.
	 * @return velocity A vector with velocity
	 */
	public Vector2 getVelocity()
	{
		return velocity;
	}
	
	/**Returns the player name
	 * @return name The name of the player
	 */
	public String getName(){
		return name;
	}
	
	/**Returns the score of the player
	 * @return score The score of the player
	 */
	public int getScore(){
		return score;
	}
	
	/**Returns the current state of the player
	 * @return state The current state of the player.
	 */
	public State getState()
	{
		return state;
	}
	public float getStateTime()
	{
		return stateTime;
	}
	
	public boolean hasKey(){
		return hasKey;
	}
	
	public void setKey(boolean keyValue){
		hasKey=keyValue;
	}

}
