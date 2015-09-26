import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;

import java.applet.AudioClip;

public class AudioDemo extends JApplet {
    private AudioClip sound;            // demo sound, wav

    private JButton playButton;         // button to play sound
    private JButton stopButton;         // button to stop sound
    private JButton loopButton;         // button to loop sound
   
    @Override
    public void init() {                                           // set up sound and buttons                     
        ButtonHandler handler = new ButtonHandler();               // inner class obj
                                                                   // for buttons
        setLayout(new FlowLayout()); 

        sound = getAudioClip(getDocumentBase(), "bounce.wav");     // load sound
 
        playButton = new JButton("Play Once");                     // set up play button
        playButton.addActionListener(handler);
        add(playButton); 

        loopButton = new JButton("Loop");                          // set up loop button
        loopButton.addActionListener(handler);
        add(loopButton); 

        stopButton = new JButton("Stop");                          // set up stop button
        stopButton.addActionListener(handler);
        add(stopButton); 
    }

    // inner class to handle buttons in this applet
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {             // handle button clicks
            if(e.getSource() == playButton) {                    // play
                sound.play();   
            } 
            if(e.getSource() == loopButton) {                     // loop
                sound.loop();   
            } 
            if(e.getSource() == stopButton) {                    // stop
                sound.stop();   
            } 
        }
    }
}