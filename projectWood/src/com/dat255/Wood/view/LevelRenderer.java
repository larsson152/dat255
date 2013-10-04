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
	private AtlasRegion keyBlockTexture;
	private AtlasRegion keyholeBlockTexture;
	private AtlasRegion greenTeleportTexture;
	private AtlasRegion redTeleportTexture;
	private AtlasRegion emptyBlockTexture;

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

	/**
	 * Helpmethod that draws the blocks
	 */
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
					else if(block.getBlockId() == '3')
					{
						spriteBatch.draw(waterBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == '4')
					{
						spriteBatch.draw(lavaBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == '5')
					{
						spriteBatch.draw(sandBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
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
						spriteBatch.draw(greenTeleportTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}
					else if(block.getBlockId() == 't')
					{
						spriteBatch.draw(redTeleportTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
					}

					else if(block.getBlockId() == 'G')
					{
						spriteBatch.draw(goalBlockTexture, block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
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
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/images/textures.pack"));
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
		keyBlockTexture = atlas.findRegion("Key_64x64");
		keyholeBlockTexture = atlas.findRegion("Keyhole_64x64");
		greenTeleportTexture = atlas.findRegion("Green_Teleport_64x64");
		redTeleportTexture = atlas.findRegion("Red_Teleport_64x64");
		emptyBlockTexture = atlas.findRegion("EmptyBlock_64x64");


		TextureAtlas atlasAnimation = new TextureAtlas(Gdx.files.internal("data/images/animchar/animchar.pack"));
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
