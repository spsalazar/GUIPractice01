import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GUI extends JFrame {

    JLabel newDog = null;
    int dogCount = 0;
    List<String> usedImages = new ArrayList<>();
    List<Point> imagePositions = new ArrayList<>();
    List<Integer> addButtonClicks = new ArrayList<>();
    List<Integer> reloadButtonClicks = new ArrayList<>();
    
    public GUI() {
        //title
        super("Bouncing Dogs");

        //Closes GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //sets GUI size
        setSize(900, 900);

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
                    Point pos = randomImagePos(newDog);
                    newDog.setIcon(loadImage(randomImage));
                    newDog.setBounds(pos.x, pos.y, 200, 200);
                    newDog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
                   playSound("DogBark.wav");

                   //resets image positions
                  addButtonClicks.add(1);
                  if (addButtonClicks.size() == 5) {
                    addButtonClicks.clear();
                    imagePositions.clear();
                  }
                  if (reloadButtonClicks.size() == 1) {
                    reloadButtonClicks.clear();
                    addButtonClicks.clear();
                    imagePositions.clear();
                  }
                  
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
                playSound("DogRemove.wav");
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
                reloadButtonActions();
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
        int x = random.nextInt(1000) + 1;
        int y = random.nextInt(800) + 1;
        image.setBounds(x, y, 200, 200);
        x += xSpeed;
        y += ySpeed;
        }
    }

    private Point randomImagePos(JLabel image) {
        Random random = new Random();
        int x = random.nextInt(700) + 1;
        int y = random.nextInt(700) + 1;
        while (x < 190 && y < 40) {
            x = random.nextInt(700) + 1;
            y = random.nextInt(700) + 1;
        }
        Point pos = new Point(x, y);
        while (overlapperChecker(imagePositions, pos, 300) || x < 190 && y < 40) {
            x = random.nextInt(700) + 1;
            y = random.nextInt(700) + 1;
            pos.setLocation(x, y); //sets the location of the image
        }
        imagePositions.add(pos);
        return pos;
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

    private static boolean overlapperChecker(List<Point> imagePositions2, Point point, int distance) {
        for (int i = 0; i < imagePositions2.size(); i++) {
            Point p = imagePositions2.get(i);
            if (p.distance(point) < distance) {
                return true;
            }
        }
        return false;
    }

    private void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error with playing sound: " + e.getMessage());
        }
    }

    private void reloadButtonActions() {
        imagePositions.clear();
        addButtonClicks.clear();
        reloadButtonClicks.add(1);
        Component[] components = getContentPane().getComponents();
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                String randomImage = uniqueImage();
                Point pos = randomImagePos(label);
                label.setIcon(loadImage(randomImage));
                label.setBounds(pos.x, pos.y, 200, 200);
                playSound("DogPant.wav");
            }
        }
    }
}


//addImageButton:
//Figure out a way to keep images from stacking onto each other

//bouncingImage:
//Figure out a way to make images bounce

//randomImagePos:
//Fix random position to stop overlapping images

//Fix issue when this sequence happens:
// Add Button is clicked 5 times, then reload button is clicked, then remove button is clicked 5 times, then add button is clicked, the prgoram crashes