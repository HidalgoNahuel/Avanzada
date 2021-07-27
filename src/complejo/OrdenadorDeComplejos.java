package complejo;

import java.util.Comparator;

public class OrdenadorDeComplejos implements Comparator<Complejo>{

	@Override
	public int compare(Complejo o1, Complejo o2) {
		
		return Double.compare(o1.modulo(), o2.modulo());
	}

}
