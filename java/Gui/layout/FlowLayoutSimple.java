package Gui.layout;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.FlowLayout;

class FlowLayoutSimple{
    public static void main(String []args){
        JFrame frame = new JFrame("FlowLayout Simple");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cPane = frame.getContentPane();
        cPane.setLayout(new FlowLayout());
        
        for(int i =0; i<20 ;i++){
            cPane.add(new JButton("Button No ."+ i));
        }
        
        frame.setLocationRelativeTo(null);
        frame.setSize(500,500);
        //frame.pack();
        frame.setVisible(true);

    }
}