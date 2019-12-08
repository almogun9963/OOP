package myMath;
import myMath.Monom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;


import java.lang.Math; 
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	private ArrayList<Monom> monoms=new ArrayList<Monom>();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		this.monoms=new ArrayList<Monom>();
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		s=s.toLowerCase();
		if(s.contains("(")||s.contains(")")){
			throw new IllegalArgumentException("error message, not a simple polynom");
		}
		else  {
			String st = "";
			if (s.charAt(0)=='-')
				st = "-";
			else
				st = st + s.charAt(0);
			int i = 1;
			while (i<s.length()) {
				char c = s.charAt(i);
				if (c=='+') {
					Monom m = new Monom (st);
					monoms.add(m);
					st = "";
				}
				else if (c=='-') {
					Monom m = new Monom (st);
					monoms.add(m);
					st = "-";
				}
				else
					st = st + c;
				i++;
			}
			if(st!="") {
				Monom m = new Monom (st);
				monoms.add(m);
			}
		}
		for(int i=0;i<monoms.size();i++) {
			for(int j=i+1;j<monoms.size();j++) {
				Monom m =monoms.get(i);
				Monom m2 =monoms.get(j);
				if(m.get_power()==m2.get_power()) {
					m.add(m2);
					monoms.remove(j);
					monoms.remove(i);
					monoms.add(m);
					i=0;
					j=0;
				}
			}
		}
	}
	@Override
	/**
	 * this function return the f(x) value of Polynom
	 * @param x: represent the x that the value is given by it
	 * @return value of f(x)
	 */
	public double f(double x) {
		double answer=0;
		for (int i=0; i<monoms.size();i++) {
			answer = answer + monoms.get(i).f(x);
		}
		return answer;
	}

	@Override
	/**
	 * this function add Polynom to the current one
	 * @param p1: represent the Polynom that we want to add
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> mi=p1.iteretor();
		while(mi.hasNext())
		{
			Monom m = mi.next();
			this.add(m);
		}
	}

	@Override
	/**
	 * this function add Monom to current Polynom
	 * @param m1 : represent the Monom that we want to add
	 */
	 public void add(Monom m1) {	
		boolean added = false;
		for(int i=0;i<this.monoms.size();i++) {
			Monom m=this.monoms.get(i);
			if(m1.get_power()==m.get_power()) {
				m.add(m1);
				added = true;
			}
		}
		if (!added)
			this.monoms.add(m1);
	}

	@Override
	/**
	 * this function substract Polynom from the current one
	 * @param p1 : represent the Polynom that we want to substract
	 */
	public void substract(Polynom_able p1) {
		Iterator<Monom> mi=p1.iteretor();
		while(mi.hasNext())
		{
			Monom m = mi.next();
			Monom minusM = new Monom ((-1)*m.get_coefficient(), m.get_power());
			this.add(minusM);
		}
	}

	@Override
	/**
	 * this fuction multiply current Polynom with given one
	 * @param p1 : represent the Polynom that we want to multiply in
	 */
	public void multiply(Polynom_able p1) {
		Iterator <Monom> mi1 = this.iteretor();
		Iterator <Monom> mi2 = p1.iteretor();
		Polynom answer = new Polynom();
		while(mi1.hasNext())
		{
			Monom tempM1 = mi1.next();
			mi2 = p1.iteretor();
			while(mi2.hasNext())
			{
				Monom tempM2 = mi2.next();
				Monom m = new Monom(tempM1.get_coefficient()*tempM2.get_coefficient(),tempM1.get_power()+tempM2.get_power());
				answer.add(m);
			}
		}
		this.monoms = answer.monoms;

	}

	@Override
	/**
	 * this function checks if given Polynom is equal to current one
	 * @param p1 : represent the Polynom we want to check if it equal to the current Polynom
	 * @return true if equals; false if not
	 */
	public boolean equals(Polynom_able p1) {
		boolean b = true;
		Iterator <Monom> mi1 = this.iteretor();
		Iterator <Monom> mi2 = p1.iteretor();
		while(mi1.hasNext() && mi2.hasNext())
		{
			if(!mi1.next().equals(mi2.next()))
				b = false;

		}
		if(mi2.hasNext()||mi1.hasNext())
			b = false;
		return b;
	}

	@Override
	/**
	 * this function checks if Polynom is 0
	 * @return true if Polynom is 0; false if not
	 */
	public boolean isZero() {
		for(int i=0;i<this.monoms.size();i++) {
			Monom m=this.monoms.get(i);
			if(m.get_coefficient()!=0) {
				return false;
			}
		}
		return true;
	}

	@Override
	/**
	 * this function finds the roots of the Polynom (where f(x)=0)
	 * @param x0 : represent the start of the given range
	 * @param x1 : represent the end of the given range
	 * @param eps : represent the epsilon.
	 * @return the value of x in range[x0,x1] that is the root of the polynom(f(x)=0)
	 */
	public double root(double x0, double x1, double eps) {
		if(this.f(x0)*this.f(x1)>0)
			throw new IllegalArgumentException("error message, IllegalArgument ");
		if(this.f(x0)==0)
			return x0;
		if(this.f(x1)==0)
			return x1;
		if(Math.abs(x0-x1)<eps)
			return ((x0+x1)/2);
		double middle=(x0+x1)/2;
		if(this.f(x0)*this.f(middle)<0)
			return root(x0,middle,eps);
		else
			return root(middle,x1,eps);
	}

	@Override
	/**
	 * this function copies the given Polynom
	 * @return the copy of the given Polynom
	 */
	public Polynom_able copy() {
		Polynom_able pa = new Polynom();
		Iterator <Monom> mi=this.iteretor();
		while(mi.hasNext())
		{
			Monom m = mi.next();
			Monom m2 = new Monom(m.get_coefficient(), m.get_power());
			pa.add(m2);
		}

		return pa;
	}

	@Override
	/**
	 * this function gives the derivative of the polynom (nigzeret)
	 */
	public Polynom_able derivative() {
		ArrayList<Monom> alist=new ArrayList<Monom>();
		for(int i=0;i<this.monoms.size();i++) {
			Monom m=this.monoms.get(i);
			if(m.get_power()==0) {
				Monom m1 = new Monom(0,0);
				alist.add(m1);
			}
			else {
				Monom m1 = new Monom(m.get_power()*m.get_coefficient(),m.get_power()-1);
				alist.add(m1);
			}
		}
		for(int i=0;i<alist.size();i++) {
			this.monoms.remove(i);
			Monom m =alist.get(i);
			this.monoms.add(i, m);			
		}
		return null;
	}

	@Override
	/**
	 * this function is calculate the area of polynom in the range[x0,x1]
	 * @param x0 : represent the start of the given range
	 * @param x1 : represent the end of the given range
	 * @param eps : represent the epsilon.
	 * @return the area above the x-axis below this Polynom in range [x0,x1] 
	 */
	public double area(double x0, double x1, double eps) {
		int parts=(int)Math.abs((x1-x0)/eps);
		double ans=0;
		double x=x0;
		for(int i=0;i<parts;++i)
		{
			if(this.f(x)>0)
				ans=ans+this.f(x)*eps;
			x=x+eps;
		}
		return ans;
	}

	@Override
	/**
	 * this function defines iterator for Polynom
	 * @return  an iterator (of Monoms) for Polynom
	 */
	public Iterator<Monom> iteretor() {
		return monoms.iterator();
	}
	@Override
	/**
	 * this function multiply the current Polynom by given Monom
	 * @param m1 : the Monom that we want to multiply the current Polynom with
	 */
	public void multiply(Monom m1) {
		for(int i=0;i<monoms.size();i++) {
			monoms.get(i).multipy(m1);
		}
	}
	/**
	 * this function is printing the current Polynom
	 * @return a string that represents the Polynom.
	 */
	public String toString() {
		String ans = "";
		Iterator<Monom> mi= this.monoms.iterator();
		while(mi.hasNext()) {
			ans = ans + mi.next().toString() + "+";
		}
		ans = ans.substring(0, ans.length()-1);
		ans =ans.replace("+-", "-");
		return ans;
	}
}
