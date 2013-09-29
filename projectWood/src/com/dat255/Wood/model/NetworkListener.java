package com.dat255.Wood.model;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {

	private Client client;
	
	public void init(Client client) {
	this.client = client;	
	}
	
	public void connected(Connection arg0) {
		System.out.println("[CLIENT] You have connected.");
	}

	public void disconnected(Connection arg0) {
		System.out.println("[CLIENT] You have disconnected.");
	}

	public void received(Connection c, Object o) {
		if(o instanceof Player){
			System.out.println("[CLIENT] Player was successfully sent and recieved!");
			Gdx.app.exit();
		}else{
			System.out.println("[CLIENT] Something went wrong, no player was recieved.");
			Gdx.app.exit();
		}
	}
}
