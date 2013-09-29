package com.dat255.Wood.server;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dat255.Wood.model.Player;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class GameServer {

	private Server server;
	private HashMap<String, Player> users;
	private HashMap<String, Integer> highscore;

	public GameServer() throws IOException{
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
	
	public void addNewUser(Player player){
		users.put(player.getName(), player);
	}
	
	public HashMap<String, Player> getUsers(){
		return users;
	}
	
	public Server getServer(){
		return this.server;
	}
	
	public Player find(String name){
		if(users.containsKey(name)){
			return users.get(name);
		}else{
			return null;
		}
	}
	
	public HashMap<String, Integer> getHighscore(String name){
		
		
		
		
		return null;
	}
	
	

}
