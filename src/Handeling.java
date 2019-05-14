import java.util.ArrayList; 

import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner;  
import java.io.FileWriter; 
import java.io.BufferedWriter;

public class Handeling { 
	
	private static ArrayList<String> handelingenList  = new ArrayList<String>();  
	private static ArrayList<String> wedstrijdIdList  = new ArrayList<String>(); 
	private static ArrayList<String> teamsList  = new ArrayList<String>(); 
	private static ArrayList<String> spelersList  = new ArrayList<String>(); 
	private static ArrayList<String> uitgevoerdeHandelingenList  = new ArrayList<String>(); 
	private static ArrayList<String> minutenList = new ArrayList<String>();
	
	private final static String inputFileHandelingen = "Handelingen.txt"; //Naam file wordt vastgelegd en kan eenvoudig aangepast worden.
	private final static String FileHandelingenDatabase = "HandelingenDatabase.txt"; // 

	public static void initiateHandelingen() { 
		Scanner lineReader = null; 
		Scanner wordReader = null; 
		String currentLine;
		String word; 
		File inputFileH = new File(inputFileHandelingen);  //File vastleggen waaruit we gaan lezen.  
		try { 
			lineReader = new Scanner(inputFileH);	//Proberen of lijn gelezen kan worden.
		} 
		catch(FileNotFoundException e) { 
			System.out.println("File not found!"); 
		}  
		while (lineReader.hasNextLine()) {       //Checken op EOF.
			currentLine = lineReader.nextLine(); //Lezen van de volgende lijn in de textfile.
			wordReader = new Scanner(currentLine);  
			while(wordReader.hasNext()) { 
				word = wordReader.next(); 
				handelingenList.add(word);
			}
		} 
		lineReader.close(); 
		wordReader.close();
	}   
	
	
	public static ArrayList<String> getHandelingen() { 
		return handelingenList;
	}  
	
	public static void addNewHandeling(String wedstrijdId, String team, String speler, String handeling, String minuut) { 
		wedstrijdIdList.add(wedstrijdId); 
		teamsList.add(team); 
		spelersList.add(speler); 
		uitgevoerdeHandelingenList.add(handeling); 
		minutenList.add(minuut);
	} 
	
	public static ArrayList<String> getTeamsForGivenMatch(String id) { 
		ArrayList<String> teamsForGivenMatch = new ArrayList<String>(); 
		int i;  
		String readWord, addThisWord;
		for(i=0;i<wedstrijdIdList.size();i++) { 
			readWord = wedstrijdIdList.get(i); 
			if(readWord.equals(id)) { 
				addThisWord = teamsList.get(i); 
				teamsForGivenMatch.add(addThisWord);
			}
		} 
		return teamsForGivenMatch;
	} 
	
	public static ArrayList<String> getSpelersForGivenMatch(String id) { 
		ArrayList<String> spelersForGivenMatch = new ArrayList<String>(); 
		int i;  
		String readWord, addThisWord;
		for(i=0;i<wedstrijdIdList.size();i++) { 
			readWord = wedstrijdIdList.get(i); 
			if(readWord.equals(id)) { 
				addThisWord = spelersList.get(i); 
				spelersForGivenMatch.add(addThisWord);
			}
		} 
		return spelersForGivenMatch;
	} 
	
	public static ArrayList<String> getHandelingenForGivenMatch(String id) { 
		ArrayList<String> handelingenForGivenMatch = new ArrayList<String>(); 
		int i;  
		String readWord, addThisWord;
		for(i=0;i<wedstrijdIdList.size();i++) { 
			readWord = wedstrijdIdList.get(i); 
			if(readWord.equals(id)) { 
				addThisWord = uitgevoerdeHandelingenList.get(i); 
				handelingenForGivenMatch.add(addThisWord);
			}
		} 
		return handelingenForGivenMatch;
	} 
	
	public static ArrayList<String> getMinutenForGivenMatch(String id) { 
		ArrayList<String> minutenForGivenMatch = new ArrayList<String>(); 
		int i;  
		String readWord, addThisWord;
		for(i=0;i<wedstrijdIdList.size();i++) { 
			readWord = wedstrijdIdList.get(i); 
			if(readWord.equals(id)) { 
				addThisWord = minutenList.get(i); 
				minutenForGivenMatch.add(addThisWord);
			}
		} 
		return minutenForGivenMatch;
	} 
	
	public static String getLastHandeling() { 
		int lastPos=0; 
		lastPos = uitgevoerdeHandelingenList.size() - 1; 
		String returnHandeling; 
		returnHandeling = uitgevoerdeHandelingenList.get(lastPos); 
		return returnHandeling;
	}
	
	public static void saveHandelingen() { 
		String currentWedstrijdId, currentTeam, currentSpeler, currentHandeling, currentMinuut, writeThis; 
		int i=0;
		BufferedWriter outputWriter = null;
		try { 
			File outputFile = new File(FileHandelingenDatabase); 
			outputWriter = new BufferedWriter(new FileWriter(outputFile));  //true zorgt ervoor dat er aan de file wordt toegevoegd en geen nieuwe w gemaakt.
			for(i=0;i<wedstrijdIdList.size();i++) { 
				currentWedstrijdId = wedstrijdIdList.get(i); 
				currentTeam = teamsList.get(i); 
				currentSpeler = spelersList.get(i); 
				currentHandeling = uitgevoerdeHandelingenList.get(i); 
				currentMinuut = minutenList.get(i); 
				writeThis = currentWedstrijdId + " " + currentTeam + " "  + currentSpeler + " " + currentHandeling + " " + currentMinuut; 
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
	
	public static void loadSavedHandelingen() { 
		Scanner lineReader = null; 
		Scanner wordReader = null; 
		String currentLine;
		String word; 
		File inputFile = new File(FileHandelingenDatabase);  //File vastleggen waaruit we gaan lezen.  
		int i=0; 
		try { 
			lineReader = new Scanner(inputFile);	//Proberen of lijn gelezen kan worden.
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
					wedstrijdIdList.add(word);
				} 
				if (i==2) { 
					teamsList.add(word);
				} 
				if (i==3) { 
					spelersList.add(word);	
				} 
				if (i==4) { 
					uitgevoerdeHandelingenList.add(word);
				} 
				if (i==5) { 
					minutenList.add(word);
				} 
			}  
			wordReader.close();
			if (i != 5) { 
				System.out.println("FOUT!!!");
			}
			i = 0;
		} 
		lineReader.close(); 
	} 
	
	public static void deleteHandelingen(String id) { 
		int i=0;  
		String readWord; 
		while(i<wedstrijdIdList.size()) { 
			readWord = wedstrijdIdList.get(i); 
			if(readWord.equals(id)) { 
				wedstrijdIdList.remove(i); 
				teamsList.remove(i); 
				spelersList.remove(i); 
				uitgevoerdeHandelingenList.remove(i); 
				minutenList.remove(i); 
			} 
			else { 
				i = i+1;
			}
		}
	} 
	
	public static void deleteLastHandeling(String id) { 
		int i=0;  
		String idLastElement; 
		i = wedstrijdIdList.size() -1;
		idLastElement = wedstrijdIdList.get(i);
		if(idLastElement.equals(id)) {
			wedstrijdIdList.remove(i); 
			teamsList.remove(i); 
			spelersList.remove(i); 
			uitgevoerdeHandelingenList.remove(i); 
			minutenList.remove(i);
		}
	}
	
	public static String getStatistiekenSpeler(String speler) { 
		int Gescoord = 0; 
		int OwnGoal = 0; 
		int SchotTussenDePalen = 0; 
		int SchotNaast = 0;
		int Assist = 0; 
		int Overtreding = 0; 
		int GeleKaart = 0; 
		int RodeKaart = 0; 
		String readSpeler; 
		String readHandeling;   
		String print;
		int i = 0;
		int size = spelersList.size(); 
		if (spelersList.isEmpty() == false) { 
			while(i<size) {  
				readSpeler = spelersList.get(i); 
				if (readSpeler.equals(speler)) { 
					readHandeling = uitgevoerdeHandelingenList.get(i);  
					if (readHandeling.equals("Gescoord")) {   
						Gescoord = Gescoord + 1;
					} 
					if (readHandeling.equals("OwnGoal")) {   
						OwnGoal = OwnGoal + 1;
					} 
					if (readHandeling.equals("SchotTussenDePalen")) {   
						SchotTussenDePalen = SchotTussenDePalen + 1;
					}
					if (readHandeling.equals("SchotNaast")) {   
						SchotNaast = SchotNaast + 1;
					}
					if (readHandeling.equals("Assist")) {   
						Assist = Assist + 1;
					}
					if (readHandeling.equals("Overtreding")) {   
						Overtreding = Overtreding + 1;
					}
					if (readHandeling.equals("GeleKaart")) {   
						GeleKaart = GeleKaart + 1;
					} 
					if (readHandeling.equals("RodeKaart")) {   
						RodeKaart = RodeKaart + 1;
					} 
					if (readHandeling.equals("RodeKaart(2xG)")) {  
						RodeKaart = RodeKaart + 1;
					}
				} 
				i = i + 1;
			}
		} 
		print = "Gescoord: " + Integer.toString(Gescoord) + "\nOwnGoal: " + Integer.toString(OwnGoal) + "\nSchotTussenDePalen: " + Integer.toString(SchotTussenDePalen); 
		print = print + "\nSchotNaast: " + Integer.toString(SchotNaast) + "\nAssist: " + Integer.toString(Assist) + "\nOvertreding: " + Integer.toString(Overtreding); 
		print = print + "\nGeleKaart: " + Integer.toString(GeleKaart) + "\nRodeKaart: " + Integer.toString(RodeKaart); 
		return print;
	}   
	
	public static String getStatistiekenTeam(ArrayList<String> ids, String team) { 
		String print=""; 
		int i = 0; 
		int j = 0; 
		int gespeeldeWedstrijden = 0; 
		int gescoordeDoelpunten = 0; 
		int tegenDoelpunten = 0; 
		int doelpuntenSaldo = 0; 
		int gewonnenWedstrijden = 0; 
		int verlorenWedstrijden = 0; 
		int gelijkeWedstrijden = 0; 
		int aantalPunten = 0; 
		int aantalSchotenBinnenKader = 0; 
		int aantalSchotenNaast = 0; 
		int uitslagWedstrijd = -15;
		int size = ids.size(); 
		int size2 = wedstrijdIdList.size(); 
		String readIdTeam="", readId="", readTeam="", readHandeling="";
		if (ids.isEmpty() == false) { 
			while(i<size) {  
				readIdTeam = ids.get(i);   
				gespeeldeWedstrijden = gespeeldeWedstrijden + 1;
				if (wedstrijdIdList.isEmpty() == false) { 
					while(j<size2) {
						readId = wedstrijdIdList.get(j); 
						if(readIdTeam.equals(readId)) { 
							readTeam = teamsList.get(j); 
							readHandeling = uitgevoerdeHandelingenList.get(j);  
							if(readTeam.equals(team)) { 
								if(readHandeling.equals("Gescoord")) { 
									gescoordeDoelpunten = gescoordeDoelpunten + 1;
								} 
								if(readHandeling.equals("OwnGoal")) { 
									tegenDoelpunten = tegenDoelpunten + 1;
								} 
								if(readHandeling.equals("SchotTussenDePalen")) { 
									aantalSchotenBinnenKader = aantalSchotenBinnenKader + 1;
								} 
								if(readHandeling.equals("SchotNaast")) { 
									aantalSchotenNaast = aantalSchotenNaast + 1;
								}  
							}
							else {  
								if(readHandeling.equals("Gescoord")) { 
									tegenDoelpunten = tegenDoelpunten + 1; 
								} 
								if(readHandeling.equals("OwnGoal")) { 
									gescoordeDoelpunten = gescoordeDoelpunten + 1; 
								}
							}  
						} 
						j = j + 1;
					} 
				}  
				uitslagWedstrijd = getScoreMatch(readIdTeam,team); 
				if(uitslagWedstrijd == 1) { 
					gewonnenWedstrijden = gewonnenWedstrijden + 1; 
				} 
				if(uitslagWedstrijd == 0) { 
					gelijkeWedstrijden = gelijkeWedstrijden + 1;
				} 
				if(uitslagWedstrijd == -1) { 
					verlorenWedstrijden = verlorenWedstrijden + 1;
				}
				i = i + 1; 
				j = 0;
			} 
		}  
		doelpuntenSaldo = gescoordeDoelpunten - tegenDoelpunten; 
		aantalPunten = 3*gewonnenWedstrijden + gelijkeWedstrijden;
		print = "Gespeelde wedstrijden: " + Integer.toString(gespeeldeWedstrijden) + "\nGewonnen wedstrijden: " + Integer.toString(gewonnenWedstrijden); 
		print = print + "\nGelijke wedstrijden: " + Integer.toString(gelijkeWedstrijden) + "\nVerloren wedstrijden: " + Integer.toString(verlorenWedstrijden); 
		print = print + "\nAantal punten: " + Integer.toString(aantalPunten) + "\nGescoorde doelpunten: " + Integer.toString(gescoordeDoelpunten); 
		print = print + "\nTegendoelpunten: " + Integer.toString(tegenDoelpunten) + "\nDoelpuntensaldo: " + Integer.toString(doelpuntenSaldo); 
		print = print + "\nSchoten binnen kader: " + Integer.toString(aantalSchotenBinnenKader) + "\nSchoten naast doel: " + Integer.toString(aantalSchotenNaast);
		return print;
	} 
	
	//Return 1 als team gewonnen heeft, 0 bij gelijkspel en -1 indien verloren.
	public static int getScoreMatch(String id, String team) { 
		int i=0;
		int scoreTeam = 0; 
		int scoreTegenstander = 0; 
		int resultaat = -10;
		int size = wedstrijdIdList.size();  
		String readId="", readTeam="", readHandeling="";
		if (wedstrijdIdList.isEmpty() == false) { 
			while(i<size) { 
				readId = wedstrijdIdList.get(i); 
				if(readId.equals(id)) { 
					readTeam = teamsList.get(i); 
					readHandeling = uitgevoerdeHandelingenList.get(i);
					if (readTeam.equals(team)) { 
						if(readHandeling.equals("Gescoord")) {
							scoreTeam = scoreTeam + 1;
						} 
						if(readHandeling.equals("OwnGoal")) { 
							scoreTegenstander = scoreTegenstander + 1;
						}
					} 
					else { 
						if(readHandeling.equals("Gescoord")) {
							scoreTegenstander = scoreTegenstander + 1;
						} 
						if(readHandeling.equals("OwnGoal")) { 
							scoreTeam = scoreTeam + 1;
						}
					}
				} 
				i = i + 1;
			}  
		}   
		if(scoreTeam > scoreTegenstander) { 
			resultaat = 1;
		} 
		if(scoreTeam == scoreTegenstander) { 
			resultaat = 0;
		} 
		if(scoreTeam < scoreTegenstander) { 
			resultaat = -1;
		} 
		return resultaat;
	}	
	
	public static boolean checkAlreadyGeel(String id, String speler) { 
		ArrayList<String> spelersForGivenMatch = new ArrayList<String>(); 
		ArrayList<String> handelingenForGivenMatch = new ArrayList<String>();  
		spelersForGivenMatch = getSpelersForGivenMatch(id); 
		handelingenForGivenMatch = getHandelingenForGivenMatch(id);
		int i=0;  
		int geleKaart = 0;
		String readSpeler, readHandeling;
		if(spelersForGivenMatch.isEmpty() == false) { 
			for(i=0;i<spelersForGivenMatch.size();i++) { 
				readSpeler = spelersForGivenMatch.get(i); 
				if(readSpeler.equals(speler)) { 
					readHandeling = handelingenForGivenMatch.get(i); 
					if(readHandeling.equals("GeleKaart")) { 
						geleKaart = 1;
					}
				}
			}
		} 
		if (geleKaart == 1) { 
			return true;
		} 
		else {
			return false;
		}
	}
	
	public static int getTotaalPunten(String team) { 
		int punten; 
		ArrayList<String> idsForThisTeam = new ArrayList<String>();
		idsForThisTeam = Match.getAllMatchIdsForTeam(team); 
		int gewonnenWedstrijden = 0; 
		int verlorenWedstrijden = 0; 
		int gelijkeWedstrijden = 0;  
		int uitslagWedstrijd = -15;
		int size = idsForThisTeam.size(); 
		int i=0;
		String readIdTeam="";
		if (idsForThisTeam.isEmpty() == false) { 
			while(i<size) {  
				readIdTeam = idsForThisTeam.get(i);   
				uitslagWedstrijd = getScoreMatch(readIdTeam,team); 
				if(uitslagWedstrijd == 1) { 
					gewonnenWedstrijden = gewonnenWedstrijden + 1; 
				} 
				if(uitslagWedstrijd == 0) { 
					gelijkeWedstrijden = gelijkeWedstrijden + 1;
				} 
				if(uitslagWedstrijd == -1) { 
					verlorenWedstrijden = verlorenWedstrijden + 1;
				}
				i = i + 1; 
			} 
		}   
		punten = 3*gewonnenWedstrijden + gelijkeWedstrijden;
		return punten; 
	} 
	
	public static int getDoelsaldo(String team) { 
		ArrayList<String> idsForThisTeam = new ArrayList<String>();
		idsForThisTeam = Match.getAllMatchIdsForTeam(team);
		int i = 0; 
		int j = 0;  
		int gescoordeDoelpunten = 0; 
		int tegenDoelpunten = 0; 
		int doelpuntenSaldo = 0; 
		int size = idsForThisTeam.size(); 
		int size2 = wedstrijdIdList.size(); 
		String readIdTeam="", readId="", readTeam="", readHandeling="";
		if (idsForThisTeam.isEmpty() == false) { 
			while(i<size) {  
				readIdTeam = idsForThisTeam.get(i);   
				if (wedstrijdIdList.isEmpty() == false) { 
					while(j<size2) {
						readId = wedstrijdIdList.get(j); 
						if(readIdTeam.equals(readId)) { 
							readTeam = teamsList.get(j); 
							readHandeling = uitgevoerdeHandelingenList.get(j);  
							if(readTeam.equals(team)) { 
								if(readHandeling.equals("Gescoord")) { 
									gescoordeDoelpunten = gescoordeDoelpunten + 1;
								} 
								if(readHandeling.equals("OwnGoal")) { 
									tegenDoelpunten = tegenDoelpunten + 1;
								} 
							}
							else {  
								if(readHandeling.equals("Gescoord")) { 
									tegenDoelpunten = tegenDoelpunten + 1; 
								} 
								if(readHandeling.equals("OwnGoal")) { 
									gescoordeDoelpunten = gescoordeDoelpunten + 1; 
								}
							}  
						} 
						j = j + 1;
					} 
				}  
				i = i + 1; 
				j = 0;
			} 
		}  
		doelpuntenSaldo = gescoordeDoelpunten - tegenDoelpunten;  
		return doelpuntenSaldo;
	}
	
	public static ArrayList<String> getKlassement(String divisie) { 
		ArrayList<String> teamsForDivisie = new ArrayList<String>(); 
		teamsForDivisie = Team.getTeamsFiltered(divisie);
		int i=0,puntenTeam1=0,puntenTeam2=0,doelsaldoTeam1=0,doelsaldoTeam2=0;
		String sorteerTeam; 
		while(i<teamsForDivisie.size()-1) { 
			puntenTeam1 = getTotaalPunten(teamsForDivisie.get(i)); 
			puntenTeam2 = getTotaalPunten(teamsForDivisie.get(i+1)); 
			doelsaldoTeam1 = getDoelsaldo(teamsForDivisie.get(i)); 
			doelsaldoTeam2 = getDoelsaldo(teamsForDivisie.get(i+1));
			if(puntenTeam1 < puntenTeam2) {
				sorteerTeam = teamsForDivisie.get(i); 
				teamsForDivisie.set(i, teamsForDivisie.get(i+1)); 
				teamsForDivisie.set(i+1, sorteerTeam);
				i = 0;
			}  
			if (puntenTeam1 == puntenTeam2) { 
				if(doelsaldoTeam1 < doelsaldoTeam2) { 
					sorteerTeam = teamsForDivisie.get(i); 
					teamsForDivisie.set(i, teamsForDivisie.get(i+1)); 
					teamsForDivisie.set(i+1, sorteerTeam);
					i = 0;
				} 
				else { 
					i = i+1;
				}
			}
			if (puntenTeam1 > puntenTeam2){ 
				i=i+1;
			}
		}
		return teamsForDivisie;
	}
	
	public static String getKlassementString(ArrayList<String> ids, String team) { 
		String klassementString="",offsetString=""; 
		int i = 0; 
		int j = 0; 
		int gespeeldeWedstrijden = 0; 
		int gescoordeDoelpunten = 0; 
		int tegenDoelpunten = 0; 
		int doelpuntenSaldo = 0; 
		int gewonnenWedstrijden = 0; 
		int verlorenWedstrijden = 0; 
		int gelijkeWedstrijden = 0; 
		int aantalPunten = 0;  
		int uitslagWedstrijd = -15;
		int size = ids.size(); 
		int size2 = wedstrijdIdList.size(); 
		String readIdTeam="", readId="", readTeam="", readHandeling="";
		if (ids.isEmpty() == false) { 
			while(i<size) {  
				readIdTeam = ids.get(i);   
				gespeeldeWedstrijden = gespeeldeWedstrijden + 1;
				if (wedstrijdIdList.isEmpty() == false) { 
					while(j<size2) {
						readId = wedstrijdIdList.get(j); 
						if(readIdTeam.equals(readId)) { 
							readTeam = teamsList.get(j); 
							readHandeling = uitgevoerdeHandelingenList.get(j);  
							if(readTeam.equals(team)) { 
								if(readHandeling.equals("Gescoord")) { 
									gescoordeDoelpunten = gescoordeDoelpunten + 1;
								} 
								if(readHandeling.equals("OwnGoal")) { 
									tegenDoelpunten = tegenDoelpunten + 1;
								}  
							}
							else {  
								if(readHandeling.equals("Gescoord")) { 
									tegenDoelpunten = tegenDoelpunten + 1; 
								} 
								if(readHandeling.equals("OwnGoal")) { 
									gescoordeDoelpunten = gescoordeDoelpunten + 1; 
								}
							}  
						} 
						j = j + 1;
					} 
				}  
				uitslagWedstrijd = getScoreMatch(readIdTeam,team); 
				if(uitslagWedstrijd == 1) { 
					gewonnenWedstrijden = gewonnenWedstrijden + 1; 
				} 
				if(uitslagWedstrijd == 0) { 
					gelijkeWedstrijden = gelijkeWedstrijden + 1;
				} 
				if(uitslagWedstrijd == -1) { 
					verlorenWedstrijden = verlorenWedstrijden + 1;
				}
				i = i + 1; 
				j = 0;
			} 
		}  
		doelpuntenSaldo = gescoordeDoelpunten - tegenDoelpunten; 
		aantalPunten = 3*gewonnenWedstrijden + gelijkeWedstrijden; 
		offsetString = spatiesTeam(team);
		klassementString = team + offsetString + spatiesGetal(gescoordeDoelpunten) + Integer.toString(gescoordeDoelpunten); 
		klassementString = klassementString + spatiesGetal(tegenDoelpunten) + Integer.toString(tegenDoelpunten); 
		klassementString = klassementString + spatiesGetal(doelpuntenSaldo) + Integer.toString(doelpuntenSaldo); 
		klassementString = klassementString + spatiesGetal(gespeeldeWedstrijden) + Integer.toString(gespeeldeWedstrijden); 
		klassementString = klassementString + spatiesGetal(gewonnenWedstrijden) + Integer.toString(gewonnenWedstrijden); 
		klassementString = klassementString + spatiesGetal(gelijkeWedstrijden) + Integer.toString(gelijkeWedstrijden); 
		klassementString = klassementString + spatiesGetal(verlorenWedstrijden) + Integer.toString(verlorenWedstrijden); 
		klassementString = klassementString + spatiesGetal(aantalPunten) + Integer.toString(aantalPunten); 
		return klassementString;
	}
	
	public static String makeKlassement(String divisie) { 
		String returnString="#      Team         D+  D-  DS  #W   W   G   V   P\n",currentTeam="",teamString="";  
		int i=0,j=0;
		ArrayList<String> klassement = new ArrayList<String>(); 
		klassement = getKlassement(divisie);
		ArrayList<String> idsForGivenTeam = new ArrayList<String>(); 
		for(i=0;i<klassement.size();i++){ 
			currentTeam  = klassement.get(i); 
			idsForGivenTeam = Match.getAllMatchIdsForTeam(currentTeam); 
			teamString = getKlassementString(idsForGivenTeam,currentTeam);  
			j = i+1;  
			returnString = returnString + j + ". " + teamString + "\n";
		}
		return returnString;
	}  
	
	public static void saveThisKlassement(String land,String divisie) { 
		BufferedWriter outputWriter = null; 
		String klassementOutputFile = "";   
		String writeThis = "";
		klassementOutputFile = klassementOutputFile + land + "-" + divisie + ".txt";  
		int i=0,j=0;
		ArrayList<String> klassement = new ArrayList<String>(); 
		klassement = getKlassement(divisie);
		ArrayList<String> idsForGivenTeam = new ArrayList<String>(); 
		String currentTeam="",teamString="";
		try { 
			File outputFile = new File(klassementOutputFile); 
			outputWriter = new BufferedWriter(new FileWriter(outputFile));  //true zorgt ervoor dat er aan de file wordt toegevoegd en geen nieuwe w gemaakt.
			writeThis ="#      Team         D+  D-  DS  #W   W   G   V   P";   
			outputWriter.write(writeThis);
			outputWriter.newLine();
			for(i=0;i<klassement.size();i++){ 
				currentTeam  = klassement.get(i); 
				idsForGivenTeam = Match.getAllMatchIdsForTeam(currentTeam); 
				teamString = getKlassementString(idsForGivenTeam,currentTeam);  
				j = i+1;  
				writeThis = j + ". " + teamString; 
				outputWriter.write(writeThis); 
				outputWriter.newLine();
			}
			outputWriter.flush();
		} catch (Exception e) { 
			e.printStackTrace();; 
		} finally { 
			try { 
				outputWriter.close();
			} catch(Exception e) { 
				
			}
		}
}
	
	public static String spatiesTeam(String team) {  
		int length = team.length(); 
		int aantalSpaties = 14 - length;  //12
		int i; 
		String returnString = "";
		for(i=0;i<=aantalSpaties;i++){ 
			returnString = returnString + " ";
		}
		return returnString;
	}

	public static String spatiesGetal(int number) { 
		String returnString=""; 
		if(number <= -10) { 
			returnString = " ";
		}
		if(number < 0  && number > -10) { 
			returnString = "  ";
		}   
		if(number >= 10) { 
			returnString = "  ";
		}
		if (number >= 0 && number < 10) { 
			returnString = "   ";
		}
		return returnString;
	}

	public static String getStringScoreMatch(String id, String thuisteam) { 
		int i=0;
		int scoreTeam = 0; 
		int scoreTegenstander = 0; 
		String resultaat = ""; 
		int size = wedstrijdIdList.size();  
		String readId="", readTeam="", readHandeling="";
		if (wedstrijdIdList.isEmpty() == false) { 
			while(i<size) { 
				readId = wedstrijdIdList.get(i); 
				if(readId.equals(id)) { 
					readTeam = teamsList.get(i); 
					readHandeling = uitgevoerdeHandelingenList.get(i);
					if (readTeam.equals(thuisteam)) { 
						if(readHandeling.equals("Gescoord")) {
							scoreTeam = scoreTeam + 1;
						} 
						if(readHandeling.equals("OwnGoal")) { 
							scoreTegenstander = scoreTegenstander + 1;
						}
					} 
					else { 
						if(readHandeling.equals("Gescoord")) {
							scoreTegenstander = scoreTegenstander + 1;
						} 
						if(readHandeling.equals("OwnGoal")) { 
							scoreTeam = scoreTeam + 1;
						}
					}
				} 
				i = i + 1;
			}  
		}   
		resultaat = Integer.toString(scoreTeam) + "-" + Integer.toString(scoreTegenstander);
		return resultaat;
	}
}


	

