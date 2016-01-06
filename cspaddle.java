package cspong;

//Author: Joshua D Moynihan
//Email:  jdmoynihan19@csu.fullerton.edu
//Course: CPSC223J
//Assignment: 5
//Due December 9, 2015
//File name: cspaddle.java
//Date last tested: 2015-12-8
//Description: This is one module in the Pong project that defines
// the functions for the speed and calculate the coordinates as the panel
// refreshes every time.
//
//Two sets of coordinates for the ball are maintained in this source file.
//There is a pair of double type coordinates: (ballrealxcoordinate,ballrealycoordinate).
//There is a pair of integer type coordinates: (ballintxcoordinate,ballintycoordinate).
//All computation regarding the location of the ball are performed with variables of type double.
//

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class cspaddle extends JPanel
{    int widthofgraphicalarea;
     int heightofgraphicalarea;
     final int ballradius = 16;
     final int balldiameter = 2*ballradius;
     double startx;
     double starty;
     double ballrealxcoordinate;
     double ballrealycoordinate;
     double realy;
     
     //variables for 1st paddle
     int a, b, da;
     
     //variables for 2nd paddle
     int c, d, dc;
     
     int mypaddleheight = 70;   //Paddle has height of 6 pixels
     int mypaddlewidth = 10;
     int mypaddlexcoord;   //Paddle begins at the far left side.  It has to start somewhere.
     int mypaddleycoord;

     //The following negative values assure that the ball remains out of view
     //until the start button is clicked.
     int ballintxcoordinate = - balldiameter;
     int ballintycoordinate = - balldiameter;
     double deltax;
     double deltay;
    private Object refreshclock;

     cspaddle(double directionoftravel, double ballvelocity, double animationrate)  //constructor
     {//directionoftravel is a real number showing the number of degrees in the angle between the positive x-axix and the path of the ball.
      //ballvelocity is a real number measuring the number of pixels traveled per second.
      //animationrate is a real number giving the number of times the coordinates of the ball are updated each second.
      deltax = ballvelocity * Math.sin(Math.toRadians(directionoftravel)) / animationrate;
      deltay = ballvelocity * Math.cos(Math.toRadians(directionoftravel)) / animationrate;
      a = 15;
      b = 450;
      da = 45;
      c = 1215;
      d = 20;
      dc = 45;
     }

     public void paint(Graphics graphicarea)
     {    super.paintComponent(graphicarea);
          this.setBackground(Color.BLACK);
          widthofgraphicalarea = getWidth();
          heightofgraphicalarea = getHeight();
          //Give the ball a red color
          graphicarea.setColor(Color.YELLOW);
          graphicarea.fillOval(ballintxcoordinate,ballintycoordinate,balldiameter,balldiameter);
          graphicarea.setColor(Color.red);
          graphicarea.fillRect (a, b, 20, 110);
          graphicarea.setColor(Color.red);
          graphicarea.fillRect (c,d, 20, 110);
     }//End of method paintComponent

     //The next method places the ball in its starting position, namely: top and center
     public void initializeball()
     {    //ballrealxcoordinate = (double)(widthofgraphicalarea/2 - ballradius);
          ballrealxcoordinate = 600.0;
          ballrealycoordinate = 286.0;
          ballintxcoordinate = (int)ballrealxcoordinate;
          ballintycoordinate = (int)ballrealycoordinate;
     }//End of initializeball
     public void setNewParameters(double directionoftravel, double ballvelocity, double animationrate){
        deltax = ballvelocity * Math.sin(Math.toRadians(directionoftravel)) / animationrate;
        deltay = ballvelocity * Math.cos(Math.toRadians(directionoftravel)) / animationrate;
     }

     //The next method advances the ball by adding suitable increments to the ball's x and y real coordinates.
     //
     public boolean moveball()
     {    boolean successfulmove = false; //Assume no move unless proven otherwise.
          if (ballintycoordinate+2*ballradius < heightofgraphicalarea){
            deltay = -1*deltay;//if hits p2 side
             
          }
          if (ballintycoordinate+2*ballradius > 30){
             deltay = -1*deltay; //if hits p1 side
             
          }
          if (ballintycoordinate+2*ballradius > b && ballintycoordinate+2*ballradius < (b+110) && ballintxcoordinate+2*ballradius <= 70){
              deltax = -1*deltax;
              //deltax = deltax+0.3;
              //deltay = deltay+0.3;
              
          }
          if (ballintycoordinate+2*ballradius > d && ballintycoordinate+2*ballradius < (d+110)){
              if(ballintxcoordinate+2*ballradius >= 1210)
              deltax = -1*deltax;
              //deltax = deltax +0.3;
              //deltay = deltay+0.3;
              
          }
            ballrealxcoordinate = ballrealxcoordinate + deltax;
            ballrealycoordinate = ballrealycoordinate + deltay;
            ballintxcoordinate = (int)Math.round(ballrealxcoordinate);
            ballintycoordinate = (int)Math.round(ballrealycoordinate);
          
            successfulmove = true;
          //}
          return successfulmove;
     }//End of moveball
     public int sendXCoord(){
         return (int) (ballrealxcoordinate+2*ballradius);
     }
     public int sendYCoord(){
         return (int) (ballrealycoordinate+2*ballradius);
     }
     public void setXCoord(int coord){
         ballrealxcoordinate = coord;
     }
     
     public void movep1up(){
         b = b - da;
     }
     public void movep1down(){
         b = b+da;
     }
     public void movep2up(){
         d = d - dc;
     }
     public void movep2down(){
         d = d + dc;
     }
}//End of Directionpanel