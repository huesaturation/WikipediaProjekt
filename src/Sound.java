import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class Sound
{
    public static Exception playMusic(String filepath)
    {
        try
        {
            File musicPath = new File(filepath);
            if (musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else
            {
                return new IOException("Filepath does not exist");
            }
        }
        catch (Exception e){
            return e;
        }
        return new Exception("200");
    }
}
