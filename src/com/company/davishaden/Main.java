/*
Name: Davis Haden
Date: 9/21/2021
Desc: Main Class for Wack-A-Mole
 */
package com.company.davishaden;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    JFrame frame = new JFrame("Wack-A-Mole Game");
	    CreaturePanel panel = new CreaturePanel();
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.getContentPane().add(panel);
	    frame.pack();
	    frame.setVisible(true);
    }
}
