package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.GridLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class GradientWindow {

    JFrame frame = new JFrame("Gradient Window");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GradientWindow gWindow = new GradientWindow();
            gWindow.start();
        });
    }

    public GradientWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);         //Required
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);      //Centered
        frame.setBackground(new Color(0, 0, 0, 0));

        Container cPane = frame.getContentPane();
        cPane.setLayout(new GridLayout(4,0));

        frame.add(new TranslucentJPanel(Color.ORANGE));
        frame.add(new TranslucentJPanel(Color.WHITE));
        frame.add(new TranslucentJPanel(Color.GREEN));
        
        JButton closeButton = new JButton("Close");
        cPane.add(closeButton, BorderLayout.SOUTH);
        closeButton.addActionListener(e -> System.exit(0));

    }

    public void start() {
        frame.setVisible(true);
    }
}

class TranslucentJPanel extends JPanel {

    private int red = 240;
    private int green = 240;
    private int blue = 240;

    public TranslucentJPanel(Color bgColor) {
        this.red = bgColor.getRed();
        this.green = bgColor.getGreen();
        this.blue = bgColor.getBlue();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        float startPointX = 0.0f;
        float startPointY = 0.0f;
        float endPointX = width;
        float endPointY = 0.0f;
        Color startColor = new Color(red, green, blue, 255);
        Color endColor = new Color(red, green, blue, 0);

        Paint paint = new GradientPaint(startPointX, startPointY, startColor,
                endPointX, endPointY, endColor);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(paint);
        g2D.fillRect(0, 0, width, height);

    }
}
