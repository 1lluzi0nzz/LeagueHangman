import java.util.*;
import java.io.*;
public class hangman {
	
	public static ArrayList<String> hang = new ArrayList<String>();
	public static ArrayList<String> word = new ArrayList<String>();
	public static ArrayList<String> chosen = new ArrayList<String>();
	public static String choice = "";
	public static String hidden = "";
	
	public static void main(String[] args) {
		try{
			setUp();
		}catch(Exception e) {	
		}
	}
	
	public static void createHangman() throws FileNotFoundException {
		File words = new File("C:\\Users\\bwhit\\Documents\\Hangman\\hangman.txt");
		Scanner scn = new Scanner(words);
		while(scn.hasNext()) {
			String full = "";
			for(int i = 0; i < 9; i++ ) {
				String ln = scn.nextLine();
				full = full + ln + "\n";
			}
			hang.add(full);
		}
	}
	
	public static void createWord() throws FileNotFoundException{
		File words = new File("C:\\Users\\bwhit\\Documents\\Hangman\\champions.txt");
		Scanner scn = new Scanner(words);
		while(scn.hasNext()) {
			String champ = scn.nextLine();
			word.add(champ);
		}
	}
	
	public static String chooseWord(){
		Random ran = new Random();
		int ch = ran.nextInt(word.size());
		choice = word.get(ch).toUpperCase();
		String newWord = "|";
		for(int i = 0; i < choice.length(); i++) {
			char car = choice.charAt(i);
			newWord = newWord + car + "|";
		}
		return newWord;
	}
	
	public static String hideWord() {
		String newWord = "|";
		for(int i = 0; i < choice.length(); i++) {
			char car = choice.charAt(i);
			if(car != '|') {
				if(car == ' ') {
					newWord = newWord + car + "|";
				}else {
					newWord = newWord + "_|";
				}
			}
		}
		return newWord;
	}
	
	public static void setUp() throws FileNotFoundException {
		Scanner scn = new Scanner(System.in);
		int hangLvl = 6;
		createHangman();
		createWord();
	    choice = chooseWord();
		hidden = hideWord();
		System.out.println("Let's Play Hangman!\n" + hang.get(hangLvl) + "\n" + hidden);
		
        while(!hidden.equals(choice) && hangLvl > 0) {
        	System.out.println("What is your guess?");
    		String input = "" + scn.nextLine().toUpperCase().charAt(0);
    		chosen.add(input);
            if(check() == false) {
            	hangLvl--;
            }
            System.out.println(hang.get(hangLvl) + "\n" + hidden);
            if(choice.equals(hidden)) {
            	System.out.println("You Win!");
            	break;
            }else if(hangLvl == 0) {
            	System.out.println("You Lose!");
            }
        }
	}
	
	public static boolean check() {
		hidden = "|";
		boolean found = false;
			for(int i = 0; i < choice.length(); i++) {
				char car = choice.charAt(i);
				if(car != '|') {
					if(chosen.contains("" + car)) {
						hidden = hidden + car + "|";
						found = true;
					}else if(car == ' '){
						hidden = hidden + " " + "|";
					}else {
						hidden = hidden + "_|" ;
					}
				}
			}

		return found;
	}
	

}
