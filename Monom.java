
package Ex1;

import java.util.Comparator;


/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		if(a==0) {
			this.set_coefficient(0);
			this.set_power(0);
		}else {
			this.set_coefficient(a);
			this.set_power(b);
		}
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	/**
	 * this function gets a monom from string
	 * @param s : represents a string of the monom and the function tells what is the coefficient and what is the power.
	 */
	public Monom(String s) {
		s=s.toLowerCase();
		s=s.replace(" ", "");
		String[] splited = s.split("\\^");
		if(splited.length==1)
		{
			if(splited[0].contains("x")) {
				if(splited[0].equals("x")) {
					_coefficient=1;
					_power=1;
				}else {
					if(splited[0].equals("-x")) {
						_coefficient=-1;
						_power=1;
					}else {
						splited[0] = splited[0].replace("x","");
						_coefficient= Double.valueOf(splited[0]);
						_power=1;
					}
				}
			}else {
				_coefficient= Double.valueOf(splited[0]);
				_power=0;
			}

		}else {
			if(splited[0].equals("-x")) {
				_coefficient=-1;
				_power=Integer.valueOf(splited[1]);
			}else if (splited[0].equals("x")){
				_coefficient=1;
				_power=Integer.valueOf(splited[1]);
			}else {
				String[] splitedx = s.split("x\\^");
				if(splitedx.length==1)
				{
					_coefficient=0;
					_power=Integer.valueOf(splitedx[0]);
				}else { 
					_coefficient= Double.valueOf(splitedx[0]);
					_power=Integer.valueOf(splitedx[1]);
				}
			}
		}
		
//		if(s.contains("x")&&s.charAt(s.length()-1)!='x'&&!s.contains("^"))
//			throw new RuntimeException("you put invalid string that present a polynom");
//		if((s.contains("x"))&&(s.indexOf("x")>1)&&(s.charAt(s.indexOf("x")-1)!='*'))
//			throw new RuntimeException("you put invalid string that present a polynom");
//		if(!s.contains("x"))
//		{
//			s=s+"*x^0";
//		}
//		else
//			if(s.indexOf("x")==0)
//				s="1*"+s;
//			else
//				if(s.indexOf("x")==1)
//					s=s.charAt(0)+"1*"+s.substring(1);
//		if(s.charAt(s.length()-1)=='x')
//			s=s+"^1";
//		String [] sec_split=s.split("x");
//		double coefficient = Double.parseDouble(sec_split[0].substring(0, sec_split[0].length()-1));
//		int power = Integer.parseInt(sec_split[1].substring(1));
//		if(power<0)
//		{
//			throw new RuntimeException("power cannot be a negative number");
//
//		}
//
//		this.set_coefficient(coefficient);
//		this.set_power(power);
	}
	/**
	 * this functions adds a given monom to a current one
	 * @param m : represents the monom we want to add (this monom + m).
	 */
	public void add(Monom m) {
		if(this._coefficient==0) {
			this._coefficient=m._coefficient;
			this._power=m._power;
		}else {
			if(m._coefficient==0) {
				m._coefficient=this._coefficient;
				m._power=this._power;
			}else {
				if(this._power!=m._power){
					throw new IllegalArgumentException("error message, not the same power");
				}else {
					this._coefficient=this._coefficient+m._coefficient;
					this._power=this._power;
				}
			}
		}

	}
	/**
	 * this function multiply a current onom with given one
	 * @param d : represents the monom we want to multiply (this monom * d).
	 */
	public void multipy(Monom d) {
		this._coefficient=this._coefficient*d._coefficient;
		this._power=this._power+d._power;
	}
	/**
	 * this function printring the monom.
	 */
	public String toString() {
		String ans;
		if(isZero()){
			int zero =0;
			ans=zero+"";
		}
		else {
			if(this._power==0) {
				ans=this._coefficient+"";
			}
			else if(this._power==1) {
				ans=this._coefficient+"x";
			}
			else {
				ans=this._coefficient+"x^"+this._power;
			}
		}

		return ans;
	}
	/**
	 * this function checks if a current monom is equal to a given one
	 * @param d represents the monom that we want to check if he is the same.
	 * @return false if he is not equal and true if he is equal.
	 */
	public boolean equals(Monom d) {
		if(this._coefficient==d._coefficient&&this._power==d._power) {
			return true;
		}else {
			return false;
		}

	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	/**
	 * this function initialize a Monom from a string.
	 * @param s represents the string that we want to initialize the Monom from.
	 * @return the new Monom that we wanted to initialize from the string.
	 */
	@Override
	public function initFromString(String s) {
		Monom m = new Monom(s);
		return m;
	}
	/**
	 * this function copies a Monom.
	 * @return the new copied Monom.
	 */
	@Override
	public function copy() {
		double co = this.get_coefficient();
		int pow = this.get_power();
		return new Monom(co,pow);	
	}


}
