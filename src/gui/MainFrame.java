package gui;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public MainFrame(){

        this.setVisible(true);
        this.setSize(400, 500);
        this.getContentPane().setBackground(new Color(28,28,28));
        ImageIcon image = new ImageIcon("next.png");
        this.setIconImage(image.getImage());
        this.setResizable(false);

    }

}
