package Gui.layout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
//import javax.swing.SwingUtilities;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;

class BoxLayoutSimple{
    public static void main(String []args){
        JFrame  frame = new JFrame("BOX Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cPane = frame.getContentPane();
        
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        
        for(int i=0; i<5 ; i++){
            panel.add(new JButton("JButton "+i));
        }
        
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.red);
        BoxLayout boxLayout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxLayout2);
        
        for(int i=0; i<5 ; i++){
            panel2.add(new JButton("JButton "+(i*3)));
        }
        
        JPanel panel3 = new JPanel();
        BoxLayout boxLayout3 = new BoxLayout(panel3, BoxLayout.PAGE_AXIS);
        panel3.setLayout(boxLayout3);
        
        for(int i=0; i<5 ; i++){
            panel3.add(new JButton("JButton "+i));
        }
        
        JPanel panel4 = new JPanel();
        panel4.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        BoxLayout boxLayout4 = new BoxLayout(panel4, BoxLayout.LINE_AXIS);
        panel4.setLayout(boxLayout4);
        
        for(int i=0; i<5 ; i++){
            panel4.add(new JButton("JButton "+(i*2)));
        }
        
        frame.add(panel,BorderLayout.NORTH);
        frame.add(panel2,BorderLayout.WEST);
        frame.add(panel3,BorderLayout.EAST);
        frame.add(panel4,BorderLayout.SOUTH);
//        frame.pack();
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}