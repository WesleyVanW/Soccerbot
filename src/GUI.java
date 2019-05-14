//Reference 
//http://www.dreamincode.net/forums/topic/23017-basic-gui-tutorial-in-java/ 
//http://stackoverflow.com/questions/24226408/populating-a-jcombobox-with-an-arraylist-from-another-class 
//http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples 
//http://stackoverflow.com/questions/8632705/how-to-close-a-gui-when-i-push-a-jbutton 
//http://stackoverflow.com/questions/24174378/using-combo-box-values-with-a-button 
//http://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing 
//http://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html 
//http://stackoverflow.com/questions/9161659/how-do-i-detect-that-a-jcombobox-is-empty
//http://stackoverflow.com/questions/1614772/how-to-change-jframe-icon

//Import packages

import javax.swing.*; 
import static javax.swing.ScrollPaneConstants.*;  //Nodig om horizontale scrollbar te verbergen

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;   
import java.util.ArrayList;  

public class GUI {
	
	//Declare variables
	private static JFrame frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10, frame11, frame12, frame13, frame14, frame15, frame16, frame17, frame18;
	private static Container pane2, pane3, pane7, pane9, pane11, pane12, pane13, pane14, pane15, pane16, pane17;
	private static JButton btnLogWedstrijd, btnHerbekijkWedstrijd, btnVraagStatistiekenOp, btnGelogdeWedstrijden, btnKlassementen, btnBeginLoggen, btnAddHandeling, btnDeleteHandeling;
	private static JButton btnSaveMatch, btnRetry, btnDelete, btnYes, btnNo, btnStartHerbekijk, btnStatistiekSpeler, btnStatistiekTeam, btnSaveToFile, btnShowKlassement, btnMenu;
	private static JLabel lblWedstrijdId, lblLand, lblDivisie,lblThuisploeg, lblUitploeg, lblDatum, lblTijd,lblStadion, lblMinuut; 
	private static JLabel lblSelecteerTeam, lblSelecteerSpeler, lblSelecteerHandeling,lblScore,lblScoreThuisteam,lblScoreUitteam,lblMinuut2,lblOngeldigeInvoer; 
	private static JLabel lblStatistiekVraag,lblTeam,lblSpeler, lblGelogdeWedstrijden, lblKlassement,lblFoto,lblFotoThuisteam, lblFotoUitteam, lblNietOpgeslagen;
	private static JTextField txtDatum, txtTijd, txtMinuut; 
	private static JTextArea  areaHandelingenThuisteam, areaMinutenHandelingen, areaHandelingenUitteam, areaStatistiekenSpeler, areaStatistiekenTeam,areaGelogdeWedstrijden,areaKlassement;
	private static Insets  insets2, insets3, insets7, insets9, insets11, insets12, insets13, insets14, insets15, insets16, insets17;  
	private static JComboBox<String> landen,divisies,thuisteams,uitteams,spelendeTeams,spelendeSpelers,handelingen,teams, spelers;
	private static JScrollPane scrollGelogdeWedstrijden,scrollKlassement, scrollHandelingenThuisteam, scrollMinutenHandelingen, scrollHandelingenUitteam; 
	
	private static String iconFile = "voetbal.png";
	private static String achtergrondFile = "stadion7.jpg"; 
	private static String spelerFile = ""; 
	private static String teamFile = ""; 
	private static String thuisteamFile = ""; 
	private static String uitteamFile = "";
	private static ImageIcon achtergrondIcon = new ImageIcon(achtergrondFile); 
	private static Image achtergrondImage;  
	private static ImageIcon spelerIcon;
	private static Image spelerImage; 
	private static ImageIcon teamIcon;
	private static Image teamImage; 
	private static ImageIcon thuisteamIcon;
	private static Image thuisteamImage; 
	private static ImageIcon uitteamIcon;
	private static Image uitteamImage;
	private static Dimension dim;
	private static ArrayList<String> listLanden = new ArrayList<String>(); //gebruiken om alle landen uit klasse Teams in te laden zodat ze geselecteerd kunnen worden in combobox.
	private static ArrayList<String> listDivisiesFilteredNoDuplicates = new ArrayList<String>(); //divisies gefiltered vanuit Teams op één land 
	private static String land; //opmaak voor het loggen match scherm
	private static ArrayList<String> listThuisteamsFiltered = new ArrayList<String>(); //teams gefilterd vanuit Teams op één divisie.
	private static String divisie; //opmaak voor het loggen match scherm
	private static ArrayList<String> listUitteamsFiltered = new ArrayList<String>(); 
	private static String stadion; //gebruiken voor opmaak van het loggen match scherm
	private static String thuisteam = ""; //
	private static String uitteam = ""; //
	private static String datum = ""; //
	private static String tijd = ""; //
	private static ArrayList<String> listHandelingen = new ArrayList<String>();   //gebruiken om combobox handelingen te genereren nadat deze is opgehaald uit klasse Handeling.
	private static ArrayList<String> listSpelersFilteredOneTeam= new ArrayList<String>(); //gebruiken om combobox spelers te genereren vanuit Speler om de juiste spelers voor een team te tonen.
	private static ArrayList<String> listSpelersRedCard = new ArrayList<String>();
	private static String geselecteerdTeam; //gebruiken om een handeling aan een wedstrijd toe te voegen
	private static String currentWedstrijdId = "-1"; //
	private static String geselecteerdeSpeler; //
	private static String geselecteerdeHandeling; //
	private static String geselecteerdeMinuut;  //
	private static String scoreThuisteam = "0"; //
	private static String scoreUitteam = "0"; //
	private static ArrayList<String> teamsForGivenMatch = new ArrayList<String>();  //gebruiken om de uitgevoerde handelingen van een wedstrijd op te vragen.
	private static ArrayList<String> spelersForGivenMatch = new ArrayList<String>(); //
	private static ArrayList<String> handelingenForGivenMatch = new ArrayList<String>(); //
	private static ArrayList<String> minutenForGivenMatch = new ArrayList<String>(); // 
	private static ArrayList<String> listTeamsFiltered = new ArrayList<String>();  
	private static String statistiekenSpeler =""; 
	private static ArrayList<String> allIdsForGivenTeam = new ArrayList<String>(); 
	private static String statistiekenTeam =""; 
	private static String gelogdeWedstrijden = ""; 
	private static String klassementString = ""; 
	private static int reedsOpgeslagen = 0; 
	
	//Main methode
	public static void main (String args[]){
		beginScherm();  
	} 
	//Keuzemenu
	public static void beginScherm() { 
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {} 
		
		//Gets dimension of computer screen 
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Create the frame
		frame1 = new JFrame ("Soccerbot"); 
		frame1.setIconImage(new ImageIcon(iconFile).getImage()); 
		frame1.setLayout (new GridLayout(5,1)); 
		frame1.setResizable(false);
					
		//Create controls
		btnLogWedstrijd = new JButton ("Log een nieuwe wedstrijd");
		btnHerbekijkWedstrijd = new JButton ("Herbekijk een wedstrijd"); 
		btnVraagStatistiekenOp = new JButton ("Vraag statistieken op"); 
		btnGelogdeWedstrijden = new JButton ("Lijst reeds gelogde wedstrijden"); 
		btnKlassementen = new JButton ("Bekijk een klassement");

		//Load all players, handelingen, teams and databases containing past matches and its handelingen. 
		Team.initiateTeams();
		Speler.initiateSpelers();  
		Handeling.initiateHandelingen(); 
		Handeling.loadSavedHandelingen();
		Match.loadSavedMatchen();
		
		//Add all components to panel
		frame1.add (btnLogWedstrijd);
		frame1.add (btnHerbekijkWedstrijd); 
		frame1.add (btnVraagStatistiekenOp); 
		frame1.add (btnGelogdeWedstrijden); 
		frame1.add (btnKlassementen);
				
		frame1.pack(); 
		
		//Set frame visible and reposition to the middle of the screen 
		frame1.setLocation((dim.width/2)-(frame1.getSize().width/2), (dim.height/2)-(frame1.getSize().height/2));
		frame1.setVisible (true);

		//Button's action 
		btnLogWedstrijd.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				logWedstrijdScherm(); 
				frame1.setVisible(false);
			}
		}); 
		
		btnHerbekijkWedstrijd.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				herbekijkInvulScherm(); 
				frame1.setVisible(false);
			}
		});
		
		btnVraagStatistiekenOp.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				keuzeStatistieken();
				frame1.setVisible(false);
			}
		}); 
		
		btnGelogdeWedstrijden.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				gelogdeWedstrijden = Match.getAllMatches(); 
				showGelogdeWedstrijden();
				frame1.setVisible(false);
			}
		}); 
		
		btnKlassementen.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {  
				klassementDivisie();
				frame1.setVisible(false);
			}
		});
	}
	//Initialiseer te loggen match
	public static void logWedstrijdScherm() { 
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Create the frame
		frame2 = new JFrame ("Soccerbot");
		frame2.setSize (360,260);  
		frame2.setIconImage(new ImageIcon(iconFile).getImage());   
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(360, 260, Image.SCALE_SMOOTH); 
		frame2.setContentPane(new JLabel(new ImageIcon(achtergrondImage))); 
		frame2.setResizable(false);
		pane2 = frame2.getContentPane();					
		insets2 = pane2.getInsets();
		pane2.setLayout (null);  
	
		
		//Create controls 
		btnBeginLoggen = new JButton("Begin te loggen");;
		btnMenu = new JButton ("Terug naar menu");  
		lblLand = new JLabel ("Land:");
		lblDivisie = new JLabel ("Divisie:");
		lblThuisploeg = new JLabel ("Thuisploeg:");
		lblUitploeg = new JLabel ("Uitploeg:");
		lblDatum = new JLabel ("Datum (dd-mm-yy):"); 
		lblTijd = new JLabel("Tijd (uu:mm):"); 
		txtDatum = new JTextField(6); 
		txtTijd = new JTextField(4); 

		//create an empty combo box with items of type String 
		landen = new JComboBox<String>();  
		divisies = new JComboBox<String>(); 
		thuisteams = new JComboBox<String>(); 
		uitteams = new JComboBox<String>();
		
		//Inheriting ArrayLists 
		listLanden = Team.getLandenNoDuplicates();   
		
		//Add items to the combobox 
		for(String word : listLanden) { 
			landen.addItem(word);
		} 
		
		//Add all components to panel  
		pane2.add (btnBeginLoggen);
		pane2.add (btnMenu); 
		pane2.add (lblLand); 
		pane2.add (lblDivisie); 
		pane2.add (lblThuisploeg); 
		pane2.add (lblUitploeg); 
		pane2.add (lblDatum); 
		pane2.add (lblTijd); 
		pane2.add (txtDatum); 
		pane2.add (txtTijd);
		pane2.add(landen);  
		pane2.add(divisies); 
		pane2.add(thuisteams); 
		pane2.add(uitteams); 

		//Place all components  
		lblLand.setBounds(insets2.left + 180 - (lblLand.getPreferredSize().width/2), insets2.top + 10, lblLand.getPreferredSize().width, lblLand.getPreferredSize().height); 
		landen.setBounds(insets2.left + 180 - (landen.getPreferredSize().width /2), insets2.top + 30, landen.getPreferredSize().width, landen.getPreferredSize().height);
		lblDivisie.setBounds(insets2.left + 180 - (lblDivisie.getPreferredSize().width /2), insets2.top + 50, lblDivisie.getPreferredSize().width, lblDivisie.getPreferredSize().height);  
		divisies.setBounds(insets2.left + 120, insets2.top + 70, 120, 20);
		lblThuisploeg.setBounds(insets2.left + 90 - (lblThuisploeg.getPreferredSize().width /2) , insets2.top + 90, lblThuisploeg.getPreferredSize().width, lblThuisploeg.getPreferredSize().height); 
		thuisteams.setBounds(insets2.left + 40, insets2.top + 110, 100, 20);
		lblUitploeg.setBounds(insets2.left + 270 - (lblUitploeg.getPreferredSize().width /2), insets2.top + 90, lblUitploeg.getPreferredSize().width, lblUitploeg.getPreferredSize().height);  
		uitteams.setBounds(insets2.left + 220, insets2.top + 110, 100, 20);
		lblDatum.setBounds(insets2.left + 90 - (lblDatum.getPreferredSize().width /2), insets2.top + 130, lblDatum.getPreferredSize().width, lblThuisploeg.getPreferredSize().height); 
		lblTijd.setBounds(insets2.left + 270 - (lblTijd.getPreferredSize().width /2), insets2.top + 130, lblTijd.getPreferredSize().width, lblTijd.getPreferredSize().height);
		txtDatum.setBounds(insets2.left + 90 - (txtDatum.getPreferredSize().width /2) , insets2.top + 150, txtDatum.getPreferredSize().width, txtDatum.getPreferredSize().height);  
		txtTijd.setBounds(insets2.left + 270 - (txtTijd.getPreferredSize().width /2), insets2.top + 150, txtTijd.getPreferredSize().width, txtTijd.getPreferredSize().height);
		btnBeginLoggen.setBounds (insets2.left + 90 - (btnBeginLoggen.getPreferredSize().width /2), insets2.top + 180, btnBeginLoggen.getPreferredSize().width, btnBeginLoggen.getPreferredSize().height);
		btnMenu.setBounds (insets2.left + 270 - (btnMenu.getPreferredSize().width /2), insets2.top + 180, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height); 
					
		//Set frame visible and reposition to the middle of the screen 
		frame2.setLocation((dim.width/2)-(frame2.getSize().width/2), (dim.height/2)-(frame2.getSize().height/2));
		frame2.setVisible (true); 
		
		//Updating ComboBoxes  
		landen.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				listDivisiesFilteredNoDuplicates.clear();
				land = landen.getSelectedItem().toString(); 
				listDivisiesFilteredNoDuplicates = Team.getDivisiesFilteredNoDuplicates(land);  
				uitteams.removeAllItems(); 
				thuisteams.removeAllItems(); 
				divisies.removeAllItems(); //alle comboboxes na landen moeten leeggemaakt worden
				for(String word : listDivisiesFilteredNoDuplicates) { 
					divisies.addItem(word);
				} 
			}
		});
		
		divisies.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				if (divisies.getItemCount() != 0) {    //Anders error wanneer je terug opnieuw van land verandert
					listThuisteamsFiltered.clear();
					divisie = divisies.getSelectedItem().toString(); 
					listThuisteamsFiltered = Team.getTeamsFiltered(divisie); 
					uitteams.removeAllItems();
					thuisteams.removeAllItems(); //alle comboboxes na divisies moeten leeggemaakt worden
					for(String word : listThuisteamsFiltered) { 
						thuisteams.addItem(word);
					}
				}
			}
		}); 
		
		thuisteams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (thuisteams.getItemCount() != 0) { 
					listUitteamsFiltered.clear(); 
					thuisteam = thuisteams.getSelectedItem().toString(); 
					listUitteamsFiltered = Team.getTeamsFilteredWithoutHometeam(listThuisteamsFiltered,thuisteam);  
					uitteams.removeAllItems(); //alle comboboxes na thuisteam moeten leeggemaakt worden
					for(String word : listUitteamsFiltered) { 
						uitteams.addItem(word);
					}
				}
			}
		});

		//Button's action 
		btnBeginLoggen.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				int id = Match.generateWedstrijdId(); 
				currentWedstrijdId = Integer.toString(id);
				land = landen.getSelectedItem().toString(); 
				divisie = divisies.getSelectedItem().toString();  
				thuisteam = thuisteams.getSelectedItem().toString(); 
				uitteam= uitteams.getSelectedItem().toString(); 
				datum = txtDatum.getText().toString(); 
				tijd = txtTijd.getText().toString();  
				stadion = Team.getStadion(thuisteam);  
				int checker = Match.checkMatchNotExists(land, divisie, thuisteam, uitteam);
				if (checkDatum(datum) && checkTijd(tijd) && checker == 1) { 
					Match.addNewMatch(currentWedstrijdId,land,divisie,thuisteam,uitteam,datum,tijd); 
					handelingenScherm(); 
					frame2.setVisible(false);
				}
				if (checkDatum(datum) == false || checkTijd(tijd) == false && checker == 1){ 
					errorOngeldigeDatumTijd(); 
					frame2.setVisible(false);
				}
				if (checker == 0){ 
					errorMatchBestaatAl(); 
					frame2.setVisible(false);
				}
			}
		});
		
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame2.setVisible(false); 
				frame1.setVisible(true);
			}
		});
		
	} 
	//Handelingen toevoegen aan match
	public static void handelingenScherm() {
	
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Create the frame
		frame3 = new JFrame ("Soccerbot");
		frame3.setSize (600,600); 
		frame3.setIconImage(new ImageIcon(iconFile).getImage());   
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH); 
		frame3.setContentPane(new JLabel(new ImageIcon(achtergrondImage)));  
		frame3.setResizable(false);
		pane3 = frame3.getContentPane();				
		insets3 = pane3.getInsets();
		pane3.setLayout (null);  
		
		//Loading team images
		thuisteamFile = thuisteam + ".jpg";  
		thuisteamIcon = new ImageIcon(thuisteamFile);
		thuisteamImage = thuisteamIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);    
		thuisteamIcon = new ImageIcon(thuisteamImage);  // transform it back 
		
		uitteamFile = uitteam + ".jpg";  
		uitteamIcon = new ImageIcon(uitteamFile);
		uitteamImage = uitteamIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);    
		uitteamIcon = new ImageIcon(uitteamImage);  // transform it back
		
		//Create controls
		btnAddHandeling = new JButton ("Voeg nieuwe handeling toe");
		btnDeleteHandeling = new JButton ("Verwijder laatste handeling");
		btnSaveMatch= new JButton ("Wedstrijd opslaan");
		btnMenu = new JButton ("Terug naar menu");   
		lblWedstrijdId = new JLabel("Id: " + currentWedstrijdId);
		lblLand = new JLabel("Land: " + land);
		lblDivisie = new JLabel("Divisie: " + divisie);  
		lblFotoThuisteam = new JLabel(); 
		lblFotoUitteam = new JLabel(); 
		lblFotoThuisteam.setIcon(thuisteamIcon); 
		lblFotoUitteam.setIcon(uitteamIcon);
		lblThuisploeg = new JLabel(thuisteam);
		lblUitploeg = new JLabel(uitteam);
		lblDatum = new JLabel(datum);  
		lblTijd = new JLabel(tijd);  
		lblStadion = new JLabel("Stadion: " + stadion);
		lblMinuut = new JLabel("Minuut");  
		lblMinuut2 = new JLabel("Minuut");
		lblScore = new JLabel("Score"); 
		lblScoreThuisteam = new JLabel(scoreThuisteam); 
		lblScoreUitteam = new JLabel(scoreUitteam);
		lblSelecteerSpeler = new JLabel("Selecteer speler"); 
		lblSelecteerTeam = new JLabel("Selecteer team"); 
		lblSelecteerHandeling = new JLabel("Selecteer handeling"); 
		txtMinuut = new JTextField(3);  
		areaHandelingenThuisteam = new JTextArea(12,40); 
		areaMinutenHandelingen = new JTextArea(12,3); 
		areaHandelingenUitteam = new JTextArea(12,40); 
		scrollHandelingenThuisteam = new JScrollPane(areaHandelingenThuisteam); 
		scrollMinutenHandelingen = new JScrollPane(areaMinutenHandelingen); 
		scrollHandelingenUitteam = new JScrollPane(areaHandelingenUitteam);
		
		//Dont't show horizontal scrollbar 
		scrollHandelingenThuisteam.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);  
		scrollMinutenHandelingen.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);   
		
		//Don't show vertical scrollbar thuisteam and minuten 
		scrollHandelingenThuisteam.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER); 
		scrollMinutenHandelingen.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		
		//attach scrollbars to each other
		scrollMinutenHandelingen.getVerticalScrollBar().setModel(scrollHandelingenThuisteam.getVerticalScrollBar().getModel());
		scrollHandelingenUitteam.getVerticalScrollBar().setModel(scrollHandelingenThuisteam.getVerticalScrollBar().getModel()); 
		scrollHandelingenUitteam.getHorizontalScrollBar().setModel(scrollHandelingenThuisteam.getHorizontalScrollBar().getModel());

		//create an empty combo box with items of type String 
		spelendeTeams = new JComboBox<String>();  
		spelendeSpelers = new JComboBox<String>(); 
		handelingen = new JComboBox<String>();
		
		//Clear arraylist containing players with red card 
		listSpelersRedCard.clear();
		
		//Inheriting ArrayLists  
		listHandelingen = Handeling.getHandelingen();
		
		//Add items to the combobox  
		//Combobox teams
		spelendeTeams.addItem(thuisteam); 
		spelendeTeams.addItem(uitteam);  
		//Combobox handelingen
		for(String word : listHandelingen) { 
			handelingen.addItem(word);
		} 
		
		//Add all components to panel
		pane3.add (btnAddHandeling);
		pane3.add (btnDeleteHandeling);
		pane3.add (btnSaveMatch); 
		pane3.add (btnMenu); 
		pane3.add (lblWedstrijdId);
		pane3.add (lblLand);  
		pane3.add (lblDivisie);  
		pane3.add (lblFotoThuisteam); 
		pane3.add (lblFotoUitteam);
		pane3.add (lblThuisploeg); 
		pane3.add (lblUitploeg); 
		pane3.add (lblDatum); 
		pane3.add (lblTijd);  
		pane3.add (lblStadion);
		pane3.add (lblMinuut); 
		pane3.add (lblMinuut2);
		pane3.add (lblScore);  
		pane3.add (lblScoreThuisteam); 
		pane3.add (lblScoreUitteam);
		pane3.add (lblSelecteerSpeler); 
		pane3.add (lblSelecteerTeam); 
		pane3.add (lblSelecteerHandeling);
		pane3.add (spelendeTeams); 
		pane3.add (spelendeSpelers); 
		pane3.add (handelingen); 
		pane3.add (txtMinuut); 
		pane3.add (scrollHandelingenThuisteam); 
		pane3.add (scrollHandelingenUitteam); 
		pane3.add (scrollMinutenHandelingen);
		
		//Place all components
		lblWedstrijdId.setBounds(insets3.left + 300 - (lblWedstrijdId.getPreferredSize().width / 2), insets3.top + 10, lblWedstrijdId.getPreferredSize().width, lblWedstrijdId.getPreferredSize().height);
		lblLand.setBounds(insets3.left + 300 - (lblLand.getPreferredSize().width / 2), insets3.top + 30, lblLand.getPreferredSize().width, lblLand.getPreferredSize().height);  
		lblDivisie.setBounds(insets3.left + 300 - (lblDivisie.getPreferredSize().width / 2), insets3.top + 50, lblDivisie.getPreferredSize().width, lblDivisie.getPreferredSize().height);  
		lblDatum.setBounds(insets3.left + 280 - (lblDatum.getPreferredSize().width), insets3.top + 70, lblDatum.getPreferredSize().width, lblDatum.getPreferredSize().height); 
		lblTijd.setBounds(insets3.left + 320, insets3.top + 70, lblTijd.getPreferredSize().width, lblDatum.getPreferredSize().height);
		lblStadion.setBounds(insets3.left + 300 - (lblStadion.getPreferredSize().width / 2), insets3.top + 90, lblStadion.getPreferredSize().width, lblStadion.getPreferredSize().height);
		lblFotoThuisteam.setBounds(insets3.left + 150 - (lblFotoThuisteam.getPreferredSize().width / 2),insets3.top + 30, lblFotoThuisteam.getPreferredSize().width, lblFotoThuisteam.getPreferredSize().height); 
		lblFotoUitteam.setBounds(insets3.left + 450 - (lblFotoUitteam.getPreferredSize().width / 2),insets3.top + 30, lblFotoUitteam.getPreferredSize().width, lblFotoUitteam.getPreferredSize().height);
		lblThuisploeg.setBounds(insets3.left + 150 - (lblThuisploeg.getPreferredSize().width / 2),insets3.top + 110, lblThuisploeg.getPreferredSize().width, lblThuisploeg.getPreferredSize().height); 
		lblUitploeg.setBounds(insets3.left + 450 - (lblUitploeg.getPreferredSize().width / 2),insets3.top + 110, lblUitploeg.getPreferredSize().width, lblUitploeg.getPreferredSize().height);
		lblScore.setBounds(insets3.left + 300 - (lblScore.getPreferredSize().width / 2), insets3.top + 130, lblScore.getPreferredSize().width, lblScore.getPreferredSize().height); 
		lblScoreThuisteam.setBounds(insets3.left + 149, insets3.top + 150, lblScoreThuisteam.getPreferredSize().width + 7, lblScoreThuisteam.getPreferredSize().height); 
		lblScoreUitteam.setBounds(insets3.left + 449 , insets3.top + 150, lblScoreUitteam.getPreferredSize().width + 7, lblScoreUitteam.getPreferredSize().height);
		lblMinuut.setBounds(300 - (lblMinuut.getPreferredSize().width / 2), insets3.top + 170, lblMinuut.getPreferredSize().width, lblMinuut.getPreferredSize().height); 
		scrollHandelingenThuisteam.setBounds(20, insets3.top + 190, 250, areaHandelingenThuisteam.getPreferredSize().height); 
		scrollHandelingenUitteam.setBounds(330 , insets3.top + 190, 250, areaHandelingenUitteam.getPreferredSize().height);
		scrollMinutenHandelingen.setBounds(299 - (areaMinutenHandelingen.getPreferredSize().width / 2), insets3.top + 190, areaMinutenHandelingen.getPreferredSize().width, areaMinutenHandelingen.getPreferredSize().height);
		lblSelecteerTeam.setBounds(insets3.left + 120 - (lblSelecteerTeam.getPreferredSize().width /2), insets3.top + 440, lblSelecteerTeam.getPreferredSize().width, lblSelecteerTeam.getPreferredSize().height); 
		lblSelecteerSpeler.setBounds(insets3.left + 240 - (lblSelecteerSpeler.getPreferredSize().width /2), insets3.top + 440, lblSelecteerSpeler.getPreferredSize().width, lblSelecteerSpeler.getPreferredSize().height);
		lblSelecteerHandeling.setBounds(insets3.left + 360 - (lblSelecteerHandeling.getPreferredSize().width /2), insets3.top + 440, lblSelecteerHandeling.getPreferredSize().width, lblSelecteerHandeling.getPreferredSize().height);
		lblMinuut2.setBounds(insets3.left + 480 - (lblMinuut2.getPreferredSize().width /2) , insets3.top + 440, lblMinuut2.getPreferredSize().width, lblMinuut2.getPreferredSize().height);
		spelendeTeams.setBounds(insets3.left + 70, insets3.top + 460, 100, 20); 
		spelendeSpelers.setBounds(insets3.left + 190, insets3.top + 460, 100, 20); 
		handelingen.setBounds(insets3.left + 297, insets3.top + 460, 126, 20); 
		txtMinuut.setBounds(insets3.left + 480 - (txtMinuut.getPreferredSize().width /2), insets3.top + 460, txtMinuut.getPreferredSize().width, txtMinuut.getPreferredSize().height); 
		btnAddHandeling.setBounds(insets3.left + 150 - (btnAddHandeling.getPreferredSize().width /2), insets3.top + 500, btnAddHandeling.getPreferredSize().width, btnAddHandeling.getPreferredSize().height);  
		btnDeleteHandeling.setBounds(insets3.left + 150 - (btnDeleteHandeling.getPreferredSize().width /2), insets3.top + 530, btnDeleteHandeling.getPreferredSize().width, btnDeleteHandeling.getPreferredSize().height);
		btnSaveMatch.setBounds(insets3.left + 300 - (btnSaveMatch.getPreferredSize().width /2), insets3.top + 520, btnSaveMatch.getPreferredSize().width, btnSaveMatch.getPreferredSize().height);
		btnMenu.setBounds(insets3.left + 450 - (btnMenu.getPreferredSize().width /2), insets3.top + 520, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
		
		//Set frame visible and reposition to the middle of the screen 
		frame3.setLocation((dim.width/2)-(frame3.getSize().width/2), (dim.height/2)-(frame3.getSize().height/2));
		frame3.setVisible (true); 
		
		
		//Updating ComboBoxes 
		spelendeTeams.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				listSpelersFilteredOneTeam.clear();
				geselecteerdTeam = spelendeTeams.getSelectedItem().toString(); 
				listSpelersFilteredOneTeam = Speler.getSpelersOneTeamNoRedCards(geselecteerdTeam,listSpelersRedCard);   
				spelendeSpelers.removeAllItems(); //alle comboboxes na teams moeten leeggemaakt worden
				for(String word : listSpelersFilteredOneTeam) { 
					spelendeSpelers.addItem(word);
				} 
			}
		}); 
		
		//Button's action  
		btnAddHandeling.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				reedsOpgeslagen = 0;
				geselecteerdTeam = spelendeTeams.getSelectedItem().toString(); 
				geselecteerdeSpeler = spelendeSpelers.getSelectedItem().toString(); 
				geselecteerdeHandeling = handelingen.getSelectedItem().toString();
				geselecteerdeMinuut = txtMinuut.getText().toString(); 
				if (geselecteerdeHandeling.equals("GeleKaart") || geselecteerdeHandeling.equals("RodeKaart")) { 
					if(geselecteerdeHandeling.equals("GeleKaart") && Handeling.checkAlreadyGeel(currentWedstrijdId,geselecteerdeSpeler) == true) { 
						Handeling.addNewHandeling(currentWedstrijdId, geselecteerdTeam, geselecteerdeSpeler, geselecteerdeHandeling, geselecteerdeMinuut); 
						Handeling.addNewHandeling(currentWedstrijdId, geselecteerdTeam, geselecteerdeSpeler, "RodeKaart(2xG)", geselecteerdeMinuut); 
						listSpelersRedCard.add(geselecteerdeSpeler); 
						//Update combobox
						listSpelersFilteredOneTeam.clear();
						geselecteerdTeam = spelendeTeams.getSelectedItem().toString(); 
						listSpelersFilteredOneTeam = Speler.getSpelersOneTeamNoRedCards(geselecteerdTeam,listSpelersRedCard);   
						spelendeSpelers.removeAllItems(); //alle comboboxes na teams moeten leeggemaakt worden
						for(String word : listSpelersFilteredOneTeam) { 
							spelendeSpelers.addItem(word);
						} 
					} 
					if(geselecteerdeHandeling.equals("RodeKaart")) { 
						Handeling.addNewHandeling(currentWedstrijdId, geselecteerdTeam, geselecteerdeSpeler, geselecteerdeHandeling, geselecteerdeMinuut); 
						listSpelersRedCard.add(geselecteerdeSpeler); 
						//Update combobox
						listSpelersFilteredOneTeam.clear();
						geselecteerdTeam = spelendeTeams.getSelectedItem().toString(); 
						listSpelersFilteredOneTeam = Speler.getSpelersOneTeamNoRedCards(geselecteerdTeam,listSpelersRedCard);   
						spelendeSpelers.removeAllItems(); //alle comboboxes na teams moeten leeggemaakt worden
						for(String word : listSpelersFilteredOneTeam) { 
							spelendeSpelers.addItem(word);
						} 
					}
					if(geselecteerdeHandeling.equals("GeleKaart") && Handeling.checkAlreadyGeel(currentWedstrijdId,geselecteerdeSpeler) == false) { 
						Handeling.addNewHandeling(currentWedstrijdId, geselecteerdTeam, geselecteerdeSpeler, geselecteerdeHandeling, geselecteerdeMinuut); 
					}
				} 
				else { 
					Handeling.addNewHandeling(currentWedstrijdId, geselecteerdTeam, geselecteerdeSpeler, geselecteerdeHandeling, geselecteerdeMinuut);
				}
				scoreUpdater(currentWedstrijdId); 
				getHandelingenForThisMatch(currentWedstrijdId);
			}
		});  
		
		btnDeleteHandeling.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {  
				reedsOpgeslagen = 0;
				String lastHandeling; 
				lastHandeling = Handeling.getLastHandeling();
				if(lastHandeling.equals("RodeKaart(2xG)") || lastHandeling.equals("RodeKaart")){ 
					if(lastHandeling.equals("RodeKaart(2xG)")) { 
						Handeling.deleteLastHandeling(currentWedstrijdId); 
						Handeling.deleteLastHandeling(currentWedstrijdId); 
						listSpelersRedCard.remove(listSpelersRedCard.size()-1); 
						//Update combobox
						listSpelersFilteredOneTeam.clear();
						geselecteerdTeam = spelendeTeams.getSelectedItem().toString(); 
						listSpelersFilteredOneTeam = Speler.getSpelersOneTeamNoRedCards(geselecteerdTeam,listSpelersRedCard);   
						spelendeSpelers.removeAllItems(); //alle comboboxes na teams moeten leeggemaakt worden
						for(String word : listSpelersFilteredOneTeam) { 
							spelendeSpelers.addItem(word);
						}
					}
					if(lastHandeling.equals("RodeKaart")){ 
						Handeling.deleteLastHandeling(currentWedstrijdId); 
						listSpelersRedCard.remove(listSpelersRedCard.size()-1); 
						//Update combobox
						listSpelersFilteredOneTeam.clear();
						geselecteerdTeam = spelendeTeams.getSelectedItem().toString(); 
						listSpelersFilteredOneTeam = Speler.getSpelersOneTeamNoRedCards(geselecteerdTeam,listSpelersRedCard);   
						spelendeSpelers.removeAllItems(); //alle comboboxes na teams moeten leeggemaakt worden
						for(String word : listSpelersFilteredOneTeam) { 
							spelendeSpelers.addItem(word);
						} 
					} 
				}  
				else { 
					Handeling.deleteLastHandeling(currentWedstrijdId); 
				}
				scoreUpdater(currentWedstrijdId);
				getHandelingenForThisMatch(currentWedstrijdId);
			}
		});
		
		btnSaveMatch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				Handeling.saveHandelingen();  
				Match.saveMatchen(); 
				reedsOpgeslagen = 1; 
			}
		});
		
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if(reedsOpgeslagen == 1) { 
					frame3.setVisible(false); 
					frame1.setVisible(true);
					reedsOpgeslagen = 0; 
					listSpelersRedCard.clear(); 
					scoreThuisteam = "0"; 
					scoreUitteam = "0";
				} 
				else { 
					frame3.setVisible(false);
					bevestigingsSchermNietOpgeslagenMatch(); 
				}
			}
		});
	}  
	//Error scherm indien ongeldige datum en/of tijd
	public static void errorOngeldigeDatumTijd() {
		
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Create the frame
		frame4 = new JFrame ("Soccerbot"); 
		frame4.setIconImage(new ImageIcon(iconFile).getImage()); 
		frame4.setLayout (new GridLayout(3,1)); 
		frame4.setResizable(false);
		
		//Create controls
		lblOngeldigeInvoer = new JLabel("ERROR: Ongeldige invoer van Datum en/of Tijd!");
		btnRetry = new JButton ("Opnieuw proberen");
		btnMenu = new JButton ("Terug naar menu");

		//Add all components to panel
		frame4.add (lblOngeldigeInvoer);
		frame4.add (btnRetry); 
		frame4.add (btnMenu); 
				
		frame4.pack(); 
		
		//Set frame visible and reposition to the middle of the screen 
		frame4.setLocation((dim.width/2)-(frame4.getSize().width/2), (dim.height/2)-(frame4.getSize().height/2));
		frame4.setVisible (true);  
		
		//Button's action  
		btnRetry.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame4.setVisible(false);
				logWedstrijdScherm();
			}
		});  
		
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame4.setVisible(false); 
				frame1.setVisible(true);
			}
		});
	}
	//Error scherm indien match tussen thuis-en uitploeg al bestaat
	public static void errorMatchBestaatAl() {
		
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Create the frame
		frame5 = new JFrame ("Soccerbot"); 
		frame5.setIconImage(new ImageIcon(iconFile).getImage()); 
		frame5.setLayout (new GridLayout(4,1));  
		frame5.setResizable(false);
		
		//Create controls
		lblOngeldigeInvoer = new JLabel("ERROR: Match bestaat al");
		btnRetry = new JButton ("Opnieuw proberen"); 
		btnDelete = new JButton ("Oude match deleten");
		btnMenu = new JButton ("Terug naar menu");

		//Add all components to panel
		frame5.add (lblOngeldigeInvoer);
		frame5.add (btnRetry);  
		frame5.add (btnDelete);
		frame5.add (btnMenu); 
				
		frame5.pack(); 
		
		//Set frame visible and reposition to the middle of the screen 
		frame5.setLocation((dim.width/2)-(frame5.getSize().width/2), (dim.height/2)-(frame5.getSize().height/2));
		frame5.setVisible (true);  
		
		//Button's action  
		btnRetry.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame5.setVisible(false);
				logWedstrijdScherm();
			}
		});  
		
		btnDelete.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame5.setVisible(false); 
				bevestigingsScherm();
			}
		});
		
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame5.setVisible(false); 
				frame1.setVisible(true);
			}
		});
	} 
	//Bevestigingsscherm om match te verwijderen
	public static void bevestigingsScherm() {
		
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Create the frame
		frame6 = new JFrame ("Soccerbot"); 
		frame6.setIconImage(new ImageIcon(iconFile).getImage());
		frame6.setLayout (new GridLayout(1,2));  
		frame6.setResizable(false);
		
		//Create controls
		btnYes = new JButton("Ja, verwijder oude match"); 
		btnNo = new JButton("Neen, behoudt oude match");  
		
		//Add all components to panel
		frame6.add (btnYes); 
		frame6.add (btnNo); 
						
		frame6.pack(); 
		
		//Set frame visible and reposition to the middle of the screen 
		frame6.setLocation((dim.width/2)-(frame6.getSize().width/2), (dim.height/2)-(frame6.getSize().height/2));
		frame6.setVisible (true); 
		
		//Button's action
		btnYes.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				String id = Match.getIdAleadyExistingMatch(land, divisie, thuisteam, uitteam); 
				Match.deleteMatch(id); 
				Handeling.deleteHandelingen(id); 
				Match.saveMatchen(); 
				Handeling.saveHandelingen();  
				Match.addNewMatch(currentWedstrijdId,land,divisie,thuisteam,uitteam,datum,tijd); 
				handelingenScherm();
				frame6.setVisible(false); 
			}
		}); 
		
		btnNo.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame6.setVisible(false); 
				errorMatchBestaatAl();
			} 
		});
	
	}  
	//Initialiseren van de match die gewenst wordt te herbekijken
	public static void herbekijkInvulScherm() { 
		
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}  
		
		//Create the frame
		frame7 = new JFrame ("Soccerbot");
		frame7.setSize (320,280); 
		frame7.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(320, 280, Image.SCALE_SMOOTH); 
		frame7.setContentPane(new JLabel(new ImageIcon(achtergrondImage)));  
		frame7.setResizable(false);
		pane7 = frame7.getContentPane();					
		insets7 = pane7.getInsets();
		pane7.setLayout (null);
							
		//Create controls
		btnStartHerbekijk = new JButton ("Herbekijk deze wedstrijd");
		btnMenu = new JButton ("Terug naar menu"); 
		lblLand = new JLabel ("Land:");
		lblDivisie = new JLabel ("Divisie:");
		lblThuisploeg = new JLabel ("Thuisploeg:");
		lblUitploeg = new JLabel ("Uitploeg:");
				

		//create an empty combo box with items of type String 
		landen = new JComboBox<String>();  
		divisies = new JComboBox<String>(); 
		thuisteams = new JComboBox<String>(); 
		uitteams = new JComboBox<String>();
				
		//Inheriting ArrayLists 
		listLanden = Team.getLandenNoDuplicates();   
				
		//Add items to the combobox 
		for(String word : listLanden) { 
			landen.addItem(word);
		} 
				
		//Add all components to panel
		pane7.add (btnStartHerbekijk);
		pane7.add (btnMenu); 
		pane7.add (lblLand); 
		pane7.add (lblDivisie); 
		pane7.add (lblThuisploeg); 
		pane7.add (lblUitploeg); 	
		pane7.add(landen);  
		pane7.add(divisies); 
		pane7.add(thuisteams); 
		pane7.add(uitteams); 

		//Place all components
		lblLand.setBounds(160 - (lblLand.getPreferredSize().width / 2), insets7.top + 10, lblLand.getPreferredSize().width, lblLand.getPreferredSize().height); 
		landen.setBounds(160 - (landen.getPreferredSize().width / 2), insets7.top + 30, landen.getPreferredSize().width, landen.getPreferredSize().height);
		lblDivisie.setBounds(160 - (lblDivisie.getPreferredSize().width / 2), insets7.top + 50, lblDivisie.getPreferredSize().width, lblDivisie.getPreferredSize().height);  
		divisies.setBounds(100, insets7.top + 70, 120, 20);
		lblThuisploeg.setBounds(80 - (lblThuisploeg.getPreferredSize().width / 2), insets7.top + 90, lblThuisploeg.getPreferredSize().width, lblThuisploeg.getPreferredSize().height); 
		thuisteams.setBounds(30, insets7.top + 110, 100, 20);
		lblUitploeg.setBounds(240 - (lblUitploeg.getPreferredSize().width / 2), insets7.top + 90, lblUitploeg.getPreferredSize().width, lblUitploeg.getPreferredSize().height);  
		uitteams.setBounds(190, insets7.top + 110, 100, 20);
		btnStartHerbekijk.setBounds (80 - (btnStartHerbekijk.getPreferredSize().width / 2), insets7.top + 170, btnStartHerbekijk.getPreferredSize().width, btnStartHerbekijk.getPreferredSize().height);
		btnMenu.setBounds (240 - (btnMenu.getPreferredSize().width / 2), insets7.top + 170, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
							
		//Set frame visible and reposition to the middle of the screen 
		frame7.setLocation((dim.width/2)-(frame7.getSize().width/2), (dim.height/2)-(frame7.getSize().height/2));
		frame7.setVisible (true); 
				
		//Updating ComboBoxes  
		landen.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				listDivisiesFilteredNoDuplicates.clear();
				land = landen.getSelectedItem().toString(); 
				listDivisiesFilteredNoDuplicates = Team.getDivisiesFilteredNoDuplicates(land);  
				uitteams.removeAllItems(); 
				thuisteams.removeAllItems(); 
				divisies.removeAllItems(); //alle comboboxes na landen moeten leeggemaakt worden
				for(String word : listDivisiesFilteredNoDuplicates) { 
					divisies.addItem(word);
				} 
			}
		});
				
		divisies.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				if (divisies.getItemCount() != 0) {    //Anders error wanneer je terug opnieuw van land verandert
					listThuisteamsFiltered.clear();
					divisie = divisies.getSelectedItem().toString(); 
					listThuisteamsFiltered = Team.getTeamsFiltered(divisie); 
					uitteams.removeAllItems();
					thuisteams.removeAllItems(); //alle comboboxes na divisies moeten leeggemaakt worden
					for(String word : listThuisteamsFiltered) { 
						thuisteams.addItem(word);
					}
				}
			}
		}); 
				
		thuisteams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (thuisteams.getItemCount() != 0) { 
					listUitteamsFiltered.clear(); 
					thuisteam = thuisteams.getSelectedItem().toString(); 
					listUitteamsFiltered = Team.getTeamsFilteredWithoutHometeam(listThuisteamsFiltered,thuisteam);  
					uitteams.removeAllItems(); //alle comboboxes na thuisteam moeten leeggemaakt worden
					for(String word : listUitteamsFiltered) { 
						uitteams.addItem(word);
					}
				}
			}
		});

		//Button's action 
		btnStartHerbekijk.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {  
				land = landen.getSelectedItem().toString(); 
				divisie = divisies.getSelectedItem().toString();  
				thuisteam = thuisteams.getSelectedItem().toString(); 
				uitteam= uitteams.getSelectedItem().toString();
				int checker = Match.checkMatchNotExists(land, divisie, thuisteam, uitteam);
				if (checker == 1) { 
					errorSchermMatchBestaatNiet(); 
					frame7.setVisible(false);
				}
				else { 
					herbekijkHandelingenScherm(); 
					frame7.setVisible(false);
				}
			}
		});
				
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame7.setVisible(false); 
				frame1.setVisible(true);
			}
		});
				
	} 
	//Error scherm indien match die wenst wordt herbekeken te worden niet bestaat
	public static void errorSchermMatchBestaatNiet() { 
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
				
		//Create the frame
		frame8 = new JFrame ("Soccerbot"); 
		frame8.setIconImage(new ImageIcon(iconFile).getImage());
		frame8.setLayout (new GridLayout(3,1)); 
		frame8.setResizable(false);
				
		//Create controls
		lblOngeldigeInvoer = new JLabel("ERROR: Match bestaat niet");
		btnRetry = new JButton ("Opnieuw proberen"); 
		btnMenu = new JButton ("Terug naar menu");

		//Add all components to panel
		frame8.add (lblOngeldigeInvoer);
		frame8.add (btnRetry);  
		frame8.add (btnMenu); 
						
		frame8.pack(); 
		
		//Set frame visible and reposition to the middle of the screen 
		frame8.setLocation((dim.width/2)-(frame8.getSize().width/2), (dim.height/2)-(frame8.getSize().height/2));
		frame8.setVisible (true);  
				
		//Button's action  
		btnRetry.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame8.setVisible(false);
				herbekijkInvulScherm();
			}
		});  
				
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame8.setVisible(false); 
				frame1.setVisible(true);
			}
		});
	}  
	//Toont de handelingen van de reeds gelogde match
	public static void herbekijkHandelingenScherm() {
		
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Create the frame
		frame9 = new JFrame ("Soccerbot");
		frame9.setSize (600,550); 
		frame9.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(600, 550, Image.SCALE_SMOOTH); 
		frame9.setContentPane(new JLabel(new ImageIcon(achtergrondImage)));  
		frame9.setResizable(false);
		pane9 = frame9.getContentPane();				
		insets9 = pane9.getInsets();
		pane9.setLayout (null); 
		
		//Inherit information about match
		currentWedstrijdId = Match.getIdAleadyExistingMatch(land, divisie, thuisteam, uitteam);
		datum = Match.getDatumAleadyExistingMatch(land, divisie, thuisteam, uitteam); 
		tijd = Match.getTijdAleadyExistingMatch(land, divisie, thuisteam, uitteam);
		stadion = Team.getStadion(thuisteam);  
		
		//Loading team images
		thuisteamFile = thuisteam + ".jpg";  
		thuisteamIcon = new ImageIcon(thuisteamFile);
		thuisteamImage = thuisteamIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);    
		thuisteamIcon = new ImageIcon(thuisteamImage);  // transform it back 
				
		uitteamFile = uitteam + ".jpg";  
		uitteamIcon = new ImageIcon(uitteamFile);
		uitteamImage = uitteamIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);    
		uitteamIcon = new ImageIcon(uitteamImage);  // transform it back
		
		//Create controls 
		btnMenu = new JButton ("Terug naar menu");    
		btnSaveToFile = new JButton ("Opslaan naar textfile");
		lblWedstrijdId = new JLabel("Id: " + currentWedstrijdId);
		lblLand = new JLabel("Land: " + land);
		lblDivisie = new JLabel("Divisie: " + divisie);  
		lblFotoThuisteam = new JLabel(); 
		lblFotoUitteam = new JLabel(); 
		lblFotoThuisteam.setIcon(thuisteamIcon); 
		lblFotoUitteam.setIcon(uitteamIcon);
		lblThuisploeg = new JLabel(thuisteam);
		lblUitploeg = new JLabel(uitteam);
		lblDatum = new JLabel(datum);  
		lblTijd = new JLabel(tijd);  
		lblStadion = new JLabel("Stadion: " + stadion);
		lblMinuut = new JLabel("Minuut");  
		lblScore = new JLabel("Score");   
		lblScoreThuisteam = new JLabel(scoreThuisteam); 
		lblScoreUitteam = new JLabel(scoreUitteam);
		areaHandelingenThuisteam = new JTextArea(12,40); 
		areaMinutenHandelingen = new JTextArea(12,3); 
		areaHandelingenUitteam = new JTextArea(12,40);  
		scrollHandelingenThuisteam = new JScrollPane(areaHandelingenThuisteam); 
		scrollMinutenHandelingen = new JScrollPane(areaMinutenHandelingen); 
		scrollHandelingenUitteam = new JScrollPane(areaHandelingenUitteam);
		
		//Dont't show horizontal scrollbar 
		scrollHandelingenThuisteam.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);  
		scrollMinutenHandelingen.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);  
		
		//Don't show vertical scrollbar thuisteam and minuten 
		scrollHandelingenThuisteam.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER); 
		scrollMinutenHandelingen.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
				
		//attach scrollbars to each other
		scrollMinutenHandelingen.getVerticalScrollBar().setModel(scrollHandelingenThuisteam.getVerticalScrollBar().getModel());
		scrollHandelingenUitteam.getVerticalScrollBar().setModel(scrollHandelingenThuisteam.getVerticalScrollBar().getModel()); 
		scrollHandelingenUitteam.getHorizontalScrollBar().setModel(scrollHandelingenThuisteam.getHorizontalScrollBar().getModel());
		
		//Make JTextArea's
		getHandelingenForThisMatch(currentWedstrijdId);
		
		//Get final score 
		scoreUpdater(currentWedstrijdId); 
		
		//Add all components to panel
		pane9.add (btnMenu);  
		pane9.add (btnSaveToFile);
		pane9.add (lblWedstrijdId);
		pane9.add (lblLand);  
		pane9.add (lblDivisie);  
		pane9.add (lblFotoThuisteam); 
		pane9.add (lblFotoUitteam);
		pane9.add (lblThuisploeg); 
		pane9.add (lblUitploeg); 
		pane9.add (lblDatum); 
		pane9.add (lblTijd);  
		pane9.add (lblStadion);
		pane9.add (lblMinuut); 
		pane9.add (lblScore);  
		pane9.add (lblScoreThuisteam); 
		pane9.add (lblScoreUitteam);
		pane9.add (scrollHandelingenThuisteam); 
		pane9.add (scrollHandelingenUitteam); 
		pane9.add (scrollMinutenHandelingen);
		
		//Place all components
		lblWedstrijdId.setBounds(insets9.left + 300 - (lblWedstrijdId.getPreferredSize().width / 2), insets9.top + 10, lblWedstrijdId.getPreferredSize().width, lblWedstrijdId.getPreferredSize().height);
		lblLand.setBounds(insets9.left + 300 - (lblLand.getPreferredSize().width / 2), insets9.top + 30, lblLand.getPreferredSize().width, lblLand.getPreferredSize().height);  
		lblDivisie.setBounds(insets9.left + 300 - (lblDivisie.getPreferredSize().width / 2), insets9.top + 50, lblDivisie.getPreferredSize().width, lblDivisie.getPreferredSize().height);  
		lblDatum.setBounds(insets9.left + 280 - (lblDatum.getPreferredSize().width), insets9.top + 70, lblDatum.getPreferredSize().width, lblDatum.getPreferredSize().height); 
		lblTijd.setBounds(insets9.left + 320, insets9.top + 70, lblTijd.getPreferredSize().width, lblDatum.getPreferredSize().height);
		lblStadion.setBounds(insets9.left + 300 - (lblStadion.getPreferredSize().width / 2), insets9.top + 90, lblStadion.getPreferredSize().width, lblStadion.getPreferredSize().height);
		lblFotoThuisteam.setBounds(insets9.left + 150 - (lblFotoThuisteam.getPreferredSize().width / 2),insets9.top + 30, lblFotoThuisteam.getPreferredSize().width, lblFotoThuisteam.getPreferredSize().height); 
		lblFotoUitteam.setBounds(insets9.left + 450 - (lblFotoUitteam.getPreferredSize().width / 2),insets9.top + 30, lblFotoUitteam.getPreferredSize().width, lblFotoUitteam.getPreferredSize().height);
		lblThuisploeg.setBounds(insets9.left + 150 - (lblThuisploeg.getPreferredSize().width / 2),insets9.top + 110, lblThuisploeg.getPreferredSize().width, lblThuisploeg.getPreferredSize().height); 
		lblUitploeg.setBounds(insets9.left + 450 - (lblUitploeg.getPreferredSize().width / 2),insets9.top + 110, lblUitploeg.getPreferredSize().width, lblUitploeg.getPreferredSize().height);
		lblScore.setBounds(insets9.left + 300 - (lblScore.getPreferredSize().width / 2), insets9.top + 130, lblScore.getPreferredSize().width, lblScore.getPreferredSize().height); 
		lblScoreThuisteam.setBounds(insets9.left + 149, insets9.top + 150, lblScoreThuisteam.getPreferredSize().width, lblScoreThuisteam.getPreferredSize().height); 
		lblScoreUitteam.setBounds(insets9.left + 449, insets9.top + 150, lblScoreUitteam.getPreferredSize().width, lblScoreUitteam.getPreferredSize().height);
		lblMinuut.setBounds(300 - (lblMinuut.getPreferredSize().width / 2), insets9.top + 170, lblMinuut.getPreferredSize().width, lblMinuut.getPreferredSize().height); 
		scrollHandelingenThuisteam.setBounds(20 , insets9.top + 190, 250, areaHandelingenThuisteam.getPreferredSize().height); 
		scrollHandelingenUitteam.setBounds(330 , insets9.top + 190, 250, areaHandelingenUitteam.getPreferredSize().height);
		scrollMinutenHandelingen.setBounds(300 - (areaMinutenHandelingen.getPreferredSize().width / 2), insets9.top + 190, areaMinutenHandelingen.getPreferredSize().width, areaMinutenHandelingen.getPreferredSize().height);
		btnSaveToFile.setBounds(insets9.left + 150 - (btnSaveToFile.getPreferredSize().width /2), insets9.top + 470, btnSaveToFile.getPreferredSize().width, btnSaveToFile.getPreferredSize().height);
		btnMenu.setBounds(insets9.left + 450 - (btnMenu.getPreferredSize().width /2), insets9.top + 470, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
		
		//Set frame visible and reposition to the middle of the screen 
		frame9.setLocation((dim.width/2)-(frame9.getSize().width/2), (dim.height/2)-(frame9.getSize().height/2));
		frame9.setVisible (true); 
		
		//Button's Action
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame9.setVisible(false);  
				frame1.setVisible(true);  
				scoreThuisteam = "0"; 
				scoreUitteam = "0";
			}
		}); 
		
		btnSaveToFile.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				saveThisMatch();
			}
		});
	}  
	//Geeft de gebruiker de mogelijkheid om te kiezen voor speler of team
	public static void keuzeStatistieken() { 
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Create the frame
		frame10 = new JFrame ("Soccerbot"); 
		frame10.setIconImage(new ImageIcon(iconFile).getImage());
		frame10.setLayout (new GridLayout(3,1)); 
		frame10.setResizable(false);
					
		//Create controls
		lblStatistiekVraag = new JLabel ("Statistiekmogelijkheden:");
		btnStatistiekSpeler = new JButton ("Statistiek Speler");
		btnStatistiekTeam = new JButton ("Statistiek Team");
		
		//Add all components to panel
		frame10.add (lblStatistiekVraag);
		frame10.add (btnStatistiekSpeler); 
		frame10.add (btnStatistiekTeam); 
				
		frame10.pack(); 
		
		//Set frame visible and reposition to the middle of the screen 
		frame10.setLocation((dim.width/2)-(frame10.getSize().width/2), (dim.height/2)-(frame10.getSize().height/2));
		frame10.setVisible (true); 
		
		//Button's Action
		btnStatistiekSpeler.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				statistiekSpeler(); 
				frame10.setVisible(false);
			}
			
		});  
		
		btnStatistiekTeam.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				statistiekTeam(); 
				frame10.setVisible(false);
			}
			
		}); 
		

	} 
	//Initialiseer de speler waarvan je de statistieken wil zien
	public static void statistiekSpeler() {
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
			
		//Create the frame
		frame11 = new JFrame ("Soccerbot");
		frame11.setSize (280,280); 
		frame11.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH); 
		frame11.setContentPane(new JLabel(new ImageIcon(achtergrondImage)));  
		frame11.setResizable(false);
		pane11 = frame11.getContentPane();					
		insets11 = pane11.getInsets();
		pane11.setLayout (null);
						
		//Create controls
		btnVraagStatistiekenOp = new JButton ("Vraag statistieken op");
		btnMenu = new JButton ("Menu"); 
		lblLand = new JLabel ("Land:");
		lblDivisie = new JLabel ("Divisie:");
		lblTeam = new JLabel ("Team:");
		lblSpeler = new JLabel ("Speler:");
		

		//create an empty combo box with items of type String 
		landen = new JComboBox<String>();  
		divisies = new JComboBox<String>(); 
		teams = new JComboBox<String>(); 
		spelers = new JComboBox<String>();
	
			
		//Inheriting ArrayLists 
		listLanden = Team.getLandenNoDuplicates();   
			
		//Add items to the combobox 
		for(String word : listLanden) { 
			landen.addItem(word);
		} 
			
		//Add all components to panel
		pane11.add (btnVraagStatistiekenOp);
		pane11.add (btnMenu); 
		pane11.add (lblLand); 
		pane11.add (lblDivisie); 
		pane11.add (lblTeam); 
		pane11.add (lblSpeler); 
		pane11.add(landen);  
		pane11.add(divisies); 
		pane11.add(teams); 
		pane11.add(spelers); 
			
		//Place all components
		lblLand.setBounds(140 - (lblLand.getPreferredSize().width / 2), insets11.top + 10, lblLand.getPreferredSize().width, lblLand.getPreferredSize().height); 
		landen.setBounds(140 - (landen.getPreferredSize().width / 2) , insets11.top + 30, landen.getPreferredSize().width, landen.getPreferredSize().height);
		lblDivisie.setBounds(140 - (lblDivisie.getPreferredSize().width / 2), insets11.top + 52, lblDivisie.getPreferredSize().width, lblDivisie.getPreferredSize().height);  
		divisies.setBounds(80, insets11.top + 70, 120, 20);
		lblTeam.setBounds(140 - (lblTeam.getPreferredSize().width /2), insets11.top + 92, lblTeam.getPreferredSize().width, lblTeam.getPreferredSize().height); 
		teams.setBounds(80, insets11.top + 110, 120, 20); 
		lblSpeler.setBounds(140 - (lblSpeler.getPreferredSize().width / 2), insets11.top + 132, lblSpeler.getPreferredSize().width, lblSpeler.getPreferredSize().height);
		spelers.setBounds(80, insets11.top + 150,120,20);
		btnVraagStatistiekenOp.setBounds (insets11.left + 70 - (btnVraagStatistiekenOp.getPreferredSize().width /2), insets11.top + 190, btnVraagStatistiekenOp.getPreferredSize().width, btnVraagStatistiekenOp.getPreferredSize().height);
		btnMenu.setBounds (insets11.left + 210 - (btnMenu.getPreferredSize().width /2) , insets11.top + 190, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
						
		//Updating ComboBoxes  
		landen.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				listDivisiesFilteredNoDuplicates.clear();
				land = landen.getSelectedItem().toString(); 
				listDivisiesFilteredNoDuplicates = Team.getDivisiesFilteredNoDuplicates(land);  
				teams.removeAllItems();
				divisies.removeAllItems(); //alle comboboxes na landen moeten leeggemaakt worden
				for(String word : listDivisiesFilteredNoDuplicates) { 
					divisies.addItem(word);
				} 
			}
		});
				
		divisies.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				if (divisies.getItemCount() != 0) {    //Anders error wanneer je terug opnieuw van land verandert
					listTeamsFiltered.clear();
					divisie = divisies.getSelectedItem().toString(); 
					listTeamsFiltered = Team.getTeamsFiltered(divisie); 
					teams.removeAllItems();
					for(String word : listTeamsFiltered) { 
						teams.addItem(word);
					}
				}
			}
		});
		
		teams.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				if(teams.getItemCount() != 0) { 
					listSpelersFilteredOneTeam.clear();
					geselecteerdTeam = teams.getSelectedItem().toString(); 
					listSpelersFilteredOneTeam = Speler.getSpelersOneTeam(geselecteerdTeam);   
					spelers.removeAllItems(); //alle comboboxes na landen moeten leeggemaakt worden
					for(String word : listSpelersFilteredOneTeam) { 
						spelers.addItem(word);
					} 
				} 
			}
		}); 
		
		
		//Set frame visible and reposition to the middle of the screen 
		frame11.setLocation((dim.width/2)-(frame11.getSize().width/2), (dim.height/2)-(frame11.getSize().height/2));
		frame11.setVisible (true);   
		
		//Button's Action 
		btnVraagStatistiekenOp.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				geselecteerdTeam = teams.getSelectedItem().toString();
				geselecteerdeSpeler = spelers.getSelectedItem().toString(); 
				statistiekenSpeler = Handeling.getStatistiekenSpeler(geselecteerdeSpeler);   
				showStatistiekenSpeler(); 
				frame11.setVisible(false);
			}
		});
		
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame11.setVisible(false); 
				frame1.setVisible(true);
			}
		});
	} 
	//Toont de statistieken van een speler
	public static void showStatistiekenSpeler() { 
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
				
		//Create the frame
		frame12 = new JFrame ("Soccerbot");
		frame12.setSize (300,300); 
		frame12.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); 
		frame12.setContentPane(new JLabel(new ImageIcon(achtergrondImage)));  
		frame12.setResizable(false);
		pane12 = frame12.getContentPane();				
		insets12 = pane12.getInsets();
		pane12.setLayout (null); 
		
		spelerFile = geselecteerdeSpeler + ".jpg";  
		spelerIcon = new ImageIcon(spelerFile);
		spelerImage = spelerIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);    
		spelerIcon = new ImageIcon(spelerImage);  // transform it back
		
		//Create controls
		btnMenu = new JButton ("Terug naar menu"); 
		lblTeam = new JLabel ("Team: " + geselecteerdTeam);
		lblSpeler = new JLabel ("Speler: " + geselecteerdeSpeler);    
		lblFoto = new JLabel(); 
		lblFoto.setIcon(spelerIcon);
		areaStatistiekenSpeler = new JTextArea(8,12);  
		
		//Set statistieken as text in area generated after pressing button in frame11
		areaStatistiekenSpeler.setText(statistiekenSpeler);  
		
		//Add all components to panel  
		pane12.add(lblFoto);
		pane12.add(btnMenu); 
		pane12.add(lblTeam); 
		pane12.add(lblSpeler); 
		pane12.add(areaStatistiekenSpeler);
		
		//Place all components 
		lblFoto.setBounds(insets12.left, insets12.top, lblFoto.getPreferredSize().width, lblFoto.getPreferredSize().height); 
		lblTeam.setBounds(150 - (lblTeam.getPreferredSize().width /2), insets12.top + 20, lblTeam.getPreferredSize().width, lblTeam.getPreferredSize().height); 
		lblSpeler.setBounds(150 - (lblSpeler.getPreferredSize().width /2), insets12.top + 40, lblSpeler.getPreferredSize().width, lblSpeler.getPreferredSize().height);
		areaStatistiekenSpeler.setBounds(150 - (areaStatistiekenSpeler.getPreferredSize().width / 2), insets12.top + 60, areaStatistiekenSpeler.getPreferredSize().width, areaStatistiekenSpeler.getPreferredSize().height);
		btnMenu.setBounds(150 -  (btnMenu.getPreferredSize().width / 2), insets12.top + 230, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
		
		
		//Set frame visible and reposition to the middle of the screen 
		frame12.setLocation((dim.width/2)-(frame12.getSize().width/2), (dim.height/2)-(frame12.getSize().height/2));
		frame12.setVisible(true);
		
		//Button's control 
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame12.setVisible(false); 
				frame1.setVisible(true);
			}
		});
		
	} 
	//Initialiseer het team waarvan je de statistieken wil zien
	public static void statistiekTeam() {
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
			
		//Create the frame
		frame13 = new JFrame ("Soccerbot");
		frame13.setSize (280,220); 
		frame13.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH); 
		frame13.setContentPane(new JLabel(new ImageIcon(achtergrondImage))); 
		frame13.setResizable(false);
		pane13 = frame13.getContentPane();					
		insets13 = pane13.getInsets();
		pane13.setLayout (null);
						
		//Create controls
		btnVraagStatistiekenOp = new JButton ("Vraag statistieken op");
		btnMenu = new JButton ("Terug naar menu"); 
		lblLand = new JLabel ("Land:");
		lblDivisie = new JLabel ("Divisie:");
		lblTeam = new JLabel ("Team:");
		

		//create an empty combo box with items of type String 
		landen = new JComboBox<String>();  
		divisies = new JComboBox<String>(); 
		teams = new JComboBox<String>(); 
	
			
		//Inheriting ArrayLists 
		listLanden = Team.getLandenNoDuplicates();   
			
		//Add items to the combobox 
		for(String word : listLanden) { 
			landen.addItem(word);
		} 
			
		//Add all components to panel
		pane13.add (btnVraagStatistiekenOp);
		pane13.add (btnMenu); 
		pane13.add (lblLand); 
		pane13.add (lblDivisie); 
		pane13.add (lblTeam); 
		pane13.add(landen);  
		pane13.add(divisies); 
		pane13.add(teams);  
			
		//Place all components
		lblLand.setBounds(140 - (lblLand.getPreferredSize().width / 2), insets13.top + 10, lblLand.getPreferredSize().width, lblLand.getPreferredSize().height); 
		landen.setBounds(140 - (landen.getPreferredSize().width / 2) , insets13.top + 30, landen.getPreferredSize().width, landen.getPreferredSize().height);
		lblDivisie.setBounds(140 - (lblDivisie.getPreferredSize().width / 2), insets13.top + 52, lblDivisie.getPreferredSize().width, lblDivisie.getPreferredSize().height);  
		divisies.setBounds(80, insets13.top + 70, 120, 20);
		lblTeam.setBounds(140 - (lblTeam.getPreferredSize().width /2), insets13.top + 92, lblTeam.getPreferredSize().width, lblTeam.getPreferredSize().height); 
		teams.setBounds(80, insets13.top + 110, 120, 20); 
		btnVraagStatistiekenOp.setBounds (insets13.left + 70 - (btnVraagStatistiekenOp.getPreferredSize().width /2), insets13.top + 150, btnVraagStatistiekenOp.getPreferredSize().width, btnVraagStatistiekenOp.getPreferredSize().height);
		btnMenu.setBounds (insets13.left + 210 - (btnMenu.getPreferredSize().width /2) , insets13.top + 150, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
						
		//Updating ComboBoxes  
		landen.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				listDivisiesFilteredNoDuplicates.clear();
				land = landen.getSelectedItem().toString(); 
				listDivisiesFilteredNoDuplicates = Team.getDivisiesFilteredNoDuplicates(land);  
				teams.removeAllItems();
				divisies.removeAllItems(); //alle comboboxes na landen moeten leeggemaakt worden
				for(String word : listDivisiesFilteredNoDuplicates) { 
					divisies.addItem(word);
				} 
			}
		});
				
		divisies.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				if (divisies.getItemCount() != 0) {    //Anders error wanneer je terug opnieuw van land verandert
					listTeamsFiltered.clear();
					divisie = divisies.getSelectedItem().toString(); 
					listTeamsFiltered = Team.getTeamsFiltered(divisie); 
					teams.removeAllItems();
					for(String word : listTeamsFiltered) { 
						teams.addItem(word);
					}
				}
			}
		});
		
		
		//Set frame visible and reposition to the middle of the screen 
		frame13.setLocation((dim.width/2)-(frame13.getSize().width/2), (dim.height/2)-(frame13.getSize().height/2));
		frame13.setVisible (true);   
		
		//Button's Action 
		btnVraagStatistiekenOp.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				geselecteerdTeam = teams.getSelectedItem().toString();
				allIdsForGivenTeam = Match.getAllMatchIdsForTeam(geselecteerdTeam);
				statistiekenTeam = Handeling.getStatistiekenTeam(allIdsForGivenTeam, geselecteerdTeam); 
				showStatistiekenTeam();
				frame13.setVisible(false);
			}
		});
		
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame13.setVisible(false); 
				frame1.setVisible(true);
			}
		});
	} 
	//Toont de statistieken van een team
	public static void showStatistiekenTeam() { 
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
				
		//Create the frame
		frame14 = new JFrame ("Soccerbot");
		frame14.setSize (300,350); 
		frame14.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(300, 350, Image.SCALE_SMOOTH); 
		frame14.setContentPane(new JLabel(new ImageIcon(achtergrondImage))); 
		frame14.setResizable(false);
		pane14 = frame14.getContentPane();				
		insets14 = pane14.getInsets();
		pane14.setLayout (null); 
		
		teamFile = geselecteerdTeam + ".jpg";  
		teamIcon = new ImageIcon(teamFile);
		teamImage = teamIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);    
		teamIcon = new ImageIcon(teamImage);  // transform it back
		
		//Create controls
		btnMenu = new JButton ("Terug naar menu"); 
		lblTeam = new JLabel ("Team: " + geselecteerdTeam);
		areaStatistiekenTeam = new JTextArea(10,12);   
		lblFoto = new JLabel(); 
		lblFoto.setIcon(teamIcon);
		
		//Set statistieken as text in area generated after pressing button in frame11
		areaStatistiekenTeam.setText(statistiekenTeam);  
		
		//Add all components to panel  
		pane14.add(lblFoto);
		pane14.add(btnMenu); 
		pane14.add(lblTeam);  
		pane14.add(areaStatistiekenTeam);
		
		//Place all components 
		lblFoto.setBounds(insets14.left, insets14.top, lblFoto.getPreferredSize().width, lblFoto.getPreferredSize().height);
		lblTeam.setBounds(150 - (lblTeam.getPreferredSize().width /2), insets14.top + 20, lblTeam.getPreferredSize().width, lblTeam.getPreferredSize().height); 
		areaStatistiekenTeam.setBounds(150 - (areaStatistiekenTeam.getPreferredSize().width / 2), insets14.top + 60, areaStatistiekenTeam.getPreferredSize().width, areaStatistiekenTeam.getPreferredSize().height);
		btnMenu.setBounds(150 -  (btnMenu.getPreferredSize().width / 2), insets14.top + 270, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
		
		//Set frame visible and reposition to the middle of the screen 
		frame14.setLocation((dim.width/2)-(frame14.getSize().width/2), (dim.height/2)-(frame14.getSize().height/2));
		frame14.setVisible(true);
		
		//Button's control 
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame14.setVisible(false); 
				frame1.setVisible(true);
			}
		});
		
	} 
	//Toont de reeds gelogde wedstrijden
	public static void showGelogdeWedstrijden() { 
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
				
		//Create the frame
		frame15 = new JFrame ("Soccerbot");
		frame15.setSize (700,500); 
		frame15.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(700,500, Image.SCALE_SMOOTH); 
		frame15.setContentPane(new JLabel(new ImageIcon(achtergrondImage))); 
		frame15.setResizable(false);
		pane15 = frame15.getContentPane();				
		insets15 = pane15.getInsets();
		pane15.setLayout (null); 
		
		//Create controls
		btnMenu = new JButton ("Terug naar menu"); 
		lblGelogdeWedstrijden = new JLabel ("Lijst reeds gelogde wedstrijden");
		areaGelogdeWedstrijden = new JTextArea(20,12);  
		scrollGelogdeWedstrijden = new JScrollPane(areaGelogdeWedstrijden);
		
		//Dont't show horizontal scrollbar 
		scrollGelogdeWedstrijden.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER); 
		
		//Set statistieken as text in area generated after pressing button in frame11
		areaGelogdeWedstrijden.setText(gelogdeWedstrijden);  
		
		//Add all components to panel 
		pane15.add(btnMenu); 
		pane15.add(lblGelogdeWedstrijden);  
		pane15.add(scrollGelogdeWedstrijden);
		
		//Place all components
		lblGelogdeWedstrijden.setBounds(350 - (lblGelogdeWedstrijden.getPreferredSize().width /2), insets15.top + 20, lblGelogdeWedstrijden.getPreferredSize().width, lblGelogdeWedstrijden.getPreferredSize().height); 
		scrollGelogdeWedstrijden.setBounds(350 - (areaGelogdeWedstrijden.getPreferredSize().width / 2 + 9), insets15.top + 60, areaGelogdeWedstrijden.getPreferredSize().width + 18, areaGelogdeWedstrijden.getPreferredSize().height);
		btnMenu.setBounds(350 -  (btnMenu.getPreferredSize().width / 2), insets15.top + 430, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
		
		//Set frame visible and reposition to the middle of the screen 
		frame15.setLocation((dim.width/2)-(frame15.getSize().width/2), (dim.height/2)-(frame15.getSize().height/2));
		frame15.setVisible(true);
		
		//Button's control 
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame15.setVisible(false); 
				frame1.setVisible(true);
			}
		});
		
	}
	//Initialiseer de divisie waarvan je de statistieken wil zien
	public static void klassementDivisie() {
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
			
		//Create the frame
		frame16 = new JFrame ("Soccerbot");
		frame16.setSize (280,200); 
		frame16.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH); 
		frame16.setContentPane(new JLabel(new ImageIcon(achtergrondImage)));
		frame16.setResizable(false);
		pane16 = frame16.getContentPane();					
		insets16 = pane16.getInsets();
		pane16.setLayout (null);
						
		//Create controls
		btnShowKlassement = new JButton ("Bekijk klassement");
		btnMenu = new JButton ("Terug naar menu"); 
		lblLand = new JLabel ("Land:");
		lblDivisie = new JLabel ("Divisie:");
		
		//create an empty combo box with items of type String 
		landen = new JComboBox<String>();  
		divisies = new JComboBox<String>();  
		
		//Inheriting ArrayLists 
		listLanden = Team.getLandenNoDuplicates();   
			
		//Add items to the combobox 
		for(String word : listLanden) { 
			landen.addItem(word);
		} 
			
		//Add all components to panel
		pane16.add (btnShowKlassement);
		pane16.add (btnMenu); 
		pane16.add (lblLand); 
		pane16.add (lblDivisie);  
		pane16.add(landen);  
		pane16.add(divisies);   
			
		//Place all components
		lblLand.setBounds(insets16.left + 140 - (lblLand.getPreferredSize().width / 2), insets16.top + 10, lblLand.getPreferredSize().width, lblLand.getPreferredSize().height); 
		landen.setBounds(insets16.left + 140 - (landen.getPreferredSize().width / 2) , insets16.top + 30, landen.getPreferredSize().width, landen.getPreferredSize().height);
		lblDivisie.setBounds(insets16.left + 140 - (lblDivisie.getPreferredSize().width / 2), insets16.top + 52, lblDivisie.getPreferredSize().width, lblDivisie.getPreferredSize().height);  
		divisies.setBounds(80, insets16.top + 70, 120, 20);
		btnShowKlassement.setBounds (insets16.left + 70 - (btnShowKlassement.getPreferredSize().width /2) , insets16.top + 110, btnShowKlassement.getPreferredSize().width, btnShowKlassement.getPreferredSize().height);
		btnMenu.setBounds (insets16.left + 210 - (btnMenu.getPreferredSize().width /2), insets16.top + 110, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
						
		//Updating ComboBoxes  
		landen.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) { 
				listDivisiesFilteredNoDuplicates.clear();
				land = landen.getSelectedItem().toString(); 
				listDivisiesFilteredNoDuplicates = Team.getDivisiesFilteredNoDuplicates(land);  
				divisies.removeAllItems(); //alle comboboxes na landen moeten leeggemaakt worden
				for(String word : listDivisiesFilteredNoDuplicates) { 
					divisies.addItem(word);
				} 
			}
		});
		
		
		//Set frame visible and reposition to the middle of the screen 
		frame16.setLocation((dim.width/2)-(frame16.getSize().width/2), (dim.height/2)-(frame16.getSize().height/2));
		frame16.setVisible (true);   
		
		//Button's Action 
		btnShowKlassement.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				divisie = divisies.getSelectedItem().toString();
				klassementString = Handeling.makeKlassement(divisie); 
				showKlassement();
				frame16.setVisible(false);
			}
		});
		
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame16.setVisible(false); 
				frame1.setVisible(true);
			}
		});
	} 
	//Toont het klassement van een divisie
	public static void showKlassement() { 
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
				
		//Create the frame
		frame17 = new JFrame ("Soccerbot");
		frame17.setSize (600,500); 
		frame17.setIconImage(new ImageIcon(iconFile).getImage()); 
		achtergrondImage = achtergrondIcon.getImage().getScaledInstance(600,500, Image.SCALE_SMOOTH); 
		frame17.setContentPane(new JLabel(new ImageIcon(achtergrondImage))); 
		frame17.setResizable(false);
		pane17 = frame17.getContentPane();				
		insets17 = pane17.getInsets();
		pane17.setLayout (null); 
		
		//Create controls
		btnSaveToFile = new JButton("Opslaan");
		btnMenu = new JButton ("Terug naar menu"); 
		lblKlassement = new JLabel ("Klassement: " + divisie);
		areaKlassement = new JTextArea(20,12);  
		scrollKlassement = new JScrollPane(areaKlassement);
		
		//Dont't show horizontal scrollbar 
		scrollKlassement.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER); 
		
		areaKlassement.setText(klassementString);  
		
		//Add all components to panel  
		pane17.add(btnSaveToFile);
		pane17.add(btnMenu); 
		pane17.add(lblKlassement);  
		pane17.add(scrollKlassement);
		
		//Place all components
		lblKlassement.setBounds(insets17.left + 300 - (lblKlassement.getPreferredSize().width /2), insets17.top + 20, lblKlassement.getPreferredSize().width, lblKlassement.getPreferredSize().height); 
		scrollKlassement.setBounds(insets17.left + 300 - (areaKlassement.getPreferredSize().width / 2 + 9), insets17.top + 60, areaKlassement.getPreferredSize().width + 18, areaKlassement.getPreferredSize().height);
		btnSaveToFile.setBounds(insets17.left + 150 - (btnSaveToFile.getPreferredSize().width /2), insets17.top + 430, btnSaveToFile.getPreferredSize().width, btnSaveToFile.getPreferredSize().height);
		btnMenu.setBounds(insets17.left + 450 -  (btnMenu.getPreferredSize().width / 2), insets17.top + 430, btnMenu.getPreferredSize().width, btnMenu.getPreferredSize().height);
		
		//Set frame visible and reposition to the middle of the screen 
		frame17.setLocation((dim.width/2)-(frame17.getSize().width/2), (dim.height/2)-(frame17.getSize().height/2));
		frame17.setVisible(true);
		
		//Button's control   
		btnSaveToFile.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				Handeling.saveThisKlassement(land, divisie);
			}
		});
		
		btnMenu.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame17.setVisible(false); 
				frame1.setVisible(true);
			}
		});
		 
	} 
	
	public static void bevestigingsSchermNietOpgeslagenMatch() {
		
		//Set Look and Feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//Create the frame
		frame18 = new JFrame ("Soccerbot"); 
		frame18.setIconImage(new ImageIcon(iconFile).getImage());
		frame18.setLayout (new GridLayout(3,1));  
		frame18.setResizable(false);
		
		//Create controls 
		lblNietOpgeslagen = new JLabel("U dreigt terug te keren naar het menu zonder de match opgeslagen te hebben!");
		btnYes = new JButton("Ik wil de match nog opslaan."); 
		btnNo = new JButton("Keer terug zonder opslaan. ");  
		
		//Add all components to panel 
		frame18.add(lblNietOpgeslagen);
		frame18.add (btnYes); 
		frame18.add (btnNo); 
						
		frame18.pack(); 
		
		//Set frame visible and reposition to the middle of the screen 
		frame18.setLocation((dim.width/2)-(frame18.getSize().width/2), (dim.height/2)-(frame18.getSize().height/2));
		frame18.setVisible (true); 
		
		//Button's action
		btnYes.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				frame18.setVisible(false);  
				frame3.setVisible(true);
			}
		}); 
		
		btnNo.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				listSpelersRedCard.clear();
				Match.deleteMatch(currentWedstrijdId); 
				Handeling.deleteHandelingen(currentWedstrijdId); 
				Match.saveMatchen(); 
				Handeling.saveHandelingen();
				frame18.setVisible(false); 
				frame1.setVisible(true);
			} 
		});
	
	}
	//Update de score van het handelingenscherm en herbekijkhandelingenscherm
	public static void scoreUpdater(String id) { 
		teamsForGivenMatch.clear(); // clearen van de arraylists zodat ze zeker leeg zijn.
		handelingenForGivenMatch.clear(); //
		teamsForGivenMatch = Handeling.getTeamsForGivenMatch(id); //inladen van de arraylists vanuit klasse Handeling
		handelingenForGivenMatch = Handeling.getHandelingenForGivenMatch(id); //
		int intScoreThuisteam = 0; 
		int intScoreUitteam = 0;
		int i=0; 
		String readHandeling, readTeam;   
		int size = handelingenForGivenMatch.size(); 
		if (handelingenForGivenMatch.isEmpty() == false) { 
			while(i<size) { 
				readHandeling = handelingenForGivenMatch.get(i);   
				if(readHandeling.equals("Gescoord")) { 
					readTeam = teamsForGivenMatch.get(i); 
					if(readTeam.equals(thuisteam)) {
						intScoreThuisteam = intScoreThuisteam + 1;
					}
					else { 
						intScoreUitteam = intScoreUitteam + 1;
					}
				} 
				if(readHandeling.equals("OwnGoal")) { 
					readTeam = teamsForGivenMatch.get(i); 
					if(readTeam.equals(thuisteam)) {
						intScoreUitteam = intScoreUitteam + 1;
					}
					else { 
						intScoreThuisteam = intScoreThuisteam + 1;
					}
				}
				i = i + 1;
			} 
		}  
		scoreThuisteam = Integer.toString(intScoreThuisteam); 
		scoreUitteam = Integer.toString(intScoreUitteam); 
		lblScoreThuisteam.setText(scoreThuisteam); 
		lblScoreUitteam.setText(scoreUitteam);
	}
	//Toont de handelingen in het handelingenscherm en het herbekijkhandelingenscherm
	public static void getHandelingenForThisMatch(String id) { 
		teamsForGivenMatch.clear(); // clearen van de arraylists zodat ze zeker leeg zijn.
		spelersForGivenMatch.clear(); //
		handelingenForGivenMatch.clear(); //
		minutenForGivenMatch.clear(); //
		teamsForGivenMatch = Handeling.getTeamsForGivenMatch(id); //inladen van de arraylists vanuit klasse Handeling
		spelersForGivenMatch = Handeling.getSpelersForGivenMatch(id); //
		handelingenForGivenMatch = Handeling.getHandelingenForGivenMatch(id); //
		minutenForGivenMatch = Handeling.getMinutenForGivenMatch(id); //
		String currentTeam,currentSpeler, currentHandeling, currentMinuut; 
		String textToDisplayThuisteam = ""; 
		String textToDisplayUitteam = ""; 
		String textToDisplayMinuut = "";  
		int currentLine = 0;
		if(teamsForGivenMatch.isEmpty() == false) { 
			while(currentLine < teamsForGivenMatch.size()) { 
				currentTeam = teamsForGivenMatch.get(currentLine); 
				currentSpeler = spelersForGivenMatch.get(currentLine); 
				currentHandeling = handelingenForGivenMatch.get(currentLine);
				currentMinuut = minutenForGivenMatch.get(currentLine);  
				currentLine = currentLine + 1;
				if(currentTeam.equals(thuisteam)) {  
					textToDisplayThuisteam = textToDisplayThuisteam + currentSpeler + " - " + currentHandeling + "\n";   
					textToDisplayUitteam = textToDisplayUitteam + "\n"; 
				} 
				else {  
					textToDisplayUitteam = textToDisplayUitteam + currentSpeler + " - " + currentHandeling + "\n";  
					textToDisplayThuisteam = textToDisplayThuisteam + "\n"; 
				}
				textToDisplayMinuut = textToDisplayMinuut + currentMinuut + "\n";
				
			}
		} 
		else { 
			textToDisplayMinuut = ""; 
			textToDisplayThuisteam = ""; 
			textToDisplayUitteam = "";
		}
		areaHandelingenThuisteam.setText(textToDisplayThuisteam); 
		areaHandelingenUitteam.setText(textToDisplayUitteam); 
		areaMinutenHandelingen.setText(textToDisplayMinuut);
	}  
	//Slaat de herbekeken match op naar een tekstbestand
	public static void saveThisMatch() { 
		String writeThis = "";  
		String wordString ="";
		BufferedWriter outputWriter = null; 
		String matchOutputFile = "";  
		matchOutputFile = matchOutputFile + thuisteam + "-" + uitteam + ".txt"; 
		String currentTeam,currentSpeler, currentHandeling, currentMinuut;   
		int currentLine = 0, secondOffset;;
		try { 
			File outputFile = new File(matchOutputFile); 
			outputWriter = new BufferedWriter(new FileWriter(outputFile));  //true zorgt ervoor dat er aan de file wordt toegevoegd en geen nieuwe w gemaakt.
			wordString = "Id: " + currentWedstrijdId;  
			writeThis = getSpaties(wordString,70);  //30
			outputWriter.write(writeThis);
			outputWriter.newLine(); 
			wordString = "Land: " + land;
			writeThis = getSpaties(wordString,70); //30
			outputWriter.write(writeThis); 
			outputWriter.newLine(); 
			wordString = "Divisie: " + divisie;  
			writeThis = getSpaties(wordString,70); //30
			outputWriter.write(writeThis); 
			outputWriter.newLine();  
			wordString = datum; 
			writeThis = getSpaties(wordString,65);  //25
			outputWriter.write(writeThis);  
			wordString = tijd; 
			writeThis = getSpaties(wordString,5);  //5
			outputWriter.write(writeThis); 
			outputWriter.newLine();  
			wordString = stadion; 
			writeThis = getSpaties(wordString,70); //30
			outputWriter.write(writeThis); 
			outputWriter.newLine();  
			wordString = thuisteam; 
			writeThis = getSpaties(wordString,35); //15
			outputWriter.write(writeThis);  
			if((wordString.length() % 2) == 0) { 
				secondOffset = (71 - (wordString.length()/2));      //31
			}
			else { 
				secondOffset = (70 - (wordString.length()/2));      //30
			}
			wordString = uitteam; 
			writeThis = getSpaties(wordString,secondOffset);   
			outputWriter.write(writeThis); 
			outputWriter.newLine();  
			wordString = "Score"; 
			writeThis = getSpaties(wordString,69);   			//29
			outputWriter.write(writeThis); 
			outputWriter.newLine(); 
			wordString = scoreThuisteam; 
			writeThis = getSpaties(wordString,34);  			//14 
			outputWriter.write(writeThis); 
			wordString = scoreUitteam; 
			writeThis = getSpaties(wordString,71);          	//31
			outputWriter.write(writeThis); 
			outputWriter.newLine(); 
			wordString = "Minuut"; 
			writeThis = getSpaties(wordString,70);  			//30
			outputWriter.write(writeThis); 
			outputWriter.newLine(); 
			if(teamsForGivenMatch.isEmpty() == false) { 
				while(currentLine < teamsForGivenMatch.size()) { 
					currentTeam = teamsForGivenMatch.get(currentLine); 
					currentSpeler = spelersForGivenMatch.get(currentLine); 
					currentHandeling = handelingenForGivenMatch.get(currentLine);
					currentMinuut = minutenForGivenMatch.get(currentLine);  
					currentLine = currentLine + 1;
					if(currentTeam.equals(thuisteam)) { 
						wordString =  currentSpeler + " - " + currentHandeling;  
						writeThis = getSpaties(wordString,35);				//15
						outputWriter.write(writeThis);  
						if((wordString.length() % 2) == 0) { 
							secondOffset = (35 - (wordString.length()/2));		//15	
						}
						else { 
							secondOffset = (34 - (wordString.length()/2));		//14
						}
						wordString = currentMinuut; 
						writeThis = getSpaties(wordString,secondOffset); 
						outputWriter.write(writeThis);
						outputWriter.newLine();
					} 
					else {  
						wordString = currentMinuut; 
						writeThis = getSpaties(wordString,70); 					//30
						outputWriter.write(writeThis);
						wordString =  currentSpeler + " - " + currentHandeling;  
						writeThis = getSpaties(wordString,35);					//15
						outputWriter.write(writeThis); 
						outputWriter.newLine();
					}
				}
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

	//Wordt gebruikt om de textfile mooi op te lijnen
	public static String getSpaties(String thisString, int offset) {  
		int length = thisString.length(); 
		length = length / 2; 
		int i; 
		int spaties = offset - length;
		String returnString = ""; 
		for(i=0;i<spaties;i++) { 
			returnString = returnString + " ";
		} 
		return returnString + thisString;
	}
	//Controleert of datum aanvaarbaar is ingegeven
	public static boolean checkDatum(String datum) { 
		String[] splits = new String[3]; 
		if (datum.equals("") == false) {
			splits = datum.split("-"); 
			int dag = Integer.parseInt(splits[0]); 
			int maand = Integer.parseInt(splits[1]); 
			int jaar = Integer.parseInt(splits[2]); 
			if(dag <= 31 && dag > 0 && maand <= 12 && maand > 0 && jaar < 100) { 
				return true;
			} 
			else { 
				return false;
			}
		}
		else { 
			return false;
		}
	}  
	//Controleert of tijd aanvaarbaar is ingegeven
	public static boolean checkTijd(String tijd) { 
		String[] splits = new String[2]; 
		if(tijd.equals("") == false) {
			splits = tijd.split(":"); 
			int uur = Integer.parseInt(splits[0]); 
			int minuten = Integer.parseInt(splits[1]); 
			if(uur < 24 && uur >= 0 && minuten <= 60 && minuten >= 0) { 
				return true;
			} 
			else { 
				return false;
			}
		}
		else { 
			return false;
		}
	}
		
} 


