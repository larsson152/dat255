package com.dat255.Wood.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.dat255.Wood.WoodGame;
import com.esotericsoftware.tablelayout.Cell;


/**
 * Hold a stage with all the actors on the screen. E.g Play-button and Exit-button.
 * @author Patrik Larsson
 *
 */
public class MainMenu implements Screen {

	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private TextButton buttonPlay, buttonExit;
	private BitmapFont white;
	private WoodGame game;
	private Sprite backgroundSprite;
	private SpriteDrawable backgroundSpriteDraw;


	public MainMenu(WoodGame game){
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();


	}

	@Override
	public void resize(int width, int height) {


	}

	@Override
	public void show() {
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("textures/button.pack");
		skin = new Skin(atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		white = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), false);

		TextButtonStyle exitTextButtonStyle = new TextButtonStyle();
		exitTextButtonStyle.up = skin.getDrawable("buttonExit.up");
		exitTextButtonStyle.down = skin.getDrawable("buttonExit.down");
		exitTextButtonStyle.font = white;

		buttonExit = new TextButton(null, exitTextButtonStyle);
		buttonExit.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}

		});

		backgroundSprite = new Sprite(new Texture("images/MenuBackground.png"));
		backgroundSpriteDraw = new SpriteDrawable(backgroundSprite);

		TextButtonStyle playTextButtonStyle = new TextButtonStyle();
		playTextButtonStyle.up = skin.getDrawable("buttonPlay.up");
		playTextButtonStyle.down = skin.getDrawable("buttonPlay.down");
		playTextButtonStyle.font = white;

		buttonPlay = new TextButton(null, playTextButtonStyle);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new UserNameScreen(game));
			}
		});

		table.setBackground(backgroundSpriteDraw);
		table.add(buttonPlay);
		table.add(buttonExit);
		for(Cell tempCell : table.getCells())
		{
			tempCell.height(Gdx.graphics.getWidth()/(table.getCells().size() + 1));
			tempCell.width(Gdx.graphics.getWidth()/(table.getCells().size() + 1));
			tempCell.space(10f);
		}
		table.validate();
		stage.addActor(table);
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
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		white.dispose();
	}

}
