ReadMe - ex1
 
Monom Class: 
 
This class represent Monom from the type a*x^b. 
 
functions: 
 
●	public Monom (double a, int b) - build a Monom object with a given coefficient (double a) and given power (int b). 

●	public Monom (Monom ot) - build a Monom by copy given one. 

●	public Monom (String s) - build a Monom by given String.

●	public double f (double x) - the function return the value of the Monom with given x.

●	public void add (Monom m) - the function add given Monom to the current one if their powers are equals. otherwise, the function throw RuntimeException. 

●	public void multipy (Monom d) - the function multipy current Monom in given one. 

●	public boolean equals (Monom m) - the function return t rue if the given Monom is equal to the current one. return f alse if not.

●	public String toString() - the function print the Monom. 

● public function initFromString(String s) - the function initialize a Monom from a string.

● public function copy() - the function copies a Monom.

 
 
Polynom Class: 
 
This class represent Polynom from the type a0 + a1*x^1 + ... + an*x^n . 
The Polynom is represented with an ArrayList of Monoms.  
 
functions: 
 
●	public Polynom() - Build an empty ArrayList. 

●	public Polynom (String s) - Build a Polynom from given String s.

●	public double f (double x) - t he function return the value of the Polynom with given x. 

●	public void add (Polynom_able p1) - the function add given Polynom to current one. 

●	public void add (Monom m1) - the function add given Monom to current Polynom. 

●	public void substract (Polynom_able p1) - the function substract given Polynom from current one. 

●	public void multiply (Polynom_able p1) - the function multiply current Polynom in given one. 

●	public void multiply (Monom m1) - the function multiply current Polynom in given Monom. 

●	public boolean equals(Polynom_able p1) - the function return t rue if the given Polynom is equal to the current one. return f alse if doesn’t. 

●	public boolean isZero () - the function return t rue if the Polynom is equal to 0. return f alse if doesn’t. 

●	public double root(double x0, double x1, double eps) - the function return the x that for it the value of the function is 0. the function throw exception if the entire function if negative. 

●	public Polynom_able copy () - the function return copy of the current Polynom. 

●	public Polynom_able derivative () - the function return the derivative of the function. 

●	public double area(double x0, double x1, double eps) - the function return the size of the area between the function to the X axis. 

●	public Iterator<Monom> iteretor () - the function create Iterator. 
 
● public function initFromString(String s) - the function initialize a Polynom from a string.
 
 
 
 ComplexFunction Class: 
 
 this class represent Complex Function from the type op(function,function)
 function can be Monom/Polynom.
 op can be plus/mul/div/max/min/comp.
 Complex Function can be more than 1 op (example: plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0).
 
 functions:
 
 
 ● public ComplexFunction () - this function creates a default Complex Function.
 
 ●	public ComplexFunction (String o, function l, function r) - this function creates a Complex Function from string,function,function.
 
 ● public ComplexFunction (Operation o, function l, function r) - this function creates a Complex Function from operation,function,function.
 
 ● public ComplexFunction (function f) - this function creates a Complex Function from function.
 
 ● public double f(double x) - this function return the f(x) value of a Complex Function.
 
 ● public function initFromString(String s) - this function initialize a function from a string.
 
 ●	public function copy() - this function copies a function.
 
 ● public boolean equals(Object obj) - this function checks if a current Object is equal to a given one.
 
 ● public void plus(function f1) - Add to this complex_function the f1 complex_function.
 
 ● public void mul(function f1) - Multiply this complex_function with the f1 complex_function.
 
 ●	public void div(function f1) - Divides this complex_function with the f1 complex_function.
 
 ●	public void max(function f1) - Computes the maximum over this complex_function and the f1 complex_function.
 
 ● public void min(function f1) - Computes the minimum over this complex_function and the f1 complex_function.
 
 ● public void comp(function f1) - This method wrap the f1 complex_function with this function: this.f(f1(x)).
 
 ● public function left() - returns the left side of the complex function - this side should always exists (should NOT be null).
 
 ● public function right() - returns the right side of the complex function - this side might not exists (aka equals null).
 
 ● public Operation getOp() - The complex_function oparation: plus, mul, div, max, min, comp.
 
 ● public String toString() - this function printring the Complex Function.
  
  
  
  
 Functions_GUI Class: 
 
 this class represent graph of a Complex Function.
 
 functions:
 
 ● public void initFromFile(String file) - Init a new collection of functions from a file
 
 ● public void saveToFile(String file) - this fucntion saves to file.
 
 ●	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) - Draws all the functions in the collection in a GUI window using the given parameters for the GUI windo and the range & resolution.
 
 ●	public void drawFunctions(String json_file) - Draws all the functions in the collection in a GUI window using the given JSON file.
 

 
 
 
 
 
 
 
 
 
 
