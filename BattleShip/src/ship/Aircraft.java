package ship;

import map.Position;
import enums.HitResult;
import enums.ShipType;

public class Aircraft extends Ship {
	public Aircraft() {
		shipName = "Aircraft";
		shipType = ShipType.AIRCRAFT;
		shipLength = AIRCRAFT_LENGTH;
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
