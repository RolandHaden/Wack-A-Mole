/*
Name: Davis Haden
Date: 9/21/2021
Desc: Main Class for Wack-A-Mole
 */
package com.company.davishaden;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
	public static JFrame frame;
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
	    frame = new JFrame("Wack-A-Mole Game");
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
	    CreaturePanel panel = new CreaturePanel();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    frame.getContentPane().add(panel);
	    frame.pack();
	    frame.setVisible(true);
		device.setFullScreenWindow(frame);
    }
}
