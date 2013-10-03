package com.dat255.Wood.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dat255.Wood.model.Block;
import com.dat255.Wood.model.Level;
import com.dat255.Wood.model.Player;
import com.dat255.Wood.model.Player.FacingDirection;
import com.dat255.Wood.model.Player.State;

public class LevelRenderer {

	//CONSTANTS
	//private static final float CAMERA_WIDTH = 9f;
	//private static final float CAMERA_HEIGHT = 16f;
	private static final float RUNNING_FRAME_DURATION = 0.125f;

	private Level level;
	private OrthographicCamera orthoCamera;

	//Debug renderer
	ShapeRenderer debugRenderer = new ShapeRenderer();

	//Textures
	private TextureRegion playerIdleLeft;
	private TextureRegion playerIdleUp;
	private TextureRegion playerIdleDown;
	private TextureRegion playerIdleRight;
	private TextureRegion playerFrame;

	private Animation walkLeftAnimation;
	private Animation walkUpAnimation;
	private Animation walkDownAnimation;
	private Animation walkRightAnimation;

	private AtlasRegion playerLeft;
	private AtlasRegion playerRight;
	private AtlasRegion playerUp;
	private AtlasRegion playerDown;
	private AtlasRegion wallBlockTexture;
	private AtlasRegion pushBlockTexture;

	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private AtlasRegion lavaBlockTexture;
	private AtlasRegion iceBlockTexture;
	private AtlasRegion waterBlockTexture;
	private AtlasRegion sandBlockTexture;
	private AtlasRegion goalBlockTexture;
	private AtlasRegion buttonOnTexture;
	private AtlasRegion ButtonOffTexture;
	private AtlasRegion horizontalDoorOpenTexture;
	private AtlasRegion horizontalDoorClosedTexture;
	private AtlasRegion verticalDoorOpenTexture;
	private AtlasRegion VerticalDoorClosedTexture;

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
						spriteBatch.draw(wallBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 2)
					{
						spriteBatch.draw(pushBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
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
			playerFrame = playerIdleLeft;
		}
		else if(player.direction == FacingDirection.RIGHT)
		{
			playerFrame = playerIdleRight;
		}
		else if(player.direction == FacingDirection.UP)
		{
			playerFrame = playerIdleUp;
		}
		else if(player.direction == FacingDirection.DOWN)
		{
			playerFrame = playerIdleDown;
		}

		if(player.getState() == State.WALKING) 
		{
			if( player.direction == FacingDirection.LEFT)
			{
				playerFrame = walkLeftAnimation.getKeyFrame(player.getStateTime(), true);
				walkLeftAnimation.getKeyFrame(player.getStateTime(), true);
			}
			else if( player.direction == FacingDirection.RIGHT)
			{
				playerFrame = walkRightAnimation.getKeyFrame(player.getStateTime(), true);
				walkRightAnimation.getKeyFrame(player.getStateTime(), true);
			}
			else if( player.direction == FacingDirection.DOWN)
			{
				playerFrame = walkDownAnimation.getKeyFrame(player.getStateTime(), true);
				walkDownAnimation.getKeyFrame(player.getStateTime(), true);
			}
			else if( player.direction == FacingDirection.UP)
			{
				playerFrame = walkUpAnimation.getKeyFrame(player.getStateTime(), true);
				walkUpAnimation.getKeyFrame(player.getStateTime(), true);
			}
		}
		spriteBatch.draw(playerFrame, player.getPosition().x, player.getPosition().y, Player.SIZE, Player.SIZE);
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
		wallBlockTexture = atlas.findRegion("WallBlock_64x64");
		pushBlockTexture = atlas.findRegion("PushBlock_64x64");
		lavaBlockTexture = atlas.findRegion("Lava_64x64");
		iceBlockTexture = atlas.findRegion("Ice_64x64");
		waterBlockTexture = atlas.findRegion("Water_64x64");
		sandBlockTexture = atlas.findRegion("Sand_64x64");
		goalBlockTexture = atlas.findRegion("Goal_64x64");
		buttonOnTexture = atlas.findRegion("Button_On_64x64");
		ButtonOffTexture = atlas.findRegion("Button_Off_64x64");
		horizontalDoorOpenTexture = atlas.findRegion("Horizontal_Door_Open_64x64");
		horizontalDoorClosedTexture = atlas.findRegion("Horizontal_Door_Closed_64x64");
		verticalDoorOpenTexture = atlas.findRegion("Vertical_Door_Open_64x64");
		VerticalDoorClosedTexture = atlas.findRegion("Vertical_Door_Closed_64x64");

		TextureAtlas atlasAnimation = new TextureAtlas(Gdx.files.internal("data/images/animchar/animchar.txt"));
		playerIdleLeft = atlasAnimation.findRegion("left-idle");
		playerIdleRight = new TextureRegion(playerIdleLeft);
		playerIdleRight.flip(true, false);
		playerIdleDown = atlasAnimation.findRegion("down-idle");
		playerIdleUp = new TextureRegion(playerIdleDown);
		playerIdleUp.flip(false, true);

		TextureRegion[] walkLeftFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			walkLeftFrames[i] = atlasAnimation.findRegion("left-move-" + (i + 1));
		}
		walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);

		TextureRegion[] walkRightFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);

		TextureRegion[] walkDownFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			walkDownFrames[i] = atlasAnimation.findRegion("down-move-" + (i + 1));
		}
		walkDownAnimation = new Animation(RUNNING_FRAME_DURATION, walkDownFrames);

		TextureRegion[] walkUpFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			walkUpFrames[i] = new TextureRegion(walkDownFrames[i]);
			walkUpFrames[i].flip(false, true);
		}
		walkUpAnimation = new Animation(RUNNING_FRAME_DURATION, walkUpFrames);
	}


}
