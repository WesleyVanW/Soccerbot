//http://stackoverflow.com/questions/5815423/sorting-arraylist-in-alphabetical-order-case-insensitive

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Speler {

	private static ArrayList<String> teams  = new ArrayList<String>();  
	private static ArrayList<String> spelers  = new ArrayList<String>();  
	
	private final static String inputFileSpelers = "Spelers.txt"; //Naam file wordt vastgelegd en kan eenvoudig aangepast worden.

	public static void initiateSpelers() { 
		Scanner lineReader = null; 
		Scanner wordReader = null; 
		String currentLine;
		String word; 
		File inputFileS = new File(inputFileSpelers);  //File vastleggen waaruit we gaan lezen.  
		int i=0; 
		try { 
			lineReader = new Scanner(inputFileS);	//Proberen of lijn gelezen kan worden.
		} 
		catch(FileNotFoundException e) { 
			System.out.println("File not found!"); 
		}  
		while (lineReader.hasNextLine()) {       //Checken op EOF.
			currentLine = lineReader.nextLine(); //Lezen van de volgende lijn in de textfile.
			wordReader = new Scanner(currentLine);  
			while(wordReader.hasNext()) { 
				word = wordReader.next(); 
				i = i+1;
				if (i==1) { 
					teams.add(word);
				} 
				if (i==2) { 
					spelers.add(word);
				} 
			} 	
		i = 0;
		} 
		lineReader.close(); 
		wordReader.close();
	}  
	
	public static ArrayList<String> getTeams() { 
		return teams; 
	}

	public static ArrayList<String> getSpelers() { 
		return spelers; 
	} 
	
	public static ArrayList<String> getSpelersOneTeam(String team) { 
		ArrayList<String> spelersOneTeam = new ArrayList<String>(); 
		int i=0;  
		String readWord, addThisWord; 
		for(i=0;i<teams.size();i++) { 
			readWord = teams.get(i); 
			if(readWord.equals(team)) { 
				addThisWord = spelers.get(i);  
				spelersOneTeam.add(addThisWord);
			}
		} 
		spelersOneTeam.sort(String::compareToIgnoreCase);
		return spelersOneTeam;
	} 
	
	public static ArrayList<String> getSpelersOneTeamNoRedCards(String team, ArrayList<String> spelersRedCard) { 
		ArrayList<String> spelersOneTeam = new ArrayList<String>(); 
		spelersOneTeam = getSpelersOneTeam(team);   
		int i,j; 
		String readSpeler, readSpelerRedCard; 
		if (spelersRedCard.isEmpty() == false) { 
			for(i=0;i<spelersRedCard.size();i++) { 
				readSpelerRedCard = spelersRedCard.get(i); 
				for(j=0;j<spelersOneTeam.size();j++) { 
					readSpeler = spelersOneTeam.get(j); 
					if(readSpelerRedCard.equals(readSpeler)) { 
						spelersOneTeam.remove(j);
					}
				}
				
			} 
		} 
		return spelersOneTeam;
	}

}
