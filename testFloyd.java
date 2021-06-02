import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class testFloyd {

	@Test
	void testFloyd() {
		ArrayList<String[]> prueba = new ArrayList<>();
		String[] guate = {"guatemala","mixco","20"};
		prueba.add(guate);
		Floyd fl = new Floyd(prueba);
		fl.addNewC("guatemala", "san-cristobal", 4);
		fl.addNewC("antigua", "mixco", 18);
		fl.addNew("guatemala", "antigua", 1);
		fl.addNewC("san-cristobal", "pamplona", 5);
		fl.addNew("pamplona", "mixco", 2);
		
		fl.algoritmo();
		
		//se escoge el viaje mas corto a pesar de tener conexion directa u otro camino
		
		String total = "La distancia mas corta entre estas ciudades es: 11\nguatemala----san-cristobal----pamplona----mixco\n\n";
		assertEquals(total,fl.resultadoFloyd("guatemala", "mixco"));
	}

}
