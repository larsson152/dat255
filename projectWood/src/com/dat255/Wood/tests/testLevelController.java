package com.dat255.Wood.tests;

import com.badlogic.gdx.math.Vector2;
import com.dat255.Wood.controller.LevelController;
import com.dat255.Wood.model.Block;
import com.dat255.Wood.model.Level;

import junit.framework.TestCase;

public class testLevelController extends TestCase 
{
	public void testMoveToRight()
	{
		Level level = new Level();
		LevelController controller = new LevelController(level);
		
		controller.rightPressed();
		controller.rightReleased();
		
		System.out.println("" + level.getPlayer().getPosition().x);
		assertEquals(2, level.getPlayer().getPosition().x);
	}
	 public void testBlockSwitch(){
		 
		 Level level = new Level();
		 Block block1= new Block(new Vector2(0,0), 0);
		 Block block2= new Block(new Vector2(1,1), 1);
		 level.getBlocks()[0][0]=block1;
		 level.getBlocks()[1][1]=block2;
		
		 
		 level.switchCollisionBlocks((int) block1.getPosition().x,(int) block1.getPosition().y,
				 (int) block2.getPosition().x,(int) block2.getPosition().y);
		 assertEquals(1,level.getBlocks()[0][0].getBlockId());
		 
	 }
	 
	 
public void testBlockID(){
		 
		 
		 Block block= new Block(new Vector2(0,0), 1);

		 assertEquals(1,block.getBlockId());
		 
	 }

public void testBlockPosX(){
	 
	 
	 Block block= new Block(new Vector2(1,3), 1);

	 assertEquals(1,(int)block.getPosition().x);
	 
}

public void testBlockPosY(){
	 
	 
	 Block block= new Block(new Vector2(1,3), 1);


	 assertEquals(3,(int)block.getPosition().y);
	 
}


}
