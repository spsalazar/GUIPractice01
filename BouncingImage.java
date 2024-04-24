import java.awt.*;
// import java.awt.Graphics;
import java.awt.event.ActionEvent; //
// import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BouncingImage extends JPanel {
    private int x = 0, y = 0;
    private int xSpeed = 2, ySpeed = 2;
    private JLabel image;

    public BouncingImage() {
        setLayout(null);
        image = new JLabel(new ImageIcon("IMG_0070.jpg")); // Change "IMG_0070.jpg" to the path of your image
        image.setBounds(x, y, image.getPreferredSize().width, image.getPreferredSize().height);
        add(image);

        Timer gameLoop = new Timer(10, (ActionEvent e) -> {
            x += xSpeed;
            y += ySpeed;

            if (x <= 0 || x >= getWidth() - image.getWidth()) {
                xSpeed = -xSpeed;
            }
            if (y <= 0 || y >= getHeight() - image.getHeight()) {
                ySpeed = -ySpeed;
            }

            image.setBounds(x, y, image.getPreferredSize().width, image.getPreferredSize().height);
            repaint();
        });
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        BouncingImage bouncingImage = new BouncingImage();
        frame.add(bouncingImage);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}