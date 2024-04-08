import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {

        //used to display the gui
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

}