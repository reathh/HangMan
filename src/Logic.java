import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Logic {
	public static String[] images = { "01_base.png", "02_beamVertical.png",
			"03_girderL.png", "04_girderR.png", "05_beamHorizontal.png",
			"06_girderT.png", "07_SNARE.png", "008_head2.png", "009_Body2.png",
			"010_handR2.png", "011_handL2.png", "012_FoodL2.png",
			"012_FoodR2.png" };
	static Graphics g;
	static String realWord;
	static char[] dots;
	static StringBuffer guessWord;
	static byte MAX_MISTAKES = 13;
	static byte mistakes = 0;
	static boolean endIt = false;
	static int minletters = 5;
	static int maxletters = 13;
	static ArrayList<JLabel> picLabels = new ArrayList<JLabel>();

	// Start a new game
	public static void InitGame() {
		// Set errors to 0
		mistakes = 0;
		// Set a new game (Forget about last win/lose)
		endIt = false;
		// Get a random word using a GET request to an API
		realWord = GetENWord().toLowerCase();

		// Make a masked with dots word that we can show to the user
		dots = new char[realWord.length()];
		for (int i = 0; i < dots.length; i++) {
			dots[i] = '*';
		}
		// Show the user the masked word (We should edit a Label in the GUI)
		// System.out.println(dots);

		// Make a StringBuffer which we can later edit when we have a guess from
		// the user (It firstly is the same as the mask word)
		String s = new String(dots);
		guessWord = new StringBuffer(s);

		try {
			Paint(Home.guessWord);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Call this method with userInput
	public static void ProcessTurn(String userInput) {
		// Make the inputChar the input the user entered (The button he pressed)
		char inputChar = userInput.toLowerCase().charAt(0);

		// If user didn't guess a letter
		if (realWord.indexOf(inputChar) == -1) {
			mistakes++;
		}
		// User guessed
		if (realWord.indexOf(inputChar) != -1) {
			for (int i = 0; i < realWord.length(); i++) {
				if (realWord.charAt(i) == inputChar) {
					guessWord.setCharAt(i, inputChar);
				}
			}
		}
		// Paint the Hangman
		try {
			Paint(Home.guessWord);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Check if max mistakes are reached
		if (mistakes == MAX_MISTAKES) {
			URL url = null;
			try {
				url = new URL("file:lose.wav");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			 AudioClip clip = Applet.newAudioClip(url);
			 clip.play();

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(ENKeyboard.q,
					"You lost! The word you were searching for was: "
							+ realWord + "\nClick \"Start New Game\"",
					"You Lose!", JOptionPane.PLAIN_MESSAGE);

			endIt = true;
		}
		// Check if word is guessed
		if (guessWord.indexOf("*") == -1) {
			endIt = true;
			URL url = null;
			try {
				url = new URL("file:win.wav");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			AudioClip clip = Applet.newAudioClip(url);
			clip.play();

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(ENKeyboard.q, "YOU WIN!", "You WIN",
					JOptionPane.PLAIN_MESSAGE);

			endIt = true;
		}
	}

	// Paint some stuff
	public static void Paint(JLabel JguessWord) throws IOException {
	
		if (mistakes != 0) {
			BufferedImage myPicture = ImageIO.read(new File(images[mistakes - 1]));
		 	JLabel picLabel = new JLabel(new ImageIcon(myPicture)); 
			 picLabels.add(picLabel);
			UI.panel.add(picLabel, BorderLayout.EAST);	
		}  



		JguessWord.setText(new String(guessWord));
		UI.panel.revalidate();
	}

	private static String GetENWord() {

		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		do {
			result = "";
			try {
				url = new URL("http://randomword.setgetgo.com/get.php");
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				while ((line = rd.readLine()) != null) {
					result += line;
				}
				rd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// TODO check why this code doesn't work as expected
			// } while (result.length() <= minletters && result.length() >= maxletters
		} while (result.length() >= maxletters);
		return result;
	}

	private static String GetBGWord() throws IOException {
		Element word;
		do {
			Document doc = Jsoup
					.connect("http://rechnik.chitanka.info/random?").get();
			word = doc.getElementById("first-heading");

		} while (word.text().indexOf(" ") != -1
				&& word.text().length() <= minletters
				&& word.text().length() >= maxletters);
		return word.text();
	}
}