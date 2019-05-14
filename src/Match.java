
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList; 
import java.util.Random;
import java.util.Scanner;

public class Match {
	
	private static ArrayList<String> wedstrijdIds = new ArrayList<String>();
	private static ArrayList<String> listLanden = new ArrayList<String>(); 
	private static ArrayList<String> listDivisies = new ArrayList<String>() ; 
	private static ArrayList<String> listThuisteams = new ArrayList<String>(); 
	private static ArrayList<String> listUitteams = new ArrayList<String>(); 
	private static ArrayList<String> listDatums = new ArrayList<String>();  
	private static ArrayList<String> listTijden = new ArrayList<String>();
	
	private final static String FileMatchenDatabase = "MatchenDatabase.txt"; 
	
	public static void addNewMatch(String id,String land, String divisie, String thuisteam, String uitteam, String datum, String tijd){ 
		wedstrijdIds.add(id);
		listLanden.add(land); 
		listDivisies.add(divisie); 
		listThuisteams.add(thuisteam); 
		listUitteams.add(uitteam); 
		listDatums.add(datum); 
		listTijden.add(tijd);
	}
	
	public static int generateWedstrijdId() { 
		Random rand = new Random(); 
		int randomNum = rand.nextInt(100000) + 1; // nextInt is exclusief maximale waarde dus +1 om die waarde te krijgen.
		return randomNum;
	}  
	
	public static void saveMatchen() { 
		String currentWedstrijdId,currentLand, currentDivisie, currentDatum, currentTijd,currentThuisteam, currentUitteam, writeThis; 
		int i=0;
		BufferedWriter outputWriter = null;
		try { 
			File outputFile = new File(FileMatchenDatabase); 
			outputWriter = new BufferedWriter(new FileWriter(outputFile)); //true zorgt ervoor dat er aan de file wordt toegevoegd en geen nieuwe w gemaakt.
			for(i=0;i<wedstrijdIds.size();i++) { 
				currentWedstrijdId = wedstrijdIds.get(i); 
				currentLand = listLanden.get(i); 
				currentDivisie = listDivisies.get(i); 
				currentDatum = listDatums.get(i); 
				currentTijd = listTijden.get(i);  
				currentThuisteam = listThuisteams.get(i); 
				currentUitteam = listUitteams.get(i);
				writeThis = currentWedstrijdId + " " + currentLand + " "  + currentDivisie + " " + currentDatum + " " + currentTijd + " " + currentThuisteam + " " + currentUitteam; 
				outputWriter.write(writeThis); 
				outputWriter.newLine();
			} 
			outputWriter.flush();
		} catch (Exception e) { 
			e.printStackTrace();; 
		} finally{ 
			try { 
				outputWriter.close();
			} catch(Exception e) { 
				
			}
		}
	} 
	
	public static void loadSavedMatchen() { 
		Scanner lineReader = null; 
		Scanner wordReader = null; 
		String currentLine;
		String word; 
		File inputFile = new File(FileMatchenDatabase);  //File vastleggen waaruit we gaan lezen.  
		int i=0; 
		try { 
			lineReader = new Scanner(inputFile);	//Proberen of lijn gelezen kan worden.
		} 
		catch(FileNotFoundException e) { 
			System.out.println("File not found!"); 
		}  
		while (lineReader.hasNextLine() == true) {       //Checken op EOF.
			currentLine = lineReader.nextLine(); //Lezen van de volgende lijn in de textfile.
			wordReader = new Scanner(currentLine);  
			while(wordReader.hasNext()) { 
				word = wordReader.next(); 
				i = i+1;
				if (i==1) { 
					wedstrijdIds.add(word);
				} 
				if (i==2) { 
					listLanden.add(word);
				} 
				if (i==3) { 
					listDivisies.add(word);	
				} 
				if (i==4) { 
					listDatums.add(word);
				} 
				if (i==5) { 
					listTijden.add(word);
				} 
				if (i==6) { 
					listThuisteams.add(word);
				} 
				if (i==7) { 
					listUitteams.add(word);
				}
			}  
			wordReader.close();
			i = 0;
		} 
		lineReader.close(); 
	} 
	
	public static int checkMatchNotExists(String land, String divisie, String thuisteam, String uitteam) { 
		int i=0; 
		String readLand, readDivisie, readThuisteam, readUitteam;  
		int passed = 1; //als al bestaat gaat passed op 0 gezet worden. 
		int size = listLanden.size(); 
		if (listLanden.isEmpty() == false) { 
			while(i<size) { 
				readLand = listLanden.get(i); 
				readDivisie = listDivisies.get(i);
				readThuisteam = listThuisteams.get(i); 
				readUitteam = listUitteams.get(i);  
				i = i + 1;
				if(readLand.equals(land) && readDivisie.equals(divisie) && readThuisteam.equals(thuisteam) && readUitteam.equals(uitteam)) { 
					passed = 0;
				}		
			} 
		}
		if(passed == 1) { 
			return 1;
		} 
		else { 
			return 0;
		}
	} 
	
	public static String getIdAleadyExistingMatch(String land, String divisie, String thuisteam, String uitteam) { 
		String id = ""; 
		int i=0; 
		String readLand, readDivisie, readThuisteam, readUitteam;   
		int size = listLanden.size(); 
		if (listLanden.isEmpty() == false) { 
			while(i<size) { 
				readLand = listLanden.get(i); 
				readDivisie = listDivisies.get(i);
				readThuisteam = listThuisteams.get(i); 
				readUitteam = listUitteams.get(i);  
				if(readLand.equals(land) && readDivisie.equals(divisie) && readThuisteam.equals(thuisteam) && readUitteam.equals(uitteam)) { 
					id = wedstrijdIds.get(i);
				}	 
				i = i + 1;
			} 
		} 
		return id;
	} 
	
	public static String getDatumAleadyExistingMatch(String land, String divisie, String thuisteam, String uitteam) { 
		String datum = ""; 
		int i=0; 
		String readLand, readDivisie, readThuisteam, readUitteam;   
		int size = listLanden.size(); 
		if (listLanden.isEmpty() == false) { 
			while(i<size) { 
				readLand = listLanden.get(i); 
				readDivisie = listDivisies.get(i);
				readThuisteam = listThuisteams.get(i); 
				readUitteam = listUitteams.get(i);  
				if(readLand.equals(land) && readDivisie.equals(divisie) && readThuisteam.equals(thuisteam) && readUitteam.equals(uitteam)) { 
					datum = listDatums.get(i);
				}	 
				i = i + 1;
			} 
		} 
		return datum;
	} 
	
	public static String getTijdAleadyExistingMatch(String land, String divisie, String thuisteam, String uitteam) { 
		String tijd = ""; 
		int i=0; 
		String readLand, readDivisie, readThuisteam, readUitteam;   
		int size = listLanden.size(); 
		if (listLanden.isEmpty() == false) { 
			while(i<size) { 
				readLand = listLanden.get(i); 
				readDivisie = listDivisies.get(i);
				readThuisteam = listThuisteams.get(i); 
				readUitteam = listUitteams.get(i);  
				if(readLand.equals(land) && readDivisie.equals(divisie) && readThuisteam.equals(thuisteam) && readUitteam.equals(uitteam)) { 
					tijd = listTijden.get(i);
				}	 
				i = i + 1;
			} 
		} 
		return tijd;
	}
	
	public static void deleteMatch(String id) { 
		int i;  
		String readWord;
		for(i=0;i<wedstrijdIds.size();i++) { 
			readWord = wedstrijdIds.get(i); 
			if(readWord.equals(id)) { 
				wedstrijdIds.remove(i); 
				listLanden.remove(i); 
				listDivisies.remove(i); 
				listDatums.remove(i); 
				listTijden.remove(i);
				listThuisteams.remove(i); 
				listUitteams.remove(i); 
			}
		}
	} 
	
	public static ArrayList<String> getAllMatchIdsForTeam(String team) { 
		int i=0; 
		ArrayList<String> wantedIds = new ArrayList<String>();
		String readThuisteam, readUitteam, addThisId; 
		int size = listThuisteams.size(); 
		if (listThuisteams.isEmpty() == false) { 
			while(i<size) { 
				readThuisteam = listThuisteams.get(i); 
				readUitteam = listUitteams.get(i); 
				if(readThuisteam.equals(team) || readUitteam.equals(team)) { 
					addThisId = wedstrijdIds.get(i);  
					wantedIds.add(addThisId);
				}	 
				i = i + 1;
			} 
		} 
		return wantedIds;
	}  
	
	public static void sortLanden() { 
		String firstWord; 
		String secondWord;  
		char firstChar; 
		char secondChar; 
		String sorteerLand; 
		String sorteerDivisie; 
		String sorteerThuisteam; 
		String sorteerUitteam; 
		String sorteerDatum; 
		String sorteerTijd;  
		String sorteerId;
		int i=0; 
		while(i<wedstrijdIds.size()-1) { //sorteer landen alfabetisch
			firstWord = listLanden.get(i); 
			secondWord = listLanden.get(i+1); 
			firstChar = firstWord.charAt(0); 
			secondChar = secondWord.charAt(0); 
			if(firstChar > secondChar) {  
				sorteerId = wedstrijdIds.get(i);
				sorteerLand = listLanden.get(i); 
				sorteerDivisie = listDivisies.get(i); 
				sorteerDatum = listDatums.get(i); 
				sorteerTijd = listTijden.get(i); 
				sorteerThuisteam = listThuisteams.get(i); 
				sorteerUitteam = listUitteams.get(i);  
				wedstrijdIds.set(i, wedstrijdIds.get(i+1));  
				wedstrijdIds.set(i+1, sorteerId); 
				listLanden.set(i, listLanden.get(i+1));  
				listLanden.set(i+1, sorteerLand); 
				listDivisies.set(i, listDivisies.get(i+1));  
				listDivisies.set(i+1, sorteerDivisie); 
				listDatums.set(i, listDatums.get(i+1));  
				listDatums.set(i+1, sorteerDatum); 
				listTijden.set(i, listTijden.get(i+1));  
				listTijden.set(i+1, sorteerTijd); 
				listThuisteams.set(i, listThuisteams.get(i+1));  
				listThuisteams.set(i+1, sorteerThuisteam); 
				listUitteams.set(i, listUitteams.get(i+1));  
				listUitteams.set(i+1, sorteerUitteam); 
				i = 0;
			}  
			else { 
				i = i+1;
			}
		}  
	} 
	
	public static void sortDivisies() { 
		String firstWord; 
		String secondWord;  
		char firstChar; 
		char secondChar; 
		char thirdChar; 
		char fourthChar; 
		String sorteerId;
		String sorteerLand; 
		String sorteerDivisie; 
		String sorteerThuisteam; 
		String sorteerUitteam; 
		String sorteerDatum; 
		String sorteerTijd;  
		int i=0;  
		while(i<listLanden.size()-1) { //sorteer divisies alfabetisch
			if(listLanden.get(i).equals(listLanden.get(i+1))) { 
				firstWord = listDivisies.get(i); 
				secondWord = listDivisies.get(i+1); 
				firstChar = firstWord.charAt(0); 
				secondChar = secondWord.charAt(0); 
				if(firstChar > secondChar) {  
					sorteerId = wedstrijdIds.get(i);
					sorteerLand = listLanden.get(i); 
					sorteerDivisie = listDivisies.get(i); 
					sorteerDatum = listDatums.get(i); 
					sorteerTijd = listTijden.get(i); 
					sorteerThuisteam = listThuisteams.get(i); 
					sorteerUitteam = listUitteams.get(i);  
					wedstrijdIds.set(i, wedstrijdIds.get(i+1));  
					wedstrijdIds.set(i+1, sorteerId); 
					listLanden.set(i, listLanden.get(i+1));  
					listLanden.set(i+1, sorteerLand); 
					listDivisies.set(i, listDivisies.get(i+1));  
					listDivisies.set(i+1, sorteerDivisie); 
					listDatums.set(i, listDatums.get(i+1));  
					listDatums.set(i+1, sorteerDatum); 
					listTijden.set(i, listTijden.get(i+1));  
					listTijden.set(i+1, sorteerTijd); 
					listThuisteams.set(i, listThuisteams.get(i+1));  
					listThuisteams.set(i+1, sorteerThuisteam); 
					listUitteams.set(i, listUitteams.get(i+1));  
					listUitteams.set(i+1, sorteerUitteam); 
					i = 0;
				}   
				if(firstChar == secondChar) {  
					thirdChar = firstWord.charAt(1); 
					fourthChar = secondWord.charAt(1);  
					if(thirdChar > fourthChar) {  
						sorteerId = wedstrijdIds.get(i);
						sorteerLand = listLanden.get(i); 
						sorteerDivisie = listDivisies.get(i); 
						sorteerDatum = listDatums.get(i); 
						sorteerTijd = listTijden.get(i); 
						sorteerThuisteam = listThuisteams.get(i); 
						sorteerUitteam = listUitteams.get(i);  
						wedstrijdIds.set(i, wedstrijdIds.get(i+1));  
						wedstrijdIds.set(i+1, sorteerId); 
						listLanden.set(i, listLanden.get(i+1));  
						listLanden.set(i+1, sorteerLand); 
						listDivisies.set(i, listDivisies.get(i+1));  
						listDivisies.set(i+1, sorteerDivisie); 
						listDatums.set(i, listDatums.get(i+1));  
						listDatums.set(i+1, sorteerDatum); 
						listTijden.set(i, listTijden.get(i+1));  
						listTijden.set(i+1, sorteerTijd); 
						listThuisteams.set(i, listThuisteams.get(i+1));  
						listThuisteams.set(i+1, sorteerThuisteam); 
						listUitteams.set(i, listUitteams.get(i+1));  
						listUitteams.set(i+1, sorteerUitteam); 
						i = 0;
					}    
					else { 
						i = i+1;
					}
				}
				else { 
					i = i+1;
				}
			} 
			else { 
				i = i + 1;
			}
		}  
	} 
	
	public static void sortThuisteams() { 
		String firstWord; 
		String secondWord;  
		char firstChar; 
		char secondChar; 
		char thirdChar; 
		char fourthChar; 
		String sorteerId;
		String sorteerLand; 
		String sorteerDivisie; 
		String sorteerThuisteam; 
		String sorteerUitteam; 
		String sorteerDatum; 
		String sorteerTijd; 
		int i=0;  
		while(i<wedstrijdIds.size()-1) { //sorteer thuisteams alfabetisch
			if(listDivisies.get(i).equals(listDivisies.get(i+1))) { 
				firstWord = listThuisteams.get(i); 
				secondWord = listThuisteams.get(i+1); 
				firstChar = firstWord.charAt(0); 
				secondChar = secondWord.charAt(0); 
				if(firstChar > secondChar) {  
					sorteerId = wedstrijdIds.get(i);
					sorteerLand = listLanden.get(i); 
					sorteerDivisie = listDivisies.get(i); 
					sorteerDatum = listDatums.get(i); 
					sorteerTijd = listTijden.get(i); 
					sorteerThuisteam = listThuisteams.get(i); 
					sorteerUitteam = listUitteams.get(i);  
					wedstrijdIds.set(i, wedstrijdIds.get(i+1));  
					wedstrijdIds.set(i+1, sorteerId); 
					listLanden.set(i, listLanden.get(i+1));  
					listLanden.set(i+1, sorteerLand); 
					listDivisies.set(i, listDivisies.get(i+1));  
					listDivisies.set(i+1, sorteerDivisie); 
					listDatums.set(i, listDatums.get(i+1));  
					listDatums.set(i+1, sorteerDatum); 
					listTijden.set(i, listTijden.get(i+1));  
					listTijden.set(i+1, sorteerTijd); 
					listThuisteams.set(i, listThuisteams.get(i+1));  
					listThuisteams.set(i+1, sorteerThuisteam); 
					listUitteams.set(i, listUitteams.get(i+1));  
					listUitteams.set(i+1, sorteerUitteam); 
					i = 0;
				}   
				if(firstChar == secondChar) {  
					thirdChar = firstWord.charAt(1); 
					fourthChar = secondWord.charAt(1);  
					if(thirdChar > fourthChar) { 
						sorteerId = wedstrijdIds.get(i);
						sorteerLand = listLanden.get(i); 
						sorteerDivisie = listDivisies.get(i); 
						sorteerDatum = listDatums.get(i); 
						sorteerTijd = listTijden.get(i); 
						sorteerThuisteam = listThuisteams.get(i); 
						sorteerUitteam = listUitteams.get(i);  
						wedstrijdIds.set(i, wedstrijdIds.get(i+1));  
						wedstrijdIds.set(i+1, sorteerId);
						listLanden.set(i, listLanden.get(i+1));  
						listLanden.set(i+1, sorteerLand); 
						listDivisies.set(i, listDivisies.get(i+1));  
						listDivisies.set(i+1, sorteerDivisie); 
						listDatums.set(i, listDatums.get(i+1));  
						listDatums.set(i+1, sorteerDatum); 
						listTijden.set(i, listTijden.get(i+1));  
						listTijden.set(i+1, sorteerTijd); 
						listThuisteams.set(i, listThuisteams.get(i+1));  
						listThuisteams.set(i+1, sorteerThuisteam); 
						listUitteams.set(i, listUitteams.get(i+1));  
						listUitteams.set(i+1, sorteerUitteam); 
						i = 0;
					}    
					else { 
						i = i+1;
					}
					
				}
				else { 
					i = i+1;
				}
			} 
			else { 
				i = i + 1;
			}
		} 
	} 
	
	public static void sortUitteams() { 
		String firstWord; 
		String secondWord;  
		char firstChar; 
		char secondChar; 
		char thirdChar; 
		char fourthChar; 
		String sorteerId;
		String sorteerLand; 
		String sorteerDivisie; 
		String sorteerThuisteam; 
		String sorteerUitteam; 
		String sorteerDatum; 
		String sorteerTijd; 
		int i=0; 
		while(i<wedstrijdIds.size()-1) { //sorteer uitteams alfabetisch
			if(listThuisteams.get(i).equals(listThuisteams.get(i+1))) { 
				firstWord = listUitteams.get(i); 
				secondWord = listUitteams.get(i+1); 
				firstChar = firstWord.charAt(0); 
				secondChar = secondWord.charAt(0); 
				if(firstChar > secondChar) {  
					sorteerId = wedstrijdIds.get(i);
					sorteerLand = listLanden.get(i); 
					sorteerDivisie = listDivisies.get(i); 
					sorteerDatum = listDatums.get(i); 
					sorteerTijd = listTijden.get(i); 
					sorteerThuisteam = listThuisteams.get(i); 
					sorteerUitteam = listUitteams.get(i);  
					wedstrijdIds.set(i, wedstrijdIds.get(i+1));  
					wedstrijdIds.set(i+1, sorteerId);
					listLanden.set(i, listLanden.get(i+1));  
					listLanden.set(i+1, sorteerLand); 
					listDivisies.set(i, listDivisies.get(i+1));  
					listDivisies.set(i+1, sorteerDivisie); 
					listDatums.set(i, listDatums.get(i+1));  
					listDatums.set(i+1, sorteerDatum); 
					listTijden.set(i, listTijden.get(i+1));  
					listTijden.set(i+1, sorteerTijd); 
					listThuisteams.set(i, listThuisteams.get(i+1));  
					listThuisteams.set(i+1, sorteerThuisteam); 
					listUitteams.set(i, listUitteams.get(i+1));  
					listUitteams.set(i+1, sorteerUitteam); 
					i = 0;
				}   
				if(firstChar == secondChar) {  
					thirdChar = firstWord.charAt(1); 
					fourthChar = secondWord.charAt(1);  
					if(thirdChar > fourthChar) { 
						sorteerId = wedstrijdIds.get(i);
						sorteerLand = listLanden.get(i); 
						sorteerDivisie = listDivisies.get(i); 
						sorteerDatum = listDatums.get(i); 
						sorteerTijd = listTijden.get(i); 
						sorteerThuisteam = listThuisteams.get(i); 
						sorteerUitteam = listUitteams.get(i);  
						wedstrijdIds.set(i, wedstrijdIds.get(i+1));  
						wedstrijdIds.set(i+1, sorteerId);
						listLanden.set(i, listLanden.get(i+1));  
						listLanden.set(i+1, sorteerLand); 
						listDivisies.set(i, listDivisies.get(i+1));  
						listDivisies.set(i+1, sorteerDivisie); 
						listDatums.set(i, listDatums.get(i+1));  
						listDatums.set(i+1, sorteerDatum); 
						listTijden.set(i, listTijden.get(i+1));  
						listTijden.set(i+1, sorteerTijd); 
						listThuisteams.set(i, listThuisteams.get(i+1));  
						listThuisteams.set(i+1, sorteerThuisteam); 
						listUitteams.set(i, listUitteams.get(i+1));  
						listUitteams.set(i+1, sorteerUitteam); 
						i = 0;
					}    
					else { 
						i = i+1;
					}
				}
				else { 
					i = i+1;
				}
			} 
			else { 
				i = i + 1;
			}
		}
	}
	
	public static void sortArrayLists() {
		sortLanden(); 
		sortDivisies(); 
		sortThuisteams(); 
		sortUitteams();
	}
	
	public static String getAllMatches() { 
		String returnString = "";  
		int i=0;  
		String currentId;
		String currentLand; 
		String currentDivisie; 
		String currentDatum; 
		String currentTijd; 
		String currentThuisteam; 
		String currentUitteam; 
		String currentScore;
		sortArrayLists();
		for(i=0;i<wedstrijdIds.size();i++) {  
			currentId = wedstrijdIds.get(i);
			currentLand = listLanden.get(i); 
			currentDivisie = listDivisies.get(i); 
			currentDatum = listDatums.get(i); 
			currentTijd = listTijden.get(i); 
			currentThuisteam = listThuisteams.get(i); 
			currentUitteam = listUitteams.get(i);  
			currentScore = Handeling.getStringScoreMatch(currentId, currentThuisteam);
			returnString = returnString + currentLand + " " + currentDivisie + " " + currentDatum + " " + currentTijd + " " + currentThuisteam + " - " + currentUitteam + " " + currentScore + "\n";
		}
		return returnString; 
	}
	
}
