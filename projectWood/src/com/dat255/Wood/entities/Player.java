package com.dat255.Wood.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {
	
	private Vector2 velocity = new Vector2();
	private TiledMapTileLayer collisionLayer;
	
	private float speed = 60 * 2, tileHeight, tileWidth;
	
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer){
		super(sprite);
		this.collisionLayer = collisionLayer;
		
	}
	
	


	//Animates the player
	@Override
	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	//Checks if the location of the player changes.
	private void update(float delta){
		
		float OldX = getX(), OldY = getY();
		tileHeight = collisionLayer.getTileHeight(); 
		tileWidth = collisionLayer.getTileWidth();
		
		
		setX(getX() + velocity.x * delta);
		setY(getY() + velocity.y * delta);
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		/*if(screenX>(getX()+128))
			velocity.x=speed;
		else if(screenX<(getX()-128))
			velocity.x=-speed;
		else if(screenY<(getY()+200))
			velocity.y=speed;
		else if(screenY>(getY()-200))
			velocity.y=-speed;
		return false;*/
		
		if(screenX>(Gdx.graphics.getWidth()/4)*3)
			velocity.x=speed;
		else if(screenX<Gdx.graphics.getWidth()/4 )
			velocity.x=-speed;
		else if(screenY>(Gdx.graphics.getHeight()/5)*4 && screenX>Gdx.graphics.getWidth()/4 && screenX<(Gdx.graphics.getWidth()/4)*3 )
			velocity.y=-speed;
		else if(screenY<Gdx.graphics.getHeight()/5 && screenX>Gdx.graphics.getWidth()/4 && screenX<(Gdx.graphics.getWidth()/4)*3){
		//	System.out.print("   "+Gdx.graphics.getHeight()/5+"   " +
			//		"    " + Gdx.graphics.getWidth()/4 +"    "+ (Gdx.graphics.getWidth()/4)*3+"   "+ screenY);
			velocity.y=speed;}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
			velocity.x=0;
			velocity.y=0;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}
	
	

}