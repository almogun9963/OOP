package Ex1;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;




public class Functions_GUI implements functions {

	private ArrayList <function> fun;

	public Functions_GUI () {
		fun = new ArrayList <function>();
	}
	/**
	 * Init a new collection of functions from a file
	 * @param file - the file name
	 * @throws IOException if the file does not exists of unreadable (wrong format)
	 */
	public void initFromFile (String file) throws IOException {
		FileReader fr = new FileReader (file);
		ComplexFunction cf = new ComplexFunction ();
		String cl;//CurrentLine
		BufferedReader br = new BufferedReader (fr);
		while ((cl = br.readLine())!=null) {
			function f = cf.initFromString(cl);
			fun.add(f);
		}
		br.close();
	}
	/**
	 * this fucntion saves to file.
	 * @param file - the file name
	 * @throws IOException if the file is not writable
	 */
	public void saveToFile (String file) throws IOException {
		FileWriter fw = new FileWriter (file);
		for (int i=0;i<fun.size();i++)
			fw.write(fun.get(i).toString());

		fw.close();
	}
	/**
	 * Draws all the functions in the collection in a GUI window using the
	 * given parameters for the GUI windo and the range & resolution
	 * @param width - the width of the window - in pixels
	 * @param height - the height of the window - in pixels
	 * @param rx - the range of the horizontal axis
	 * @param ry - the range of the vertical axis
	 * @param resolution - the number of samples with in rx: the X_step = rx/resulution
	 */
	//	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
	//
	//		StdDraw.setCanvasSize(width, height);
	//		StdDraw.setXscale(rx.get_min(), rx.get_max());
	//		StdDraw.setYscale(ry.get_min(), ry.get_max());
	//		StdDraw.setPenColor(Color.BLACK);
	//		Font font = new Font ("David", Font.BOLD, 16);
	//		StdDraw.setFont(font);
	//
	//		//vertical lines
	//		for (double i = rx.get_min();i<rx.get_max();i++) {
	//			StdDraw.line(i, ry.get_min(), i, ry.get_max());
	//			StdDraw.text (i+0.5,0.5,i+"");
	//		}
	//
	//		//horizontal lines
	//		for (double i=ry.get_min();i<ry.get_max();i++) {
	//			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
	//			StdDraw.text(0.5, i+0.5, i+"");
	//		}
	//		StdDraw.setPenColor(Color.BLACK);
	//		StdDraw.setPenRadius(0.005);
	//		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);//line X
	//		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
	//
	//		double d = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
	//		Color[] color = {Color.BLUE,Color.CYAN,Color.MAGENTA,Color.RED,Color.GREEN,Color.PINK};
	//		for (int i=0; i<fun.size();i++) {
	//			StdDraw.setPenColor(color[i%7]);
	//			for (double j = rx.get_min();j<rx.get_max();j++){
	//				StdDraw.line(j, fun.get(i).f(j), j+d, fun.get(i).f(j+d));
	//			}
	//		}
	//	}

	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) { 		// rescale the coordinate system
		Color[] color = {Color.BLUE,Color.CYAN,Color.MAGENTA,Color.RED,Color.GREEN,Color.PINK};
		StdDraw.setCanvasSize(width, height);		
		StdDraw.setXscale(rx.get_min(), rx.get_max());		
		StdDraw.setYscale(ry.get_min(), ry.get_max()); 		
		StdDraw.setPenColor(Color.LIGHT_GRAY);		
		StdDraw.setFont(new Font("Arial", Font.BOLD, 15));//horizon lines	
		
		for(double i= ry.get_min(); i<=ry.get_max();i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);			
			StdDraw.text(0.2,i+0.2,i+""); 		
		}//vertical lines		
		
		for(double i=rx.get_min(); i<=rx.get_max(); i++) {			
			StdDraw.line(i, ry.get_min(), i, ry.get_max());			
			StdDraw.text(i+0.2,0.2,i+""); 		
		} //Drawing the base lines.	
		
		StdDraw.setPenColor(Color.BLACK);		
		StdDraw.setPenRadius(0.005);// x line.		
		StdDraw.line(rx.get_min(),0, rx.get_max(), 0);//y line.		
		StdDraw.line(0, ry.get_min(), 0, ry.get_max()); 		
		double d = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
		
		for(int i=0; i<this.fun.size();i++) {			
			StdDraw.setPenColor(color[i%color.length]);			
			for(double j =rx.get_min(); j< rx.get_max(); j=j+d) {				
				StdDraw.line(j, fun.get(i).f(j), j+d, fun.get(i).f(j+d));			
			}		
		}
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return fun.addAll(c);
	}

	@Override
	public void clear() {
		fun.clear();
	}

	@Override
	public boolean contains(Object o) {
		return fun.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return fun.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return fun.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return fun.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return fun.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return fun.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return fun.retainAll(c);
	}

	@Override
	public int size() {
		return fun.size();
	}

	@Override
	public Object[] toArray() {
		return fun.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return fun.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return fun.add(e);
	}
	/**
	 * Draws all the functions in the collection in a GUI window using the given JSON file
	 * @param json_file - the file with all the parameters for the GUI window. 
	 * Note: is the file id not readable or in wrong format should use default values. 
	 */
	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();	
		try {		
			GUI_params json = gson.fromJson(new FileReader(json_file), GUI_params.class);			
			String result = gson.toJson(json);			
			System.out.println(result);			
			drawFunctions( json.Width,json.Height,new Range(json.Range_X[0], json.Range_X[1]) ,new Range(json.Range_Y[0], json.Range_Y[1]), json.Resolution);
		}catch(Exception e) {
			e.printStackTrace();	
		}	
	}

}
