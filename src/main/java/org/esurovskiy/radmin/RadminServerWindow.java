package org.esurovskiy.radmin;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class RadminServerWindow extends JFrame {
    private JPanel rootPanel;

    public RadminServerWindow() throws IOException {
        setSize(500, 500);
        setContentPane(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BufferedImage image = ImageIO.read(
                new File("/home/esurovskiy/cat.jpeg"));
        getGraphics().drawImage(
                image,0,0, this);
    }

    public static void main(String[] args) throws Exception {
        RadminServerWindow window = new RadminServerWindow();
        new RadminServer().startServer(window.getGraphics(), window);
    }
}
