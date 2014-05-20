import java.awt.Graphics;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Logic {

	static Graphics g;
	static  String realWord;
	static char[] dots;
	static StringBuffer guessWord;
	static List<Character> usedChars = new ArrayList<Character>();
	static String message;
	static byte MAX_MISTAKES = 12;
	static byte mistakes;
	static boolean endIt = false;
	
	public static void main(String[] args) throws IOException {
		
		//First we initialize the game with InitGame()
		//Then we call ProcessTurn(userInput) after a button is clicked
//		Scanner in = new Scanner(System.in);
//	InitGame();
//	while (true) {
//		String s = in.nextLine();
//		ProcessTurn(s);
//		paint(g);
//		if (endIt)
//			break;
//	}
//	
	}
	
	//Start a new game
	public static void InitGame() throws IOException { 
			//Set errors to 0
		mistakes = 0;
			//Set a new game (Forget about last win/lose)
		endIt = false;
	//Get a random word using a GET request to an API
			 realWord = GetENWord().toLowerCase();
			
			//Make a masked with dots word that we can show to the user
			dots = new char[realWord.length()];
			for (int i = 0; i < dots.length; i++) {
				dots[i] = '*'; 
			}
			//Show the user the masked word (We should edit a Label in the GUI)
			System.out.println(dots);
			
			//Make a StringBuffer which we can later edit when we have a guess from the user (It firstly is the same as the mask word)
			String s = new String(dots);
			guessWord = new StringBuffer(s);
			
			message = "";
			usedChars.clear();
	}
	
	//Call this method with userInput = TextBoxWithLetter.getText() 
	private static void ProcessTurn(String userInput) {
		
		char inputChar = userInput.toLowerCase().charAt(0);
		
		
		//Check if the user input is a letter
		if (!Character.isAlphabetic(inputChar)) {
			message = "Input must be a letter";
			return;
		}
		//Check if the user input is more than 1 letter
		if (userInput.length() > 1) {
			message = "Input must be 1 letter only";
			return;
		} 
		//Check if user input isn't already used
		if (usedChars.contains(inputChar)) {
			message = "Letter already used";
			return;
		}
		//Check if user hasn't already guessed this letter
		if (new String(guessWord).indexOf(inputChar) != -1) {
			message = "Letter has been already guessed";
			return;
		}
		
		
		//If user didn't guess a letter
		if (realWord.indexOf(inputChar) == -1) {
			message = "";
			mistakes++;
			usedChars.add(inputChar);
		}
		//User guessed
		if (realWord.indexOf(inputChar) != -1) {
			for (int i = 0; i < realWord.length(); i++) {
				if (realWord.charAt(i) == inputChar) {
					guessWord.setCharAt(i, inputChar);
				}
			}
		}
		
		//Check if max mistakes are reached
				if (mistakes == MAX_MISTAKES) {
					message = "You lost! The word you were searching for was: " + realWord + "\nClick \"Start New Game\"";
					endIt = true;
					return;
				}
				//Check if word is guessed
				if (guessWord.indexOf("*") == -1) {
					message = "You won! Click \"Start New Game\"";
					endIt = true;
					return;
				}
		
	}
	
	
	//Paint some stuff
	  public static void paint(JLabel JguessWord) {
			
          //If you want more or less errors just change variable MAX_ERRORS and edit the below ifs (add or remove)

          if (mistakes >  0){    
        	//Show a picture
        	  //g.DrawImage(pictureIllustrating 1 mistake)
          }
          if (mistakes ==  1){   
        	//Show a picture
          }
          if (mistakes == 2){
        	//Show a picture
          }
          if (mistakes == 3){
        	//Show a picture
          }
          if (mistakes == 4){    
        	//Show a picture
          }
          if (mistakes == 5){
        	//Show a picture
          }
          if (mistakes == 6){    
        	//Show a picture
          }
          if (mistakes == 7){   
        	//Show a picture
          }
          if (mistakes == 8){
        	//Show a picture
          }
          if (mistakes == 9){    
        	//Show a picture
          }
          if (mistakes == 10){
        	//Show a picture
          }
          if (mistakes == 11){    
        	//Show a picture
          }
          if (mistakes == 12){
        	//Show a picture
          }
          
          // Draw the messages(Already used letter; More than one letter; etc..) + the masked word + all used chars
          
          JguessWord.setText(new String(guessWord));
//			g.drawString( new String (guessWord), x, y);
//			
//          	for(Character usedchar : usedChars){
//			g.drawString(usedchar + " ", x, y);
//          	}
          System.out.println(message);
          System.out.println(guessWord);
//    	for(Character usedchar : usedChars){
//		System.out.println(usedchar + " ");
//    	}
    	System.out.println(usedChars + " ");
    	System.out.println("Mistakes: " + mistakes);
  
  }
	
	
		private static String GetENWord() {
		      URL url;
		      HttpURLConnection conn;
		      BufferedReader rd;
		      String line;
		      String result = "";
		      try {
		         url = new URL("http://randomword.setgetgo.com/get.php");
		         conn = (HttpURLConnection) url.openConnection();
		         conn.setRequestMethod("GET");
		         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		         while ((line = rd.readLine()) != null) {
		            result += line;
		         }
		         rd.close();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		      return result;
		   }
		private static String GetBGWord() throws IOException {
			Element word;
			do {
			Document doc = Jsoup.connect("http://rechnik.chitanka.info/random?").get();
			 word = doc.getElementById("first-heading");
		
			} while (word.text().indexOf(" ") != -1);
				return word.text();
		}

}