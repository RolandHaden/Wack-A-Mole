/*
Name: Davis Haden
Date: 9/24/2021
Desc: Creature class for Wack-a-Mole. Moves the creature when it is clicked and adds a score. It also has a 1.1%
chance of moving while the mouse is moving.
 */
package com.company.davishaden;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreaturePanel extends JPanel {
    //Integers
    private int pointX = 250;
    private int pointY = 250;
    private int score = 0;
    private final int imgWidth = 100;
    private final int imgHeight = 94;
    //Objects
    private final ImageIcon finalImage;
    private final ImageIcon backgroundImage;
    private final ImageIcon mouseImage;
    private final JLabel scoreDisplay;
    private final Timer timer;
    private final Clip audioClip;

    public CreaturePanel() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //declaring variables and objects
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        LineListener listener = new LineListener();
        timer = new Timer(1000, new MovementListener());
        //Audio Stuff
        File audioFile = new File("sound/retrobulb.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        //Message
        JPanel messagePanel = new JPanel();
        JLabel message = new JLabel("Click on the creature! Once you reach a score of 10, you can leave the program! ;)");
        //Creature Image
        BufferedImage bufferedImage = ImageIO.read(new File("images/creature.png"));
        Image icon = bufferedImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT);
        finalImage = new ImageIcon(icon);
        //Background Image
        BufferedImage bufferedBackground = ImageIO.read(new File("images/background.png"));
        Image background = bufferedBackground.getScaledInstance((int) screensize.getWidth(), (int) screensize.getHeight(), Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(background);
        //Mouse Image
        BufferedImage bufferedMouse = ImageIO.read(new File("images/mouse.png"));
        Image mouse = bufferedMouse.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        mouseImage = new ImageIcon(mouse);
        //Message Panel
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS));
        messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messagePanel.setBackground(Color.black);
        //Message
        message.setAlignmentX(CENTER_ALIGNMENT);
        message.setForeground(Color.white);
        message.setFont(new Font("Futura", Font.BOLD, 12));
        messagePanel.add(message);
        //Score Display
        scoreDisplay = new JLabel("Score: 0");
        scoreDisplay.setAlignmentX(CENTER_ALIGNMENT);
        scoreDisplay.setForeground(Color.white);
        scoreDisplay.setFont(new Font("Futura", Font.BOLD, 12));
        messagePanel.add(scoreDisplay);
        //adding elements
        add(messagePanel);
        addMouseListener(listener);
        addMouseMotionListener(listener);
        //setting main panel attributes
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        setCursor(blankCursor);
        setPreferredSize(new Dimension((int) screensize.getWidth(),(int) screensize.getHeight()));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundImage.paintIcon(this,g,0,0);
        finalImage.paintIcon(this, g, pointX, pointY);
        mouseImage.paintIcon(this,g,(int) MouseInfo.getPointerInfo().getLocation().getX(),(int) MouseInfo.getPointerInfo().getLocation().getY());
    }
    //Resets the sound and plays it again
    private void playSound(Clip c){
            c.stop();
            c.setFramePosition(0);
            c.start();
    }
    //Changes the location of the creature at a timed variation
    private class MovementListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            pointX = (int) (Math.random() * (getWidth()-imgWidth));
            pointY = (int) (Math.random() * (getHeight()-imgHeight));
            scoreDisplay.setText("Score: " + score);
            repaint();
        }
    }
    //Tracks the movement of the mouse and changes the score
    private class LineListener implements MouseListener,MouseMotionListener {
        public void mouseClicked(MouseEvent e){//Not consistent
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getPoint().getX() <= pointX + finalImage.getIconWidth() && e.getPoint().getX() >= pointX && e.getPoint().getY() <= pointY + finalImage.getIconHeight() && e.getPoint().getY() >= pointY){
                pointX = (int) (Math.random() * (getWidth()-imgWidth));
                pointY = (int) (Math.random() * (getHeight()-imgHeight));
                score = score + 1;
                playSound(audioClip);
                scoreDisplay.setText("Score: " + score);
                repaint();
            }
            if(score >= 10){
                //Main.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                System.exit(0);
            }
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            timer.start();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
            timer.stop();
        }

        @Override //Randomly moves the creature when ever the player drags their mouse.
        public void mouseDragged(MouseEvent e) {
            timer.start();
        }

        @Override //Randomly moves the creature when ever the player moves their mouse.
        public void mouseMoved(MouseEvent e) {
            timer.start();
            repaint();
        }

    }

}
