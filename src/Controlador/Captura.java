/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Fuente=> https://javalangnullpointer.wordpress.com/2007/03/30/capturar-la-pantalla-con-java-screen-capture/
 */
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Captura {

    static public void captureScreen(String fileName) throws Exception {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File(fileName));
    }
//----

    public void Capturar() {
        try {
            System.out.println("[ Captura iniciada ]");
            //sleep 5 sg
            Thread.currentThread().sleep(10);
            String FILENAME = "C:/CapturaCorposoft/capturaError.png";
            Captura.captureScreen(FILENAME);
            System.out.println("[ Captura finalizada ]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//----
}
//end of class Captura
