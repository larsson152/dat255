package com.dat255.Wood.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dat255.Wood.WoodGame;

public class Highscore implements Screen {

    private SpriteBatch spriteBatch;
    private Texture hsTexture;
    private WoodGame game;
    
    public Highscore(WoodGame game)
    {
            this.game = game;
    }

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(hsTexture, 0, 0);
        spriteBatch.end();
        		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void show() {
        spriteBatch = new SpriteBatch();
        hsTexture = new Texture(Gdx.files.internal("images/highscore.png"));
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
