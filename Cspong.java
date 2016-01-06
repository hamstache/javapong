package cspong;

//Author: Joshua D Moynihan
//Email: jdmoynihan19@csu.fullerton.edu
//Course: CPSC223J
//Assignment: 5
//Due December 9, 2015
//Usage: This program creates a game of Pong where the ball is bounced back and
// forth by two paddles and whoever misses the ball gives a point to the other player. 
//File name: Cspong.java

//Date last tested: 2015-12-8
//Purpose for this file: The main driver for a program showing a simple game of Pong.


import javax.swing.JFrame;

public class Cspong
{    public static void main(String[] args)
         {JFrame myframe = new csponginterface();
          myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          myframe.setSize(1250,700);
          myframe.setVisible(true);
         }//End of main
}//End of class cspong