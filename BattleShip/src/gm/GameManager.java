package gm;

import enums.*;
import player.*;
import map.*;

public class GameManager {
	private Player player1;
	private Player player2;
	private int turn = 0;
	
	public GameManager() {
		player1 = new Player();
		player2 = new Player();
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
		
		//maxTrun, minTurn은 gameRestart 동안의 최대 및 최소 Trun수를 의미한다.
		//초기화 값은 비교를 위해 기본 설정한 값으로 무의미함
		int maxTurn = 0; 
		int minTurn = 64; 
		
		final int numOfGameRestart = 10;
		
		for (int i = 0; i < numOfGameRestart; i++) {
			initGame();
			
			Position attackPos;
			HitResult result;
			
			while (!GameEndCondition()) {
				showGameUI();
			}
			
			if (turn > maxTurn)maxTurn = turn;
			if (turn < minTurn)minTurn = turn;
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
//		player2.showAllShipData();
//		printf("turn:%3d\n", Turn);
//		player1.printEnemyBoardData();
//		getchar();
	}
}
