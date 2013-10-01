package com.dat255.Wood.model;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class GameClient {

	private Client client; 
	private Player player;

	public GameClient(Player player){
		this.player = player;
		client = new Client(); // The Client is used for handling communication with the Server
		registerPackets();	// Used to register all the classes that will be sent over network
		NetworkListener nl = new NetworkListener(); // Methods on this object is called when something is recieved or transmitted
		nl.init(this);	// Gives the listener access to the client for future use
		client.addListener(nl);

		client.start();	
	}

	private void registerPackets(){
		Kryo kryo = client.getKryo();
		kryo.register(Player.class);
		kryo.register(Vector2.class);
		kryo.register(Rectangle.class);
		kryo.register(Player.FacingDirection.class);
		kryo.register(Player.State.class);
	}

	public Client getClient(){
		return client;
	}

	public Player getPlayer(){
		return player;
	}

	public void addPlayer(Player player){

		//Trying to connect to the server
		//NOTE! IP address has to be changed when testing the server!
		try {
			System.out.println("Trying to connect!");
			client.connect(5000, "192.168.1.132", 1337);	//NOTE! IP address has to be changed when testing the server!
			System.out.println("Connected!");
			client.sendTCP(player);
		} catch (IOException e) {
			System.out.println("Could not connect :(");
			e.printStackTrace();
			client.stop();
		}
	}
	
	public HashMap<String, Integer> getHighscore(){
		return null;
	}
}
