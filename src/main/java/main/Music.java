package main;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The type Music. Controllador de la Musica de fondo y de otros sonidos.
 */
public class Music {

	/**
	 * The Musica fondo.
	 */
	public Media musicaFondo;
	public MediaPlayer fondoReproductor;

	/**
	 * The Sonidos.
	 */
	public Media sonidos;
	public MediaPlayer sonidoReproductor;

	/**
	 * Instantiates a new Music. Crea la musica de fondo del menu
	 *
	 * @param path the path
	 */
	public Music(String path){
		try {
			musicaFondo = new Media(getClass().getResource(path).toURI().toString());
			fondoReproductor = new MediaPlayer(musicaFondo);
			fondoReproductor.setAutoPlay(true);			
			setVolumen(0.15);
			fondoReproductor.setCycleCount(MediaPlayer.INDEFINITE);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates a new Music.
	 */
	public Music() {
		
	}


	/**
	 * Play sound. Crea la musica para sonidos esporadicos
	 *
	 * @param path the path
	 */
	public void play_sound(String path){
		try {
			sonidos = new Media(getClass().getResource(path).toURI().toString());
			sonidoReproductor = new MediaPlayer(sonidos);
			sonidoReproductor.setAutoPlay(true);			
			sonidoReproductor.setVolume(getVolumen());
		
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Sonido tragaperras.
	 */
	public void sonido_tragaperras() {
		play_sound("/media/sound1.mp3");
	}

	/**
	 * Play. Retoma la reproduccion de la muscia de fondo
	 */
	public void play(){
		fondoReproductor.play();
	}

	/**
	 * Pause. Para la reproduccion de la muscia de fondo
	 *
	 * @throws UnsupportedAudioFileException the unsupported audio file exception
	 * @throws IOException                   the io exception
	 * @throws LineUnavailableException      the line unavailable exception
	 */
	public void pause() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		fondoReproductor.pause();
	}

	/**
	 * Stop. Detiene la reproduccion de la muscia de fondo
	 *
	 * @throws UnsupportedAudioFileException the unsupported audio file exception
	 * @throws IOException                   the io exception
	 * @throws LineUnavailableException      the line unavailable exception
	 */
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		fondoReproductor.stop();
	}

	/**
	 * Sets volumen. Pone el volumen de la musica de fondo
	 *
	 * @param vol the vol
	 */
	public void setVolumen(Double vol) {
		fondoReproductor.setVolume(vol);
	}

	/**
	 * Gets volumen. Obtiene el volumen de la musica de fondo
	 *
	 * @return the volumen
	 */
	public double getVolumen() {
		return fondoReproductor.getVolume();
	}
	
} 