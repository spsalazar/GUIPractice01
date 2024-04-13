import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class menuex {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dropdown Menu Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // JMenuBar menuBar = new JMenuBar();
        
        // JMenu menu = new JMenu("File");

        // JMenuItem item1 = new JMenuItem("New");
        // JMenuItem item2 = new JMenuItem("Open");
        // JMenuItem item3 = new JMenuItem("Save");
        // JMenuItem item4 = new JMenuItem("Exit");

        // menu.add(item1);
        // menu.add(item2);
        // menu.add(item3);
        // menu.addSeparator();
        // menu.add(item4);

        // // menuBar.add(menu);

        // // frame.setJMenuBar(menuBar);

        // JButton button = new JButton();


        JPanel panel = new JPanel(new FlowLayout());
        frame.add(panel);
        // String[] options = {"Option 1", "Option 2", "Option 3"};
        // JComboBox<String> dropdown = new JComboBox<>(options);
        // dropdown.setPreferredSize(new Dimension(150, 25));
        // panel.add(dropdown);

        JButton dropdownButton = new JButton("Dropdown");

        JPopupMenu popupMenu = new JPopupMenu();

        dropdownButton.setBounds(10, 10, 30, 30);


        JMenuItem item1 = new JMenuItem("Option 1");
        JMenuItem item2 = new JMenuItem("Option 2");
        JMenuItem item3 = new JMenuItem("Option 3");
        popupMenu.add(item1);
        popupMenu.add(item2);
        popupMenu.add(item3);
        popupMenu.setPreferredSize(new Dimension(100, 80));


        dropdownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(dropdownButton, 0, dropdownButton.getHeight());
            }
        });

        panel.add(dropdownButton);


        frame.setVisible(true);
    }
}
