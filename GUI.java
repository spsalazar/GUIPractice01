import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame {
    
    public GUI() {
        //title
        super("Bouncing Dogs");

        //Closes GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //sets GUI size
        setSize(700, 700);

        //loads gui at the center of the screen
        setLocationRelativeTo(null);

        //positions components to set location
        setLayout(null);

        //removes ability to resize GUI
        setResizable(false);

        //adds GUI Components
        GUIComponents();

        //sets image icon
        ImageIcon mainImage = new ImageIcon("funny-french-bulldog-puppy-maika-777.jpg");
        setIconImage(mainImage.getImage());
    }

    private void GUIComponents() {
        //Hello Friend text
        // JLabel text01 = new JLabel("Hello friend");
        // text01.setBounds(5, 5, 400, 45);
        // text01.setFont(new Font("Droid Sans Hebrew", Font.PLAIN, 24));
        // add(text01);

        //adds french bulldog image
        JLabel dogTie = new JLabel();
        dogTie.setIcon(loadImage("funny-french-bulldog-puppy-maika-777.jpg"));
        randomImagePos(dogTie);
        dogTie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(dogTie);

        //bounce button
        JButton bounceButton = new JButton("Bounce!");
        bounceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bounceButton.setBounds(10, 10, 90, 30);
        add(bounceButton);

        
    }

    private ImageIcon loadImage(String image) {
        try {
            //reads image
            BufferedImage imagee = ImageIO.read(new File(image));
            return new ImageIcon(imagee);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Could not find image");
        return null;
    }


    //x pos, y pos, width, height
    private void bouncingImage(JLabel image) {
        Random random = new Random();
        int xSpeed = 3;
        int ySpeed = 3;
        while (true) {
        int x = random.nextInt(500) + 1;
        int y = random.nextInt(500) + 1;
        image.setBounds(x, y, 200, 200);
        x += xSpeed;
        y += ySpeed;
        }
    }

    private void randomImagePos(JLabel image) {
        Random random = new Random();
        int x = random.nextInt(500) + 1;
        int y = random.nextInt(500) + 1;
        while (x < 100 && y < 40) {
            x = random.nextInt(500) + 1;
            y = random.nextInt(500) + 1;
        }
        image.setBounds(x, y, 200, 200);
    }

    private void generateRandomImage() {
        
    }


}
