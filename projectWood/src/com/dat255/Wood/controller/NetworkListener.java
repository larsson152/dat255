package com.dat255.Wood.controller;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;
import com.dat255.Wood.model.GameClient;
import com.dat255.Wood.model.HighScore;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {

	private GameClient gClient;
	
	public void init(GameClient gClient) {
	this.gClient = gClient;	
	}
	
	public void connected(Connection arg0) {
		System.out.println("[CLIENT] You are now connected.");
		gClient.getClient().sendTCP(gClient.getHighScore());
	}

	public void disconnected(Connection arg0) {
		System.out.println("[CLIENT] You are now disconnected.");
	}

	public void received(Connection c, Object o) {
		if(o instanceof ArrayList){
			ArrayList<HighScore> o2 = (ArrayList<HighScore>) o;
			gClient.getHighScore().setHighScore(o2);
			
			System.out.println("Storleken på arraymapen är: " + o2.size());
					
			
			for(HighScore hs: o2){
				System.out.println("Score: " +hs.getLocalScore());
			}
			
		}else if (o instanceof Array){
			System.out.println("[CLIENT] Recieved High Score.");
		}
	}
}