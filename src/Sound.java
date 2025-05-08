import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.JOptionPane;

public class Sound {
    protected String filepath;

    public Sound(String filepath) {
        this.filepath = filepath;
    }

    public void play() {
        playMusic(filepath);
        JOptionPane.showMessageDialog(null, "Press Ok to stop playing");
    }

    private void playMusic(String location) {
        try {
            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

