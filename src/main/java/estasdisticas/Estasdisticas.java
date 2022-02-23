package estasdisticas;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Estasdisticas.
 */
public class Estasdisticas {
	
	private int tiradas;
	private int partidasCubo;
	private int partidasMayorOMenor;
	private int puntosAntes;
	private int puntosDespues;

	/**
	 * Instantiates a new Estasdisticas.
	 */
	public Estasdisticas() {
		this.tiradas=0;
		this.partidasCubo=0;
		this.partidasMayorOMenor=0;
		this.puntosAntes=0;
		this.puntosDespues=0;
	}

	/**
	 * Gets tiradas.
	 *
	 * @return the tiradas
	 */
	public int getTiradas() {
		return tiradas;
	}

	/**
	 * Sets tiradas.
	 *
	 * @param tiradas the tiradas
	 */
	public void setTiradas(int tiradas) {
		this.tiradas = tiradas;
	}

	/**
	 * Gets partidas cubo.
	 *
	 * @return the partidas cubo
	 */
	public int getPartidasCubo() {
		return partidasCubo;
	}

	/**
	 * Sets partidas cubo.
	 *
	 * @param partidasCubo the partidas cubo
	 */
	public void setPartidasCubo(int partidasCubo) {
		this.partidasCubo = partidasCubo;
	}

	/**
	 * Gets partidas mayor o menor.
	 *
	 * @return the partidas mayor o menor
	 */
	public int getPartidasMayorOMenor() {
		return partidasMayorOMenor;
	}

	/**
	 * Sets partidas mayor o menor.
	 *
	 * @param partidasMayorOMenor the partidas mayor o menor
	 */
	public void setPartidasMayorOMenor(int partidasMayorOMenor) {
		this.partidasMayorOMenor = partidasMayorOMenor;
	}

	/**
	 * Gets puntos antes.
	 *
	 * @return the puntos antes
	 */
	public int getPuntosAntes() {
		return puntosAntes;
	}

	/**
	 * Sets puntos antes.
	 *
	 * @param puntosAntes the puntos antes
	 */
	public void setPuntosAntes(int puntosAntes) {
		this.puntosAntes = puntosAntes;
	}

	/**
	 * Gets puntos despues.
	 *
	 * @return the puntos despues
	 */
	public int getPuntosDespues() {
		return puntosDespues;
	}

	/**
	 * Sets puntos despues.
	 *
	 * @param puntosDespues the puntos despues
	 */
	public void setPuntosDespues(int puntosDespues) {
		this.puntosDespues = puntosDespues;
	}

}