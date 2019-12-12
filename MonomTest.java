package Ex1Testing;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Ex1.Monom;


class MonomTest {
	@Test
	void checkingFromString() {
		String s = "3x^7";
		Monom m1 = new Monom (s);
		Monom expected = new Monom(3,7);
		assertTrue(m1.equals(expected));
	}
	@Test
	void checkingAdd() {
		Monom m1 = new Monom(1,2);
		Monom m2 = new Monom(54,2);
		m1.add(m2);
		Monom expected = new Monom(55,2);
		assertTrue(m1.equals(expected));
	}
	@Test
	void checkingMultipy() {
		Monom m1 = new Monom(2,3);
		Monom m2 = new Monom(54,2);
		m1.multipy(m2);
		Monom expected = new Monom(108,5);
		assertTrue(m1.equals(expected));
	}
	@Test
	void checkingFX() {
		Monom m1 = new Monom(2,3);
		double fx = m1.f(3);
		double expected = 54;
		assertEquals("testing f(x)",expected,fx,0.0000001);
	}
	@Test
	void checkingInitFromString() {
		String s = "3x^2";
		Monom m1 = new Monom(0,0);
		m1=(Monom)m1.initFromString(s);
		Monom expected = new Monom(s);	
		assertTrue(m1.equals(expected));
	}
}

