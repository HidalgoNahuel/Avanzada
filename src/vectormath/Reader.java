package vectormath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

	private Scanner sc;
	
	public Reader(String path) {
	
		try {
			sc = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Archivo Inexistente");
		}		
		
	}
	
	public double readDouble(){
		return sc.nextDouble();
	}
	
	public int readInt() {
		return sc.nextInt();
	}
	
	public boolean hasNext() {
		return sc.hasNext();
	}
}
