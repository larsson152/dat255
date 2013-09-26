package com.dat255.Wood.view;

import com.dat255.Wood.model.Block;
import com.dat255.Wood.model.Level;
import com.dat255.Wood.model.Player;
import com.dat255.Wood.model.Player.FacingDirection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class LevelRenderer {
	
	//CONSTANTS
	private static final float CAMERA_WIDTH = 9f;
	private static final float CAMERA_HEIGHT = 16f;
	
	private Level level;
	private OrthographicCamera orthoCamera;
	
	//Debug renderer
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	//Textures
	private AtlasRegion playerLeft;
	private AtlasRegion playerRight;
	private AtlasRegion playerUp;
	private AtlasRegion playerDown;
	private AtlasRegion blockTexture;
	
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	
	public LevelRenderer(Level level, boolean debug)
	{
		this.level = level;
		this.orthoCamera = new OrthographicCamera(9,16); //Creates an OrthographicCamera with (float viewportWidth, float viewportHeight)
		//this.orthoCamera.position.set(4.5f, 8, 0); //Sets the cameras position (float x,float y, float z)
		this.orthoCamera.update(); //From libgdx API: Recalculates the projection and view matrix of this camera and the Frustum planes.
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	public void render()
	{
		spriteBatch.setProjectionMatrix(orthoCamera.combined);
		spriteBatch.begin();
		drawBlocks();
		drawPlayer();
		spriteBatch.end();
		this.orthoCamera.position.set(level.getPlayer().getPosition().x, level.getPlayer().getPosition().y, 0);
		orthoCamera.update();
		if(debug == true)
		{
			//drawDebug();
		}
	}
	
	/*
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
	}*/
	
	private void drawBlocks()
	{
		Block[][] blocks = level.getBlocks();
		Block block = null;
		
		
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				block = blocks[i][j];
				if(block != null)
				{
					if(block.getBlockId() == 1)
					{
						spriteBatch.draw(blockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 0)
					{
						//Rita inget aka tomt block.
					}
				}
			}
		}
	}
	
	private void drawPlayer()
	{
		Player player = level.getPlayer();
		if(player.direction == FacingDirection.LEFT)
		{
			spriteBatch.draw(playerLeft, player.getPosition().x, player.getPosition().y, Player.SIZE, Player.SIZE);
		}
		else if(player.direction == FacingDirection.RIGHT)
		{
			spriteBatch.draw(playerRight, player.getPosition().x, player.getPosition().y, Player.SIZE, Player.SIZE);
		}
		else if(player.direction == FacingDirection.UP)
		{
			spriteBatch.draw(playerUp, player.getPosition().x, player.getPosition().y, Player.SIZE, Player.SIZE);
		}
		else if(player.direction == FacingDirection.DOWN)
		{
			spriteBatch.draw(playerDown, player.getPosition().x, player.getPosition().y, Player.SIZE, Player.SIZE);
		}
	}
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	private void loadTextures()
	{
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/images/textures.pack"));
		playerLeft = atlas.findRegion("Red_LEFT_64x64");
		playerRight = atlas.findRegion("Red_RIGHT_64x64");
		playerUp = atlas.findRegion("Red_UP_64x64");
		playerDown = atlas.findRegion("Red_DOWN_64x64");
		blockTexture = atlas.findRegion("Block_64x64");
	}
	

}
