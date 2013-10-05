package com.dat255.Wood.model;

import com.badlogic.gdx.utils.ArrayMap;

public class HighScore {

	private String name = null;
	private int score = -1 ;
	private ArrayMap<String, Integer> topTen;
	
	public HighScore(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	public HighScore(String name){
		this.name = name;		
	}
	
	public void sendHighScore(){
		new GameClient().send(this);			
	}
	
	public int getServerScore(String name){
		return 0;
	}
	
	public int getLocalScore(){
		return score;
	}
	
	public ArrayMap<String, Integer> getHighScore(){
		new GameClient().send(null);
		return topTen;
	}
	
	public void setHighScore(ArrayMap<String, Integer> topTen){
		this.topTen = topTen;
	}
	
	public String getName(){
		return name;
	}
}
