package main;

import java.io.File;
import java.io.IOException; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException; 

public class Sounds { 

	public Clip clip; 
	public AudioInputStream audioStream; 
	public static String Path; 

	
	public Sounds(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
		
		Path = path;
		
		audioStream = AudioSystem.getAudioInputStream(new File(Path).getAbsoluteFile()); 

		clip = AudioSystem.getClip(); 
		clip.open(audioStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 


	
	public void play(){ 
		clip.start(); 
	} 
	
	public void pause() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
		clip.stop();
	} 
	
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException { 
		clip.stop(); 
		clip.close(); 
	} 
	
	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException { 
		clip.stop(); 
		clip.close(); 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	} 

	public void volumen(float vol) {
		FloatControl volumenControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volumenControl.setValue(vol);
	}

} 