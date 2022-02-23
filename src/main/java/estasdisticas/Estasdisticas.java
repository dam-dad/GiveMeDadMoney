package estasdisticas;

import java.util.ArrayList;
import java.util.List;

public class Estasdisticas {
	
	private int tiradas;
	private int partidasCubo;
	private int partidasMayorOMenor;
	private int puntosAntes;
	private int puntosDespues;
	
	public Estasdisticas() {
		this.tiradas=0;
		this.partidasCubo=0;
		this.partidasMayorOMenor=0;
		this.puntosAntes=0;
		this.puntosDespues=0;
	}

	public int getTiradas() {
		return tiradas;
	}

	public void setTiradas(int tiradas) {
		this.tiradas = tiradas;
	}

	public int getPartidasCubo() {
		return partidasCubo;
	}

	public void setPartidasCubo(int partidasCubo) {
		this.partidasCubo = partidasCubo;
	}

	public int getPartidasMayorOMenor() {
		return partidasMayorOMenor;
	}

	public void setPartidasMayorOMenor(int partidasMayorOMenor) {
		this.partidasMayorOMenor = partidasMayorOMenor;
	}

	public int getPuntosAntes() {
		return puntosAntes;
	}

	public void setPuntosAntes(int puntosAntes) {
		this.puntosAntes = puntosAntes;
	}

	public int getPuntosDespues() {
		return puntosDespues;
	}

	public void setPuntosDespues(int puntosDespues) {
		this.puntosDespues = puntosDespues;
	}

}