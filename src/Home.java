import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class Home extends JPanel implements ActionListener {
	public JMenuItem restart, easy, medium, hard, bulgarian, english, fileExit;
	public static JLabel guessWord;
	public Home() throws IOException {

		// Label containing the guess word
		guessWord = new JLabel("guessWord");
		guessWord.setHorizontalAlignment(SwingConstants.CENTER);
		guessWord.setBounds(1, 30, 600, 40);
		guessWord.setFont(new Font("TimesRoman", Font.BOLD, 30));

		// The menu bar
		JMenuBar menubar = new JMenuBar();
		// create icons
		ImageIcon iconRestart = new ImageIcon("restart.png");
		ImageIcon iconExit = new ImageIcon("exit.png");

		JMenu options = new JMenu("Options");

		JMenu difficulty = new JMenu("Difficult");
		// set submenu for difficult
		easy = new JMenuItem("Easy");
		easy.addActionListener(this);
		medium = new JMenuItem("Medium");
		medium.addActionListener(this);
		hard = new JMenuItem("Hard");
		hard.addActionListener(this);

		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);

		JMenu language = new JMenu("Language");
		// set submenu for language
		bulgarian = new JMenuItem("Bulgarian");
		english = new JMenuItem("English");

		language.add(bulgarian);
		language.add(english);

		restart = new JMenuItem("Restart", iconRestart);
		restart.addActionListener(this);
		restart.requestFocus();
		// set shortcut for restart
		restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.CTRL_MASK));
		// exit event
		fileExit = new JMenuItem("Exit", iconExit);
		fileExit.addActionListener(this);
		fileExit.requestFocus();
		// set shortcut for exit
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.CTRL_MASK));

		menubar.add(options);
		options.add(difficulty);
		options.add(language);
		options.add(restart);
		options.add(fileExit);

		UI.panel.add(guessWord);

		UI.panel.setLayout(new BorderLayout());
		UI.panel.add(menubar, BorderLayout.NORTH);

		

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == restart) {
			
			for (JLabel picLabel : Logic.picLabels) {
				UI.panel.remove(picLabel);
			}
			
			Logic.InitGame();
			for (int i = 0; i < ENKeyboard.keyboard.length; i++) {
				ENKeyboard.keyboard[i].setEnabled(true);
			}

		} else if (source == fileExit) {
			System.exit(0);
		} else if (source == easy) {
			Logic.minletters = 1;
			Logic.maxletters = 5;
		} else if (source == medium) {
			Logic.minletters = 5;
			Logic.maxletters = 13;
		} else if (source == hard) {
			Logic.minletters = 13;
			Logic.maxletters = 50;
		}
	}
}