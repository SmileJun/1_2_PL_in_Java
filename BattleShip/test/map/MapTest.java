package map;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

	protected Map map;
	@Before
	public void setUp() throws Exception {
		map = new Map();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testName() throws Exception {
		assertEquals("a", "a");
	}

}
