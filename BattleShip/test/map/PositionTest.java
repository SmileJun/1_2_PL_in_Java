package map;

import static org.junit.Assert.*;
import map.Position;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCharCast() throws Exception {
		Position pos = new Position();
		assertEquals(0, pos.x);
		
		pos.makeRandomPos(pos);
	}

}
