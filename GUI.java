import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;

public class GUI extends JFrame {

    JLabel newDog = null;
    int dogCount = 0;
    List<String> usedImages = new ArrayList<>();
    
    public GUI() {
        //title
        super("Bouncing Dogs");

        //Closes GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //sets GUI size
        setSize(800, 800);

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
          //count Text
          JButton countText = new JButton("0");
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

        //remove button
        JButton removeImageButton = new JButton("-");
        removeImageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeImageButton.setBounds(240, 10, 30, 30);
        removeImageButton.setEnabled(false);

        //add button
        JButton addImageButton = new JButton("+");
        addImageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addImageButton.setBounds(150, 10, 30, 30);
        addImageButton.addActionListener(new ActionListener() {
            boolean pressedOnce = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    newDog = new JLabel();
                    String randomImage = uniqueImage();
                    newDog.setIcon(loadImage(randomImage));
                    randomImagePos(newDog);
                    add(newDog);
                    revalidate();
                    repaint();
                   dogCount++;
                   countText.setText(Integer.toString(dogCount));
                   pressedOnce = true;
                   if (dogCount == 5) {
                    addImageButton.setEnabled(false);
                   }
                   // Enables remove button
                   removeImageButton.setEnabled(true);
            }
        });
        add(addImageButton);
        
        //removeButton actions
        removeImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adds all components(images) to the JFrame
                Component[] components = getContentPane().getComponents();
                for (int i = 0; i < components.length; i++) {
                    //Check if the component is a JLabel representing a dog image
                    Component component = components[i];
                    if (component instanceof JLabel) {
                        remove(component);
                        dogCount--;
                        countText.setText(Integer.toString(dogCount));
                        removeImageButton.setEnabled(dogCount > 0);
                        //Enables add button
                        addImageButton.setEnabled(dogCount < 5);
                        break;
                    }
                }
                revalidate();
                repaint();
            }
        });
        removeImageButton.setEnabled(false);
        add(removeImageButton);

         //reloads images
         JButton reloadImageButton = new JButton();
         reloadImageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         reloadImageButton.setBounds(110, 10, 30, 30);
         reloadImageButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                Component[] components = getContentPane().getComponents();
                for (int i = 0; i < components.length; i++) {
                    Component component = components[i];
                    if (component instanceof JLabel) {
                        JLabel label = (JLabel) component;
                        String randomImage = uniqueImage();
                        randomImagePos(label);
                        label.setIcon(loadImage(randomImage));
                    }
                }
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
        int x = random.nextInt(600) + 1;
        int y = random.nextInt(600) + 1;
        image.setBounds(x, y, 200, 200);
        x += xSpeed;
        y += ySpeed;
        }
    }

    private void randomImagePos(JLabel image) {
        Random random = new Random();
        int x = random.nextInt(600) + 1;
        int y = random.nextInt(600) + 1;
        while (x < 190 && y < 40) {
            x = random.nextInt(600) + 1;
            y = random.nextInt(600) + 1;
        }
        image.setBounds(x, y, 200, 200);
    }

    private String generateRandomImage() {
        Random random = new Random();
        int randomImage = random.nextInt(9) + 1;
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
            case 8: //Bat dog
            return "BatDog.jpg";
            case 9: //Shy dog
            return "shy.png";
        }
        return null;
    }

    private String uniqueImage() {
        Random random = new Random();
        String randomImage;
        do {
            randomImage = generateRandomImage();
            if (usedImages.size() == 5) {
                usedImages.clear();
            }
        } while (usedImages.contains(randomImage));
        usedImages.add(randomImage);
        return randomImage;
    }

    private void randomPosCheck() {
        Component[] component = getContentPane().getComponents();

    }

}

//addImageButton:
//Figure out a way to keep images from stacking onto each other

//bouncingImage:
//Figure out a way to make images bounce

