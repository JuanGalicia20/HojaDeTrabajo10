import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class testNodo {

	@Test
	void testAgregar() {
		ArrayList<String[]> prueba = new ArrayList<>();
		String[] guate = {"guatemala","mixco","20"};
		prueba.add(guate);
		Floyd fl = new Floyd(prueba);
		assertEquals(true, fl.getCiudades().contains("guatemala") &&fl.getCiudades().contains("mixco"));
	}
	
	@Test
	void testAgregar2() {
		ArrayList<String[]> prueba = new ArrayList<>();
		String[] guate = {"guatemala","mixco","20"};
		prueba.add(guate);
		Floyd fl = new Floyd(prueba);
		fl.addNewC("guatemala", "san-cristobal", 4);
		assertEquals(true, fl.getCiudades().contains("san-cristobal"));
	}
}
