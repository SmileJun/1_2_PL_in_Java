package ship;

import map.Position;
import enums.HitResult;
import enums.ShipType;

public class Cruiser extends Ship {
	public Cruiser() {
		shipName = "Cruiser";
		shipType = ShipType.CRUISER;
		shipLength = CRUISER_LENGTH;
	}

	public HitResult hitCheck(Position pos) {
		HitResult result = super.hitCheck(pos);
		
		if (result == HitResult.DESTROY) {
			HitResult.DESTROY.setName(shipName);
		}
		
		return result;
	}
}
