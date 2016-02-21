package Gui;

import java.awt.BorderLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class ShapedWindow extends JFrame {

    public ShapedWindow() {

        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(200, 200);

        Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, 200, 100);
        Rectangle2D.Double rect = new Rectangle2D.Double(0, 100, 200, 200);

        Path2D path = new Path2D.Double();
        path.append(rect, true);
        path.append(ellipse, true);
        this.setShape(path);
        
        JButton closeButton = new JButton("Close");
        this.add(closeButton, BorderLayout.SOUTH);
        closeButton.addActionListener(e -> System.exit(0));
    }
/**
  * The platform must support PERPIXEL_TRANSPARENT translucency.
  * The window must be undecorated. we can make a JFrame or JDialog undecorated by calling the setUndecorated(false) method on them.
  * The window must not be in full-screen mode.
*/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShapedWindow frame = new ShapedWindow();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
