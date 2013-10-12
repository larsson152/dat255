package com.dat255.Wood.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.dat255.Wood.WoodGame;

/**
 * Stores users name and score and can send it to a server
 * @author Patrik Larsson
 *
 */
public class HighScore implements Serializable {

	private String name = null;
	private long score = -1 ;
	private boolean isGetterType = false;
	//private WoodGame game;
	
	
	public HighScore() {
	
	}
	
	public HighScore(String name){
		this.name = name;		
	}
	
	public HighScore(String name, long score){

		this.name = name;
		this.score = score;
	}
		
	public void send(){
		isGetterType = false;
		new GameClient(this).send(this);
	}
	
	public void updateScorelist(){
		System.out.println("Updating score list...");
		isGetterType = true;
		new GameClient(this).send(this);
	}
		
	public String getName(){
		return name;
	}

	public long getLocalScore() {
		return score;
	}
	
	public boolean isGetter(){
		return isGetterType;
	}
	
	
}
