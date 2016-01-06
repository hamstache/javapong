package cspong;
//Author: Joshua D Moynihan
//Email: jdmoynihan19@csu.fullerton.edu
//Course: CPSC223J
//Assignment: 5
//Due December 9, 2015
//File name: csponginterface.java
//Date last tested: 2015-12-8
//Description: This is one module in the Pong program.  This module
//defines the user interface.
//
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

public class csponginterface extends JFrame implements KeyListener
{    
     JPanel titlepanel;
     JLabel titlelabel;
     
     cspaddle linearpanel;
     
     JPanel buttonpanel;
     JButton newgame;
     JButton go;
     JButton pause;
     JButton exit;
     
     JLabel playerleft;
     JTextField leftscore;
     int leftpoints = 0;
     JLabel playerright;
     JTextField rightscore;
     int rightpoints = 0;
     JLabel winner;
     JTextField winnername;
     
     Timer refreshclock;
     //Timer animationclock;
     Buttonhandlerclass buttonhandler;
     Refreshclass refreshhandler;
     //Animationclass animationhandler;
     final double degreesofrotation = 3.0;
     final double millisecondpersecond = 1000.0;
     Random randomGenerator = new Random();
     double directionoftravel = randomGenerator.nextInt(360); //degrees.  The ball will move into the fourth traditional quadrant of the Cartesian plane.
     double directionoftravelcorrected = 90.0 + directionoftravel; //Correction because computer's y-axis is upside down.
     double speedofball = 350.18;  //This is the number of pixels traveled by the ball in one second.
     double rateofanimation = 25.5;  //This is the number of times the ball's coordinates are updated each second.
     int animationinterval = (int)Math.round(millisecondpersecond/rateofanimation);
     double rateofrefresh = 35.333;  //This is the number of times the graphical area is repainted each second.
     int refreshinterval = (int)Math.round(millisecondpersecond/rateofrefresh);

     public csponginterface()
         {super("Direction Control Ball");
          //Do not use FlowLayout when working with Graphical objects.
          //Make 3 panels and place them from top to bottom onto any object of type Linearinterface.
          //Make the 1st panel
          titlelabel = new JLabel("CPSC 223J Assignment 5 By Josh Moynihan");
          titlepanel = new JPanel();
          titlepanel.add(titlelabel);
          titlepanel.setPreferredSize(new Dimension(1200,25));
          add(titlepanel,BorderLayout.NORTH);
          
          //Make the 2nd panel
          linearpanel = new cspaddle(directionoftravelcorrected,speedofball,rateofanimation);
          linearpanel.setBackground(Color.BLUE);
          linearpanel.setPreferredSize(new Dimension(1200, 600));
          add(linearpanel,BorderLayout.CENTER);
          
          //Make the 3rd panel
          buttonhandler = new Buttonhandlerclass();
          
          newgame = new JButton("New Game");
          go = new JButton("Go");
          pause = new JButton("Pause");
          exit = new JButton("Exit");

          playerleft = new JLabel("Player Left");
          leftscore = new JTextField(5);
          winner = new JLabel("Winner");
          winnername = new JTextField(10);
          playerright = new JLabel("Player Right");
          rightscore = new JTextField(5);
          
          newgame.addActionListener(buttonhandler);
          go.addActionListener(buttonhandler);
          go.setVisible(false);
          pause.addActionListener(buttonhandler);
          pause.setVisible(false);
          exit.addActionListener(buttonhandler);
          
          buttonpanel = new JPanel();
          buttonpanel.add(newgame);
          buttonpanel.add(go);
          buttonpanel.add(pause);
          buttonpanel.add(exit);
          buttonpanel.add(playerleft);
          buttonpanel.add(leftscore);
          buttonpanel.add(winner);
          buttonpanel.add(winnername);
          buttonpanel.add(playerright);
          buttonpanel.add(rightscore);
          buttonpanel.setPreferredSize(new Dimension(1200,50));
          add(buttonpanel,BorderLayout.SOUTH);

          //Make a clock that controls the graphical refresh rate.
          refreshhandler = new Refreshclass();
          refreshclock = new Timer(refreshinterval,refreshhandler);

          //Make a clock that controls the animation rate.
          //animationhandler = new Animationclass();
          //animationclock = new Timer(animationinterval,animationhandler);
          
          //enableEvents(java.awt.AWTEvent.KEY_EVENT_MASK);
          addKeyListener(this);
          setFocusable(true);
         }//End of Gameinterface constructor.
        
      public void keyPressed(KeyEvent event)
      {int keycode = event.getKeyCode();
       switch(keycode)
       {
        case KeyEvent.VK_W:
            linearpanel.movep1up();
            //System.out.print("up");
            break;
        case KeyEvent.VK_S:
            linearpanel.movep1down();
            //System.out.print("down");
            break;
        case KeyEvent.VK_UP:
        //case KeyEvent.VK_KP_UP:
            linearpanel.movep2up();
            //System.out.print("up");
            break;
        case KeyEvent.VK_DOWN:
        //case KeyEvent.VK_KP_DOWN:
            linearpanel.movep2down();
            //System.out.print("down");
            break;
        //There is no default: all other keypresses are disregarded
       }//End switch
      }//End method keyPressed(KeyEvent event)
     public void keyReleased(KeyEvent event){
         
     }
     public void keyTyped(KeyEvent event){
         
     }

     private class Buttonhandlerclass implements ActionListener
     {    public void actionPerformed(ActionEvent event)
              {
                if(event.getSource() == newgame){
                    linearpanel.initializeball();
                    double newrand = randomGenerator.nextInt(89);
                    double newrandcorrected = newrand+90;
                    if (newrandcorrected == 90 || newrandcorrected == 0 || newrandcorrected == 180 || newrandcorrected == 270){
                        newrand = randomGenerator.nextInt(360);
                        newrandcorrected = newrand+90;
                    }
                    linearpanel.setNewParameters(newrand, speedofball, rateofanimation);
                    linearpanel.repaint();
                    leftpoints = 0;
                    rightpoints = 0;
                    leftscore.setText(Integer.toString(leftpoints));
                    rightscore.setText(Integer.toString(rightpoints));
                    go.setVisible(true);
                    pause.setVisible(false);
                    refreshclock.stop();
                    //animationclock.stop();
                      
                }
                else if(event.getSource() == go)
                {
                   animationinterval = (int)Math.round(millisecondpersecond/rateofanimation);
                   linearpanel.repaint();
                   refreshclock.start();
                   //animationclock.start();
                   go.setVisible(false);
                   pause.setVisible(true);
                   
                   
                }
                else if (event.getSource() == pause){
                    //animationclock.stop();
                    refreshclock.stop();
                    pause.setVisible(false);
                    go.setVisible(true);
                }
                else if (event.getSource() == exit) {
                    System.exit(0);
                }
                else
                  System.out.printf("%s\n","An error ocurred in a button.");
              }
     }//End of Buttonhandlerclass

     private class Refreshclass implements ActionListener
     {    public void actionPerformed(ActionEvent event)
              {if(event.getSource() == refreshclock){
                  setFocusable(true);
                  requestFocus();
                  requestFocusInWindow();
                  linearpanel.moveball();
                  linearpanel.repaint();
                  if (linearpanel.sendXCoord() < 3 ){
                       refreshclock.stop();
                       leftpoints = leftpoints+1;
                       rightscore.setText(Integer.toString(leftpoints));
                       go.setVisible(true);
                       pause.setVisible(false);
                       linearpanel.setXCoord(600);
                  }
                   if (linearpanel.sendXCoord() > 1285 ){
                       refreshclock.stop();
                       rightpoints = rightpoints+1;
                       leftscore.setText(Integer.toString(rightpoints));
                       go.setVisible(true);
                       pause.setVisible(false);
                       linearpanel.setXCoord(600);
                   }
                  if (leftpoints == 5){
                       winnername.setText("Right");
                       go.setVisible(false);
                       pause.setVisible(false);
                   }
                   if (rightpoints == 5){
                       winnername.setText("Left");
                       go.setVisible(false);
                       pause.setVisible(false);
                   }
                  
              }
               else
                  System.out.printf("%s\n","Error in the refresh clock.");
              }
     }//end of Refreshclass

}//End of Directioninterface