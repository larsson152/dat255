package com.dat255.Wood.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * @author Lucas
 * This class plays music and sounds in the game.
 */


public class soundHandler {
	private static Sound pickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bell.wav"));
	private static Music bckMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/vaxeln_halla.wav"));
	
	public soundHandler(){
		
		
	}
	
	/**
	 * This method starts the background music and sets it looping.
	 */
	
	public static void setUpMusic(){
		
		bckMusic.setLooping(true);
		bckMusic.setVolume(0.5f);
		bckMusic.play();
	}
	
	/**
	 * This method plays the sound when you pick up something.
	 */
	
	public static void playPick(){
		
		pickSound.play(2.0f);
	}
	
	public static void dispose(){
		bckMusic.dispose();
		pickSound.dispose();
	}

}
