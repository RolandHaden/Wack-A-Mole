/*
Name: Davis Haden
Date: 9/21/2021
Desc: Creature class for Wack-a-Mole. Moves the creature when it is clicked and adds a score. It also has a 0.4%
chance of moving while the mouse is moving.
 */
package com.company.davishaden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreaturePanel extends JPanel {
    private int pointX = 250;
    private int pointY = 250;
    private int score = 0;
    private final ImageIcon finalImage;
    private final JLabel message;
    public CreaturePanel(){
        finalImage = new ImageIcon("images/creature.png");
        message = new JLabel("Click on the creature!       Score: 0");
        message.setForeground(Color.white);
        add(message);
        LineListener listener = new LineListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setBackground(new Color(188, 129, 219));
        setPreferredSize(new Dimension(500,500));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        finalImage.paintIcon(this, g, pointX, pointY);
    }
    private class LineListener implements MouseListener,MouseMotionListener {
        public void mouseClicked(MouseEvent e){
            /*if(e.getPoint().getX() <= pointX + 50 && e.getPoint().getX() >= pointX && e.getPoint().getY() <= pointY + 41 && e.getPoint().getY() >= pointY){
                pointX = (int) (Math.random() * 450);
                pointY = (int) (Math.random() * 450);
                score = score + 1;
                message.setText("Click on the creature!       Score: " + score);
                repaint();
            }*///Use this string of code, if the creature is not responding to clicks.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getPoint().getX() <= pointX + 50 && e.getPoint().getX() >= pointX && e.getPoint().getY() <= pointY + 41 && e.getPoint().getY() >= pointY){
                pointX = (int) (Math.random() * 450);
                pointY = (int) (Math.random() * 450);
                score = score + 1;
                message.setText("Click on the creature!       Score: " + score);
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(Math.random()*450 < 2) {
                pointX = (int) (Math.random() * 450);
                pointY = (int) (Math.random() * 450);
                message.setText("Click on the creature!       Score: " + score);
                repaint();
            }
        }
    }

}
