package Gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.BorderLayout;

class TransparentWindow{
    
    JFrame frame;
    
    public static void main(String []args){
        SwingUtilities.invokeLater(() -> {
            TransparentWindow testingWindow = new TransparentWindow();
            testingWindow.start();
        });
    }
    
    public TransparentWindow(){
        frame = new JFrame("A Transparent Window Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);     //To remove TitleBar and Other Stuff  
        frame.setOpacity(0.80f);            //Making Window Transparent(0.0 - 1.0)
        //To Make Frame Transparent 
        /**
         * The TRANSLUCENT translucency must be supported by the underlying system
         * The window must be undecorated (see Frame.setUndecorated(boolean) and Dialog.setUndecorated(boolean))
         * The window must not be in full-screen mode (see GraphicsDevice.setFullScreenWindow(Window)) 
         */
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);              // Center it on the screen
        
        Container contentPane = frame.getContentPane();
        
        JButton closeButton = new JButton("CLOSE");
        contentPane.add(closeButton, BorderLayout.SOUTH);
        closeButton.addActionListener(e -> System.exit(0));
    }
    
    private void start(){
        frame.setVisible(true);
    }
      
}