package Gui.layout;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;

class FlowLayoutAlignAndGap{
    public FlowLayoutAlignAndGap(){
        JFrame frame = new JFrame("Flow Layout 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        frame.setLocationRelativeTo(null);

        int horizontalGap = 20;
        int verticalGap = 10;
        FlowLayout layout = new FlowLayout(FlowLayout.TRAILING, horizontalGap, verticalGap);
        
        Container cPane = frame.getContentPane();
        cPane.setBackground(Color.red);
        cPane.setLayout(layout);
        
        for (int i = 1; i <= 25; i++) {
            cPane.add(new JButton("Button  " + i));
        }
        
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    
    public static void main(String []args){
        SwingUtilities.invokeLater(() -> {
            FlowLayoutAlignAndGap frame = new FlowLayoutAlignAndGap();
        });
    }
}