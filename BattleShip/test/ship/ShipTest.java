package ship;

import static org.junit.Assert.*;
import map.Position;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShipTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor() throws Exception {
		Ship ship = new Ship();
		assertEquals(ship.getShipPos(), (Position)null);
		
		ship.init();
		assertNotEquals(ship.getShipPos(), (Position)null);
	}

}
