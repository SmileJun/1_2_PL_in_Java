package player;

import java.util.ArrayList;
import java.util.Iterator;

import enums.*;
import map.*;
import map.MapCell.CellState;
import ship.*;

public class Player {
	ArrayList<Ship> shipList;
	Map myMap;
	Map enemyMap;
	
	public Player() {
		shipList = new ArrayList<Ship>(Ship.SHIP_NUM);

		for (int i = 0; i < Ship.AIRCRAFT_NUM; i++) {
			shipList.add(new Aircraft());
		}
		for (int i = 0; i < Ship.BATTLESHIP_NUM; i++) {
			shipList.add(new BattleShip());
		}
		for (int i = 0; i < Ship.CRUISER_NUM; i++) {
			shipList.add(new Cruiser());
		}
		for (int i = 0; i < Ship.DESTROYER_NUM; i++) {
			shipList.add(new Destroyer());
		}
		myMap = new Map();
		enemyMap = new Map();
	}
	
	public void init() {
		for (Iterator<Ship> iterator = shipList.iterator(); iterator.hasNext();) {
			Ship ship = iterator.next();
			ship.init();
		}
		
		myMap.init();
		enemyMap.init();
	}
	
	public void placeShips() {
		Position randomPos = new Position();
		Direction randomDir = new Direction();
		int[] dx = { 0 };
		int[] dy = { 0 };
		
		for (Iterator<Ship> iterator = shipList.iterator(); iterator.hasNext();) {
			Ship ship = iterator.next();
			System.out.println("it OK");
			do {
				System.out.println("do OK");
				makeRandomPos(randomPos);
				makeRandomDir(randomDir);
			} while (!myMap.isValidPlacePosition(randomPos, randomDir, ship.getShipLength()));
			
			randomDir.makeDxDyValueWithDir(dx, dy);
			
			for (int i = 0; i < ship.getShipLength(); i++) {
				MapCell currentCell = myMap.getCell(randomPos);
				currentCell.setState(CellState.SHIP_STATE);
				
				ship.addShipPart(randomPos);
				
				randomPos.setX((char)(randomPos.getX() + dx[0]));
				randomPos.setY((char)(randomPos.getY() + dy[0]));				
			}
		}
	}
	
	public void makeRandomPos(Position randomPos) {
		char randX = (char) (Map.START_COL + (char)((int)Math.random()*Map.COL_SIZE));
		char randY = (char) (Map.START_ROW + (char)((int)Math.random()*Map.ROW_SIZE));
		randomPos.setX(randX);
		randomPos.setY(randY);
	}
	
	public void makeRandomDir(Direction dir) {
		dir.setDir((int)Math.random()*Direction.DIRECTION_NUM);
	}

	public Position makeAttackPosition() {
		Position attackPos = new Position();
		do {
			makeRandomPos(attackPos);
		} while(!enemyMap.isValidAttackPos(attackPos));
		
		return attackPos;
	}

	/**
	 * 공격자가 적을 대상으로 공격을 한다
	 * @param pos 공격 위치
	 * @return 피격자가 보내온 공격결과 데이터
	 */
	public HitResult attack(Position pos) {
		HitResult result;
		
		for (Iterator<Ship> iterator = shipList.iterator(); iterator.hasNext();) {
			Ship ship = iterator.next();
			result = ship.hitCheck(pos);
			
			if (result != HitResult.MISS) {
				return result;			
			}
		}
		return HitResult.MISS;
	}
	
	/**
	 * 공격자가 공격을 한 뒤 피격자로부터 온 결과를 이용해 공격에 대한 처리를 수행
	 * @param pos : 공격했던 위치
	 * @param res : 피격자가 공격을 받은 후 보내온 결과
	 */
	public void attackHandler(Position pos, HitResult res) {
		if (pos == null) {
			return;
		}

		if (res == HitResult.HIT) {
			MapCell targetCell = enemyMap.getCell(pos);
			targetCell.setState(CellState.HIT_STATE);
		}
		
		if (res == HitResult.MISS) {
			MapCell targetCell = enemyMap.getCell(pos);
			targetCell.setState(CellState.MISS_STATE);
		}
		
		if (res == HitResult.DESTROY) {
			MapCell targetCell = enemyMap.getCell(pos);
			targetCell.setState(CellState.DESTROY_STATE);
		}
	}

	public boolean isAllShipsDestroyed() {
		int shipsTotalHP = 0;
		
		for (Iterator<Ship> iterator = shipList.iterator(); iterator.hasNext();) {
			Ship ship = iterator.next();
			shipsTotalHP += ship.getShipHP();
		}
		
		if (shipsTotalHP > 0)
			return false;
		
		return true;

	}
	
	public void showShipListData() {
		for (Iterator<Ship> iterator = shipList.iterator(); iterator.hasNext();) {
			Ship ship = iterator.next();
			ship.showShipData();
		}
	}
	
	public ArrayList<Ship> getShipList() {
		return shipList;
	}

	public Map getMyMap() {
		return myMap;
	}

	public Map getEnemyMap() {
		return enemyMap;
	}

}
