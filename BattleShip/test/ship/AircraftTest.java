package ship;

import static org.junit.Assert.*;
import map.Position;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.ShipType;

public class AircraftTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Aircraft aShip = new Aircraft();
		aShip.init();
		assertEquals("Aircraft", aShip.getShipName());
		assertEquals(5, aShip.getShipHP());
		assertEquals(5, aShip.getShipLength());
		assertEquals(ShipType.AIRCRAFT, aShip.getShipType());
	}
}
