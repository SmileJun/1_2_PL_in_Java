package ship;

import map.Position;
import enums.HitResult;
import enums.ShipType;

public class BattleShip extends Ship {
	public BattleShip() {
		shipName = "BattleShip";
		shipType = ShipType.BATTLESHIP;
		shipLength = BATTLESHIP_LENGTH;
		shipPos = new Position[shipLength];
	}

	public HitResult hitCheck(Position pos) {
		HitResult result = super.hitCheck(pos);
		
		if (result == HitResult.DESTROY) {
			HitResult.DESTROY.setName(shipName);
		}
		
		return result;
	}
}
