package com.dat255.Wood.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dat255.Wood.WoodGame;
import com.dat255.Wood.controller.UserTextInputListener;

public class UserNameScreen implements Screen {

	private SpriteBatch spriteBatch;
    private Texture connTexture;
    private WoodGame game;
	
    
    public UserNameScreen(WoodGame game){
    	this.game = game;
    }
    
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(connTexture, 0, 0);
        spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
        spriteBatch = new SpriteBatch();
        connTexture = new Texture(Gdx.files.internal("images/connecting.png"));
        
        UserTextInputListener listener = new UserTextInputListener(game);
        Gdx.input.getTextInput(listener, "Enter username (must be more than 3 characters)", "");
        
        
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
