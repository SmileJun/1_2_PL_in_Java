package ship;

import map.Position;
import enums.HitResult;
import enums.ShipType;

public class Destroyer extends Ship {
	public Destroyer() {
		shipName = "Destroyer";
		shipType = ShipType.DESTROYER;
		shipLength = DESTROYER_LENGTH;
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
