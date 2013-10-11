package com.dat255.Wood.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dat255.Wood.WoodGame;

/**
 * Shows a list of all the five best scores
 * @author Patrik Larsson
 *
 */
public class Highscore implements Screen {

    private SpriteBatch spriteBatch;
    private Texture hsTexture;
    private WoodGame game;
    private BitmapFont font;
    
    public Highscore(WoodGame game)
    {
            this.game = game;
    }

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(spriteBatch, "test" , 25, 160);
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
        font =  new BitmapFont(Gdx.files.internal("fonts/font.fnt"), false);
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
