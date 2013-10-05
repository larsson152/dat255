package com.dat255.Wood.server;

import com.dat255.Wood.model.HighScore;
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
		if(o instanceof HighScore){
			
			HighScore hs = (HighScore) o;
			
			if(hs.getLocalScore() == -1){
				gServer.addPlayer(hs.getName());
			}else{
				gServer.updateScore(hs.getName(), hs.getLocalScore());
			}
			
		}else if(o == null){
			gServer.getServer().sendToTCP(c.getID(), gServer.getScoreMap());
		}
	}
}
