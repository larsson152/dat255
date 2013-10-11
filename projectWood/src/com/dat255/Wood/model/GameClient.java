package com.dat255.Wood.model;

import java.io.IOException;
import java.util.ArrayList;

import com.dat255.Wood.WoodGame;
import com.dat255.Wood.controller.NetworkListener;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

/**
 * Handles the connections with server and sends object to server
 * @author Patrik Larsson
 *
 */
public class GameClient {

	private Client client; 
	private HighScore hs;
	
	public GameClient(){
		client = new Client(); // The Client is used for handling communication with the Server
		registerPackets();	// Used to register all the classes that will be sent over network
		NetworkListener nl = new NetworkListener(); // Methods on this object is called when something is recieved or transmitted
		nl.init(this,hs);	// Gives the listener access to the client for future use
		client.addListener(nl);
		client.start();
	}

	public GameClient(HighScore hs){
		this.hs = hs;
		client = new Client(); // The Client is used for handling communication with the Server
		registerPackets();	// Used to register all the classes that will be sent over network
		NetworkListener nl = new NetworkListener(); // Methods on this object is called when something is recieved or transmitted
		nl.init(this,hs);	// Gives the listener access to the client for future use
		client.addListener(nl);
		client.start();	
	}

	private void registerPackets(){
		Kryo kryo = client.getKryo();
		kryo.register(HighScore.class);
		kryo.register(ArrayList.class);
		//kryo.register(Object[].class);
	}
	
	public HighScore getHighScore(){
		return hs;
	}

	public void send(HighScore hs){
		//Trying to connect to the server
		//NOTE! IP address has to be changed when testing the server!
		try {
			System.out.println("Trying to connect!");
			client.connect(5000, "192.168.1.132", 1337);	//NOTE! IP address has to be changed when testing the server!
			System.out.println("Connected!");
		} catch (IOException e) {
			System.out.println("Could not connect :(");
			e.printStackTrace();
			client.stop();
		}
	}

	public void addPlayer(String name){


	}

	public Client getClient() {

		return client;
	}
}
