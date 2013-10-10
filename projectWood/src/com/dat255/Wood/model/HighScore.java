package com.dat255.Wood.model;

import java.io.Serializable;

import com.badlogic.gdx.utils.ArrayMap;

public class HighScore implements Serializable {

	private String name = null;
	private int score = -1 ;
	private ArrayMap<String, Integer> topTen;
	private boolean isGetterType = false;
	
	
	
	
	public HighScore(){
		
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
	
	public ArrayMap<String, Integer> getHighScore(){
		isGetterType = true;
		new GameClient().send(this);
		return topTen;
	}
	
	public String getName(){
		return name;
	}

	public void setHighScore(ArrayMap<String, Integer> topTen) {
		this.topTen = topTen;
	}

	public int getLocalScore() {
		return score;
	}
	
	public boolean isGetter(){
		return isGetterType;
	}
	
	
}
