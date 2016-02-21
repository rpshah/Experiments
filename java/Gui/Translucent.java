package Gui;

import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT;
import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

class Translucent {
  public static void main(String[] args) {
    GraphicsEnvironment graphicsEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice graphicsDevice = graphicsEnv.getDefaultScreenDevice();

    boolean isSupported = graphicsDevice.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT);
    System.out.println("PERPIXEL_TRANSPARENT  supported: " + isSupported);

    isSupported = graphicsDevice.isWindowTranslucencySupported(TRANSLUCENT);
    System.out.println("TRANSLUCENT  supported: " + isSupported);

    isSupported = graphicsDevice.isWindowTranslucencySupported(PERPIXEL_TRANSLUCENT);
    System.out.println("PERPIXEL_TRANSLUCENT  supported: " + isSupported);
  
    
    /**
     * PERPIXEL_TRANSPARENT: A pixel in a window is either opaque or transparent. That is, the alpha value for a pixel is either 0.0 or 1.0.
     * TRANSLUCENT: all pixels in a window have the same translucency, which can be defined by an alpha value between 0.0 and 1.0.
     * PERPIXEL_TRANSLUCENT: each pixel in a window can have its own alpha value between 0.0 and 1.0. It lets we define the translucency in a window on a per pixel basis.
    */
  
  }
}