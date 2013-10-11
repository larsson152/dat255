package com.dat255.Wood.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * @author Lucas
 * This class plays music and sounds in the game.
 */


/**
 * @author Henrik
 *
 */
public class SoundHandler {
	private static Sound pickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bell.wav"));
	private static Music bckMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/vaxeln_halla.wav"));
	private static Sound waterSound = Gdx.audio.newSound(Gdx.files.internal("sounds/water.wav"));
	private static Sound unlockSound = Gdx.audio.newSound(Gdx.files.internal("sounds/unlock.wav"));
	private static Sound teleportSound = Gdx.audio.newSound(Gdx.files.internal("sounds/teleport.wav"));
	private static Sound applauseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/applause.wav"));
	
	public SoundHandler(){
		
		
	}
	
	/**
	 * This method starts the background music and sets it looping.
	 */
	
	public static void setUpMusic(){
		
		bckMusic.setLooping(true);
		bckMusic.setVolume(0.5f);
		bckMusic.play();
	}
	
	public static void stopBackgroundMusic(){
		bckMusic.stop();
	}
	
	/**
	 * This method plays the sound when you pick up something.
	 */
	
	public static void playPick(){
		
		pickSound.play(3.5f);
	}
	
public static void stopPick(){
		
		pickSound.stop();
	}
	
	/**
	 *  Plays a sound of something being dropped in water.
	 */
	public static void playWater() {
		waterSound.play(0.2f);
	}
	
	public static void stopWater() {
		waterSound.stop();
	}
	/**
	 * Plays the sound of unlocking a door.
	 */
	public static void playUnlock() {
		unlockSound.play(3.5f);
	}
	
	public static void stopUnlock() {
		unlockSound.stop();
	}
	
	/**
	 * Plays the sound of something teleporting.
	 */
	public static void playTeleport() {
		teleportSound.play(0.2f);
	}
	
	public static void stopTeleport() {
		teleportSound.stop();
	}
	
	/**
	 * Plays an applause sound when the player wins a level.
	 */
	public static void playApplause() {
		applauseSound.play(1.933f);
	}
	
	public static void stopApplause() {
		applauseSound.stop();
	}
	
	public static void stopAllSound() {
		stopPick();
		stopWater();
		stopUnlock();
		stopTeleport();
		stopApplause();
		stopBackgroundMusic();
	}
	
	public static void dispose(){
		bckMusic.dispose();
		pickSound.dispose();
		waterSound.dispose();
		unlockSound.dispose();
		teleportSound.dispose();
	}

}
