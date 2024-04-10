import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    JLabel newDog = null;
    int dogCount = 0;
    
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

        //adds french bulldog image
        JLabel dog01 = new JLabel();
        dog01.setIcon(loadImage(generateRandomImage()));
        randomImagePos(dog01);
        dog01.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(dog01);

          //count Text
          JButton countText = new JButton("1");
          countText.setBounds(179, 10, 62, 30);
          countText.setFont(new Font("Droid Sans Hebrew", Font.PLAIN, 20));
          countText.setEnabled(false);
          countText.setBackground(Color.WHITE);
          add(countText);

        //bounce button
        JButton bounceButton = new JButton("Bounce!");
        bounceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bounceButton.setBounds(10, 10, 90, 30);
        add(bounceButton);

        //add button
        JButton addImageButton = new JButton("+");
        addImageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addImageButton.setBounds(150, 10, 30, 30);
        addImageButton.addActionListener(new ActionListener() {
            boolean pressedOnce = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    newDog = new JLabel();
                    newDog.setIcon(loadImage(generateRandomImage()));
                    randomImagePos(newDog);
                    add(newDog);
                    revalidate();
                    repaint();
                   dogCount++;
                   countText.setText(Integer.toString(dogCount));
                   pressedOnce = true;
                
            }
        });
        add(addImageButton);
        
        //remove button
        JButton removeImageButton = new JButton("-");
        removeImageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeImageButton.setBounds(240, 10, 30, 30);
        removeImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newDog != null) {
                    remove(newDog);
                    revalidate();
                    repaint();
                    dogCount--;
                    countText.setText(Integer.toString(dogCount));
                    newDog = null;
                }
            }
        });
        add(removeImageButton);

         //reloads images
         JButton reloadImageButton = new JButton();
         reloadImageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         reloadImageButton.setBounds(110, 10, 30, 30);
         reloadImageButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 randomImagePos(dog01);
                 dog01.setIcon(loadImage(generateRandomImage()));
                 randomImagePos(newDog);
                 newDog.setIcon(loadImage(generateRandomImage()));
             }
         });
         add(reloadImageButton);


        
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
        while (x < 190 && y < 40) {
            x = random.nextInt(500) + 1;
            y = random.nextInt(500) + 1;
        }
        image.setBounds(x, y, 200, 200);
    }

    private String generateRandomImage() {
        Random random = new Random();
        int randomImage = random.nextInt(7) + 1;
        switch(randomImage) {
            case 1: //Frenchie Frog
            return "funny-french-bulldog-puppy-maika-777.jpg";
            case 2: //Dog in tie
            return "IMG_0070.jpg";
            case 3: //Sly dog
            return "url.jpg";
            case 4: //Eyebrow dog
            return "IMG_3010.jpg";
            case 5: //Crying Chihuahua
            return "IMG_3009.jpg";
            case 6: //Moy dog
            return "IMG_3005.jpg";
            case 7: //Lightskin Pitbull
            return "IMG_3008.jpg";
        }
        return null;
    }


}




//addImageButton:
//Figure out a way to keep adding a new image every click
//Figure out a way to keep images from stacking onto each other
//Figure out a way to stop image from resetting its position

//bouncingImage:
//Figure out a way to make images bounce

//removeImageButton:
//Figure out a way to remove both images

//fix bugs on add and remove image buttons