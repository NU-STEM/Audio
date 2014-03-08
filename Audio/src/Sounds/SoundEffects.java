package Sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class SoundEffects {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
		Clip clip1 = prepAudioClip("soundFiles/click.wav", 30);// Volume: 0-30
		clip1.start();
///////////////// OR /////////////////
		Thread.sleep(1000);//needed or clips play at the same time and only sounds like a single audio clip!
		prepAudioClip("soundFiles/click.wav", 15).start();// Volume: 0-30 
		
		
		JOptionPane.showMessageDialog(null, "click");
		
	}
	
	

	public static Clip prepAudioClip(String filePath, float gain) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream stream;
		AudioFormat format;
		DataLine.Info info;
		Clip clip = null;
		float vol = gain-30;

		
		stream = AudioSystem.getAudioInputStream(SoundEffects.class.getResource(filePath));
		format = stream.getFormat();
		
		info = new DataLine.Info(Clip.class, format);
		clip = (Clip)AudioSystem.getLine(info);
		
		clip.open(stream);
		
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(vol);
		
		return clip;
	}
}
