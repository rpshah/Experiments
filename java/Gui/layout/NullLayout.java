package Gui.layout;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Container;

class NullLayout{
    public static void main(String []args){
        JFrame frame = new JFrame("NULL LAYOUT ! ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);          //null means Absolute Positioning
        
        JButton b1 = new JButton("BUTTON 1 ");
        b1.setBounds(10,10,100,200);
        JButton b2 = new JButton("Hello World !");
        b2.setBounds(120,120,200,100);
        
        Container cPane = frame.getContentPane();
        cPane.setLayout(null);
        cPane.add(b1);
        cPane.add(b2);
        
        frame.setSize(400, 350);
        frame.setVisible(true);
    }
}