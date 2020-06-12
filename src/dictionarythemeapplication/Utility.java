package dictionarythemeapplication;

import javafx.scene.control.Alert;
import com.darkprograms.speech.synthesiser.SynthesiserV2;
import javafx.scene.image.ImageView;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.IOException;

/**
 * Utility là chương trình đọc văn bản bằng api google
 * @author Hope
 */
public class Utility {

    static SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    //static SynthesiserV2 synthesizer = new SynthesiserV2("pz7XtlQC-PYx-jrVMJErTcg");
    
    /**
     * Đọc văn bản
     * @param text Văn bản
     */
    public static void playSound(final String text) {
        //Create a new Thread because JLayer is running on the current Thread and will make the application to lag
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
                    player.play();
                } catch (IOException | JavaLayerException e) {

                    e.printStackTrace(); //Print the exception ( we want to know , not hide below our finger , like many developers do...)
                }
            }
        });
        //We don't want the application to terminate before this Thread terminates
        thread.setDaemon(false);
        //Start the Thread
        thread.start();

    }
}
