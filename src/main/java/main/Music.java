package main;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer; 

public class Music { 

	public Media musicaFondo;
	public MediaPlayer fondoReproductor;
	
	public Media sonidos;
	public MediaPlayer sonidoReproductor;
	
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
	public Music() {
		
	}
	
	
	public void sound_tragaperras(){
		try {
			sonidos = new Media(getClass().getResource("/media/sound1.mp3").toURI().toString());
			sonidoReproductor = new MediaPlayer(sonidos);
			sonidoReproductor.setAutoPlay(true);			
			sonidoReproductor.setVolume(getVolumen());
		

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}


	public void play(){ 
		fondoReproductor.play();
	} 
	
	public void pause() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
		fondoReproductor.pause();
	} 
	
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
		fondoReproductor.stop();
	} 
	
	public void setVolumen(Double vol) {
		fondoReproductor.setVolume(vol);
	}
	
	public double getVolumen() {
		return fondoReproductor.getVolume();
	}
} 