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
	private int score = -1 ;
	private ArrayList<HighScore> topTen;
	private boolean isGetterType = false;
	private WoodGame game;
	
	
	
	public HighScore(WoodGame game){
		this.game = game;
	}
	
	public HighScore(String name){
		this.name = name;		
	}
	
	public HighScore(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	
	
	public void send(){
		isGetterType = false;
		new GameClient().send(this);
	}
	
	public ArrayList<HighScore> getHighScore(){
		isGetterType = true;
		new GameClient().send(this);
		return topTen;
	}
	
	public String getName(){
		return name;
	}

	public void setHighScore(ArrayList<HighScore> topTen) {
		this.topTen = topTen;
	}

	public int getLocalScore() {
		return score;
	}
	
	public boolean isGetter(){
		return isGetterType;
	}
	
	
}
