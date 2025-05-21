
import javax.swing.*;


public class Max_Verstappen_Interactive {

    /*
    public static void main(String[] args) {

    //JOptionPane.showMessageDialog(null,"Press Ok to stop playing");
    }*/

    public static JPanel getMaxVerstappenInteractive()
    {
        String filepath = "Verstappen.wav";
        Sound.playMusic(filepath);
        //JOptionPane.showMessageDialog(null,"");
        return new JPanel();
    }

}
