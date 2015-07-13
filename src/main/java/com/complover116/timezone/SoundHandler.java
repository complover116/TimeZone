package com.complover116.timezone;

import javax.sound.sampled.Clip;


public class SoundHandler {
	public static boolean playSound(String filename) {
			Clip cl = ImageContainer.sounds.get(filename);
			if(cl == null){
				Printer.errorOnce("Sound "+filename+" is not loaded");
			}else{
			cl.setMicrosecondPosition(0);
			cl.start();
			}
		return true;
	}
	public static boolean playSound(String filename, double pitch) {
		/*try {
		
			// Open an audio input stream.
        URL url = GUI.class.getResource("/sound/"+filename+".wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        int ch = audioIn.getFormat().getChannels();
        float rate = audioIn.getFormat().getSampleRate();
        AudioFormat auf = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, (float) (audioIn.getFormat().getSampleRate()*pitch), 16, ch, ch*2, rate, audioIn.getFormat().isBigEndian());
        AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(auf, audioIn);
        // Get a sound clip resource.
        Clip clip = AudioSystem.getClip();
        // Open audio clip and load samples from the audio input stream.
        clip.open(audioIn2);
        clip.start();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}*/
		return true;
	}
}
