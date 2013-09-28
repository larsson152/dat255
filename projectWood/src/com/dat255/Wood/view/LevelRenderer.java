package com.dat255.Wood.view;

import com.dat255.Wood.model.Block;
import com.dat255.Wood.model.Level;
import com.dat255.Wood.model.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class LevelRenderer {
	
	//CONSTANTS
	private static final float CAMERA_WIDTH = 16f;
	private static final float CAMERA_HEIGHT = 9f;
	
	private Level level;
	private OrthographicCamera orthoCamera;
	
	//Debug renderer
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	//Textures
	private Texture playerTexture;
	private Texture blockTexture;
	
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;
	private float ppuY;
	
	public LevelRenderer(Level level, boolean debug)
	{
		this.level = level;
		this.orthoCamera = new OrthographicCamera(16,9); //Creates an OrthographicCamera with (float viewportWidth, float viewportHeight)
		this.orthoCamera.position.set(8, 4.5f, 0); //Sets the cameras position (float x,float y, float z)
		this.orthoCamera.update(); //From libgdx API: Recalculates the projection and view matrix of this camera and the Frustum planes.
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	public void render()
	{
		spriteBatch.begin();
		drawBlocks();
		drawPlayer();
		spriteBatch.end();
		if(debug == true)
		{
			drawDebug();
		}
	}
	
	private void drawDebug()
	{
		debugRenderer.setProjectionMatrix(orthoCamera.combined);
		debugRenderer.begin(ShapeType.Line);
		for(Block block : level.getBlocks())
		{
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		
		Player player = level.getPlayer();
		Rectangle rect = player.getBounds();
		float x1 = player.getPosition().x + rect.x;
		float y1 = player.getPosition().y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
	
	private void drawBlocks()
	{
		for(Block block : level.getBlocks())
			spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
	}
	
	private void drawPlayer()
	{
		Player player = level.getPlayer();
		spriteBatch.draw(playerTexture, player.getPosition().x * ppuX, player.getPosition().y * ppuY, Player.SIZE * ppuX, Player.SIZE * ppuY);
	}
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		ppuX = (float) width / CAMERA_WIDTH;
		ppuY = (float) height / CAMERA_HEIGHT;
	}
	
	private void loadTextures()
	{
		playerTexture = new Texture(Gdx.files.internal("data/images/Red_64x64.png"));
		blockTexture = new Texture(Gdx.files.internal("data/images/Block_64x64.png"));
	}
	

}
