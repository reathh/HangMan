import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import javafx.scene.layout.BackgroundImage;
public class UI extends JPanel {

	public static JPanel panel = new JPanel();
	public static JFrame frame = new JFrame("Hangman");

	public UI() {

	}

	public static void main(String[] args) throws IOException {
	
		new Home();
		panel.add(new ENKeyboard());

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Hangman-Game-red.png"));
		frame.setSize(600, 650);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		panel.setSize(600, 650);
		
		frame.getContentPane().add(panel);
		

		Logic.InitGame();

	}
}