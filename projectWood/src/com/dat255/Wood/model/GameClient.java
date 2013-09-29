package com.dat255.Wood.model;

import java.io.IOException;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class GameClient {

	public Client client;
	
	public GameClient(){
		client = new Client();
		registerPackets();
		NetworkListener nl = new NetworkListener();
		nl.init(client);
		client.addListener(nl);

		client.start();

		
		try {
			System.out.println("Trying to connect!");
			//client.connect(50000, "192.168.1.132", 1337);
			client.connect(5000, "192.168.1.132", 1337);
			System.out.println("Connected!");
			client.sendTCP(new Player(new Vector2(4,2)));
			System.out.println("Player sent!");
		} catch (IOException e) {
			System.out.println("Could not connect :(");
			e.printStackTrace();
			client.stop();

			
		}
		
	}
	
	private void registerPackets(){
		Kryo kryo = client.getKryo();
		kryo.register(Player.class);
		kryo.register(Vector2.class);
		kryo.register(Rectangle.class);
		kryo.register(Player.FacingDirection.class);
		kryo.register(Player.State.class);
	}
}
