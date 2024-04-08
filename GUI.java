import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame {
    
    public GUI() {
        //title
        super("Hello World!");

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
        ImageIcon mainImage = new ImageIcon("/Users/ssalazar/GUIPractice01/funny-french-bulldog-puppy-maika-777.jpg");
        setIconImage(mainImage.getImage());
    }

    private void GUIComponents() {
        //Hello Friend text
        JLabel text01 = new JLabel("Hello friend");
        text01.setBounds(5, 5, 351, 45);
        add(text01);

        //adds french bulldog image
        JLabel dogTie = new JLabel();
        dogTie.setIcon(loadImage("/Users/ssalazar/GUIPractice01/funny-french-bulldog-puppy-maika-777.jpg"));
        dogTie.setBounds(100, 200, 200, 200);
        add(dogTie);

        
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


}
