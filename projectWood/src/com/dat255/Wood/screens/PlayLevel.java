package com.dat255.Wood.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dat255.Wood.WoodGame;

public class PlayLevel implements Screen {
	
	private WoodGame game;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	public PlayLevel( WoodGame game)
	{
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0,0,0,1); //RGBA
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		renderer.setView(camera);
		renderer.render();

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
		
		TmxMapLoader loader = new TmxMapLoader();
		map =loader.load("data/maps/Wooddebug.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		camera = new OrthographicCamera();

	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();

	}

}