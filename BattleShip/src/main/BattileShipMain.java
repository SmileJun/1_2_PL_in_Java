package main;

import gm.*;


public class BattileShipMain {
	public static void main(String[] args) {
		GameManager gm = new GameManager();

		gm.initGame();

		System.out.println("Game Start");
		gm.run();
		return;
	}
}
