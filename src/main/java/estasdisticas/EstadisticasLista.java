package estasdisticas;

import java.util.ArrayList;
import java.util.List;

public class EstadisticasLista {
	
	public static List<Estasdisticas> getPersonas() {
		List<Estasdisticas> personas = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			Estasdisticas p = new Estasdisticas();
			p.setPartidasCubo(p.getPartidasCubo());
			p.setPartidasMayorOMenor(p.getPartidasMayorOMenor());
			p.setPuntosAntes(p.getPuntosAntes());
			p.setPuntosDespues(p.getPuntosDespues());
			p.setTiradas(p.getTiradas());
			personas.add(p);
		}
		return personas;
	}
}
