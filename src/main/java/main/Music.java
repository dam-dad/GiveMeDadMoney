package main;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer; 

public class Music { 

	public static String Path; 
	public Media musica;
	public MediaPlayer reproductor;

	
	public Music(String path){ 
		try {
			musica = new Media(GiveMeDADMoney.class.getResource(path).toURI().toString());
			reproductor = new MediaPlayer(musica);
			reproductor.setAutoPlay(true);			
			volumen(0.1);
			reproductor.setCycleCount(MediaPlayer.INDEFINITE);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	} 
	
	public void sound(String path){ 
		try {
			musica = new Media(GiveMeDADMoney.class.getResource(path).toURI().toString());
			reproductor = new MediaPlayer(musica);
			reproductor.setAutoPlay(true);			
			volumen(0.1);
			reproductor.setCycleCount(MediaPlayer.INDEFINITE);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	} 


	public void play(){ 
		reproductor.play();
	} 
	
	public void pause() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
		reproductor.pause();
	} 
	
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
		reproductor.stop();
	} 
	

	public void volumen(Double vol) {
		reproductor.setVolume(vol);
	}

} 