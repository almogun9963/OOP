package myMath;

public class PolynomTest {
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
		Polynom p = new Polynom("x^3");
		System.out.println(p.root(-2, 2, 0.000001));
//		System.out.println(p.toString());
	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
	}
	public static void test3() {
		Polynom p1=new Polynom("1+x+x^2-x^3+x^4");
		System.out.println("p1)"+p1);
		
		p1.derivative();
		System.out.println("p1')"+p1);
		
		Polynom p2=new Polynom("1+x+x^2-x^3+x^4");
		p2.add(p2);
		System.out.println("p1+p1)"+p2);
		
		Polynom p3=new Polynom("1-x^4");
		p3.multiply(p3);
		System.out.println("p3^2)"+p3);
		
		Polynom p4=new Polynom("1-x^2+x^3");	
		System.out.println("f(x)=1-x^2+x^3,f(3)="+p4.f(3));
		
		Polynom p5=new Polynom("1-x^2+x^3");
		Polynom p6=new Polynom("1-x^2+x^3");
		p5.substract(p6);
		System.out.println("should be 0:  "+p5);
		
		System.out.println("should be true:  "+p5.isZero());
	}
}
