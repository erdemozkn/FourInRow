
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame win = new JFrame("Four in a row Game");
        win.setSize(900, 785);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = win.getContentPane();
        c.setBackground(Color.LIGHT_GRAY);
        win.add(new Game());
        win.setVisible(true);
    }
}
