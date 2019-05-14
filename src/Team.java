
import java.util.ArrayList; 

import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 

public class Team {
	private static ArrayList<String> landen = new ArrayList<String>(); 
	private static ArrayList<String> divisies = new ArrayList<String>(); 
	private static ArrayList<String> teams  = new ArrayList<String>(); 
	private static ArrayList<String> stadions  = new ArrayList<String>();  
	
	private final static String inputFileTeams = "Teams.txt"; //Naam file wordt vastgelegd en kan eenvoudig aangepast worden.
	
	public static void initiateTeams() { 
		Scanner lineReader = null; 
		Scanner wordReader = null; 
		String currentLine;
		String word; 
		File inputFileT = new File(inputFileTeams);  //File vastleggen waaruit we gaan lezen.  
		int i=0; 
		try { 
			lineReader = new Scanner(inputFileT);	//Proberen of lijn gelezen kan worden.
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
					landen.add(word);
				} 
				if (i==2) { 
					divisies.add(word);
				} 
				if (i==3) { 
					teams.add(word);	
				} 
				if (i==4) { 
					stadions.add(word);
				}
			} 
			i = 0;
		} 
		lineReader.close(); 
		wordReader.close();
	}  
	
	public static ArrayList<String> getLandenNoDuplicates() { 
		ArrayList<String> noDuplicates = new ArrayList<String>();  
		for (String word : landen) { 
			if(noDuplicates.contains(word) == false) { 
				noDuplicates.add(word);
			}
		} 
		noDuplicates.sort(String::compareToIgnoreCase);
		return noDuplicates; 
	} 
	
	public static ArrayList<String> getDivisiesFilteredNoDuplicates(String land) { 
		ArrayList<String> filtered = new ArrayList<String>();  
		ArrayList<String> filteredNoDuplicates = new ArrayList<String>();
		int i; 
		String readWord, addThisWord;
		for (i=0;i<landen.size();i++) {   //Met normale for iteratie over ArrayList wou hij niet werken
			readWord = landen.get(i);
			if(readWord.equals(land)) {  //bij == moet het hetzelfde object ofzoiets zijn en werkte het niet
				addThisWord = divisies.get(i);
				filtered.add(addThisWord);
			}
		}  
		for (String word : filtered) { 
			if(filteredNoDuplicates.contains(word) == false) { 
				filteredNoDuplicates.add(word);
			}
		} 
		filteredNoDuplicates.sort(String::compareToIgnoreCase);
		return filteredNoDuplicates;
	}
	
	public static ArrayList<String> getTeamsFiltered(String divisie) { 
		ArrayList<String> filtered = new ArrayList<String>(); 
		int i; 
		String readWord, addThisWord;
		for (i=0;i<divisies.size();i++) {   //Met normale for iteratie over ArrayList wou hij niet werken
			readWord = divisies.get(i);
			if(readWord.equals(divisie)) {  //bij == moet het hetzelfde object ofzoiets zijn en werkte het niet
				addThisWord = teams.get(i);
				filtered.add(addThisWord);
			}
		} 
		filtered.sort(String::compareToIgnoreCase);
		return filtered; 
	}  
	
	public static ArrayList<String> getTeamsFilteredWithoutHometeam(ArrayList<String> list, String team) { 
		ArrayList<String> filteredWithoutHometeam = new ArrayList<String>(); 
		int i; 
		String readWord, addThisWord;
		for (i=0;i<list.size();i++) {   //Met normale for iteratie over ArrayList wou hij niet werken
			readWord = list.get(i);
			if(readWord.equals(team) == false) {  //bij == moet het hetzelfde object ofzoiets zijn en werkte het niet
				addThisWord = list.get(i);
				filteredWithoutHometeam.add(addThisWord);
			}
		}  
		filteredWithoutHometeam.sort(String::compareToIgnoreCase);
		return filteredWithoutHometeam;
	}
	
	public static String getStadion(String team) { 
		String stadion = "Error";
		String readWord; 
		int i; 
		for(i=0;i<teams.size();i++) { 
			readWord = teams.get(i); 
			if(readWord.equals(team)) { 
				stadion = stadions.get(i); 
			}
		} 
		return stadion;
	}
	
}
	