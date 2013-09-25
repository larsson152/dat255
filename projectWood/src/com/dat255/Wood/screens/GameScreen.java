package com.dat255.Wood.screens;

import com.dat255.Wood.model.Level;
import com.dat255.Wood.view.LevelRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.dat255.Wood.controller.LevelController;

public class GameScreen implements Screen, InputProcessor{

	//INSTANCE VARIABLES
	private Level level;
	private LevelRenderer renderer;
	private LevelController controller;
	
	private int width;
	private int height;
	
	//From libgdx wiki: 
	//Method called by the game loop from the application every time rendering should be performed.
	//Game logic updates are usually also performed in this method.
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl20.glClearColor(0, 0, 0.2f, 1); //Clears the screen with the color (R,G,B,A)
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		controller.update(delta);
		renderer.render();
	}

	//From libgdx wiki: 
	//This method is called every time the game screen is re-sized and the game is not in the paused state.
	//It is also called once just after the create() method.
	//
	//The parameters are the new width and height the screen has been resized to in pixels.
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	//From libgdx API documentation: 
	//Called when this screen becomes the current screen for a Game.
	@Override
	public void show() {
		// TODO Auto-generated method stub
		level = new Level();
		renderer = new LevelRenderer(level, true);
		controller = new LevelController(level);
		Gdx.input.setInputProcessor(this);
	}

	//From libgdx API documentation: 
	//Called when this screen is no longer the current screen for a Game.
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);		
	}

	//From libgdx wiki: 
	//On Android this method is called when the Home button is pressed or an incoming call is received.
	//On desktop this is called just before dispose() when exiting the application.
	//
	//A good place to save the game state.
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	//From libgdx wiki: 
	//This method is only called on Android, when the application resumes from a paused state.
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	//From libgdx wiki: 
	//Called when the application is destroyed. It is preceded by a call to pause().
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);		
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
		// TODO Auto-generated method stub
		if (screenX < width / 2 && screenY > height / 2) 
		{
			controller.leftPressed();
		}
		if (screenX > width / 2 && screenY > height / 2) 
		{
			controller.rightPressed();
		}
		if (screenX < width / 2 && screenY < height / 2) 
		{
			controller.upPressed();
		}
		if (screenX > width / 2 && screenY < height / 2) 
		{
			controller.downPressed();
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if (screenX < width / 2 && screenY > height / 2) 
		{
			controller.leftReleased();
		}
		if (screenX > width / 2 && screenY > height / 2) 
		{
			controller.rightReleased();
		}
		if (screenX < width / 2 && screenY < height / 2) 
		{
			controller.upReleased();
		}
		if (screenX > width / 2 && screenY < height / 2) 
		{
			controller.downReleased();
		}
		return true;
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

}
