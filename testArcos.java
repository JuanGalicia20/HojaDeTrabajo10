import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class testArcos {

	@Test
	void testAgregar() {
		ArrayList<String[]> prueba = new ArrayList<>();
		String[] guate = {"guatemala","mixco","20"};
		prueba.add(guate);
		Floyd fl = new Floyd(prueba);
		fl.addNewC("guatemala", "san-cristobal", 4);
		int i = fl.getCiudades().indexOf("guatemala");
		int k = fl.getCiudades().indexOf("san-cristobal");
		assertEquals(4,fl.getKm()[i][k]);
	}
	
	
	
	@Test
	void testEliminar() {
		ArrayList<String[]> prueba = new ArrayList<>();
		String[] guate = {"guatemala","mixco","20"};
		prueba.add(guate);
		Floyd fl = new Floyd(prueba);
		fl.addNewC("guatemala", "san-cristobal", 4);
		int i = fl.getCiudades().indexOf("guatemala");
		int k = fl.getCiudades().indexOf("san-cristobal");
		fl.interrumpir("guatemala","san-cristobal");
		assertEquals((int) Double.POSITIVE_INFINITY,fl.getKm()[i][k]);
	}
}
