package estasdisticas;

import java.util.ArrayList;
import java.util.List;

import menuController.BaseController;

public class EstadisticasLista {

	public static List<Estasdisticas> getPersonas() {
		List<Estasdisticas> personas = new ArrayList<>();
		Estasdisticas p=BaseController.getInstance().getEstadisticas();
		p.setPartidasCubo(p.getPartidasCubo());
		p.setPartidasMayorOMenor(p.getPartidasMayorOMenor());
		p.setPuntosAntes(p.getPuntosAntes());
		p.setPuntosDespues(p.getPuntosDespues());
		p.setTiradas(p.getTiradas());
		personas.add(p);
		return personas;
	}
}
