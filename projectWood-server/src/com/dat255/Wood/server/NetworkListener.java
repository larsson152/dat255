package com.dat255.Wood.server;

import com.dat255.Wood.model.HighScore;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Listens for incoming connections to server.
 * @author Patrik Larsson
 *
 */
public class NetworkListener extends Listener {
	
	GameServer gServer;
	
	public void init(GameServer gServer){
		this.gServer = gServer;
	}
	
	//This method is called when someone connects. Not used.
	public void connected(Connection arg0) {
		System.out.println("[SERVER] Someone connected!");
	}

	//This method is called when someone disconnects. Not used.
	public void disconnected(Connection arg0) {
		System.out.println("[SERVER] Someone has disconnected.");
	}

	//Method is called when server recieves an object
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
				System.out.println("Adding player.");
				gServer.addPlayer(hs);
			}
			
		}
	}
}
