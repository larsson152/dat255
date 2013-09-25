package com.dat255.Wood.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dat255.Wood.WoodGame;
import com.dat255.Wood.tween.SpriteAccessor;

public class SplashScreen implements Screen {

	private SpriteBatch batch;
	private Sprite splash;
	private WoodGame game;
<<<<<<< HEAD:projectWood/src/com/dat255/Wood/screens/Splash.java
	private TweenManager tweenManager;

	public Splash(WoodGame game)
=======
	
	public SplashScreen(WoodGame game)
>>>>>>> remotes/origin/gustav-wip:projectWood/src/com/dat255/Wood/screens/SplashScreen.java
	{
		this.game = game;
	}

	@Override
	public void render(float delta) {
<<<<<<< HEAD:projectWood/src/com/dat255/Wood/screens/Splash.java
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tweenManager.update(delta);

		batch.begin();
		splash.draw(batch);
		batch.end();

		//game.setScreen(game.playLevel);

=======
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		splash.draw(batch);
		batch.end();
		
		if(Gdx.input.isTouched())
		{
			game.playGame();
		}
>>>>>>> remotes/origin/gustav-wip:projectWood/src/com/dat255/Wood/screens/SplashScreen.java
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		batch = new SpriteBatch();
<<<<<<< HEAD:projectWood/src/com/dat255/Wood/screens/Splash.java
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		Texture splashTexture = new Texture("splash/splash.png");
=======
		
		Texture splashTexture = new Texture("data/images/Splash.png");
>>>>>>> remotes/origin/gustav-wip:projectWood/src/com/dat255/Wood/screens/SplashScreen.java
		//Texture.setEnforcePotImages(false);
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1, 2).setCallback(new TweenCallback(){

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu(game));
			}
			
		}).start(tweenManager);
		

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
		batch.dispose();
		splash.getTexture().dispose();
	}

}