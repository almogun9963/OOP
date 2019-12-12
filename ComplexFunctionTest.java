package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {

	@Test
	void checkingPlus() {	
		function f1 = new Polynom("2x+3");
		function f2 = new Polynom("3");
		ComplexFunction cf = new ComplexFunction("plus",f1,f2);
		cf.plus(f2);
		String expected ="plus(plus(2.0x+3.0,3.0),3.0)";
		assertEquals(expected, cf.toString());
	}
	@Test
	void checkingMul() {	
		function f1 = new Polynom("x");
		ComplexFunction cf = new ComplexFunction("mul",f1,f1);
		cf.mul(f1);
		String expected ="mul(mul(1.0x,1.0x),1.0x)";
		assertEquals(expected, cf.toString());
	}
	@Test
	void checkingDiv() {	
		function f1 = new Polynom("x");
		ComplexFunction cf = new ComplexFunction("div",f1,f1);
		cf.div(f1);
		String expected ="div(div(1.0x,1.0x),1.0x)";
		assertEquals(expected, cf.toString());
	}
	@Test
	void checkingMax() {	
		function f1 = new Polynom("x");
		function f2 = new Polynom("3");
		ComplexFunction cf = new ComplexFunction("max",f1,f2);
		cf.max(f2);
		String expected ="max(max(1.0x,3.0),3.0)";
		assertEquals(expected, cf.toString());
	}
	@Test
	void checkingMin() {	
		function f1 = new Polynom("x");
		function f2 = new Polynom("3");
		ComplexFunction cf = new ComplexFunction("min",f1,f2);
		cf.min(f2);
		String expected ="min(min(1.0x,3.0),3.0)";
		assertEquals(expected, cf.toString());
	}
	@Test
	void checkingComp() {	
		function f1 = new Polynom("2x+3");
		function f2 = new Polynom("3");
		ComplexFunction cf = new ComplexFunction("comp",f1,f2);
		cf.comp(f2);
		String expected ="comp(comp(2.0x+3.0,3.0),3.0)";
		
		assertEquals(expected, cf.toString());
	}
	@Test
	void checkingEquals() {	
		function f1 = new Polynom("x^3");
		function f2 = new Polynom("x");
		ComplexFunction cf1 = new ComplexFunction("div",f1,f2);
		ComplexFunction cf2 = new ComplexFunction("mul",f2,f2);
		assertTrue(cf1.equals(cf2));
	}
	@Test
	void checkingFX() {	
		function f1 = new Polynom("2x+3");
		function f2 = new Polynom("3");
		ComplexFunction cf = new ComplexFunction("plus",f1,f2);
		double x=cf.f(3);
		double expected =12;
		assertEquals(expected, x);
	}
	@Test
	void checkingInitFromString() {	
		String s ="plus(div(2,5x),2x)";
		ComplexFunction t = new ComplexFunction();
		ComplexFunction cf =(ComplexFunction) t.initFromString(s);
		function f1 = new Polynom("2");
		function f2 = new Polynom("5x");
		function f3 = new Polynom("2x");
		ComplexFunction expected = new ComplexFunction("div",f1,f2);
		expected.plus(f3);
		assertTrue(expected.equals(cf));
	}
	@Test
	void checkingCopy() {
		function f1 = new Polynom("2x+3");
		function f2 = new Polynom("3");
		ComplexFunction cf = new ComplexFunction("plus",f1,f2);
		cf.div(f1);
		ComplexFunction expected=(ComplexFunction) cf.copy();
		assertTrue(cf.equals(expected));		
	}	
}
