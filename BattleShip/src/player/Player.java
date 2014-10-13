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
			
			do {
				makeRandomPos(randomPos);
				makeRandomDir(randomDir);
			} while (!myMap.isValidPlacePosition(randomPos, randomDir, ship.getShipLength()));
			
			randomDir.makeDxDyValueWithDir(dx, dy);
			
			for (int i = 0; i < ship.getShipLength(); i++) {
				MapCell currentCell = myMap.getCell(randomPos);
				currentCell.setState(CellState.SHIP_STATE);
				
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

	public HitResult attackerAction() {
		Position pos = new Position();
		do {
			makeRandomPos(pos);
		} while(!enemyMap.isValidAttackPos(pos));
		
		return (HitResult)null;
	}

	public HitResult beAttackedAction(Position pos) {
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
}
