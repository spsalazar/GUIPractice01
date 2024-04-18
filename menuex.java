import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;

public class menuex {

    static boolean mouseInsideMenu = false;
    static boolean mouseInsideSubmenu = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dropdown Menu Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel panel = new JPanel(new FlowLayout());
        frame.add(panel);

        //  //menu button
        //  JButton menuButton = new JButton();
        //  menuButton.setBounds(250, 10, 30, 30);
        
        //  //stuff inside menu
        //  JPopupMenu popupMenu = new JPopupMenu();
        //  JMenuItem backgroundColor = new JMenuItem("Background Color");

        //  JPopupMenu colorSubmenu = new JPopupMenu();
        //  colorSubmenu.add(new JMenuItem("Black"));
        //  backgroundColor.add(colorSubmenu);

        // backgroundColor.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //        // Get the source of the event, which is the clicked menu item
        // JMenuItem clickedItem = (JMenuItem) e.getSource();
        // System.out.println("Location of clicked menu item: " + clickedItem.getLocationOnScreen());
        // // Get the location of the clicked menu item on the screen
        // Point location = clickedItem.getLocationOnScreen();
        // // Calculate the y-coordinate to position the submenu just below the menu item
        // int y = location.y + clickedItem.getHeight();
        // // Show the submenu at the calculated location relative to the frame
        // colorSubmenu.show(frame, location.x + clickedItem.getWidth(), y);
        // System.out.println("Submenu shown");
        //     }
        // });

        // menuButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         popupMenu.show(menuButton, -95, menuButton.getHeight());
        //     }
        //  });

        // popupMenu.add(backgroundColor);
        // frame.add(menuButton);


        // frame.setVisible(true);




        //menu button
        JButton menuButton = new JButton();
        menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuButton.setBounds(740, 10, 30, 30);
       
        //stuff inside menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem backgroundColor = new JMenuItem("Background Color");
        backgroundColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPopupMenu colorSubmenu = new JPopupMenu();
        colorSubmenu.add(new JMenuItem("Black"));
        backgroundColor.add(colorSubmenu);

       backgroundColor.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               backgroundColor.add(colorSubmenu);
               colorSubmenu.show(backgroundColor, backgroundColor.getWidth(), 0);
           }
       });
       

        JMenuItem muteSound = new JMenuItem("Mute");
        muteSound.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        popupMenu.add(backgroundColor);
        popupMenu.add(muteSound);
        menuButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               popupMenu.show(menuButton, -95, menuButton.getHeight());
           }
        });

        add(menuButton);
    }
}


// JButton dropdownButton = new JButton("Dropdown");

        // JPopupMenu popupMenu = new JPopupMenu();

        // dropdownButton.setBounds(10, 10, 30, 30);


        // JMenuItem item1 = new JMenuItem("Option 1");
        // JMenuItem item2 = new JMenuItem("Option 2");
        // JMenuItem item3 = new JMenuItem("Option 3");
        // popupMenu.add(item1);
        // popupMenu.add(item2);
        // popupMenu.add(item3);
        // popupMenu.setPreferredSize(new Dimension(100, 80));

        // //Creates submenu
        // JPopupMenu submenu = new JPopupMenu();
        // for (int i = 1; i <= 10; i++) {
        //     submenu.add(new JMenuItem("Sub Option " + i));
        // }

        // //MouseListener to handle hover event for item1 and submenu
        // MouseAdapter menuHoverListener = new MouseAdapter() {
        //     @Override
        //     public void mouseEntered(MouseEvent e) {
        //         mouseInsideMenu = true;
        //         if (e.getSource() == item1) {
        //             submenu.show(item1, item1.getWidth(), 0);
        //         } else if (e.getSource() == submenu) {
        //             mouseInsideSubmenu = true;
        //         }
        //     }

        //     @Override
        //     public void mouseExited(MouseEvent e) {
        //         // mouseInsideMenu = false;
        //         // if (!mouseInsideSubmenu) {
        //         //     submenu.setVisible(false);
        //         //     mouseInsideMenu = false;
        //         // }
        //         if (e.getSource() == submenu) {
        //             mouseInsideSubmenu =false;
        //         }
        //         if (!mouseInsideMenu && !mouseInsideSubmenu) {
        //             submenu.setVisible(false);
        //         }
        //     }
        // };

        // //MouseListener to handle hover event for submenu
        // submenu.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseEntered(MouseEvent e) {
        //         mouseInsideSubmenu = true;
        //     }

        //     @Override
        //     public void mouseExited(MouseEvent e) {
        //         mouseInsideSubmenu = false;
        //         if (!mouseInsideMenu) {
        //             submenu.setVisible(false);
        //         }
        //     }
        // });

        // item1.addMouseListener(menuHoverListener);

        // //Add ActionListener to each JMenuItem in the submenu
        // for (Component subItem : submenu.getComponents()) {
        //     if (subItem instanceof JMenuItem) {
        //         JMenuItem menuItem = (JMenuItem) subItem;
        //         menuItem.addActionListener(new ActionListener() {
        //             public void actionPerformed(ActionEvent e) {
        //                 JOptionPane.showMessageDialog(frame, menuItem.getText() + " selected");
        //             }
        //         });
        //     }
        // }


        // dropdownButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         popupMenu.show(dropdownButton, 0, dropdownButton.getHeight());
        //     }
        // });

        // panel.add(dropdownButton);

        // //Use timer to check if the mouse is inside the menu/submenu and keep them open
        // Timer timer = new Timer(100, new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         if (!mouseInsideMenu && !popupMenu.isShowing() && submenu.isVisible()) {
        //             submenu.setVisible(false);
        //         }
        //     }
        // });