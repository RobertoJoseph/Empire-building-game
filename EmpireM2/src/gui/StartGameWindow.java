package gui;

import engine.*;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import buildings.*;
import units.*;
import javax.sound.sampled.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class StartGameWindow extends JFrame implements ActionListener {
	JTextField textField;
	JTextArea textArea;
	String playerName = "";
	String playerCity = "";
	JFrame frame;
	JFrame frame2;

	JLabel armyTarget;
	int q;

	ImageIcon background;
	ImageIcon background2;
	ImageIcon userBack;
	ImageIcon cairo;
	ImageIcon rome;
	ImageIcon sparta;
	ImageIcon startButton;
	ImageIcon submit;
	ImageIcon armyIcon;
	JLabel myLabel;
	JLabel myLabel2;
	String cityPress;
	String buildingPress;
	JLabel player;
	JLabel turncountP;
	JLabel label;
	JLabel goldP;
	JLabel idleArmies;
	JLabel foodP;
	JPanel startGame;
	JPanel worldMapView;
	JPanel cairoStats;
	JPanel informationPanel;

	Game game;
	Player user;
	City defend;

	JPanel romeDetails;
	JPanel spartaDetails;
	JPanel cairoDetails;

	JButton archeryRange;
	JButton barracks;
	JButton farm;
	JButton market;
	JButton stable;
	JButton initiateArmy;

	JPanel archeryRangeDetails;
	JPanel barracksDetails;
	JPanel farmDetails;
	JPanel marketDetails;
	JPanel stableDetails;

	int y;
	JButton Upgrade;
	JButton recruit;
	JButton ArmiesinCites;
	JPanel ArmiesinCitiesP;

	JLabel det = new JLabel();
	JButton showDefendingArmy;
	JPanel defendingArmyPanel;

	JButton idleArmiesButton;
	JButton besigingArmiesButton;
	JButton marchingArmiesButton;

	JPanel idlePanel;
	JPanel besigingPanel;
	JPanel marchingPanel;

	JTextArea idleTextArea = new JTextArea();
	String temp = "Type" + "   " + "Current count" + "   " + "Level" + "   " + "Max soldier count";
	String temp2 = "Type" + "   " + "Current count" + "   " + "Level" + "   " + "Max soldier count" + "  "
			+ "BESEIGING CITY" + "  " + "Turns Under Siege";
	String temp3 = "Type" + "   " + "Current count" + "   " + "Level" + "   " + "Max soldier count" + "  "
			+ "Target city" + "  " + "Distance to target";
	JTextArea besigingTextArea = new JTextArea();
	JTextArea marchingTextArea = new JTextArea();

	JTextArea defendingArmyArea;
	JTextArea armiesInCitesArea;
	JPanel gameInformation;

	JButton endturn;

	JButton attack;
	JButton autoResolve;
	JButton besieging;

	JButton targetCityButton;
	JLabel playerInfo;
	JButton cairoButton;
	JButton romeButton;
	JButton spartaButton;
	JButton startGameButton;
	JButton cairoInMap;
	JButton spartaInMap;
	JButton romeInMap;
	JComboBox<String> comboBox;
	int x = 0;

	JButton buildButton;

	JPanel targetPanel;
	ArrayList<JButton> armyButtons;

	ArrayList<JButton> attackerArmy;
	Unit attackingUnit;
	ArrayList<JButton> defenderArmy;
	Unit defendingUnit;

	Army attacker;

	JButton armycounter = null;
	JFrame intiateArmyFrame;

	City armyInitiator;
	JFrame initiateFrame;
	// Relocating Units
	JButton relocate;
	Unit relocatingUnit;
	Army relocatingArmy;
	JButton relocateUnitButton;
	JFrame relocateFrame;

	ArrayList<JButton> userControlledArmiesButtons;
	JPanel controlledArmiesPanel;
	JPanel defendingArmyInCityPanel;

	ArrayList<JButton> defendingArmyButtonUnits;
	ArrayList<JButton> relocateArmies;

	JFrame attackFrame;
	JPanel cityUnits;
	JPanel defendingUnits;
	JButton fightButton;
	JPanel logPanel;
	Army test;
	JTextArea news;
	String logNews = "Type" + "  " + "Current Soldier count" + "  " + "Level";

	int r = 0;

	JPanel promptPanel;
	boolean flag;
	
	File sound;
	static AudioInputStream stream;
	static Clip clip;
	static int counter = 0;
	int turnsUnderSiege;

	public StartGameWindow() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
	
	 
		textArea = new JTextArea();
		textArea.setOpaque(true);
		relocateArmies = new ArrayList<>();

		armyTarget = new JLabel();

		news = new JTextArea();

		news.setPreferredSize(new Dimension(600, 200));
		news.setOpaque(false);

		attackFrame = new JFrame();
		cityUnits = new JPanel();
		JLabel Attacklabel = new JLabel("Controlled Army Units");
		cityUnits.setBackground(new Color(0xC2C8D2));
		defendingUnits = new JPanel();
		JLabel Defendlabel = new JLabel("Enemey Army Units");
		defendingUnits.setBackground(new Color(0x7F90AD));
		cityUnits.setPreferredSize(new Dimension(500, 300));
		defendingUnits.setPreferredSize(new Dimension(500, 300));
		cityUnits.add(Attacklabel);
		defendingUnits.add(Defendlabel);
		fightButton = new JButton("fight");
		fightButton.setBackground(Color.red);
		fightButton.setPreferredSize(new Dimension(500, 50));
		fightButton.addActionListener(this);
		logPanel = new JPanel();
		logPanel.setBackground(new Color(0xF14B48));
		logPanel.setPreferredSize(new Dimension(600, 200));

		attackFrame.setLayout(new FlowLayout());
		attackFrame.add(cityUnits);

		attackFrame.add(defendingUnits);
		attackFrame.add(fightButton);
		attackFrame.add(logPanel);

		attackFrame.setVisible(false);
		attackFrame.setSize(1080, 600);
		defenderArmy = new ArrayList<JButton>();
		attackerArmy = new ArrayList<JButton>();
		armyButtons = new ArrayList<JButton>();
		defendingArmyButtonUnits = new ArrayList<JButton>();
		userControlledArmiesButtons = new ArrayList<JButton>();
		String[] buildings = { "null", "ArcheryRange", "Barracks", "Stable", "Farm", "Market" };
		comboBox = new JComboBox<String>(buildings);
		comboBox.addActionListener(this);

		setLayout(null);

		userBack = new ImageIcon(this.getClass().getResource("/userBack1.jpg"));

		playerInfo = new JLabel(userBack);
		playerInfo.setBackground(Color.pink);
		playerInfo.setBounds(1000, 0, 500, 150);

		gameInformation = new JPanel();
		gameInformation.setBounds(1000, 150, 500, 800);
		gameInformation.setBackground(new Color(0xE7D9D6));
		comboBox.setVisible(false);

		gameInformation.add(comboBox);

		relocate = new JButton("Relocate");
		relocateUnitButton = new JButton("Relocate Unit");
		relocate.addActionListener(this);
		relocateUnitButton.addActionListener(this);

		armiesInCitesArea = new JTextArea();
//		armiesInCitesArea.setBackground(Color.blue);
		armiesInCitesArea.setOpaque(false);
		armiesInCitesArea.setPreferredSize(new Dimension(400, 500));
		armiesInCitesArea.setMargin(new Insets(10, 10, 10, 10));
		armiesInCitesArea.setEditable(false);
		armiesInCitesArea.setText(temp);

		defendingArmyArea = new JTextArea();
		defendingArmyArea.setBackground(Color.orange);
		defendingArmyArea.setPreferredSize(new Dimension(310, 500));
		defendingArmyArea.setMargin(new Insets(10, 10, 10, 10));
		defendingArmyArea.setOpaque(true);
		defendingArmyArea.setText(temp);
		defendingArmyArea.setEditable(false);

		marchingTextArea.setOpaque(true);
		marchingTextArea.setPreferredSize(new Dimension(500, 900));

		marchingTextArea.setText(temp + "  " + "Target city" + "  " + "Distance to target");
		marchingTextArea.setEditable(false);

		besigingTextArea.setOpaque(true);
		besigingTextArea.setPreferredSize(new Dimension(500, 900));

		besigingTextArea.setText(temp + "  " + "Beseging city" + "  " + "Turns under siege");
		besigingTextArea.setEditable(false);
		idleTextArea.setEditable(false);

		idleTextArea.setOpaque(true);
		idleTextArea.setPreferredSize(new Dimension(500, 900));
		idleTextArea.setText(temp);
		idleTextArea.setEditable(false);
		showDefendingArmy = new JButton("Defending Army");
		showDefendingArmy.addActionListener(this);

		archeryRange = new JButton("ArcheryRange");
		barracks = new JButton("barracks");
		stable = new JButton("stable");
		farm = new JButton("farm");
		market = new JButton("market");

		archeryRange.addActionListener(this);
		barracks.addActionListener(this);
		farm.addActionListener(this);
		market.addActionListener(this);
		stable.addActionListener(this);

		Upgrade = new JButton("Upgrade");
		Upgrade.addActionListener(this);

		recruit = new JButton("Recruit");
		recruit.addActionListener(this);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setSize(2560,1600);

//		setSize(1000, 900);
//		panel.setBackground(Color.red);
		
		background = new ImageIcon(this.getClass().getResource("/backgr.jpg"));
		//
		myLabel = new JLabel(background);
		myLabel.setBounds(-80, 0, 1600, 800);
//		myLabel.setBackground()
		textField = new JTextField();
		textField.setBounds(730, 305, 220, 38);
		textField.setFont(new Font("Serif", Font.BOLD, 20));
		textField.setForeground(Color.white);
		textField.setOpaque(false);
		textField.setBorder(BorderFactory.createLineBorder(new Color(0x0D306C), 3));
		myLabel.add(textField);

		submit = new ImageIcon(this.getClass().getResource("/1.png"));
		JButton submitButton = new JButton("Submit", submit);
		submitButton.setBorderPainted(false);
		submitButton.setContentAreaFilled(false);
		submitButton.setBounds(980, 300, 58, 48);
		myLabel.add(submitButton);

//				 

		cairoButton = new JButton();
		cairoButton.setBorderPainted(false);
		cairoButton.setContentAreaFilled(false);
		cairoButton.setBounds(730, 400, 140, 90);
		myLabel.add(cairoButton);

//		rome = new ImageIcon(this.getClass().getResource("/rome.jpg"));
		romeButton = new JButton();
		romeButton.setBorderPainted(false);
		romeButton.setContentAreaFilled(false);
		romeButton.setBounds(540, 380, 150, 120);

		JButton spartaButton = new JButton("R");
		spartaButton.setBorderPainted(false);
		spartaButton.setContentAreaFilled(false);
		spartaButton.setBounds(920, 400, 150, 100);

		startGameButton = new JButton();
		startGameButton.setBorderPainted(false);
		startGameButton.setContentAreaFilled(false);
		startGameButton.setBounds(730, 600, 140, 100);

		myLabel.add(startGameButton);
		myLabel.add(romeButton);
		myLabel.add(spartaButton);
		add(myLabel);

		cairoButton.addActionListener(this);
		romeButton.addActionListener(this);
		spartaButton.addActionListener(this);
		startGameButton.addActionListener(this);
		submitButton.addActionListener(this);
//		worldMapView = new JPanel();
//		worldMapView.setPreferredSize(new Dimension(1000, 800));

		background2 = new ImageIcon(this.getClass().getResource("/zezo.jpeg"));

		myLabel2 = new JLabel(background2);
		myLabel2.setSize(1000, 800);
		promptPanel = new JPanel();
		promptPanel.setBounds(0, 40, 1000, 800);
		promptPanel.setLayout(new FlowLayout());
		promptPanel.setBackground(new Color(0xE86B68));

		promptPanel.setVisible(false);
		myLabel2.add(promptPanel);

		initiateArmy = new JButton("initiate army");
		initiateArmy.addActionListener(this);
		buildButton = new JButton("build");
		buildButton.addActionListener(this);

		//

		defendingArmyPanel = new JPanel();
		defendingArmyPanel.setBounds(900, 100, 300, 500);
		defendingArmyPanel.setBackground(Color.orange);
		defendingArmyPanel.setVisible(false);
//		myLabel2.add(defendingArmyPanel);
		gameInformation.add(defendingArmyPanel);

		//

//		myLabel2.add(turncount);

		endturn = new JButton("end turn");
		endturn.setBounds(900, 730, 100, 70);
		endturn.addActionListener(this);
		myLabel2.add(endturn);

		textArea.setPreferredSize(new Dimension(300, 200));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBackground(Color.orange);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setFont(new Font("Verdana", Font.BOLD, 15));
		textArea.setEditable(false);

		//

		ArmiesinCites = new JButton("ArmiesinCities");
		ArmiesinCites.addActionListener(this);
		ArmiesinCitiesP = new JPanel();

		ArmiesinCitiesP.setVisible(false);
		ArmiesinCitiesP.setBackground(Color.pink);
		ArmiesinCitiesP.setPreferredSize(new Dimension(400, 600));
		gameInformation.add(ArmiesinCitiesP);

		JLabel text2 = new JLabel("Player Name:");
		text2.setFont(new Font("Kirang Haerang", Font.PLAIN, 18));
		text2.setForeground(Color.black);
		text2.setBounds(10, 0, 300, 100);
		playerInfo.add(text2);
		//
		player = new JLabel(playerName);
		player.setFont(new Font("Kirang Haerang", Font.PLAIN, 18));
		player.setForeground(Color.black);
		player.setBounds(150, 0, 300, 100);
		playerInfo.add(player);

		JLabel turncount = new JLabel("TurnCount:");
		turncount.setFont(new Font("Kirang Haerang", Font.PLAIN, 18));
		turncount.setForeground(Color.black);
		turncount.setBounds(10, 25, 300, 100);
		playerInfo.add(turncount);

		turncountP = new JLabel("");
		turncountP.setFont(new Font("Kirang Haerang", Font.PLAIN, 18));
		turncountP.setForeground(Color.black);
		turncountP.setBounds(150, 25, 300, 100);
		playerInfo.add(turncountP);

		JLabel gold = new JLabel("Gold :");
		gold.setFont(new Font("Kirang Haerang", Font.PLAIN, 18));
		gold.setForeground(Color.black);
		gold.setBounds(10, 50, 300, 100);
		playerInfo.add(gold);
		//
		goldP = new JLabel("");
		goldP.setFont(new Font("Kirang Haerang", Font.PLAIN, 18));
		goldP.setForeground(Color.black);
		goldP.setBounds(150, 50, 300, 100);
		playerInfo.add(goldP);

		JLabel food = new JLabel("Food :");
		food.setFont(new Font("Kirang Haerang", Font.PLAIN, 18));
		food.setForeground(Color.black);
		food.setBounds(10, 75, 300, 100);
		playerInfo.add(food);

		foodP = new JLabel("0");
		foodP.setFont(new Font("Kirang Haerang", Font.PLAIN, 18));
		foodP.setForeground(Color.black);
		foodP.setBounds(150, 75, 300, 100);
		playerInfo.add(foodP);

		informationPanel = new JPanel();

		informationPanel.setLayout(new GridLayout(3, 0, 12, 10));
		informationPanel.setFont(new Font("Verdana", Font.PLAIN, 18));
//		informationPanel.setForeground(Color.black);
		informationPanel.setBounds(90, 250, 600, 500);
		informationPanel.setBackground(Color.cyan);
		informationPanel.setVisible(false);
		myLabel2.add(informationPanel);

		idleArmies = new JLabel("");
		idleArmies.setFont(new Font("Verdana", Font.BOLD, 18));
		idleArmies.setForeground(Color.black);
		idleArmies.setBounds(250, 600, 300, 100);
		myLabel2.add(idleArmies);

		barracksDetails = new JPanel();

		farmDetails = new JPanel();

		barracksDetails.setPreferredSize(new Dimension(400, 400));
		barracksDetails.setOpaque(false);
		barracksDetails.setLayout(new GridLayout(4, 0));
		barracksDetails.setVisible(false);
		gameInformation.add(barracksDetails);

		targetCityButton = new JButton("target city");
		targetCityButton.setVisible(false);
		targetCityButton.setPreferredSize(new Dimension(130, 90));
		targetCityButton.setBorderPainted(false);
		targetCityButton.setForeground(Color.white);
		targetCityButton.setBackground(Color.red);

		targetCityButton.setOpaque(true);
		targetCityButton.addActionListener(this);
		gameInformation.add(targetCityButton);
//
		targetPanel = new JPanel();
		targetPanel.setBackground(new Color(0xC2C8D2));
		targetPanel.setVisible(false);
		targetPanel.setPreferredSize(new Dimension(300, 500));
		gameInformation.add(targetPanel);

		stableDetails = new JPanel();
		stableDetails.setPreferredSize(new Dimension(400, 500));
		stableDetails.setOpaque(false);
		stableDetails.setLayout(new GridLayout(4, 0));
		stableDetails.setVisible(false);
		stableDetails.setBackground(Color.orange);
		gameInformation.add(stableDetails);

		farmDetails.setPreferredSize(new Dimension(400, 500));
		farmDetails.setOpaque(false);
		farmDetails.setLayout(new GridLayout(4, 0));
		farmDetails.setVisible(false);
		farmDetails.setBackground(Color.orange);
		gameInformation.add(farmDetails);
//		myLabel2.add(farmDetails);
		marketDetails = new JPanel();
		marketDetails.setPreferredSize(new Dimension(400, 500));
		marketDetails.setLayout(new GridLayout(4, 0));
//		marketDetails.setBackground(Color.orang);
		marketDetails.setOpaque(false);
		marketDetails.setVisible(false);
		gameInformation.add(marketDetails);

		archeryRangeDetails = new JPanel();
		archeryRangeDetails.setLayout(new GridLayout(4, 0));
		archeryRangeDetails.setPreferredSize(new Dimension(400, 500));
		archeryRangeDetails.setOpaque(false);
		archeryRangeDetails.setVisible(false);
		gameInformation.add(archeryRangeDetails);
		attack = new JButton("attack");
		attack.setBounds(440, 600, 200, 90);
		autoResolve = new JButton("autoResolve");
		autoResolve.addActionListener(this);
		autoResolve.setBounds(540, 600, 200, 90);
		besieging = new JButton("besieging");
		besieging.setBounds(640, 600, 200, 90);
		attack.setVisible(false);
		autoResolve.setVisible(false);
		besieging.setVisible(false);
		besieging.addActionListener(this);
		attack.addActionListener(this);

		myLabel2.add(attack);
		myLabel2.add(autoResolve);
		myLabel2.add(besieging);
//		ImageIcon cairoImage = new ImageIcon(th)

		ImageIcon cairoImage = new ImageIcon(this.getClass().getResource("/Cairo.png"));
		cairoInMap = new JButton("", cairoImage);
		cairoInMap.setBorderPainted(false);
		cairoInMap.setContentAreaFilled(false);
		ImageIcon spartaImage = new ImageIcon(this.getClass().getResource("/Sparta.png"));
		spartaInMap = new JButton("", spartaImage);
		spartaInMap.setBorderPainted(false);
		spartaInMap.setContentAreaFilled(false);

		ImageIcon romeImage = new ImageIcon(this.getClass().getResource("/Rome.png"));
		romeInMap = new JButton("", romeImage);
		romeInMap.setBorderPainted(false);
		romeInMap.setContentAreaFilled(false);

		idleArmiesButton = new JButton("IDLE");
		idleArmiesButton.addActionListener(this);
		idleArmiesButton.setBounds(100, 600, 100, 70);
		myLabel2.add(idleArmies);
		cairoInMap.addActionListener(this);
		romeInMap.addActionListener(this);
		spartaInMap.addActionListener(this);
		cairoInMap.setBounds(50, 200, 200, 200);
		romeInMap.setBounds(675, 100, 100, 200);
		spartaInMap.setBounds(550, 400, 100, 200);

		myLabel2.add(cairoInMap);
		myLabel2.add(romeInMap);
		myLabel2.add(spartaInMap);
		idleArmiesButton = new JButton("IDLE");
		besigingArmiesButton = new JButton("BESIGING");
		marchingArmiesButton = new JButton("MARCHING");
		marchingArmiesButton.addActionListener(this);
		marchingArmiesButton.setBounds(370, 700, 100, 40);
//		armyDetails.setPreferredSize(new Dimension(500,500));
		besigingArmiesButton.addActionListener(this);
		besigingArmiesButton.setBounds(50, 700, 100, 40);
		idleArmiesButton.setBounds(210, 700, 100, 40);
		idleArmiesButton.addActionListener(this);
		myLabel2.add(idleArmiesButton);
		myLabel2.add(besigingArmiesButton);
		myLabel2.add(marchingArmiesButton);

//
//		cairoStats = new JPanel();
//		cairoStats.setBackground(Color.red);
//		cairoStats.setBounds(100, 400, 200, 190);
//
//		myLabel2.add(cairoStats);
		idlePanel = new JPanel();
		idlePanel.setBackground(Color.pink);
		idlePanel.setBounds(250, 300, 500, 600);

		idlePanel.setVisible(false);
		myLabel2.add(idlePanel);

		romeDetails = new JPanel();
		romeDetails.setBackground(Color.yellow);
		romeDetails.setVisible(false);
		romeDetails.setBounds(150, 400, 400, 300);
		romeDetails.setOpaque(false);
		myLabel2.add(romeDetails);
		cairoDetails = new JPanel();

		cairoDetails.setBackground(Color.yellow);
		cairoDetails.setVisible(false);
		cairoDetails.setBounds(150, 400, 400, 300);
		cairoDetails.setOpaque(false);

		myLabel2.add(cairoDetails);

		spartaDetails = new JPanel();

		spartaDetails.setBackground(Color.yellow);
		spartaDetails.setVisible(false);
		spartaDetails.setBounds(150, 400, 400, 300);
		spartaDetails.setOpaque(false);

		myLabel2.add(spartaDetails);

//		worldMapView.add(myLabel2);

//		worldMapView.setVisible(true);

//		add(myLabel2);

//		add(tttt);
		playerInfo.setVisible(false);
		relocateFrame = new JFrame();
		relocateFrame.setLayout(new FlowLayout());
		defendingArmyInCityPanel = new JPanel();
		JLabel label = new JLabel("ARMIES IN CITIES");
		defendingArmyInCityPanel.setBackground(new Color(0xC2C8D2));
		controlledArmiesPanel = new JPanel();
		JLabel label2 = new JLabel("CITY DEFENDNG ARMY");
		controlledArmiesPanel.setBackground(new Color(0x7F90AD));
		defendingArmyInCityPanel.setPreferredSize(new Dimension(500, 400));
		controlledArmiesPanel.setPreferredSize(new Dimension(500, 400));
		defendingArmyInCityPanel.add(label);
		controlledArmiesPanel.add(label2);
		relocateUnitButton.setBackground(Color.red);
		relocateUnitButton.setPreferredSize(new Dimension(400, 100));

		relocateFrame.add(defendingArmyInCityPanel);
		relocateFrame.add(controlledArmiesPanel);
		relocateFrame.add(relocateUnitButton);
		relocateFrame.setVisible(true);
		relocateFrame.setSize(1080, 600);
		relocateFrame.setVisible(false);

		validate();
		repaint();
		revalidate();

//				

//		

//		add(panel2);
//		
//		
//		
//		
//
//	}

	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new StartGameWindow();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Submit")) {
			playerName = textField.getText();

		}

		if (e.getSource().equals(cairoButton)) {

			playerCity = "Cairo";

		}
		if (e.getSource().equals(romeButton)) {

			playerCity = "Rome";
		}
		if (e.getActionCommand().equals("R")) {
			playerCity = "Sparta";
		}
		if (e.getSource().equals(startGameButton) && !playerCity.equals("") && !playerName.equals("")) {
			try {
				
				game = new Game(playerName, playerCity);
				user = game.getPlayer();
				myLabel.setVisible(false);
				myLabel2.setVisible(true);
				playerInfo.setVisible(true);
				add(playerInfo);
				add(myLabel2);
				add(gameInformation);
				player.setText(playerName);
				turncountP.setText("" + game.getCurrentTurnCount());
				goldP.setText("" + user.getTreasury());

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource().equals(spartaInMap)) {

			if (spartaDetails.isVisible()) {
				spartaDetails.setVisible(false);
			} else {
				spartaDetails.setVisible(true);
				comboBox.setVisible(false);
				spartaDetails.setVisible(false);
				myLabel2.add(attack);
				myLabel2.add(autoResolve);
				myLabel2.add(besieging);
				targetPanel.setVisible(false);
				ArmiesinCitiesP.setVisible(false);
				cairoDetails.setVisible(false);
				romeDetails.setVisible(false);
				archeryRangeDetails.setVisible(false);
				stableDetails.setVisible(false);
				barracksDetails.setVisible(false);
				farmDetails.setVisible(false);
				marketDetails.setVisible(false);
				attack.setVisible(false);
				autoResolve.setVisible(false);
				besieging.setVisible(false);

				cityPress = "Sparta";
				flag = false;
				boolean flag2 = false;
				for (City c : user.getControlledCities()) {

					if (!c.getName().equals("Sparta")) {
						flag2 = false;

					}

					else {

						flag2 = true;

						targetCityButton.setVisible(false);
						if (spartaDetails.isVisible()) {
							spartaDetails.setVisible(false);

						} else {

							spartaDetails.setVisible(true);

							for (MilitaryBuilding m : c.getMilitaryBuildings()) {
								if (m instanceof ArcheryRange) {
									spartaDetails.add(archeryRange);

								} else if (m instanceof Barracks) {
									spartaDetails.add(barracks);
								} else {
									spartaDetails.add(stable);
								}

							}
							for (EconomicBuilding eco : c.getEconomicalBuildings()) {
								if (eco instanceof Farm) {
									spartaDetails.add(farm);

								} else {
									spartaDetails.add(market);
								}

							}

						}
						spartaDetails.add(showDefendingArmy);
						spartaDetails.add(ArmiesinCites);
						spartaDetails.add(buildButton);
						spartaDetails.add(initiateArmy);
						spartaDetails.add(relocate);
						break;
					}

				}
				if (!flag2) {
					for (City s : game.getAvailableCities()) {
						if (s.getName().equals("Sparta")) {

							for (Army a : user.getControlledArmies()) {
								if (a.getCurrentLocation().equals("Sparta")) {
									if (a.getCurrentStatus().equals(Status.BESIEGING)) {

										if (attack.isVisible()) {
											attack.setVisible(false);
											autoResolve.setVisible(false);

										} else {

											attack.setVisible(true);
											autoResolve.setVisible(true);
										}
										flag = true;
										break;

									} else {
										if (attack.isVisible()) {
											attack.setVisible(false);
											autoResolve.setVisible(false);
										} else {

											attack.setVisible(true);
											autoResolve.setVisible(true);
											besieging.setVisible(true);
										}
										flag = true;
										break;
									}

								} else if (a.getTarget().equals("Sparta")) {
									flag = true;
									JOptionPane.showMessageDialog(null,
											"Distance to target: " + a.getDistancetoTarget());
									break;
								}
							}
							if (!flag) {
								if (targetCityButton.isVisible()) {
									targetCityButton.setVisible(false);

								} else {

									targetCityButton.setVisible(true);
								}
								// TODO Button with transparent background to call TargetCity method

							}

//						JOptionPane.showMessageDialog(null, "You dont control this city");

						}
					}

				}
			}
		}

		if (e.getSource().equals(cairoInMap)) {

			if (cairoDetails.isVisible()) {
				cairoDetails.setVisible(false);
			} else {
				cairoDetails.setVisible(true);
				comboBox.setVisible(false);
				myLabel2.add(attack);
				myLabel2.add(autoResolve);
				myLabel2.add(besieging);
				targetPanel.setVisible(false);
				ArmiesinCitiesP.setVisible(false);
				cairoDetails.setVisible(false);
				romeDetails.setVisible(false);
				archeryRangeDetails.setVisible(false);
				stableDetails.setVisible(false);
				barracksDetails.setVisible(false);
				farmDetails.setVisible(false);
				marketDetails.setVisible(false);
				attack.setVisible(false);
				autoResolve.setVisible(false);
				besieging.setVisible(false);

				cityPress = "Cairo";
				flag = false;
				boolean flag2 = false;
				for (City c : user.getControlledCities()) {

					if (!c.getName().equals("Cairo")) {
						flag2 = false;
					}

					else {

						flag2 = true;

						targetCityButton.setVisible(false);
						if (cairoDetails.isVisible()) {
							cairoDetails.setVisible(false);

						} else {

							cairoDetails.setVisible(true);

							for (MilitaryBuilding m : c.getMilitaryBuildings()) {
								if (m instanceof ArcheryRange) {
									cairoDetails.add(archeryRange);

								} else if (m instanceof Barracks) {
									cairoDetails.add(barracks);
								} else {
									cairoDetails.add(stable);
								}

							}
							for (EconomicBuilding eco : c.getEconomicalBuildings()) {
								if (eco instanceof Farm) {
									cairoDetails.add(farm);

								} else {
									cairoDetails.add(market);
								}

							}

						}
						cairoDetails.add(showDefendingArmy);
						cairoDetails.add(ArmiesinCites);
						cairoDetails.add(initiateArmy);
						cairoDetails.add(relocate);
						cairoDetails.add(buildButton);
						break;
					}

				}
				if (!flag2) {
					for (City s : game.getAvailableCities()) {
						if (s.getName().equals("Cairo")) {

							for (Army a : user.getControlledArmies()) {
								if (a.getCurrentLocation().equals("Cairo")) {
									if (a.getCurrentStatus().equals(Status.BESIEGING)) {

										if (attack.isVisible()) {
											attack.setVisible(false);
											autoResolve.setVisible(false);

										} else {

											attack.setVisible(true);
											autoResolve.setVisible(true);
										}
										flag = true;
										break;

									} else {
										if (attack.isVisible()) {
											attack.setVisible(false);
											autoResolve.setVisible(false);
										} else {

											attack.setVisible(true);
											autoResolve.setVisible(true);
											besieging.setVisible(true);
										}
										flag = true;
										break;
									}

								} else if (a.getTarget().equals("Cairo")) {
									flag = true;
									JOptionPane.showMessageDialog(null,
											"Distance to target: " + a.getDistancetoTarget());
									break;
								}
							}
							if (!flag) {
								if (targetCityButton.isVisible()) {
									targetCityButton.setVisible(false);

								} else {

									gameInformation.add(targetCityButton);
									targetCityButton.setVisible(true);
								}
								// TODO Button with transparent background to call TargetCity method

							}

//						JOptionPane.showMessageDialog(null, "You dont control this city");

						}
					}

				}
			}
		}

		if (e.getSource().equals(romeInMap)) {

			if (romeDetails.isVisible()) {
				romeDetails.setVisible(false);
			} else {
				romeDetails.setVisible(true);

				comboBox.setVisible(false);
				myLabel2.add(attack);
				myLabel2.add(autoResolve);
				myLabel2.add(besieging);
				targetPanel.setVisible(false);
				ArmiesinCitiesP.setVisible(false);
				cairoDetails.setVisible(false);
				romeDetails.setVisible(false);
				archeryRangeDetails.setVisible(false);
				stableDetails.setVisible(false);
				barracksDetails.setVisible(false);
				farmDetails.setVisible(false);
				marketDetails.setVisible(false);
				attack.setVisible(false);
				autoResolve.setVisible(false);
				besieging.setVisible(false);

				cityPress = "Rome";
				flag = false;
				boolean flag2 = false;
				for (City c : user.getControlledCities()) {

					if (!c.getName().equals("Rome")) {
						flag2 = false;
					}

					else {

						flag2 = true;

						targetCityButton.setVisible(false);
						if (romeDetails.isVisible()) {
							romeDetails.setVisible(false);

						} else {

							romeDetails.setVisible(true);

							for (MilitaryBuilding m : c.getMilitaryBuildings()) {
								if (m instanceof ArcheryRange) {
									romeDetails.add(archeryRange);

								} else if (m instanceof Barracks) {
									romeDetails.add(barracks);
								} else {
									romeDetails.add(stable);
								}

							}
							for (EconomicBuilding eco : c.getEconomicalBuildings()) {
								if (eco instanceof Farm) {
									romeDetails.add(farm);

								} else {
									romeDetails.add(market);
								}

							}

						}
						romeDetails.add(showDefendingArmy);
						romeDetails.add(ArmiesinCites);
						romeDetails.add(buildButton);
						romeDetails.add(initiateArmy);
						romeDetails.add(relocate);
						break;
					}

				}
				if (!flag2) {
					for (City s : game.getAvailableCities()) {
						if (s.getName().equals("Rome")) {

							for (Army a : user.getControlledArmies()) {
								if (a.getCurrentLocation().equals("Rome")) {
									if (a.getCurrentStatus().equals(Status.BESIEGING)) {

										if (attack.isVisible()) {
											attack.setVisible(false);
											autoResolve.setVisible(false);

										} else {

											attack.setVisible(true);
											autoResolve.setVisible(true);
										}
										flag = true;
										break;

									} else {
										if (attack.isVisible()) {
											attack.setVisible(false);
											autoResolve.setVisible(false);
										} else {

											attack.setVisible(true);
											autoResolve.setVisible(true);
											besieging.setVisible(true);
										}
										flag = true;
										break;
									}

								} else if (a.getTarget().equals("Rome")) {
									flag = true;
									JOptionPane.showMessageDialog(null,
											"Distance to target: " + a.getDistancetoTarget());
									break;
								}
							}
							if (!flag) {
								if (targetCityButton.isVisible()) {
									targetCityButton.setVisible(false);

								} else {

									gameInformation.add(targetCityButton);
									targetCityButton.setVisible(true);
								}
								// TODO Button with transparent background to call TargetCity method

							}

//						JOptionPane.showMessageDialog(null, "You dont control this city");

						}
					}

				}
			}
		}

		if (e.getSource().equals(startGameButton) && playerCity.equals("") && playerName.equals("")) {

			JOptionPane.showMessageDialog(null, "Please Choose a City and enter your name");
		}

		if (e.getSource().equals(startGameButton) && playerCity.equals("") && !playerName.equals("")) {
			JOptionPane.showMessageDialog(null, "Please Choose a City");
		}
		if (e.getSource().equals(startGameButton) && !playerCity.equals("") && playerName.equals("")) {

			JOptionPane.showMessageDialog(null, "Please enter your name");

		}

		if (e.getActionCommand().equals("ArcheryRange")) {
			targetCityButton.setVisible(false);
			targetPanel.setVisible(false);
			defendingArmyPanel.setVisible(false);
			barracksDetails.setVisible(false);
			stableDetails.setVisible(false);
			farmDetails.setVisible(false);
			marketDetails.setVisible(false);
			comboBox.setVisible(false);
			ArmiesinCitiesP.setVisible(false);

			if (archeryRangeDetails.isVisible()) {
				archeryRangeDetails.setVisible(false);
				archeryRangeDetails.removeAll();
			} else {
				archeryRangeDetails.setLayout(new FlowLayout());

				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						for (MilitaryBuilding m : c.getMilitaryBuildings()) {
							if (m instanceof ArcheryRange) {
								textArea.setText("Building type: ArcheryRange \n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n" + "Recruitment Cost: "
										+ m.getRecruitmentCost() + "\n\n");

								archeryRangeDetails.add(textArea);
								archeryRangeDetails.add(Upgrade);
								archeryRangeDetails.add(recruit);

							}

						}
					}

				}
				archeryRangeDetails.setVisible(true);

			}
			buildingPress = "ArcheryRange";
		}

		if (e.getActionCommand().equals("stable")) {

			targetCityButton.setVisible(false);
			targetPanel.setVisible(false);
			defendingArmyPanel.setVisible(false);
			barracksDetails.setVisible(false);
			archeryRangeDetails.setVisible(false);
			farmDetails.setVisible(false);
			marketDetails.setVisible(false);
			comboBox.setVisible(false);
			ArmiesinCitiesP.setVisible(false);

			if (stableDetails.isVisible()) {
				stableDetails.setVisible(false);
				stableDetails.removeAll();
			} else {
				stableDetails.setLayout(new FlowLayout());

				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						for (MilitaryBuilding m : c.getMilitaryBuildings()) {
							if (m instanceof Stable) {
								textArea.setText("Building type: Stable " + "\n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n" + "Recruitment Cost: "
										+ m.getRecruitmentCost() + "\n\n");

								stableDetails.add(textArea);
								stableDetails.add(Upgrade);
								stableDetails.add(recruit);

							}

						}
					}

				}
				stableDetails.setVisible(true);

			}
			buildingPress = "Stable";
		}

		if (e.getActionCommand().equals("barracks")) {
			targetCityButton.setVisible(false);
			targetPanel.setVisible(false);
			defendingArmyPanel.setVisible(false);
			archeryRangeDetails.setVisible(false);
			stableDetails.setVisible(false);
			farmDetails.setVisible(false);
			marketDetails.setVisible(false);
			ArmiesinCitiesP.setVisible(false);
			comboBox.setVisible(false);
			if (barracksDetails.isVisible()) {
				barracksDetails.setVisible(false);
				barracksDetails.removeAll();
			} else {
				barracksDetails.setLayout(new FlowLayout());

				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						for (MilitaryBuilding m : c.getMilitaryBuildings()) {
							if (m instanceof Barracks) {
								textArea.setText("Building type: Barracks " + "\n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n" + "Recruitment Cost: "
										+ m.getRecruitmentCost() + "\n\n");

								barracksDetails.add(textArea);
								barracksDetails.add(Upgrade);
								barracksDetails.add(recruit);

							}

						}
					}

				}
				barracksDetails.setVisible(true);

			}
			buildingPress = "Barracks";
		}

		if (e.getActionCommand().equals("ArmiesinCities")) {

			targetCityButton.setVisible(false);
			targetPanel.setVisible(false);
			armiesInCitesArea.setText("");
			ArmiesinCitiesP.removeAll();
			defendingArmyPanel.setVisible(false);
			barracksDetails.setVisible(false);
			archeryRangeDetails.setVisible(false);
			stableDetails.setVisible(false);
			farmDetails.setVisible(false);
			marketDetails.setVisible(false);
			comboBox.setVisible(false);
			if (ArmiesinCitiesP.isVisible()) {
				ArmiesinCitiesP.setVisible(false);

			}

			else {

				String x = "";
				for (Army c : user.getControlledArmies()) {
					if (c.getCurrentLocation().equals(cityPress)) {
						armiesInCitesArea.setText(armiesInCitesArea.getText() + temp);
						for (Unit a : c.getUnits()) {

							if (a instanceof Archer) {
								x = "Archer" + "  " + a.getLevel() + "  " + a.getCurrentSoldierCount() + "  "
										+ a.getMaxSoldierCount();
							} else if (a instanceof Infantry) {
								x = "Infantry" + "  " + a.getLevel() + "  " + a.getCurrentSoldierCount() + "  "
										+ a.getMaxSoldierCount();

							} else {
								x = "Cavalry" + "  " + a.getLevel() + "  " + a.getCurrentSoldierCount() + "  "
										+ a.getMaxSoldierCount();
							}
							armiesInCitesArea.setText(armiesInCitesArea.getText() + "\n" + x);

						}
						armiesInCitesArea.setText(armiesInCitesArea.getText() + "\n" + "\n");

					}
				}
				ArmiesinCitiesP.add(armiesInCitesArea);
				ArmiesinCitiesP.setVisible(true);

			}

		}
		if (e.getActionCommand().equals("farm")) {
			targetCityButton.setVisible(false);

			targetPanel.setVisible(false);
			defendingArmyPanel.setVisible(false);
			barracksDetails.setVisible(false);
			stableDetails.setVisible(false);
			archeryRangeDetails.setVisible(false);
			marketDetails.setVisible(false);
			comboBox.setVisible(false);
			ArmiesinCitiesP.setVisible(false);

			if (farmDetails.isVisible()) {
				farmDetails.setVisible(false);
				farmDetails.removeAll();
			} else {
				farmDetails.setLayout(new FlowLayout());

				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						for (EconomicBuilding m : c.getEconomicalBuildings()) {
							if (m instanceof Farm) {
								textArea.setText("Building type: farm " + "\n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n");

								farmDetails.add(textArea);
								farmDetails.add(Upgrade);
							}

						}
					}

				}
				farmDetails.setVisible(true);

			}
			buildingPress = "Farm";
		}
		if (e.getActionCommand().equals("market")) {
			targetCityButton.setVisible(false);
			targetPanel.setVisible(false);
			defendingArmyPanel.setVisible(false);
			barracksDetails.setVisible(false);
			stableDetails.setVisible(false);
			archeryRangeDetails.setVisible(false);
			farmDetails.setVisible(false);
			comboBox.setVisible(false);
			ArmiesinCitiesP.setVisible(false);
			if (marketDetails.isVisible()) {
				marketDetails.setVisible(false);
				marketDetails.removeAll();
			} else {
				marketDetails.setLayout(new FlowLayout());

				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						for (EconomicBuilding m : c.getEconomicalBuildings()) {
							if (m instanceof Market) {
								textArea.setText("Building type: Market " + "\n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n");

								marketDetails.add(textArea);
								marketDetails.add(Upgrade);

							}

						}
					}

				}
				marketDetails.setVisible(true);

			}
			buildingPress = "Market";
		}

		if (e.getActionCommand().equals("Upgrade")) {
			for (City c : user.getControlledCities()) {
				if (c.getName().equals(cityPress)) {
					for (MilitaryBuilding m : c.getMilitaryBuildings()) {
						if (m instanceof ArcheryRange && buildingPress.equals("ArcheryRange")) {
							try {
								user.upgradeBuilding(m);
								goldP.setText("" + user.getTreasury());
								textArea.setText("Building type: ArcheryRange \n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n" + "Recruitment Cost: "
										+ m.getRecruitmentCost() + "\n\n");
//								archeryRangeDetails.removeAll();
								archeryRangeDetails.add(textArea);
								archeryRangeDetails.add(Upgrade);
								archeryRangeDetails.add(recruit);

							} catch (NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "not enough gold");
							} catch (BuildingInCoolDownException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Building in cooldown");
							} catch (MaxLevelException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Max Level");
							}
						} else if (m instanceof Barracks && buildingPress.equals("Barracks")) {
							try {
								user.upgradeBuilding(m);
								goldP.setText("" + user.getTreasury());
								textArea.setText("Building type: Barracks " + "\n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n" + "Recruitment Cost: "
										+ m.getRecruitmentCost() + "\n\n");
								barracksDetails.removeAll();
								barracksDetails.add(textArea);
								barracksDetails.add(Upgrade);
								barracksDetails.add(recruit);

							} catch (NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "not enough gold");
							} catch (BuildingInCoolDownException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Building in cooldown");
							} catch (MaxLevelException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Max Level");
							}

						} else if (m instanceof Stable && buildingPress.equals("Stable")) {
							try {
								user.upgradeBuilding(m);
								goldP.setText("" + user.getTreasury());
								textArea.setText("Building type: Stable " + "\n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n" + "Recruitment Cost: "
										+ m.getRecruitmentCost() + "\n\n");
								stableDetails.removeAll();
								stableDetails.add(textArea);
								stableDetails.add(Upgrade);
								stableDetails.add(recruit);

							} catch (NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "not enough gold");
							} catch (BuildingInCoolDownException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Building in cooldown");
							} catch (MaxLevelException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Max Level");
							}
						}

					}

					for (EconomicBuilding m : c.getEconomicalBuildings()) {
						if (m instanceof Farm && buildingPress.equals("Farm")) {
							try {
								user.upgradeBuilding(m);
								goldP.setText("" + user.getTreasury());
								textArea.setText("Building type: farm " + "\n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n");
								farmDetails.removeAll();
								farmDetails.add(textArea);
								farmDetails.add(Upgrade);

							} catch (NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "not enough gold");
							} catch (BuildingInCoolDownException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Building in cooldown");
							} catch (MaxLevelException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Max Level");
							}
						} else if (m instanceof Market && buildingPress.equals("Market")) {
							try {
								user.upgradeBuilding(m);
								goldP.setText("" + user.getTreasury());
								textArea.setText("Building type: Market \n\n" + "Building Level: " + m.getLevel()
										+ "\n\n" + "Upgrade cost: " + m.getUpgradeCost() + "\n\n");
								marketDetails.removeAll();
								marketDetails.add(textArea);
								marketDetails.add(Upgrade);

							} catch (NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "not enough gold");
							} catch (BuildingInCoolDownException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Building in cooldown");
							} catch (MaxLevelException e1) {
								// TODO Auto-generated catch block

								JOptionPane.showMessageDialog(null, "Max Level");
							}
						}
					}
				}
			}
		}

		if (e.getActionCommand().equals("build")) {
			targetCityButton.setVisible(false);
			targetPanel.setVisible(false);
			barracksDetails.setVisible(false);
			stableDetails.setVisible(false);
			farmDetails.setVisible(false);
			marketDetails.setVisible(false);
			comboBox.setVisible(false);
			ArmiesinCitiesP.setVisible(false);
			archeryRangeDetails.setVisible(false);
			defendingArmyPanel.setVisible(false);
			if (comboBox.isVisible()) {
				comboBox.setVisible(false);
			} else {
				comboBox.setVisible(true);

			}

		}

		if (e.getSource().equals(comboBox)) {
			if (comboBox.getSelectedIndex() == 1) {
				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						try {

							user.build("ArcheryRange", cityPress);
							if (cityPress.equals("Cairo")) {
								cairoDetails.add(archeryRange);

							} else if (cityPress.equals("Rome")) {
								romeDetails.add(archeryRange);
							} else {
								spartaDetails.add(archeryRange);
							}
							goldP.setText("" + user.getTreasury());

							break;

						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Not Enough gold");
						}

					}
				}
			} else if (comboBox.getSelectedIndex() == 2) {
				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						try {
							user.build("Barracks", cityPress);
							if (cityPress.equals("Cairo")) {
								cairoDetails.add(barracks);

							} else if (cityPress.equals("Rome")) {
								romeDetails.add(barracks);
							} else {
								spartaDetails.add(barracks);
							}
							goldP.setText("" + user.getTreasury());
							break;

						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Not Enough gold");
						}

					}
				}
			} else if (comboBox.getSelectedIndex() == 3) {
				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						try {
							user.build("Stable", cityPress);
							if (cityPress.equals("Cairo")) {
								cairoDetails.add(stable);

							} else if (cityPress.equals("Rome")) {
								romeDetails.add(stable);
							} else {
								spartaDetails.add(stable);
							}
							goldP.setText("" + user.getTreasury());
							break;

						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Not Enough gold");
						}

					}
				}
			} else if (comboBox.getSelectedIndex() == 4) {
				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						try {
							user.build("Farm", cityPress);
							if (cityPress.equals("Cairo")) {
								cairoDetails.add(farm);

							} else if (cityPress.equals("Rome")) {
								romeDetails.add(farm);
							} else {
								spartaDetails.add(farm);
							}
							goldP.setText("" + user.getTreasury());
							break;

						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Not Enough gold");
						}

					}
				}
			} else if (comboBox.getSelectedIndex() == 5) {

				for (City c : user.getControlledCities()) {
					if (c.getName().equals(cityPress)) {
						try {
							user.build("Market", cityPress);

							if (cityPress.equals("Cairo")) {
								cairoDetails.add(market);

							} else if (cityPress.equals("Rome")) {
								romeDetails.add(market);
							} else {
								spartaDetails.add(market);
							}
							goldP.setText("" + user.getTreasury());
							break;

						} catch (NotEnoughGoldException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Not Enough gold");
						}

					}
				}
			}

		}

		if (e.getActionCommand().equals("Recruit"))

		{
			for (City c : user.getControlledCities()) {
				if (c.getName().equals(cityPress)) {
					for (MilitaryBuilding m : c.getMilitaryBuildings()) {
						if (m instanceof ArcheryRange && buildingPress.equals("ArcheryRange")) {
							try {
								user.recruitUnit("Archer", c.getName());
								goldP.setText("" + user.getTreasury());
							} catch (NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Not enough gold");
							} catch (BuildingInCoolDownException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Building in cooldown");

							} catch (MaxRecruitedException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Max recruited ");

							}
						} else if (m instanceof Barracks && buildingPress.equals("Barracks")) {
							try {
								user.recruitUnit("Infantry", c.getName());
								goldP.setText("" + user.getTreasury());
							} catch (NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Not enough gold");
							} catch (BuildingInCoolDownException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Building in cooldown");

							} catch (MaxRecruitedException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Max recruited ");

							}
						} else if (m instanceof Stable && buildingPress.equals("Stable")) {
							try {
								user.recruitUnit("Cavalry", c.getName());
								goldP.setText("" + user.getTreasury());
							} catch (NotEnoughGoldException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Not enough gold");
							} catch (BuildingInCoolDownException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Building in cooldown");

							} catch (MaxRecruitedException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Max recruited ");
								;
							}
						}

					}

				}
			}
		}
		if (e.getActionCommand().equals("Defending Army")) {

			targetCityButton.setVisible(false);
			targetPanel.setVisible(false);
			defendingArmyArea.setText("");
			barracksDetails.setVisible(false);
			stableDetails.setVisible(false);
			farmDetails.setVisible(false);
			marketDetails.setVisible(false);
			comboBox.setVisible(false);
			ArmiesinCitiesP.setVisible(false);
			archeryRangeDetails.setVisible(false);
			defendingArmyPanel.removeAll();
			if (defendingArmyPanel.isVisible()) {
				defendingArmyPanel.setVisible(false);
			} else {

				String x = "";

				for (City c : user.getControlledCities()) {
					defendingArmyArea.setText(defendingArmyArea.getText() + temp);
					if (c.getName().equals(playerCity)) {
						for (Unit a : c.getDefendingArmy().getUnits()) {
							if (a instanceof Archer) {
								x = "Archer" + "       " + a.getCurrentSoldierCount() + "              " + a.getLevel()
										+ "             " + a.getMaxSoldierCount();

							} else if (a instanceof Infantry) {
								x = "Infantry" + "       " + a.getCurrentSoldierCount() + "              "
										+ a.getLevel() + "             " + a.getMaxSoldierCount();

							} else {
								x = "Cavalry" + "       " + a.getCurrentSoldierCount() + "              " + a.getLevel()
										+ "             " + a.getMaxSoldierCount();

							}
							defendingArmyArea.setText(defendingArmyArea.getText() + "\n" + x);
						}

					}

				}
				defendingArmyPanel.add(defendingArmyArea);
				defendingArmyPanel.setVisible(true);
			}

		}
		if (e.getActionCommand().equals("IDLE")) {
			idleUnits();

			// TESTING

//
//			Army khra = new Army("rome");
//			khra.setCurrentStatus(Status.IDLE);
//			int i = 0;
//			Player pp = new Player("Roby");
//			while (i < 20) {
//				Archer x = new Archer(1, 60, 20, 40, 20);
//				Infantry y = new Infantry(1, 2, 3, 4, 5);
//				Cavalry z = new Cavalry(33, 44, 55, 22, 11);
//				x.setParentArmy(khra);
//				y.setParentArmy(khra);
//				z.setParentArmy(khra);
//				khra.getUnits().add(x);
//				khra.getUnits().add(y);
//				khra.getUnits().add(z);
//				i++;
//			}
//			pp.getControlledArmies().add(khra);

//
//			idlePanel.setVisible(true);
//			idlePanel.add(text);
//		}
		}
		if (e.getActionCommand().equals("BESIGING")) {
			besigingUnits();

		}
		if (e.getActionCommand().equals("MARCHING")) {

			marchingUnits();
		}

		if (e.getActionCommand().equals("end turn")) {
			targetCityButton.setVisible(false);
			ArmiesinCitiesP.setVisible(false);
			romeDetails.setVisible(false);
			cairoDetails.setVisible(false);
			spartaDetails.setVisible(false);
			archeryRangeDetails.setVisible(false);
			stableDetails.setVisible(false);
			barracksDetails.setVisible(false);
			farmDetails.setVisible(false);
			marketDetails.setVisible(false);
			targetCityButton.setVisible(false);
			attack.setVisible(false);
			besieging.setVisible(false);
			autoResolve.setVisible(false);
			if (game.isGameOver()) {
				if (user.getControlledCities().size() == game.getAvailableCities().size()) {
					dispose();
					JOptionPane.showMessageDialog(null, "YOU HAVE WON");
					myLabel2.setVisible(false);
					gameInformation.setVisible(false);
				} else {
					dispose();
					myLabel2.setVisible(false);
					gameInformation.setVisible(false);
					JOptionPane.showMessageDialog(null, "HARD LUCK");
				}

			} else {
				game.endTurn();
				turncountP.setText("" + game.getCurrentTurnCount());
				foodP.setText("" + user.getFood());
				goldP.setText("" + user.getTreasury());
				attack.setPreferredSize(new Dimension(200, 90));
				autoResolve.setPreferredSize(new Dimension(200, 90));
				besieging.setPreferredSize(new Dimension(200, 90));
				for (Army d : user.getControlledArmies()) {
					if (d.getDistancetoTarget() == 0) {
						// TODO CHANGE SIZES
						attack.setVisible(true);

						autoResolve.setVisible(true);
						besieging.setVisible(true);
						armyTarget.setText("Please choose action to do for city: " + d.getCurrentLocation());
						endturn.setVisible(false);
						promptPanel.add(armyTarget);
						promptPanel.add(attack);
						promptPanel.add(autoResolve);
						promptPanel.add(besieging);
						promptPanel.setVisible(true);

					}
				}
				for (City f : game.getAvailableCities()) {
					if (f.getTurnsUnderSiege() == 3) {
						cityPress = f.getName();
						armyTarget.setText("Please choose action to do for city: " + f.getName());
						attack.setVisible(true);
						autoResolve.setVisible(true);
						endturn.setVisible(false);
						promptPanel.add(attack);
						promptPanel.add(autoResolve);
						promptPanel.setVisible(true);
					}
				}

			}
		}
		if (e.getSource().equals(autoResolve)) {
			endturn.setVisible(true);
			promptPanel.setVisible(false);
			for (City a : game.getAvailableCities()) {
				if (a.getName().equals(cityPress)) {
					for (Army d : user.getControlledArmies()) {
						if (d.getCurrentLocation().equals(cityPress)) {

							try {
								a.setUnderSiege(false);
								a.setTurnsUnderSiege(-1);
								game.autoResolve(d, a.getDefendingArmy());
								boolean flag = false;
								for (City city : user.getControlledCities()) {
									if (city.getName().equals(a.getName())) {
										flag = true;
										break;

									} else {
										flag = false;
									}
								}
								if (flag) {
									JOptionPane.showMessageDialog(null, "YOU HAVE WON THE BATTLE");

								} else {
									JOptionPane.showMessageDialog(null, "YOU HAVE LOST THE BATTLE");

								}

								break;
							} catch (FriendlyFireException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "FriendlyFireEXception");
							}

						}
					}

					break;
				}

			}

		}
		if (e.getSource().equals(besieging)) {
			endturn.setVisible(true);
			promptPanel.setVisible(false);

			for (City l : game.getAvailableCities()) {
				if (l.getName().equals(cityPress)) {
					for (Army h : user.getControlledArmies()) {
						if (h.getCurrentLocation().equals(cityPress)) {
							try {
								user.laySiege(h, l);
							} catch (TargetNotReachedException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "TARGET NOT REACHED");

							} catch (FriendlyCityException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "FRIENDLY FIRE");

							}
							break;
						}
					}
					break;
				}
			}
		}
		if (e.getActionCommand().equals("attack")) {
			endturn.setVisible(true);
			promptPanel.setVisible(false);
			attacking();

		}
		if (e.getSource().equals(fightButton)) {
			news.setText("");
			String type = "";
			String type2 = "";
			news.setText(logNews + "\n");
			try {
				if (attackingUnit == null) {
					JOptionPane.showMessageDialog(null, "CHOOSE ATTACKING UNIT");

				} else if (defendingUnit == null) {
					JOptionPane.showMessageDialog(null, "CHOOSE DEFENDING UNIT");

				} else {
//					counter++;
//					JLabel ll = new JLabel("Round" + " " + counter + "\n" + "Defending Army");
//					logPanel.add(ll);

					if (attackingUnit instanceof Archer) {
						type = "Archer";

					} else if (attackingUnit instanceof Infantry)
						type = "Infantry";
					else
						type = "Cavalry";

					if (defendingUnit instanceof Archer)
						type2 = "Archer";

					else if (defendingUnit instanceof Infantry)
						type2 = "Infantry";
					else
						type2 = "Cavalry";

					logPanel.add(news);
					int attackercount = attackingUnit.getCurrentSoldierCount();
					int defendercount = defendingUnit.getCurrentSoldierCount();
					news.setText(news.getText() + "\n" + "Before Attack\n");
					news.setText(news.getText() + "Current Soldier Count is:   " + attackercount);
					news.setText(news.getText() + "\n");
					news.setText(news.getText() + "Enemy Current Soldier Count is:   " + defendercount);
					attackingUnit.attack(defendingUnit);
					// After attack
					attackercount = attackingUnit.getCurrentSoldierCount();
					defendercount = defendingUnit.getCurrentSoldierCount();
					news.setText(news.getText() + "\n" + "After Attack\n");
					news.setText(news.getText() + "Current Soldier Count Became:   " + attackercount);
					news.setText(news.getText() + "\n");
					news.setText(news.getText() + "Enemy Current Soldier Count Became:   " + defendercount);

					logPanel.add(news);

					attacking();
					if (attacker.getUnits().size() == 0) {
						user.getControlledArmies().remove(attacker);
						JOptionPane.showMessageDialog(null, "YOU HAVE LOST");

					} else if (defend.getDefendingArmy().getUnits().size() == 0) {
						JOptionPane.showMessageDialog(null, "YOU HAVE WON");

						game.occupy(attacker, cityPress);

					} else {

						attackingUnit = attacker.getUnits().get((int) (Math.random() * attacker.getUnits().size()));
						defendingUnit = defend.getDefendingArmy().getUnits()
								.get((int) (Math.random() * defend.getDefendingArmy().getUnits().size()));
						defendingUnit.attack(attackingUnit);
						attackercount = attackingUnit.getCurrentSoldierCount();
						defendercount = defendingUnit.getCurrentSoldierCount();
						news.setText(news.getText() + "\n" + "Second Attack\n");
						news.setText(news.getText() + "Current Soldier Count Became:   " + attackercount);
						news.setText(news.getText() + "\n");
						news.setText(news.getText() + "Enemy Current Soldier Count Became:   " + defendercount);
						logPanel.add(news);
						attackingUnit = null;
						defendingUnit = null;
						attacking();
					}
					if (attacker.getUnits().size() == 0) {
						user.getControlledArmies().remove(attacker);
						JOptionPane.showMessageDialog(null, "YOU HAVE LOST");
					} else if (defend.getDefendingArmy().getUnits().size() == 0) {
						JOptionPane.showMessageDialog(null, "YOU HAVE WON");
						game.occupy(attacker, cityPress);

					}
				}

			} catch (

			FriendlyFireException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "FRIENDLY FIRE");
			}

		}

		if (e.getSource().equals(targetCityButton)) {
			comboBox.setVisible(false);
			targetCityButton.setVisible(false);

			x = 0;

			if (targetPanel.isVisible()) {

				armyButtons.clear();
				targetPanel.removeAll();
				targetPanel.setVisible(false);

			} else {

				for (Army a : user.getControlledArmies()) {
					if (a.getCurrentStatus().equals(Status.IDLE)) {
						JButton z = new JButton("Army" + " " + (x + 1));
						armyButtons.add(z);
						targetPanel.add(armyButtons.get(x));
						z.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								y = armyButtons.indexOf(z);
								test = user.getControlledArmies().get(y);
								game.targetCity(test, cityPress);

							}
						});

						x++;
					}
				}

				targetPanel.setVisible(true);

			}
		}
		if (e.getSource().equals(initiateArmy)) {

			initiateFrame = new JFrame();
			initiateFrame.setSize(1000, 600);
			initiateFrame.setLayout(new FlowLayout());
			initiateFrame.setVisible(true);

			x = 0;

			for (City c : user.getControlledCities()) {
				if (c.getName().equals(cityPress)) {
					armyInitiator = c;
					break;

				}

			}

			for (Unit u : armyInitiator.getDefendingArmy().getUnits()) {
				String type = "";

				if (u instanceof Archer) {
					type = "Archer";
				} else if (u instanceof Infantry) {
					type = "Infantry";
				} else {
					type = "Cavalry";
				}
				JButton z = new JButton(type + " " + "Level:  " + u.getLevel());

				defenderArmy.add(z);
				initiateFrame.add(defenderArmy.get(x));
				z.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
//						initiateFrame.setVisible(false);
						initiateFrame.dispose();
						y = defenderArmy.indexOf(z);
						defenderArmy.remove(y);
						Unit newUnit = armyInitiator.getDefendingArmy().getUnits().get(y);
						user.initiateArmy(armyInitiator, newUnit);
						initiateFrame.revalidate();
						initiateFrame.repaint();

					}
				});

				x++;

			}

		}

		if (e.getSource().equals(relocate)) {
			defendingArmyButtonUnits.clear();
			userControlledArmiesButtons.clear();
			relocateArmies.clear();
			controlledArmiesPanel.removeAll();
			defendingArmyInCityPanel.removeAll();
			relocateFrame.setVisible(true);
			x = 0;
			q = 0;
			for (City c : user.getControlledCities()) {
				if (c.getName().equals(cityPress)) {
					JButton z = new JButton("Defender Army");
					userControlledArmiesButtons.add(z);
					z.setPreferredSize(new Dimension(200, 100));
					controlledArmiesPanel.add(userControlledArmiesButtons.get(x));
					z.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							y = userControlledArmiesButtons.indexOf(z);
							relocatingArmy = c.getDefendingArmy();

						}
					});
				}
			}

			x++;
			for (Army a : user.getControlledArmies()) {
				if (a.getCurrentLocation().equals(cityPress)) {
					JButton z = new JButton("Army" + " " + x);
					userControlledArmiesButtons.add(z);
					z.setPreferredSize(new Dimension(100, 100));
					controlledArmiesPanel.add(userControlledArmiesButtons.get(x));
					z.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							y = userControlledArmiesButtons.indexOf(z);
							relocatingArmy = user.getControlledArmies().get(y - 1);

						}
					});

				}
				x++;

			}
			x = 0;

			for (City c : user.getControlledCities()) {
				if (c.getName().equals(cityPress)) {
					JButton z = new JButton("Defender Army");
					relocateArmies.add(z);
					z.setPreferredSize(new Dimension(200, 100));
					defendingArmyInCityPanel.add(relocateArmies.get(x));
					z.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							y = relocateArmies.indexOf(z);
							relocatingArmy = c.getDefendingArmy();
							defendingArmyInCityPanel.removeAll();
							relocateFrame.dispose();
							relocateFrame.setVisible(true);

							for (Unit u : c.getDefendingArmy().getUnits()) {
								String type = "";
								if (u instanceof Archer) {
									type = "Archer";
								} else if (u instanceof Infantry) {
									type = "Infantry";
								} else {
									type = "Cavalry";
								}
								JButton z = new JButton(type + " " + "Level:  " + u.getLevel());
								defendingArmyButtonUnits.add(z);
								defendingArmyInCityPanel.add(defendingArmyButtonUnits.get(q));
								z.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										r = defendingArmyButtonUnits.indexOf(z);
										relocatingUnit = c.getDefendingArmy().getUnits().get(r);

									}
								});
								q++;
							}

						}
					});
				}
			}

			x++;
			q = 0;
			for (Army a : user.getControlledArmies()) {
				if (a.getCurrentLocation().equals(cityPress)) {
					JButton z = new JButton("Army" + " " + x);
					relocateArmies.add(z);
					z.setPreferredSize(new Dimension(100, 100));
					defendingArmyInCityPanel.add(relocateArmies.get(x));
					z.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							y = relocateArmies.indexOf(z);
							relocatingArmy = user.getControlledArmies().get(y - 1);
							defendingArmyInCityPanel.removeAll();
							relocateFrame.dispose();
							relocateFrame.setVisible(true);
							for (Unit u : user.getControlledArmies().get(y - 1).getUnits()) {
								String type = "";
								if (u instanceof Archer) {
									type = "Archer";
								} else if (u instanceof Infantry) {
									type = "Infantry";
								} else {
									type = "Cavalry";
								}
								JButton z = new JButton(type + " " + "Level:  " + u.getLevel());
								defendingArmyButtonUnits.add(z);
								defendingArmyInCityPanel.add(defendingArmyButtonUnits.get(q));
								z.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										r = defendingArmyButtonUnits.indexOf(z);
										relocatingUnit = user.getControlledArmies().get(y - 1).getUnits().get(r);

									}
								});
								q++;
							}
						}
					});

				}
				x++;

			}

			relocateFrame.repaint();
			relocateFrame.revalidate();

		}
		if (e.getSource().equals(relocateUnitButton)) {

			if (relocatingArmy == (null)) {
				JOptionPane.showMessageDialog(null, "PLEASE CHOOSE ARMY");

			} else if (relocatingUnit == (null)) {
				JOptionPane.showMessageDialog(null, "PLEASE CHOOSE UNIT");
			} else {
				try {
					relocatingArmy.relocateUnit(relocatingUnit);
					relocateFrame.setVisible(false);
					defendingArmyButtonUnits.remove(r);
				} catch (MaxCapacityException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Max Capacity Exception");
				}

			}

		}

	}

	private void attacking() {
		defenderArmy.clear();
		attackerArmy.clear();
		defendingUnits.removeAll();
		cityUnits.removeAll();
		attackFrame.dispose();
		attackFrame.setVisible(true);
		attackFrame.setResizable(false);
		int b = 0;
		defend = null;
		attacker = null;

		for (City a : game.getAvailableCities()) {
			if (a.getName().equals(cityPress)) {
				a.setUnderSiege(false);
				a.setTurnsUnderSiege(-1);
				defend = a;
				break;
			}

		}
		String type = "";
		for (Unit x : defend.getDefendingArmy().getUnits()) {

			if (x instanceof Archer) {
				type = "Archer";
			} else if (x instanceof Cavalry) {
				type = "Cavalry";
			} else {
				type = "Infantry";
			}
			JButton z = new JButton(type + "   " + x.getCurrentSoldierCount() + "  " + x.getLevel());
			defenderArmy.add(z);
			defendingUnits.add(defenderArmy.get(b));
			z.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					y = defenderArmy.indexOf(z);
					defendingUnit = defend.getDefendingArmy().getUnits().get(y);
				}
			});
			b++;
		}
		b = 0;
		type = "";

		for (Army a : user.getControlledArmies()) {
			attacker = a;
			if (a.getCurrentLocation().equals(cityPress)) {
				for (Unit x : a.getUnits()) {
					if (x instanceof Archer) {
						type = "Archer";
					} else if (x instanceof Cavalry) {
						type = "Cavalry";
					} else {
						type = "Infantry";
					}
					JButton z = new JButton(type + "   " + x.getCurrentSoldierCount() + "  " + x.getLevel());
					attackerArmy.add(z);
					cityUnits.add(attackerArmy.get(b));

					z.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							y = attackerArmy.indexOf(z);
							attackingUnit = a.getUnits().get(y);
						}
					});
					b++;
				}
			}

		}
		attackFrame.revalidate();
		attackFrame.repaint();
	}

	private void marchingUnits() {
		marchingTextArea.setText("");
		String x;
		JFrame t = new JFrame();
		t.setBounds(100, 200, 700, 600);
		t.setTitle("MARCHING ARMY");
//			t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (Army a : user.getControlledArmies()) {
			if (a.getCurrentStatus().equals(Status.MARCHING)) {
				marchingTextArea.setText(marchingTextArea.getText() + temp3);
				for (Unit u : a.getUnits()) {
					if (u instanceof Archer) {
						x = "Archer" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount() + "    " + a.getTarget() + "    " + a.getDistancetoTarget();

					} else if (u instanceof Infantry) {
						x = "Infantry" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount() + "    " + a.getTarget() + "    " + a.getDistancetoTarget();
					} else {
						x = "Cavalry" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount() + "    " + a.getTarget() + "    " + a.getDistancetoTarget();
					}
					marchingTextArea.setText(marchingTextArea.getText() + "\n" + x);
				}
				marchingTextArea.setText(marchingTextArea.getText() + "\n" + "\n");

			}

		}
		t.add(marchingTextArea);
		t.setVisible(true);
		t.validate();
		t.repaint();
	}

	private void besigingUnits() {

		besigingTextArea.setText("");
		String x;
		JFrame t = new JFrame();
		t.setBounds(100, 200, 700, 600);
		t.setTitle("BESEGING ARMY");
//		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (Army a : user.getControlledArmies()) {
			if (a.getCurrentStatus().equals(Status.BESIEGING)) {
				besigingTextArea.setText(besigingTextArea.getText() + temp2);

				for (Unit u : a.getUnits()) {
					for (City c : game.getAvailableCities()) {
						if (c.getName().equals(a.getCurrentLocation())) {
							turnsUnderSiege = c.getTurnsUnderSiege();

						}

					}
					if (u instanceof Archer) {
						x = "Archer" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount() + "    " + a.getCurrentLocation() + "    " + turnsUnderSiege;

					} else if (u instanceof Infantry) {

						x = "Infantry" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount() + "    " + a.getCurrentLocation() + "    " + turnsUnderSiege;

					} else {

						x = "Cavalry" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount() + "    " + a.getCurrentLocation() + "     " + turnsUnderSiege;

					}
					besigingTextArea.setText(besigingTextArea.getText() + "\n" + x);
				}
				besigingTextArea.setText(besigingTextArea.getText() + "\n" + "\n");

			}

		}

		t.add(besigingTextArea);
		t.setVisible(true);
		t.validate();
		t.repaint();
	}

	

	private void idleUnits() {
		idleTextArea.setText("");
		String x;
		JFrame t = new JFrame();
		t.setBounds(100, 200, 700, 600);
		t.setTitle("IDLE ARMY");
//		t.setDefaultCloseOperation(EXIT_ON_CLOSE);

		for (Army a : user.getControlledArmies()) {
			if (a.getCurrentStatus().equals(Status.IDLE)) {
				idleTextArea.setText(idleTextArea.getText() + temp);
				for (Unit u : a.getUnits()) {
					if (u instanceof Archer) {

						x = "Archer" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount();

					} else if (u instanceof Infantry) {
						x = "Infantry" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount();
					} else {
						x = "Cavalry" + "    " + u.getCurrentSoldierCount() + "    " + u.getLevel() + "    "
								+ u.getMaxSoldierCount();
					}
					idleTextArea.setText(idleTextArea.getText() + "\n" + x);
				}
				idleTextArea.setText(idleTextArea.getText() + "\n" + "\n");

			}

		}

		t.add(idleTextArea);
		t.setVisible(true);
		t.validate();
		t.repaint();
	}
}
