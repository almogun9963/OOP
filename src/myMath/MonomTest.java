package myMath;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
 *****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
		Polynom p = new Polynom("-x");
		System.out.print(p.toString());
	}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));

		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}	
	}
	private static void test3() {
		System.out.println("*****  Test3:  *****");
		Monom m1 = new Monom(1,2);
		Monom m2 = new Monom(54,2);
		m1.add(m2);
		System.out.println("1)"+m1);
		
		Monom m3 = new Monom(10,12);
		Monom m4 = new Monom(54,3);
		m3.multipy(m4);
		System.out.println("3)"+m3);
		
		Monom m5 = new Monom(0,15);
		Monom m6 = new Monom(54,2);
		m5.add(m6);
		System.out.println("5)"+m5);
		
		Monom m7 = new Monom(54,2);
		Monom m8 = new Monom(0,15);
		m7.add(m8);
		System.out.println("7)"+m7);
		
		Monom m9 = new Monom(0,2);
		Monom m10 = new Monom(0,4);
		m9.add(m10);
		System.out.println("9)"+m9);
				
		Monom m11 = new Monom(0,15);
		Monom m12 = new Monom(54,2);
		m11.multipy(m12);
		System.out.println("11)"+m11);
		
		Monom m13 = new Monom(54,2);
		Monom m14 = new Monom(0,15);
		m13.multipy(m14);
		System.out.println("13)"+m13);
		
		Monom m15 = new Monom(0,2);
		Monom m16 = new Monom(0,15);
		m15.multipy(m16);
		System.out.println("15)"+m15);
		
		String s = "3y^7";
		Monom m17 = new Monom (s);
		System.out.println(m17.get_coefficient());
		System.out.println(m17.get_power());
		
		String s2 = "shalom";
		Monom m18 = new Monom (s2);
		System.out.println(m18.get_coefficient());
		System.out.println(m18.get_power());
		

	}
}
