package com.dat255.Wood.model;

import java.util.HashMap;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {

	private GameClient gClient;
	
	public void init(GameClient gClient) {
	this.gClient = gClient;	
	}
	
	public void connected(Connection arg0) {
		System.out.println("[CLIENT] You are now connected.");
	}

	public void disconnected(Connection arg0) {
		System.out.println("[CLIENT] You are now disconnected.");
	}

	public void received(Connection c, Object o) {
		if(o instanceof Player){
			System.out.println("[CLIENT] Player was successfully sent and recieved!");
		}else if (o instanceof HashMap){
			System.out.println("[CLIENT] Recieved High Score.");
		}
	}
}
