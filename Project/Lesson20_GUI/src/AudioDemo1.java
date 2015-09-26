import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;

import java.applet.AudioClip;

public class AudioDemo1 extends JApplet {
    private AudioClip sound;            // demo sound, wav

    private JButton playButton;         // button to play sound
   
    @Override
    public void init() {               // set up sound and button to play it                    
        setLayout(new FlowLayout()); 

        sound = getAudioClip(getDocumentBase(), "bounce.wav");     // load sound

        playButton = new JButton("Play Once");                     // set up play button
        playButton.addActionListener(                     
           new ActionListener() {                                  // handle via anonymous inner class
              public void actionPerformed(ActionEvent e) {          // handle button clicks
                sound.play();   
              }
           }
        );
        add(playButton); 
    }
}