package ship;

import map.Position;
import enums.HitResult;
import enums.ShipType;

public class Ship {
	protected final static int AIRCRAFT_LENGTH = 5;
	protected final static int BATTLESHIP_LENGTH = 4;
	protected final static int CRUISER_LENGTH = 3;
	protected final static int DESTROYER_LENGTH = 2;
	protected final static int MAX_SHIP_SIZE = AIRCRAFT_LENGTH;
	public final static int AIRCRAFT_NUM = 1;
	public final static int BATTLESHIP_NUM = 1;
	public final static int CRUISER_NUM = 1;
	public final static int DESTROYER_NUM = 2;
	public final static int SHIP_NUM = AIRCRAFT_NUM + BATTLESHIP_NUM + CRUISER_NUM + DESTROYER_NUM ;
	
	
	protected String shipName;
	protected Position[] shipPos;
	protected ShipType shipType;
	protected int shipHP;
	protected int shipLength;

	
	public void init() {
		if (shipPos == null)
			shipPos = new Position[shipLength];

		shipHP = shipLength;
	}

	public int getShipIdx(Position pos) {
		for (int i = 0; i < shipLength; i++) {
			if (pos.equals(shipPos[i]))
				return i;
		}

		return -1;
	}

	public void addShipPart(Position pos) {
		for (int i = 0; i < shipLength; i++) {
			if (shipPos[i].getX() == 0 || shipPos[i].getY() == 0) {
				shipPos[i] = pos;
				break;
			}
		}
	}

	public HitResult hitCheck(Position pos) {
		int hitIdx = getShipIdx(pos);

		if (hitIdx == -1)
			return HitResult.MISS;

		hitAction(hitIdx);

		if (shipHP <= 0) {
			return HitResult.DESTROY;
		}

		return HitResult.HIT;
	}

	protected void hitAction(int hitIdx) {
		shipHP--;
		shipPos[hitIdx] = (Position) null;
	}

	
	
	public String getShipName() {
		return shipName;
	}

	public Position[] getShipPos() {
		return shipPos;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public int getShipHP() {
		return shipHP;
	}

	public int getShipLength() {
		return shipLength;
	}

}
