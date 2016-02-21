package Gui.layout;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class BorderLayoutEx {
  public static void main(String[] args) {
    JFrame frame = new JFrame("BorderLayout Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container container = frame.getContentPane();
    container.setLayout(new BorderLayout(30,50));
    
    // Add a button to each of the five areas of the BorderLayout
    container.add(new JButton("North"), BorderLayout.NORTH);
    container.add(new JButton("South"), BorderLayout.SOUTH);
    container.add(new JButton("Center"), BorderLayout.CENTER);
    container.add(new JButton("West"), BorderLayout.WEST);
    
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout(10,5));
    panel.add(new JButton("North"), BorderLayout.NORTH);
    panel.add(new JButton("South"), BorderLayout.SOUTH);
    panel.add(new JButton("East"), BorderLayout.EAST);
    panel.add(new JButton("West"), BorderLayout.WEST);
    panel.add(new JButton("CENTER"), BorderLayout.CENTER);
    
    container.add(panel, BorderLayout.EAST);

    frame.setSize(500,500);
    frame.setVisible(true);
  }
}