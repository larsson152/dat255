package com.dat255.Wood.server;

import com.dat255.Wood.model.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {
	
	GameServer gServer;
	
	public void init(GameServer gServer){
		this.gServer = gServer;
	}
	
	public void connected(Connection arg0) {
		System.out.println("[SERVER] Someone connected!");
	}

	public void disconnected(Connection arg0) {
		System.out.println("[SERVER] Someone has disconnected.");
	}

	public void received(Connection c, Object o) {
		if(o instanceof Player){
			gServer.addNewUser((Player)o);
		}else if(o == null){
			gServer.getServer().sendToTCP(c.getID(), gServer.getHighscore());
		}
	}
}
