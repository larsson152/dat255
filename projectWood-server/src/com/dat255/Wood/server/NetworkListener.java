package com.dat255.Wood.server;

import com.dat255.Wood.model.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class NetworkListener extends Listener {
	
	Server Server;
	
	public void init(GameServer gServer){
		this.Server = gServer.getServer();
	}
	
	public void connected(Connection arg0) {
		Log.info("[SERVER] Someone has connected.");
		System.out.println("[SERVER] Someone connected!");
	}

	public void disconnected(Connection arg0) {
		System.out.println("[SERVER] Someone has disconnected.");
	}

	public void received(Connection c, Object o) {
		if(o instanceof Player){
			System.out.println("[SERVER] Player recieved!");
			
		}else{
			System.out.println("[SERVER] Something went wrong, no player recieved.");
		}
	}
}
