package com.dat255.Wood.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dat255.Wood.WoodGame;
import com.dat255.Wood.entities.Player;

public class PlayLevel implements Screen {
	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	private WoodGame game;
	
	public PlayLevel(WoodGame game)
	{
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(camera);
		renderer.render();
		
		renderer.getSpriteBatch().begin();   //Renders the player
		player.draw(renderer.getSpriteBatch());
		camera.position.x = player.getX();
		camera.position.y = player.getY();
		camera.update();
		renderer.getSpriteBatch().end();

	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight= height;
		camera.viewportWidth= width;
		
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		camera.position.x = layer.getWidth() * layer.getTileWidth() / 2;
		camera.position.y = layer.getHeight() * layer.getTileHeight() / 2;
		
		camera.update();

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		TmxMapLoader loader = new TmxMapLoader();
		map =loader.load("data/maps/Wooddebug.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();
		
		player = new Player(new Sprite(new Texture("data/entities/Hero.png")),(TiledMapTileLayer) map.getLayers().get(0));
		
		//Sets player starting position.
		player.setPosition(9 * player.getCollisionLayer().getTileWidth(), 10 * player.getCollisionLayer().getTileHeight());
	
		Gdx.input.setInputProcessor(player);
	}
	

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.getTexture().dispose();

	}

}