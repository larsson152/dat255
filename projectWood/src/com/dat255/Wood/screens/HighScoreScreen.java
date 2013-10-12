package com.dat255.Wood.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dat255.Wood.WoodGame;
import com.dat255.Wood.model.HighScore;

/**
 * Shows a list of all the five best scores
 * @author Patrik Larsson
 *
 */
public class HighScoreScreen implements Screen {

    private SpriteBatch spriteBatch;
    private Texture hsTexture;
    private WoodGame game;
    private BitmapFont font;
    CharSequence cs;
    String toCharSeq= "";
    
    public HighScoreScreen(WoodGame game)
    {
            this.game = game;
    }

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        spriteBatch.draw(hsTexture, 0, 0);
        font.draw(spriteBatch, cs , 25, 160);
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
        ArrayList<HighScore> scoreList = game.getScoreList();

        for(HighScore hs: scoreList){

        	if(hs.getLocalScore()>0){
        	toCharSeq = toCharSeq + hs.getName() + " " + hs.getLocalScore() + "\n";
        	}
        }
        cs = toCharSeq;
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
