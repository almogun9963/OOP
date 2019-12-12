package Ex1Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

class PolynomTest {

	@Test
	void checkingFromString() {
		String s = "-x+6-4.7x^2";
		Polynom p1 = new Polynom (s);
		Polynom expected = new Polynom ();
		String[] monoms = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			expected.add(m);
		}

		assertTrue(p1.equals(expected));
	}
	@Test
	void checkingFX() {
		String s1 = "-x+6-4x^3";
		Polynom p1 = new Polynom(s1);
		double x =p1.f(2);
		double expected = -28;
		assertEquals("testing f(x)",expected,x,0.0000001);
	}
	@Test
	void checkingAdd() {
		String s1 = "-x+6-4x^2";
		Polynom p1 = new Polynom(s1);
		String s2 = "-x+6-4x^2";
		Polynom p2 = new Polynom(s2);
		p1.add(p2);
		String s = "-2x+12-8x^2";
		Polynom expected = new Polynom(s);
		assertTrue(p1.equals(expected));
	}
	@Test
	void checkingSubstract() {
		String s1 = "-x+6-4x^2";
		Polynom p1 = new Polynom(s1);
		String s2 = "-x+6-4x^2";
		Polynom p2 = new Polynom(s2);
		p1.substract(p2);
		String s = "0";
		Polynom expected = new Polynom(s);
		assertTrue(p1.equals(expected));
	}
	@Test
	void checkingMultiply() {
		String s1 = "x+1";
		Polynom p1 = new Polynom(s1);
		String s2 = "x+1";
		Polynom p2 = new Polynom(s2);
		p1.multiply(p2);
		String s = "x^2+2x+1";
		Polynom expected = new Polynom(s);
		assertTrue(p1.equals(expected));
	}	
	@Test
	void checkingIsZero() {
		String s1 = "0";
		Polynom p1 = new Polynom(s1);
		assertTrue(p1.isZero());
		
		String s2 = "0+0+0+0";
		Polynom p2 = new Polynom(s2);
		assertTrue(p2.isZero());
	}
	@Test
	void checkingCopy() {
		String s1 = "x^2-1";
		Polynom p1 = new Polynom(s1);
		Polynom_able p2=p1.copy();
		assertTrue(p1.equals(p2));
	}
	@Test
	void checkingDerivative() {
		String s1 = "x^2+x+1";
		Polynom p1 = new Polynom(s1);
		String s2 = "2x+1";
		Polynom expected = new Polynom(s2);
		p1.derivative();
		assertTrue(p1.equals(expected));
		
		String s3 = "1";
		Polynom p3 = new Polynom(s3);
		String s4 = "0";
		Polynom expected2 = new Polynom(s4);
		p3.derivative();
		assertTrue(p3.equals(expected2));
	}
	public static boolean compDouble(double a , double b) {
		double diff = a-b;
		if (a == b || (diff < 0.01 && diff > -0.01)) return true;
		return false;
	}
	@Test
	void checkingAreaAndRoot() {
		String[][] polynoms = {{"3x^2","-6x^3","9x","-2"},
				{"x","5x","0","-5"},
				{"4x^6", "-5x^5", "1"}};
		double[][] res = {{0,0.2135},{0,0.83334},{2.404,0.9999}}; 
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p1 = new Polynom();
			for (int j = 0; j < polynoms[i].length; j++) {
				Monom temp = new Monom(polynoms[i][j]);
				p1.add(temp);
			}
			assertTrue(compDouble(p1.area(-1, 0, 0.0001), res[i][0]));
			assertTrue(compDouble(p1.root(0, 1, 0.0001), res[i][1]));
		}
	}
	@Test
	void checkingInitFromString() {
		String s = "x^2+5";
		Polynom p1 = new Polynom("0");
		p1=(Polynom)p1.initFromString(s);
		Polynom expected = new Polynom(s);
		assertTrue(p1.equals(expected));
	}
}

