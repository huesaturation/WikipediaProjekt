
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.JOptionPane;

//TODO refactor to generalize this class to play any audio
public class Max_Verstappen_Interactive {

    //TODO Not needed
    public static void main(String[] args) {
    String filepath = "Verstappen.wav";
    PlayMusic(filepath);
    JOptionPane.showMessageDialog(null,"Press Ok to stop playing");
    }

    //TODO Refactor location to filePath
    //TODO Return Exception and delete Sysout
    public static void PlayMusic(String location)
    {
        try{
            File musicPath = new File(location);
            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else{
                System.out.println("Can`t find file");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
