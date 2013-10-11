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
			System.out.println("Server recieved a Highscore-object!");
			HighScore hs = (HighScore) o;
			
			if(hs.getLocalScore() == -1 && !hs.isGetter()){
				System.out.println("Adding player.");
				gServer.addPlayer(hs);
			}else if (hs.isGetter()){
				System.out.println("Returning Scoremap.");
				gServer.getServer().sendToTCP(c.getID(), gServer.getScoreList());				
			}else{
				//System.out.println("Updating score.");
				//gServer.updateScore(hs.getName(), hs.getLocalScore());
				System.out.println("Adding player.");
				gServer.addPlayer(hs);
			}
			
		}
	}
}
