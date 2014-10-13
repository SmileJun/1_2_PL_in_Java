package main;

import gm.*;


public class BattileShipMain {
	public static void main(String[] args) {
		GameManager gm = new GameManager();

		System.out.println("hello");
		gm.initGame();
		gm.run();
		return;
	}
}
