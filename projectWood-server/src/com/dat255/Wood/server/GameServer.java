package com.dat255.Wood.server;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;
import com.dat255.Wood.model.Player;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class GameServer {

	private Server server;
	private ArrayMap<String, Integer> scoreMap;

	public GameServer() throws IOException{
		scoreMap = new ArrayMap<String,Integer>(1000);
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
		kryo.register(Player.class);
		kryo.register(Vector2.class);
		kryo.register(Rectangle.class);
		kryo.register(Player.FacingDirection.class);
		kryo.register(Player.State.class);
		kryo.register(HashMap.class);
	}

	public static void main(String[] args){
		try {
			new GameServer();
			Log.set(Log.LEVEL_DEBUG);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Server getServer(){
		return server;
	}

	public ArrayMap<String,Integer> getScoreMap(){
		return scoreMap;
	}

	public void addPlayer(String name){
		if(!scoreMap.containsKey(name)){
			scoreMap.put(name, 0);
		}
	}

	public void updateScore(String name, int score){
		scoreMap.put(name, score);		
	}

}
