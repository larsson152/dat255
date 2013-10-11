package com.dat255.Wood.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.utils.ArrayMap;
import com.dat255.Wood.model.HighScore;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class GameServer {

	private Server server;
	private ArrayMap<String, Integer> scoreMap;
	private ArrayList<HighScore> scoreList;

	public GameServer() throws IOException{
		scoreMap = new ArrayMap<String,Integer>(1000);
		scoreList = new ArrayList<>();
		server = new Server();
		registerPackets();
		NetworkListener nl = new NetworkListener();
		nl.init(this);
		server.addListener(nl);
		server.bind(1337);
		server.start();
	}

	private void registerPackets(){
		Kryo kryo = server.getKryo();
		kryo.register(HighScore.class);
		kryo.register(ArrayList.class);
		//kryo.register(Object[].class);
	}
	
	public Server getServer(){
		return server;
	}

	public ArrayMap<String,Integer> getScoreMap(){
		return scoreMap;
	}

	/*public void addPlayer(String name){
		if(!scoreMap.containsKey(name)){
			scoreMap.put(name, 0);
		}
	}*/
	
	public void addPlayer(HighScore hs){
		scoreList.add(hs);
		Collections.sort(scoreList, new ScoreComparator());
	}

	public void updateScore(HighScore hs){
				for(HighScore score: scoreList){
					if(score.getName().equals(hs.getName())){
						
					}
				}
	}
	
	public static void main(String[] args){
		try {
			new GameServer();
			Log.set(Log.LEVEL_DEBUG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HighScore> getScoreList() {

		return scoreList;
	}

}
