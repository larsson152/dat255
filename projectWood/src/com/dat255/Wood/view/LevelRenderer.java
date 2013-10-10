package com.dat255.Wood.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.dat255.Wood.model.Block;
import com.dat255.Wood.model.Level;
import com.dat255.Wood.model.Player;
import com.dat255.Wood.model.Player.FacingDirection;
import com.dat255.Wood.model.Player.State;



/**
 * This class renders the level and its objects
 *
 */
public class LevelRenderer {

	//CONSTANTS
	//private static final float CAMERA_WIDTH = 9f;
	//private static final float CAMERA_HEIGHT = 16f;
	private static final float WALKING_FRAME_DURATION = 0.175f;

	private Level level;
	private OrthographicCamera orthoCamera;

	//Debug renderer
	ShapeRenderer debugRenderer = new ShapeRenderer();
	private boolean debug = false;
	
	private int width;
	private int height;
	//Textures
	private TextureRegion idleLeft;
	private TextureRegion idleUp;
	private TextureRegion idleDown;
	private TextureRegion idleRight;
	private TextureRegion playerFrame;

	private Animation leftAnimation;
	private Animation upAnimation;
	private Animation downAnimation;
	private Animation rightAnimation;

	private AtlasRegion wallBlockTexture;
	private AtlasRegion pushBlockTexture;
	private SpriteBatch spriteBatch;
	private AtlasRegion fireBlockTexture;
	private AtlasRegion iceBlockTexture;
	private AtlasRegion waterBlockTexture;
	private AtlasRegion goalBlockTexture;
	private AtlasRegion keyBlockTexture;
	private AtlasRegion keyholeBlockTexture;
	private AtlasRegion yellowTeleportTexture;
	private AtlasRegion blueTeleportTexture;
	private AtlasRegion groundBlockTexture;
	private AtlasRegion emptyBlockTexture;
	
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

	/**
	 * Renders the level. Draws blocks, draws the player and sets up the camera
	 */
	public void render()
	{
		spriteBatch.setProjectionMatrix(orthoCamera.combined);
		spriteBatch.begin();
		drawBlocks();
		drawPlayer();
		spriteBatch.end();
		this.orthoCamera.position.set(level.getPlayer().getPosition().x, level.getPlayer().getPosition().y, 0);
		orthoCamera.update();
	}


	/**
	 * Helpmethod that draws the blocks
	 */
	private void drawBlocks()
	{
		Block[][] groundLayer = level.getGroundLayer();
		Block[][] collisionLayer = level.getCollisionLayer();
		Block block = null;


		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				block = groundLayer[i][j];
				if(block != null)
				{
					if(block.getBlockId() == '0')
					{
						spriteBatch.draw(groundBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == '3')
					{
						spriteBatch.draw(waterBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == '4')
					{
						spriteBatch.draw(fireBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == '6')
					{
						spriteBatch.draw(iceBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 'K')
					{
						spriteBatch.draw(keyBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 'H')
					{
						spriteBatch.draw(keyholeBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 'T')
					{
						spriteBatch.draw(blueTeleportTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 't')
					{
						spriteBatch.draw(yellowTeleportTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 'G')
					{
						spriteBatch.draw(goalBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
				}
			}
		}
		
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				block = collisionLayer[i][j];
				if(block != null)
				{
					if(block.getBlockId() == '0')
					{
						spriteBatch.draw(emptyBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == '1')
					{
						spriteBatch.draw(wallBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == '2')
					{
						spriteBatch.draw(pushBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 'K')
					{
						spriteBatch.draw(keyBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 'H')
					{
						spriteBatch.draw(keyholeBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
				}
			}
		}
	}

	/**
	 * Helpmethod that draws the player
	 */

	private void drawPlayer()
	{

		Player player = level.getPlayer();

		if(player.direction == FacingDirection.LEFT)
		{
			playerFrame = idleLeft;
		}
		else if(player.direction == FacingDirection.RIGHT)
		{
			playerFrame = idleRight;
		}
		else if(player.direction == FacingDirection.UP)
		{
			playerFrame = idleUp;
		}
		else if(player.direction == FacingDirection.DOWN)
		{
			playerFrame = idleDown;
		}

		if(player.getState() == State.WALKING) 
		{
			if( player.direction == FacingDirection.LEFT)
			{
				playerFrame = leftAnimation.getKeyFrame(player.getStateTime(), true);
				leftAnimation.getKeyFrame(player.getStateTime(), true);
			}
			else if( player.direction == FacingDirection.RIGHT)
			{
				playerFrame = rightAnimation.getKeyFrame(player.getStateTime(), true);
				rightAnimation.getKeyFrame(player.getStateTime(), true);
			}
			else if( player.direction == FacingDirection.DOWN)
			{
				playerFrame = downAnimation.getKeyFrame(player.getStateTime(), true);
				downAnimation.getKeyFrame(player.getStateTime(), true);
			}
			else if( player.direction == FacingDirection.UP)
			{
				playerFrame = upAnimation.getKeyFrame(player.getStateTime(), true);
				upAnimation.getKeyFrame(player.getStateTime(), true);
			}
		}
		spriteBatch.draw(playerFrame, player.getPosition().x, player.getPosition().y, Player.SIZE, Player.SIZE);
	}

	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	/**
	 * Method that loads the textures.
	 */

	private void loadTextures()
	{
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/textures.pack"));
		wallBlockTexture = atlas.findRegion("wall");
		pushBlockTexture = atlas.findRegion("pushBlock");
		iceBlockTexture = atlas.findRegion("ice");
		waterBlockTexture = atlas.findRegion("water");
		goalBlockTexture = atlas.findRegion("finish");
		keyBlockTexture = atlas.findRegion("key");
		keyholeBlockTexture = atlas.findRegion("lock");
		yellowTeleportTexture = atlas.findRegion("yellowTeleport");
		blueTeleportTexture = atlas.findRegion("blueTeleport");
		groundBlockTexture = atlas.findRegion("ground");
		fireBlockTexture = atlas.findRegion("fire");
		emptyBlockTexture = atlas.findRegion("empty");
		
		//Character textures
		TextureAtlas atlasAnimation = new TextureAtlas(Gdx.files.internal("textures/animchar.pack"));
		idleLeft = atlasAnimation.findRegion("left-idle");
		idleRight = new TextureRegion(idleLeft);
		idleRight.flip(true, false);
		idleDown = atlasAnimation.findRegion("down-idle");
		idleUp = atlasAnimation.findRegion("up-idle");

		//Add textures for every step in the animation for each direction
		TextureRegion[] walkLeftFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			walkLeftFrames[i] = atlasAnimation.findRegion("left-move-" + (i + 1));
		}
		leftAnimation = new Animation(WALKING_FRAME_DURATION, walkLeftFrames);

		TextureRegion[] walkRightFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		rightAnimation = new Animation(WALKING_FRAME_DURATION, walkRightFrames);

		TextureRegion[] walkDownFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			walkDownFrames[i] = atlasAnimation.findRegion("down-move-" + (i + 1));
		}
		downAnimation = new Animation(WALKING_FRAME_DURATION, walkDownFrames);

		TextureRegion[] walkUpFrames = new TextureRegion[2];
		for (int i = 0; i < 2; i++) {
			walkUpFrames[i] = atlasAnimation.findRegion("up-move-" + (i + 1));
		}
		upAnimation = new Animation(WALKING_FRAME_DURATION, walkUpFrames);
	}
}
