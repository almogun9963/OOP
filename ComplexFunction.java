package Ex1;

import java.util.Stack;

public class ComplexFunction implements complex_function{
	
	private function right;
	private function left;
	private Operation op;
	/**
	 * this function creates a default Complex Function.
	 */
	public ComplexFunction () {
		left = new Polynom ("0");
		right = new Polynom ("0");
		op = Operation.None;
	}
	/**
	 * this function creates a Complex Function from string,function,function.
	 * @param o: represent the string of the operation of the Complex Function.
	 * @param l: represent left function.
	 * @param r: represent right function.
	 */
	public ComplexFunction (String o, function l, function r) {
		left = l.copy();
		right = r.copy();
		o=o.toLowerCase();
		switch (o) {
		case "plus":
			op = op.Plus;
			break;
		case "mul":
			op = op.Times;
			break;
		case "div":
			op = op.Divid;
			break;
		case "max":
			op = op.Max;
			break;
		case "min":
			op = op.Min;
			break;
		case "comp":
			op = op.Comp;
			break;
		default:
			throw new RuntimeException ("ERR the opertion you entered is illegal.");
		}
	}
	/**
	 * this function creates a Complex Function from operation,function,function.
	 * @param o: represent the operation of the Complex Function.
	 * @param l: represent left function.
	 * @param r: represent right function.
	 */
	public ComplexFunction (Operation o, function l, function r) {
		left = l;
		right = r;
		op = o;
	}
	/**
	 * this function creates a Complex Function from function.
	 * @param f: represent the function of the Complex Function.
	 */
	public ComplexFunction (function f) {
		this.left = f;
		this.right = null;
		this.op = op.None;
	}
	/**
	 * this function return the f(x) value of a Complex Function .
	 * @param x: represent the x that the value is given by it.
	 * @return the value of f(x).
	 */
	public double f(double x) {
		
		switch (op) {
		case None:
			return left.f(x);
		case Plus:
			return left.f(x)+right.f(x);
		case Times:
			return left.f(x)*right.f(x);
		case Divid:
			if (right.f(x)!=0)
				return left.f(x)/right.f(x);
			throw new RuntimeException ("ERR Divide in 0 is not possible");
		case Min:
			return Math.min(left.f(x), right.f(x));
		case Max:
			return Math.max(left.f(x), right.f(x));
		case Comp:
			return left.f(right.f(x));
		case Error:
			throw new RuntimeException ("ERR got operation Error");
		}
		return 0;
	}
	/**
	 * this function initialize a function from a string.
	 * @param s represents the string that we want to initialize the function from.
	 * @return the new function that we wanted to initialize from the string.
	 */
	public function initFromString(String s) {
		s=s.replace(" ", "");
		if (!s.contains(",")) {
			Polynom p=new Polynom (s);
			return p;
		}
		int k=1;
		int i;
		for (i=s.indexOf('(')+1;i<s.length();i++) {
			if (s.charAt(i)=='(')
				k++;
			if (s.charAt(i)==',')
				k--;
			if (k==0)
				break;
		}
		
		String o = s.substring(0,s.indexOf('('));
		function l = initFromString (s.substring(s.indexOf('(')+1,i));
		function r = initFromString (s.substring(i+1,s.lastIndexOf(')')));
		function cf = new ComplexFunction (o,l,r);
		return cf;
		
	}
	/**
	 * this function copies a function.
	 * @return the new copied function.
	 */
	public function copy() {
		function f = new ComplexFunction (this.op, this.left, this.right);
		return f;
	}
	/**
	 * this function checks if a current Object is equal to a given one
	 * @param d represents the Object that we want to check if he is the same.
	 * @return false if he is not equal and true if he is equal.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof ComplexFunction))
			return false;
		ComplexFunction cf1 = new ComplexFunction (((ComplexFunction)obj).op,((ComplexFunction)obj).left,((ComplexFunction)obj).right);
		ComplexFunction cf2 = new ComplexFunction(this.op,this.left,this.right);
		for (int i=-25;i<25&&i!=0;i++) {
			if (cf2.f(i)!=cf1.f(i))
				return false;
		}
		return true;
	}

	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1) {
		function f = new ComplexFunction (this.op, this.left, this.right);
		this.left = f;
		this.right = f1;
		this.op = op.Plus;
	}
	/** Multiply this complex_function with the f1 complex_function.
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {
		function f = new ComplexFunction (this.op, this.left, this.right);
		this.left = f;
		this.right = f1;
		this.op = op.Times;
	}
	/** Divides this complex_function with the f1 complex_function.
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {
		function f = new ComplexFunction (this.op, this.left, this.right);
		this.left = f;
		this.right = f1;
		this.op = op.Divid;
	}
	/** Computes the maximum over this complex_function and the f1 complex_function.
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {
		function f = new ComplexFunction (this.op, this.left, this.right);
		this.left = f;
		this.right = f1;
		this.op = op.Max;
	}
	/** Computes the minimum over this complex_function and the f1 complex_function.
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {
		function f = new ComplexFunction (this.op, this.left, this.right);
		this.left = f;
		this.right = f1;
		this.op = op.Min;
	}
	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x)).
	 * @param f1 complex function.
	 */
	public void comp(function f1) {
		function f = new ComplexFunction (this.op, this.left, this.right);
		this.left = f;
		this.right = f1;
		this.op = op.Comp;
	}
	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation.
	 */
	public function left() {
		return this.left;
	}
	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation.
	 */
	public function right() {
		return this.right;
	}
	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp.
	 * @return the current op.
	 */
	public Operation getOp(){
		return this.op;
	}
	/**
	 * this function printring the Complex Function.
	 */
	public String toString() {
		switch (op) {
		case Plus:
			return "plus(" + this.left + "," + this.right + ")";
		case Times:
			return "mul(" + this.left + "," + this.right + ")";
		case Divid:
			return "div(" + this.left + "," + this.right + ")";
		case Max:
			return "max(" + this.left + "," + this.right + ")";
		case Min:
			return "min(" + this.left + "," + this.right + ")";
		case Comp:
			return "comp(" + this.left + "," + this.right + ")";
		case None:
			if (this.right==null)
				return "" + this.left;
			break;
		default:
			break;
		}
		return this.op + "(" + this.left + "," + this.right + ")";
	}

}
