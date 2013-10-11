package com.dat255.Wood.controller;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;
import com.dat255.Wood.WoodGame;
import com.dat255.Wood.model.GameClient;
import com.dat255.Wood.model.HighScore;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Handles incoming connections and transmissions from server
 * @author Patrik Larsson
 *
 */
public class NetworkListener extends Listener {

	private GameClient gClient;
	private HighScore hs;
	
	public void init(GameClient gClient, HighScore hs) {
	this.gClient = gClient;	
	this.hs = hs;
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
			hs.setHighScore(o2);
			
		}
	}
}