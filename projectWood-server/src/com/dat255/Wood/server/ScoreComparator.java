package com.dat255.Wood.server;

import java.util.Comparator;

import com.dat255.Wood.model.HighScore;

public class ScoreComparator implements Comparator<HighScore>
{
	@Override
	public int compare(HighScore a, HighScore b) {
		if (a.getLocalScore() < b.getLocalScore()){
			return 1;
		}else if(a.getLocalScore() > b.getLocalScore()){
			return -1;
		}else{
			return 0;
		}
	}
}