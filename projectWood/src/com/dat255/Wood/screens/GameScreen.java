package com.dat255.Wood.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dat255.Wood.controller.LevelController;
import com.dat255.Wood.model.Level;
import com.dat255.Wood.view.LevelRenderer;


/**
 * This class represents a game screen where a dpad and score
 * display is rendered.
 *
 */
public class GameScreen implements Screen{

	//INSTANCE VARIABLES
	private Level level;
	private LevelRenderer renderer;
	private LevelController controller;
	private Stage stage;
	private Stage pauseStage;
	private int width;
	private int height;
	private TextureAtlas atlas;
	private TextureAtlas pauseAtlas;
	private Skin dpadSkin;
	private Skin pauseSkin;
	private ImageButton buttonUp;
	private ImageButton buttonDown;
	private ImageButton buttonRight;
	private ImageButton buttonCenter;
	private ImageButton buttonLeft;
	private ImageButton pauseButton;
	private boolean paused;
	
	private Music bckMusic;
	
	
	private SpriteBatch scoreBatch;
	private BitmapFont scoreFont;
	
	/**From libgdx wiki:
	*Method called by the game loop from the application every time rendering should be performed.
	*Game logic updates are usually also performed in this method.
	*@param delta Seconds since last frame
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl20.glClearColor(0, 0, 0.2f, 1); //Clears the screen with the color (R,G,B,A)
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(!paused)
		{
			controller.update(delta);
			renderer.render();

			scoreBatch.begin();
			scoreFont.draw(scoreBatch,"Keys: " + level.getPlayer().getNoOfKeys() + " Score: "+ level.getLevelScore(), 25, 100);
			scoreBatch.end();

			stage.act(delta);
			stage.draw();
		}
		else
		{
			renderer.render();
			pauseStage.act(delta);
			pauseStage.draw();
		}
	}

	/**From libgdx wiki:
	*This method is called every time the game screen is re-sized and the game is not in the paused state.
	*It is also called once just after the create() method.
	*The parameters are the new width and height the screen has been resized to in pixels.
	*@param width new width in pixels
	*@param height new height in pixels
	*/
	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	/**From libgdx API documentation:
	 *Called when this screen becomes the current screen for a Game.
	 *The method also sets up the d-pad and scoredisplay. 
	 */
	@Override
	public void show() {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		level = new Level();
		renderer = new LevelRenderer(level, true);
		controller = new LevelController(level);
		
		//Create the skin for the d-pad
		atlas = new TextureAtlas("textures/dpad.txt");
		dpadSkin = new Skin(atlas);
		
		//Set up the score display
		scoreBatch = new SpriteBatch();
		scoreFont = new BitmapFont();
		scoreFont.setColor(2.0f, 2.0f, 1.0f, 1.0f);
		
		//Set up music
		bckMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/vaxeln_halla.wav"));
		bckMusic.setLooping(true);
		bckMusic.setVolume(0.5f);
		bckMusic.play();
		
		//Call the function for adding the d-pad
		addDpad();
		//Add Actors for all direction buttons
		stage.addActor(buttonUp);
		stage.addActor(buttonDown);
		stage.addActor(buttonLeft);
		stage.addActor(buttonRight);
		stage.addActor(buttonCenter);
		
		//PauseScreen stuff
		pauseAtlas = new TextureAtlas("images/pause.pack");
		pauseSkin = new Skin(pauseAtlas);
		pauseStage = new Stage();
		addPauseButton();
		pauseStage.addActor(pauseButton);

	}
	
	private void addPauseButton()
	{
		//Set the visuals
		pauseButton = new ImageButton(pauseSkin.getDrawable("pause_screen"));
		pauseButton.setBackground(pauseSkin.getDrawable("pause_screen"));

		//Set size and position
		//pauseButton.setHeight(Gdx.graphics.getHeight()/4);
		//pauseButton.setWidth(Gdx.graphics.getWidth()/1.2f);
		pauseButton.setX((Gdx.graphics.getWidth()/2) - (pauseButton.getWidth()/2));
		pauseButton.setY(Gdx.graphics.getHeight()/2);

		//Add listener to make it clickable
		pauseButton.addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				paused = false;
				Gdx.input.setInputProcessor(stage);
			}
		});
	}
	
	/**
	 * This method adds the whole d-pad to the screen.
	 */

	private void addDpad() {
		addDpadCenter();
		addDpadLeft();
		addDpadUp();
		addDpadDown();
		addDpadRight();
	}

	private void addDpadDown() {
		//Set the visuals
		buttonDown = new ImageButton(dpadSkin.getDrawable("dpad_down"));
		buttonDown.setBackground(dpadSkin.getDrawable("dpad_down"));
		
		//Set size and position
		buttonDown.setHeight((float)buttonCenter.getHeight());
		buttonDown.setWidth((float)buttonCenter.getWidth());
		buttonDown.setX((float)buttonCenter.getX());
		buttonDown.setY((float)buttonCenter.getY() - buttonCenter.getHeight());
		
		//Add listener to make it clickable
		buttonDown.addListener(new ClickListener(){
			
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
		{
			controller.downPressed();
			return true;
		}
			
		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button)
		{
				controller.downReleased();
		}
		});
	}

	private void addDpadRight() {
		//Set the visuals
		buttonRight = new ImageButton(dpadSkin.getDrawable("dpad_right"));
		buttonRight.setBackground(dpadSkin.getDrawable("dpad_right"));
		
		//Set size and position
		buttonRight.setHeight((float)buttonCenter.getHeight());
		buttonRight.setWidth((float)buttonCenter.getWidth());
		buttonRight.setX((float) buttonCenter.getX() + buttonCenter.getWidth());
		buttonRight.setY((float) buttonCenter.getY());
		
		//Add listener to make it clickable
		buttonRight.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				controller.rightPressed();
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				controller.rightReleased();
			}
		});
	}

	private void addDpadUp() {
		//Set the visuals
		buttonUp = new ImageButton(dpadSkin.getDrawable("dpad_up"));
		buttonUp.setBackground(dpadSkin.getDrawable("dpad_up"));
		
		//Set size and position
		buttonUp.setHeight((float)buttonCenter.getHeight());
		buttonUp.setWidth((float)buttonCenter.getWidth());
		buttonUp.setX((float) buttonCenter.getX());
		buttonUp.setY((float) buttonCenter.getY() + buttonCenter.getHeight());
		
		//Add listener to make it clickable
		buttonUp.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				controller.upPressed();
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				controller.upReleased();
			}
		});
	}

	private void addDpadLeft() {
		//Set the visuals
		buttonLeft = new ImageButton(dpadSkin.getDrawable("dpad_left"));
		buttonLeft.setBackground(dpadSkin.getDrawable("dpad_left"));
		
		//Set size and position
		buttonLeft.setHeight((float)buttonCenter.getHeight());
		buttonLeft.setWidth((float)buttonCenter.getWidth());
		buttonLeft.setX((float) buttonCenter.getX() - buttonCenter.getWidth());
		buttonLeft.setY((float) buttonCenter.getY());
		
		//Add listener to make it clickable
		buttonLeft.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				controller.leftPressed();
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button)
			{
				controller.leftReleased();
			}
		});
	}

	private void addDpadCenter() {
		//Set the visuals
		buttonCenter = new ImageButton(dpadSkin.getDrawable("dpad_center"));
		buttonCenter.setBackground(dpadSkin.getDrawable("dpad_center"));
		
		//Set size and position
		buttonCenter.setHeight((float)70);
		buttonCenter.setWidth((float)70);
		buttonCenter.setX((float)Gdx.graphics.getWidth() - 2*buttonCenter.getWidth());
		buttonCenter.setY((float) buttonCenter.getHeight());
	}
	
	public Level getLevel(){
		
		return level;
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
		Gdx.input.setInputProcessor(pauseStage);
		paused = true;
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

}
