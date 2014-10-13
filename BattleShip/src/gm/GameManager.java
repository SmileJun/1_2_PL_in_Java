package gm;

import enums.*;
import player.*;
import map.*;

public class GameManager {
	private enum GameMode {
		GAME_PLAY, GAME_TEST, 
	}
	
	private Player player1;
	private Player player2;
	private int turn = 0;
	private GameMode mode;
	
	public GameManager() {
		player1 = new Player();
		player2 = new Player();
		mode = GameMode.GAME_TEST;
	}
	
	public void initGame() {
		player1.init();
		player1.placeShips();
		player2.init();
		player2.placeShips();
		
		turn = 0;
	}
	
	public void run() {
		int totalTurn = 0;
		
		//maxTrun, minTurn은 gameRestart 동안의 최대 및 최소 Turn 수를 의미한다.
		//초기화 값은 비교를 위해 기본 설정한 값으로 무의미함
		int maxTurn = 0; 
		int minTurn = 64; 
		
		final int numOfGameRestart = 10;
		
		for (int i = 0; i < numOfGameRestart; i++) {
			initGame();
			
			Position attackPos;
			HitResult hitResult;
			
			//player1이 player2를 공격
			//player2는 공격하지 않음
			while (!GameEndCondition()) {
				showGameUI();
				attackPos = player1.makeAttackPosition();
				hitResult = player2.attack(attackPos);
				player1.attackHandler(attackPos, hitResult);
				turn++;
			}			
			totalTurn += turn;

			if (turn > maxTurn) maxTurn = turn;
			if (turn < minTurn) minTurn = turn;
			System.out.printf("Game no%d : %d\n", i + 1, turn);
		}
		System.out.printf("average : %g max : %d min : %d \n", (double)totalTurn / numOfGameRestart, maxTurn, minTurn);
	}
	
	private boolean GameEndCondition() {
		if(player1.isAllShipsDestroyed() || player2.isAllShipsDestroyed())
			return true;
		
		// if criticalErrorOccured
		// return true;
		
		return false;
	}

	public void showGameUI() {
		if (mode == GameMode.GAME_PLAY) {
			System.out.println("turn: " + turn);
			player1.getMyMap().drawMap();
			player1.getEnemyMap().drawMap();
			player1.showShipListData();
		}
		
		if (mode == GameMode.GAME_TEST) {
			System.out.println("turn: " + turn);
			player1.getEnemyMap().drawMap();
			player2.getMyMap().drawMap();
			player2.showShipListData();
		}
	}
}
