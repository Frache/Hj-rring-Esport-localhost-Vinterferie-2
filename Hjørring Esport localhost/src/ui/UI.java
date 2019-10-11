package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import ui.TournamentPanel;
import ui.PlayerPanel;
import ui.TeamPanel;
import ui.StatisticsPanel;
import ui.GamePanel;
import ui.Round1Panel;
import ui.Round2Panel;
import ui.Round3Panel;
import ui.GroupStagePanel;
import ui.FinalsPanel;
import ui.ManageTournamentPanel;


import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import controller.MatchController;
import controller.PlayerController;
import controller.RoundController;
import controller.TeamController;
import controller.TournamentController;
import db.MisMatchException;
import db.NoConnectionException;
import db.WrongInputException;
import model.Match;
import model.Player;
import model.Round;
import model.Team;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DropMode;



public class UI extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5217455452737040244L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private TournamentPanel tournamentPanel;
	private PlayerPanel playerPanel;
	private TeamPanel teamPanel;
	private StatisticsPanel statisticsPanel;
	private GamePanel gamePanel;
	private JTabbedPane tabbedPane_1;
	private Round1Panel runde1Panel;
	private Round2Panel runde2Panel;
	private Round3Panel runde3Panel;
	private GroupStagePanel gruppeSpilPanel;
	private FinalsPanel finalerPanel;
	private ManageTournamentPanel administrerTurneringPanel;
	private JButton btnGenerererKamprunde2;
	private JButton btnGenerererKamprunde3;
	private JButton btnGenerererGruppespil;
	private JButton btnFindFinalister;
	private JButton btnGemResultat;
	private JLabel lblTurneringensNavn;
	private JLabel lblSpil;
	private JComboBox comboBox;
	private JTextField textField;
	private JButton btnOpretTurnering;
	private JButton btnSletTurnering;
	private JLabel lblTeam;
	private JLabel lblTeam_1;
	private JTextField textField_2;
	private JTextField textField_1;
	private JButton btnGenerererTurnering;
	
	private RoundController roundController;
	private MatchController matchController;
	private TeamController teamController;
	
	private JButton btnOpdaterRunde1;
	private ArrayList<JLabel> firstRoundLabels = new ArrayList<>();
	private ArrayList<JTextField> firstRoundTextFields = new ArrayList<>();
	private ArrayList<JLabel> secondRoundLabels = new ArrayList<>();
	private ArrayList<JTextField> secondRoundTextFields = new ArrayList<>();
	private ArrayList<JLabel> thirdRoundLabels = new ArrayList<>();
	private ArrayList<JTextField> thirdRoundTextFields = new ArrayList<>();
	private ArrayList<JLabel> groupStageLabels = new ArrayList<>();
	private ArrayList<JTextField> groupStageTextFields = new ArrayList<>();
	private ArrayList<JLabel> finalLabels = new ArrayList<>();
	private ArrayList<JTextField> finalTextFields = new ArrayList<>();
	private JLabel lblForbindelse;
	private JPanel panelFindTeams;
	private JPanel panelEditTeam;
	private JTextField textFieldSearchField;
	private JButton btnFindTeams;
	private JPanel panelFindTeamsTop;
	private JPanel panelFindTeamsBottom;
	private JButton btnDeleteTeam;
	private JScrollPane scrollPaneTeams;
	private JTable teamTable;
	private TeamTableModel teamTableModel;
	private JPanel panelEditTeamCenter;
	private JPanel panelEditTeamBottom;
	private JButton btnClear;
	private JButton btnUpdate;
	private JButton btnCreate;
	private JLabel lblHoldnavn;
	private JTextField textFieldHoldnavn;
	private JLabel lblTeamtag;
	private JTextField textFieldTeamTag;
	private JLabel labelHoldID;
	private JTextField textFieldHoldID;
	private JButton btnOpdaterRunde2;
	private JButton btnOpdaterRunde3;
	private JTextField textField_3;
	private JLabel lblTeam_2;
	private JLabel lblTeam_3;
	private JTextField textField_4;
	private JButton btnOpdaterGruppespil;
	private JPanel panelFindPlayer;
	private JPanel panelEditPlayer;
	private JPanel panelFindPlayerTop;
	private JScrollPane scrollPanePlayers;
	private JPanel panelFindPlayerBottom;
	private JTextField textFieldFindPlayer;
	private JButton btnFindPlayer;
	private JPanel panelEditPlayerCenter;
	private JLabel lblPlayerID;
	private JTextField textFieldPlayerID;
	private JLabel lblFname;
	private JTextField textFieldFname;
	private JLabel lblLname;
	private JTextField textFieldLname;
	private JLabel lblAge;
	private JTextField textFieldAge;
	private JLabel lblZipCode;
	private JTextField textFieldZipCode;
	private JLabel lblGamerTag;
	private JTextField textFieldGamerTag;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblHold;
	private JComboBox<String> comboBoxTeam;
	private JPanel panelEditPlayerBottom;
	private JButton btnClearPlayer;
	private JButton btnUpdatePlayer;
	private JButton btnCreatePlayer;
	private JTable playerTable;
	private PlayerTabelModel playerTabelModel;
	private PlayerController playerController;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					UI frame = new UI();
					frame.setVisible(true);
					ConnectionUI connectionUI = new ConnectionUI(frame);
					connectionUI.start();
					
				
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws MisMatchException 
	 */
	public UI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		lblForbindelse = new JLabel("Forbindelse");
		lblForbindelse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblForbindelse.setOpaque(true);
		lblForbindelse.setBackground(Color.GREEN);
		lblForbindelse.setForeground(Color.BLACK);
		lblForbindelse.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblForbindelse, BorderLayout.NORTH);
		try 
		{
			roundController = new RoundController();
			matchController = new MatchController();
			teamController = new TeamController();
			
		} 
		catch (NoConnectionException e) 
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.OK_OPTION);
			e.printStackTrace();
		}
		catch (WrongInputException e)
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.OK_OPTION);
			e.printStackTrace();
		}
		catch (MisMatchException e)
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.OK_OPTION);
			e.printStackTrace();
		}

		init();
	}
	
	private void init() 
	{
		//deletes all matches from the Database.
		matchController.deleteAllMatches();
				
		//Create the visual Design for the User Interface
		this.tournamentPanel = new TournamentPanel();
		
		tabbedPane.addTab("Turnering", tournamentPanel);
		GridBagLayout gbl_tournamentPanel = new GridBagLayout();
		gbl_tournamentPanel.columnWidths = new int[]{0, 0};
		gbl_tournamentPanel.rowHeights = new int[]{0, 0};
		gbl_tournamentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_tournamentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		tournamentPanel.setLayout(gbl_tournamentPanel);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 0;
		gbc_tabbedPane_1.gridy = 0;
		tournamentPanel.add(tabbedPane_1, gbc_tabbedPane_1);
		
		// Tabs i menu
		this.playerPanel = new PlayerPanel();
		tabbedPane.addTab("Spiller", playerPanel);
		playerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelFindPlayer = new JPanel();
		playerPanel.add(panelFindPlayer);
		panelFindPlayer.setLayout(new BorderLayout(0, 0));
		
		panelFindPlayerTop = new JPanel();
		panelFindPlayer.add(panelFindPlayerTop, BorderLayout.NORTH);
		panelFindPlayerTop.setLayout(new BorderLayout(0, 0));
		
		textFieldFindPlayer = new JTextField();
		panelFindPlayerTop.add(textFieldFindPlayer, BorderLayout.CENTER);
		textFieldFindPlayer.setColumns(10);
		
		btnFindPlayer = new JButton("Find");
		panelFindPlayerTop.add(btnFindPlayer, BorderLayout.EAST);
		
		scrollPanePlayers = new JScrollPane();
		panelFindPlayer.add(scrollPanePlayers, BorderLayout.CENTER);
		
		playerTable = new JTable();
		playerTabelModel = new PlayerTabelModel();
		playerTable.setModel(playerTabelModel);
		playerTable.getSelectionModel().addListSelectionListener((e) -> changedPlayerSelection());
		playerTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPanePlayers.setViewportView(playerTable);
		
		
		panelFindPlayerBottom = new JPanel();
		panelFindPlayer.add(panelFindPlayerBottom, BorderLayout.SOUTH);
		
		panelEditPlayer = new JPanel();
		playerPanel.add(panelEditPlayer);
		panelEditPlayer.setLayout(new BorderLayout(0, 0));
		
		panelEditPlayerCenter = new JPanel();
		panelEditPlayer.add(panelEditPlayerCenter, BorderLayout.NORTH);
		GridBagLayout gbl_panelEditPlayerCenter = new GridBagLayout();
		gbl_panelEditPlayerCenter.columnWidths = new int[]{0, 0, 0, 0, 400, 0};
		gbl_panelEditPlayerCenter.rowHeights = new int[]{100, 0, 50, 0, 50, 0, 50, 0, 50, 0, 50, 0, 50, 0, 50, 0, 0};
		gbl_panelEditPlayerCenter.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelEditPlayerCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelEditPlayerCenter.setLayout(gbl_panelEditPlayerCenter);
		
		lblPlayerID = new JLabel("Spiller ID:");
		lblPlayerID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPlayerID = new GridBagConstraints();
		gbc_lblPlayerID.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerID.gridx = 2;
		gbc_lblPlayerID.gridy = 1;
		panelEditPlayerCenter.add(lblPlayerID, gbc_lblPlayerID);
		
		textFieldPlayerID = new JTextField();
		textFieldPlayerID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPlayerID.setEditable(false);
		textFieldPlayerID.setColumns(10);
		GridBagConstraints gbc_textFieldPlayerID = new GridBagConstraints();
		gbc_textFieldPlayerID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPlayerID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPlayerID.gridx = 4;
		gbc_textFieldPlayerID.gridy = 1;
		panelEditPlayerCenter.add(textFieldPlayerID, gbc_textFieldPlayerID);
		
		lblFname = new JLabel("Fornavn:");
		lblFname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblFname = new GridBagConstraints();
		gbc_lblFname.insets = new Insets(0, 0, 5, 5);
		gbc_lblFname.gridx = 2;
		gbc_lblFname.gridy = 3;
		panelEditPlayerCenter.add(lblFname, gbc_lblFname);
		
		textFieldFname = new JTextField();
		textFieldFname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldFname.setColumns(10);
		GridBagConstraints gbc_textFieldFname = new GridBagConstraints();
		gbc_textFieldFname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFname.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFname.gridx = 4;
		gbc_textFieldFname.gridy = 3;
		panelEditPlayerCenter.add(textFieldFname, gbc_textFieldFname);
		
		lblLname = new JLabel("Efternavn:");
		lblLname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLname = new GridBagConstraints();
		gbc_lblLname.insets = new Insets(0, 0, 5, 5);
		gbc_lblLname.gridx = 2;
		gbc_lblLname.gridy = 5;
		panelEditPlayerCenter.add(lblLname, gbc_lblLname);
		
		textFieldLname = new JTextField();
		textFieldLname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldLname.setColumns(10);
		GridBagConstraints gbc_textFieldLname = new GridBagConstraints();
		gbc_textFieldLname.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldLname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLname.gridx = 4;
		gbc_textFieldLname.gridy = 5;
		panelEditPlayerCenter.add(textFieldLname, gbc_textFieldLname);
		
		lblAge = new JLabel("Alder:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 2;
		gbc_lblAge.gridy = 7;
		panelEditPlayerCenter.add(lblAge, gbc_lblAge);
		
		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldAge = new GridBagConstraints();
		gbc_textFieldAge.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAge.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAge.gridx = 4;
		gbc_textFieldAge.gridy = 7;
		panelEditPlayerCenter.add(textFieldAge, gbc_textFieldAge);
		textFieldAge.setColumns(10);
		
		lblZipCode = new JLabel("Postnummer:");
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblZipCode = new GridBagConstraints();
		gbc_lblZipCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblZipCode.gridx = 2;
		gbc_lblZipCode.gridy = 9;
		panelEditPlayerCenter.add(lblZipCode, gbc_lblZipCode);
		
		textFieldZipCode = new JTextField();
		textFieldZipCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldZipCode = new GridBagConstraints();
		gbc_textFieldZipCode.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldZipCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldZipCode.gridx = 4;
		gbc_textFieldZipCode.gridy = 9;
		panelEditPlayerCenter.add(textFieldZipCode, gbc_textFieldZipCode);
		textFieldZipCode.setColumns(10);
		
		lblGamerTag = new JLabel("Spillernavn:");
		lblGamerTag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblGamerTag = new GridBagConstraints();
		gbc_lblGamerTag.insets = new Insets(0, 0, 5, 5);
		gbc_lblGamerTag.gridx = 2;
		gbc_lblGamerTag.gridy = 11;
		panelEditPlayerCenter.add(lblGamerTag, gbc_lblGamerTag);
		
		textFieldGamerTag = new JTextField();
		textFieldGamerTag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldGamerTag = new GridBagConstraints();
		gbc_textFieldGamerTag.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldGamerTag.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGamerTag.gridx = 4;
		gbc_textFieldGamerTag.gridy = 11;
		panelEditPlayerCenter.add(textFieldGamerTag, gbc_textFieldGamerTag);
		textFieldGamerTag.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 13;
		panelEditPlayerCenter.add(lblEmail, gbc_lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldEmail.setDropMode(DropMode.INSERT);
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 4;
		gbc_textFieldEmail.gridy = 13;
		panelEditPlayerCenter.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		lblHold = new JLabel("Hold:");
		lblHold.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblHold = new GridBagConstraints();
		gbc_lblHold.insets = new Insets(0, 0, 0, 5);
		gbc_lblHold.gridx = 2;
		gbc_lblHold.gridy = 15;
		panelEditPlayerCenter.add(lblHold, gbc_lblHold);
		
		comboBoxTeam = new JComboBox();
		comboBoxTeam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_comboBoxTeam = new GridBagConstraints();
		gbc_comboBoxTeam.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTeam.gridx = 4;
		gbc_comboBoxTeam.gridy = 15;
		panelEditPlayerCenter.add(comboBoxTeam, gbc_comboBoxTeam);
		comboBoxTeam.addItem("Vælg et hold");
		try {
			for(Team team: teamController.findTeams())
			{
			comboBoxTeam.addItem(team.getName());
			}
		} catch (NoConnectionException | MisMatchException e1) {
			
			e1.printStackTrace();
		}
		
		panelEditPlayerBottom = new JPanel();
		panelEditPlayer.add(panelEditPlayerBottom, BorderLayout.SOUTH);
		GridBagLayout gbl_panelEditPlayerBottom = new GridBagLayout();
		gbl_panelEditPlayerBottom.columnWidths = new int[]{200, 0, 100, 0, 100, 0, 0};
		gbl_panelEditPlayerBottom.rowHeights = new int[]{0, 100, 0, 0};
		gbl_panelEditPlayerBottom.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelEditPlayerBottom.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelEditPlayerBottom.setLayout(gbl_panelEditPlayerBottom);
		
		btnClearPlayer = new JButton("Ryd felter");
		btnClearPlayer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnClearPlayer = new GridBagConstraints();
		gbc_btnClearPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_btnClearPlayer.gridx = 1;
		gbc_btnClearPlayer.gridy = 0;
		panelEditPlayerBottom.add(btnClearPlayer, gbc_btnClearPlayer);
		
		btnUpdatePlayer = new JButton("Opdater");
		btnUpdatePlayer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnUpdatePlayer = new GridBagConstraints();
		gbc_btnUpdatePlayer.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdatePlayer.gridx = 3;
		gbc_btnUpdatePlayer.gridy = 0;
		panelEditPlayerBottom.add(btnUpdatePlayer, gbc_btnUpdatePlayer);
		
		btnCreatePlayer = new JButton("Opret");
		btnCreatePlayer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				btnCreatePlayerClicked();
			}
		});
		btnCreatePlayer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCreatePlayer = new GridBagConstraints();
		gbc_btnCreatePlayer.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreatePlayer.gridx = 5;
		gbc_btnCreatePlayer.gridy = 0;
		panelEditPlayerBottom.add(btnCreatePlayer, gbc_btnCreatePlayer);
		this.teamPanel = new TeamPanel();
		tabbedPane.addTab("Hold", teamPanel);
		teamPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelFindTeams = new JPanel();
		teamPanel.add(panelFindTeams);
		panelFindTeams.setLayout(new BorderLayout(0, 0));
		
		panelFindTeamsTop = new JPanel();
		panelFindTeams.add(panelFindTeamsTop, BorderLayout.NORTH);
		panelFindTeamsTop.setLayout(new BorderLayout(0, 0));
		
		textFieldSearchField = new JTextField();
		textFieldSearchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelFindTeamsTop.add(textFieldSearchField);
		textFieldSearchField.setColumns(10);
		
		scrollPaneTeams = new JScrollPane();
		panelFindTeams.add(scrollPaneTeams, BorderLayout.CENTER);
		
		teamTable = new JTable();
		teamTableModel = new TeamTableModel();
		teamTable.setModel(teamTableModel);
		teamTable.getSelectionModel().addListSelectionListener((e)-> changedTeamSelection());
		teamTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPaneTeams.setViewportView(teamTable);
		
		btnFindTeams = new JButton("Find");
		btnFindTeams.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				btnFindClicked();
			}
		});
		btnFindTeams.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFindTeams.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFindTeamsTop.add(btnFindTeams, BorderLayout.EAST);
		
		panelFindTeamsBottom = new JPanel();
		panelFindTeams.add(panelFindTeamsBottom, BorderLayout.SOUTH);
		GridBagLayout gbl_panelFindTeamsBottom = new GridBagLayout();
		gbl_panelFindTeamsBottom.columnWidths = new int[]{353, 77, 0};
		gbl_panelFindTeamsBottom.rowHeights = new int[]{234, 0};
		gbl_panelFindTeamsBottom.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelFindTeamsBottom.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelFindTeamsBottom.setLayout(gbl_panelFindTeamsBottom);
		
		btnDeleteTeam = new JButton("Delete");
		btnDeleteTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDeleteClicked();
			}
		});
		btnDeleteTeam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnDeleteTeam = new GridBagConstraints();
		gbc_btnDeleteTeam.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDeleteTeam.gridx = 1;
		gbc_btnDeleteTeam.gridy = 0;
		panelFindTeamsBottom.add(btnDeleteTeam, gbc_btnDeleteTeam);
		
		panelEditTeam = new JPanel();
		teamPanel.add(panelEditTeam);
		panelEditTeam.setLayout(new BorderLayout(0, 0));
		
		panelEditTeamCenter = new JPanel();
		panelEditTeam.add(panelEditTeamCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelEditTeamCenter = new GridBagLayout();
		gbl_panelEditTeamCenter.columnWidths = new int[]{0, 0, 0, 0, 400, 0};
		gbl_panelEditTeamCenter.rowHeights = new int[]{100, 0, 50, 0, 50, 0, 0};
		gbl_panelEditTeamCenter.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelEditTeamCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelEditTeamCenter.setLayout(gbl_panelEditTeamCenter);
		
		labelHoldID = new JLabel("Hold ID:");
		labelHoldID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_labelHoldID = new GridBagConstraints();
		gbc_labelHoldID.insets = new Insets(0, 0, 5, 5);
		gbc_labelHoldID.gridx = 2;
		gbc_labelHoldID.gridy = 1;
		panelEditTeamCenter.add(labelHoldID, gbc_labelHoldID);
		
		textFieldHoldID = new JTextField();
		textFieldHoldID.setEditable(false);
		textFieldHoldID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldHoldID = new GridBagConstraints();
		gbc_textFieldHoldID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHoldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHoldID.gridx = 4;
		gbc_textFieldHoldID.gridy = 1;
		panelEditTeamCenter.add(textFieldHoldID, gbc_textFieldHoldID);
		textFieldHoldID.setColumns(10);
		
		lblHoldnavn = new JLabel("Holdnavn:");
		lblHoldnavn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblHoldnavn = new GridBagConstraints();
		gbc_lblHoldnavn.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoldnavn.gridx = 2;
		gbc_lblHoldnavn.gridy = 3;
		panelEditTeamCenter.add(lblHoldnavn, gbc_lblHoldnavn);
		
		textFieldHoldnavn = new JTextField();
		textFieldHoldnavn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldHoldnavn = new GridBagConstraints();
		gbc_textFieldHoldnavn.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHoldnavn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHoldnavn.gridx = 4;
		gbc_textFieldHoldnavn.gridy = 3;
		panelEditTeamCenter.add(textFieldHoldnavn, gbc_textFieldHoldnavn);
		textFieldHoldnavn.setColumns(10);
		
		lblTeamtag = new JLabel("TeamTag:");
		lblTeamtag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblTeamtag = new GridBagConstraints();
		gbc_lblTeamtag.insets = new Insets(0, 0, 0, 5);
		gbc_lblTeamtag.gridx = 2;
		gbc_lblTeamtag.gridy = 5;
		panelEditTeamCenter.add(lblTeamtag, gbc_lblTeamtag);
		
		textFieldTeamTag = new JTextField();
		textFieldTeamTag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldTeamTag = new GridBagConstraints();
		gbc_textFieldTeamTag.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTeamTag.gridx = 4;
		gbc_textFieldTeamTag.gridy = 5;
		panelEditTeamCenter.add(textFieldTeamTag, gbc_textFieldTeamTag);
		textFieldTeamTag.setColumns(10);
		
		panelEditTeamBottom = new JPanel();
		panelEditTeam.add(panelEditTeamBottom, BorderLayout.SOUTH);
		GridBagLayout gbl_panelEditTeamBottom = new GridBagLayout();
		gbl_panelEditTeamBottom.columnWidths = new int[]{200, 200, 200, 0};
		gbl_panelEditTeamBottom.rowHeights = new int[]{29, 200, 0};
		gbl_panelEditTeamBottom.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelEditTeamBottom.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelEditTeamBottom.setLayout(gbl_panelEditTeamBottom);
		
		//Clear button in Team-Tab
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearInfo();
			}

			
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnClear.insets = new Insets(0, 0, 5, 5);
		gbc_btnClear.gridx = 0;
		gbc_btnClear.gridy = 0;
		panelEditTeamBottom.add(btnClear, gbc_btnClear);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createTeam();
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate.gridx = 1;
		gbc_btnCreate.gridy = 0;
		panelEditTeamBottom.add(btnCreate, gbc_btnCreate);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				btnClickedUpdate();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdate.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnUpdate.gridx = 2;
		gbc_btnUpdate.gridy = 0;
		panelEditTeamBottom.add(btnUpdate, gbc_btnUpdate);
		this.statisticsPanel = new StatisticsPanel();
		tabbedPane.addTab("Statistik", statisticsPanel);
		this.gamePanel = new GamePanel();
		tabbedPane.addTab("Spil", gamePanel);
		this.administrerTurneringPanel = new ManageTournamentPanel();
		tabbedPane_1.addTab("Administrer turnering", administrerTurneringPanel);
		administrerTurneringPanel.setLayout(new MigLayout("", "[112px][329px][147px][][][][][][][][][][][][][]", "[22px][22px][25px][25px]"));
		
		lblTurneringensNavn = new JLabel("Turneringens navn:");
		lblTurneringensNavn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		administrerTurneringPanel.add(lblTurneringensNavn, "cell 0 0,alignx center,aligny center");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		administrerTurneringPanel.add(textField, "cell 1 0,growx,aligny center");
		textField.setColumns(10);
		
		
		
		lblSpil = new JLabel("Spil");
		lblSpil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		administrerTurneringPanel.add(lblSpil, "cell 0 1,alignx right,aligny center");
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		administrerTurneringPanel.add(comboBox, "cell 1 1,growx,aligny center");
		
		btnOpretTurnering = new JButton("Opret Turnering");
		btnOpretTurnering.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOpretTurnering.setHorizontalAlignment(SwingConstants.LEADING);
		administrerTurneringPanel.add(btnOpretTurnering, "cell 1 2,alignx center,aligny center");
		
		btnSletTurnering = new JButton("Slet nuv\u00E6rende turnering");
		btnSletTurnering.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSletTurnering.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		administrerTurneringPanel.add(btnSletTurnering, "cell 1 3,alignx center,aligny center");
		
		btnGenerererTurnering = new JButton("Genererer turnering");
		btnGenerererTurnering.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerererTurnering.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{				
				createRound(1);
				btnGenerererTurnering.setEnabled(false);
				btnCreate.setEnabled(false);
				btnDeleteTeam.setEnabled(false);
				btnUpdate.setEnabled(false);
				btnOpdaterRunde1.setVisible(true);
			}			
		});
		
		administrerTurneringPanel.add(btnGenerererTurnering, "cell 2 3,alignx left,aligny top");
		
		// Tabs i Turnering
		
		this.runde1Panel = new Round1Panel();
		tabbedPane_1.addTab("Runde 1", runde1Panel);
		runde1Panel.setLayout(new MigLayout("", "[][75.00px,fill][fill][fill][100px:n:200px,left][100px:n:200px,grow,fill][fill][][][][][][][][][][]", "[][25px][][][][][][][][][][][][][][]"));
		
		btnOpdaterRunde1 = new JButton("Opdater kampe");
		btnOpdaterRunde1.addActionListener(e -> submitMatchResult(1, firstRoundLabels, firstRoundTextFields)); //lambda used
		runde1Panel.add(btnOpdaterRunde1, "cell 4 15");
		btnOpdaterRunde1.setVisible(false);
		
		
		btnGenerererKamprunde2 = new JButton("Genererer n\u00E6ste kamprunde");
		btnGenerererKamprunde2.setEnabled(false);
		btnGenerererKamprunde2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					roundController.updateTreeStructure();
				} catch (MisMatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				createRound(2);
				btnOpdaterRunde1.setEnabled(false);
				btnGenerererKamprunde2.setEnabled(false);
				btnOpdaterRunde2.setVisible(true);
				for(JTextField txt: firstRoundTextFields)
				{
					txt.setEditable(false);
				}
			}
		});

		runde1Panel.add(btnGenerererKamprunde2, "cell 5 15,alignx left,aligny center");
		
		//create round 2 tab
		this.runde2Panel = new Round2Panel();
		tabbedPane_1.addTab("Runde 2", runde2Panel);
		runde2Panel.setLayout(new MigLayout("", "[][75.00px,fill][fill][fill][100px:n:200px,left][100px:n:200px,grow,fill][fill][][][][][][][][][][]", "[][25px][][][][][][][][][][][][][][]"));
		
		btnOpdaterRunde2 = new JButton("Opdater kampe");
		btnOpdaterRunde2.addActionListener(e -> submitMatchResult(2, secondRoundLabels, secondRoundTextFields)); //lambda used
		runde2Panel.add(btnOpdaterRunde2, "cell 4 15");
		btnOpdaterRunde2.setVisible(false);
		
		btnGenerererKamprunde3 = new JButton("Genererer n\u00E6ste kamprunde");
		btnGenerererKamprunde3.setEnabled(false);
		btnGenerererKamprunde3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					roundController.updateTreeStructure();
				}
				catch (MisMatchException e) 
				{
					e.printStackTrace();
				}
				createRound(3);
				btnOpdaterRunde2.setEnabled(false);
				btnGenerererKamprunde3.setEnabled(false);
				btnOpdaterRunde3.setVisible(true);
				for(JTextField txt: secondRoundTextFields)
				{
					txt.setEditable(false);
				}
			}
		});

		runde2Panel.add(btnGenerererKamprunde3, "cell 5 15,alignx left,aligny center");
		
		//create round 3 tab
		this.runde3Panel = new Round3Panel();
		tabbedPane_1.addTab("Runde 3", runde3Panel);
		runde3Panel.setLayout(new MigLayout("", "[][][][][][]", "[][][25px][][]"));
		
		btnOpdaterRunde3 = new JButton("Opdater kampe");
		btnOpdaterRunde3.addActionListener(e -> submitMatchResult(3, thirdRoundLabels, thirdRoundTextFields)); //lambda used
		runde3Panel.add(btnOpdaterRunde3, "cell 4 15");
		btnOpdaterRunde3.setVisible(false);
		
		btnGenerererGruppespil = new JButton("Genererer n\u00E6ste kamprunde");
		btnGenerererGruppespil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<ArrayList<Match>> groups = new ArrayList<>();
				btnOpdaterRunde3.setEnabled(false);
				btnGenerererGruppespil.setEnabled(false);
				try 
				{
					roundController.updateTreeStructure();
					groups = roundController.createGroupStage();
				} 
				catch (MisMatchException | WrongInputException | NoConnectionException e) 
				{
					e.printStackTrace();
				}
				displayGroupStage(groups);
				for(JTextField txt: thirdRoundTextFields)
				{
					txt.setEditable(false);
				}
			}
		});
		runde3Panel.add(btnGenerererGruppespil, "cell 5 15,alignx left,aligny center");
		btnGenerererGruppespil.setEnabled(false);
		this.gruppeSpilPanel = new GroupStagePanel();
		
		JScrollPane scrollPane = new JScrollPane(gruppeSpilPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		tabbedPane_1.addTab("Gruppespil", scrollPane);
		gruppeSpilPanel.setLayout (new MigLayout("", "[][200px][][][80px:n][][][][]", "[][][25px][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		btnOpdaterGruppespil = new JButton("Opdater kampe");
		btnOpdaterGruppespil.setVisible(false);
		btnOpdaterGruppespil.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				submitMatchResult(4, groupStageLabels, groupStageTextFields);
			}
		});
		gruppeSpilPanel.add(btnOpdaterGruppespil, "cell 7 28");
		
		btnFindFinalister = new JButton("Find Finalister");
		btnFindFinalister.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Round round = null;
				try 
				{
					round = roundController.findFinalists();
				} 
				catch (MisMatchException | WrongInputException | NoConnectionException e) 
				{
					e.printStackTrace();
				}
				displayFinalists(round);
			}
		});
		btnFindFinalister.setEnabled(false);
		gruppeSpilPanel.add(btnFindFinalister, "cell 8 28");
		this.finalerPanel = new FinalsPanel();
		tabbedPane_1.add("Finaler", finalerPanel);
		finalerPanel.setLayout(new MigLayout("", "[105px][][][][]", "[25px][][][][][][][][][][][][][][][]"));
		
		btnGemResultat = new JButton("Gem resultat");
		btnGemResultat.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		finalerPanel.add(btnGemResultat, "cell 4 15,alignx center,aligny center");
	}
	
	
	protected void displayFinalists(Round round)
	{
		int index = 0;
		for (Match match: round.getMatches()) 
		{
			index ++;
			String textField_1temp = "cell 0 " + index + ",alignx center,aligny center";
			//fixed size for each component
			Dimension d = new Dimension(100, 20);
			Dimension d1 = new Dimension(200, 20);
			
			
			textField_1 = new JTextField();
			textField_1.setMinimumSize(d);
			textField_1.setMaximumSize(d);
			textField_1.setColumns(10);
			
			//create new Font
			String fontName = textField_1.getFont().getName();
			Font font = new Font(fontName, Font.BOLD, 16);
			textField_1.setFont(font);
	
			String jLabeltemp = "cell 1 "+ index + ",alignx left,aligny center";
			lblTeam = new JLabel(match.getTeamOne().getName());
			lblTeam.setMinimumSize(d1);
			lblTeam.setMaximumSize(d1);
			lblTeam.setFont(font);
	
			String jLabel_1temp = "cell 2 " + index + ",alignx right,aligny center";
			lblTeam_1 = new JLabel(match.getTeamTwo().getName());
			lblTeam_1.setMinimumSize(d1);
			lblTeam_1.setMaximumSize(d1);
			lblTeam_1.setFont(font);
	
			String textField_2temp = "cell 3 "+ index + ",alignx center,aligny center";
			textField_2 = new JTextField();
			textField_2.setMinimumSize(d);
			textField_2.setMaximumSize(d);
			textField_2.setFont(font);
			
			finalerPanel.add(textField_1, textField_1temp);
			finalTextFields.add(textField_1);

			finalerPanel.add(lblTeam, jLabeltemp);
			finalLabels.add(lblTeam);	

			finalerPanel.add(lblTeam_1, jLabel_1temp);
			finalLabels.add(lblTeam_1);

			finalerPanel.add(textField_2, textField_2temp);
			finalTextFields.add(textField_2);
			
		}
		
	}
	
	protected void displayGroupStage(ArrayList<ArrayList<Match>> groups) 
	{
		btnOpdaterGruppespil.setVisible(true);
		boolean newRow = true;
		int index = 0;
		int groupNumber = 0;
		//For each group
		for(ArrayList<Match> group: groups)
		{
			index++;
			groupNumber++;
			String groupName = ("Kampe for Gruppe: " + groupNumber);
			String textFieldRestraints = "cell 0 " + index + ",alignx center,aligny center";
			JLabel teamName = new JLabel(groupName);
			teamName.setMinimumSize(new Dimension(200, 20));
			teamName.setMaximumSize(new Dimension(200, 20));
			teamName.setFont(new Font("TimesRoman", Font.BOLD, 20));
			gruppeSpilPanel.add(teamName, textFieldRestraints);
			
			
			
			//For each match
			for(Match match: group)
			{

				
				if(newRow)	
				{
				index ++;
				
				String textField_1temp = "cell 0 " + index + ",alignx center,aligny center";
				//fixed size for each component
				Dimension d = new Dimension(100, 20);
				Dimension d1 = new Dimension(200, 20);
				
					textField_1 = new JTextField();
					textField_1.setMinimumSize(d);
					textField_1.setMaximumSize(d);
					textField_1.setColumns(10);
					
					//create new Font
					String fontName = textField_1.getFont().getName();
					Font font = new Font(fontName, Font.BOLD, 16);
					textField_1.setFont(font);
			
					String jLabeltemp = "cell 1 "+ index + ",alignx left,aligny center";
					lblTeam = new JLabel(match.getTeamOne().getName());
					lblTeam.setMinimumSize(d1);
					lblTeam.setMaximumSize(d1);
					lblTeam.setFont(font);
			
					String jLabel_1temp = "cell 2 " + index + ",alignx right,aligny center";
					lblTeam_1 = new JLabel(match.getTeamTwo().getName());
					lblTeam_1.setMinimumSize(d1);
					lblTeam_1.setMaximumSize(d1);
					lblTeam_1.setFont(font);
			
					String textField_2temp = "cell 3 "+ index + ",alignx center,aligny center";
					textField_2 = new JTextField();
					textField_2.setMinimumSize(d);
					textField_2.setMaximumSize(d);
					textField_2.setFont(font);
			
					gruppeSpilPanel.add(textField_1, textField_1temp);
					groupStageTextFields.add(textField_1);

					gruppeSpilPanel.add(lblTeam, jLabeltemp);
					groupStageLabels.add(lblTeam);	

					gruppeSpilPanel.add(lblTeam_1, jLabel_1temp);
					groupStageLabels.add(lblTeam_1);

					gruppeSpilPanel.add(textField_2, textField_2temp);
					groupStageTextFields.add(textField_2);
					newRow = false;
				}
				else
				{
					String textField_1temp = "cell 5 " + index + ",alignx center,aligny center";
					//fixed size for each component
					Dimension d = new Dimension(100, 20);
					Dimension d1 = new Dimension(200, 20);
					
					textField_3 = new JTextField();
					textField_3.setMinimumSize(d);
					textField_3.setMaximumSize(d);
					textField_3.setColumns(10);
					
					//create new Font
					String fontName = textField_3.getFont().getName();
					Font font = new Font(fontName, Font.BOLD, 16);
					textField_3.setFont(font);
			
					String jLabeltemp = "cell 6 "+ index + ",alignx left,aligny center";
					lblTeam_2 = new JLabel(match.getTeamOne().getName());
					lblTeam_2.setMinimumSize(d1);
					lblTeam_2.setMaximumSize(d1);
					lblTeam_2.setFont(font);
			
					String jLabel_1temp = "cell 7 " + index + ",alignx right,aligny center";
					lblTeam_3 = new JLabel(match.getTeamTwo().getName());
					lblTeam_3.setMinimumSize(d1);
					lblTeam_3.setMaximumSize(d1);
					lblTeam_3.setFont(font);
			
					String textField_2temp = "cell 8 "+ index + ",alignx center,aligny center";
					textField_4 = new JTextField();
					textField_4.setMinimumSize(d);
					textField_4.setMaximumSize(d);
					textField_4.setFont(font);
			
					gruppeSpilPanel.add(textField_3, textField_1temp);
					groupStageTextFields.add(textField_3);

					gruppeSpilPanel.add(lblTeam_2, jLabeltemp);
					groupStageLabels.add(lblTeam_2);	

					gruppeSpilPanel.add(lblTeam_3, jLabel_1temp);
					groupStageLabels.add(lblTeam_3);

					gruppeSpilPanel.add(textField_4, textField_2temp);
					groupStageTextFields.add(textField_4);
					newRow = true;
				}
			}
		}
		JOptionPane.showMessageDialog(this, "Gruppespil er oprettet. Tryk på fanen 'Gruppespil' for at tilgå kampene.", "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	
	
	private void createRound(int roundNumber) 
	{
		if(roundController.getCurrentRound() == null || roundNumber == roundController.getCurrentRound().getRoundNumber()+1 )
		{
			Round round = null;
			int index = 0;
			try 
			{
				round = roundController.createRound(roundNumber);
			} 
			catch (MisMatchException e) 
			{
				JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			} 
			catch (WrongInputException e) 
			{
				JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			} 
			catch (NoConnectionException e) 
			{
				JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
			if(round != null)
			{
				for (Match match: round.getMatches()) 
				{
					index ++;
					String textField_1temp = "cell 0 " + index + ",alignx center,aligny center";
					//fixed size for each component
					Dimension d = new Dimension(100, 20);
					Dimension d1 = new Dimension(200, 20);
					
					
					textField_1 = new JTextField();
					textField_1.setMinimumSize(d);
					textField_1.setMaximumSize(d);
					textField_1.setColumns(10);
					
					//create new Font
					String fontName = textField_1.getFont().getName();
					Font font = new Font(fontName, Font.BOLD, 16);
					textField_1.setFont(font);
			
					String jLabeltemp = "cell 1 "+ index + ",alignx left,aligny center";
					lblTeam = new JLabel(match.getTeamOne().getName());
					lblTeam.setMinimumSize(d1);
					lblTeam.setMaximumSize(d1);
					lblTeam.setFont(font);
			
					String jLabel_1temp = "cell 2 " + index + ",alignx right,aligny center";
					lblTeam_1 = new JLabel(match.getTeamTwo().getName());
					lblTeam_1.setMinimumSize(d1);
					lblTeam_1.setMaximumSize(d1);
					lblTeam_1.setFont(font);
			
					String textField_2temp = "cell 3 "+ index + ",alignx center,aligny center";
					textField_2 = new JTextField();
					textField_2.setMinimumSize(d);
					textField_2.setMaximumSize(d);
					textField_2.setFont(font);
			
					switch(roundNumber)
					{
						case 1:
							runde1Panel.add(textField_1, textField_1temp);
							firstRoundTextFields.add(textField_1);

							runde1Panel.add(lblTeam, jLabeltemp);
							firstRoundLabels.add(lblTeam);	

							runde1Panel.add(lblTeam_1, jLabel_1temp);
							firstRoundLabels.add(lblTeam_1);
				
							runde1Panel.add(textField_2, textField_2temp);
							firstRoundTextFields.add(textField_2);
							break;
						case 2:
							runde2Panel.add(textField_1, textField_1temp);
							secondRoundTextFields.add(textField_1);

							runde2Panel.add(lblTeam, jLabeltemp);
							secondRoundLabels.add(lblTeam);	

							runde2Panel.add(lblTeam_1, jLabel_1temp);
							secondRoundLabels.add(lblTeam_1);
				
							runde2Panel.add(textField_2, textField_2temp);
							secondRoundTextFields.add(textField_2);
							break;
						case 3:
							runde3Panel.add(textField_1, textField_1temp);
							thirdRoundTextFields.add(textField_1);

							runde3Panel.add(lblTeam, jLabeltemp);
							thirdRoundLabels.add(lblTeam);	

							runde3Panel.add(lblTeam_1, jLabel_1temp);
							thirdRoundLabels.add(lblTeam_1);
				
							runde3Panel.add(textField_2, textField_2temp);
							thirdRoundTextFields.add(textField_2);
							break;
					}
				}
			}
			// JDialog bokse, om runden kunne oprettes.
			if(round != null)
			{
				switch(roundNumber)
				{
				case 1:
					JOptionPane.showMessageDialog(this, "1. Turneringsrunde er oprettet - tryk på fanen Runde 1", "Info", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 2:
					JOptionPane.showMessageDialog(this, "Runde 2 er oprettet - tryk på fanen Runde 2", "Info", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 3:
					JOptionPane.showMessageDialog(this, "Runde 3 er oprettet - tryk på fanen Runde 3", "Info", JOptionPane.INFORMATION_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(this, "Runden er oprettet", "Info", JOptionPane.INFORMATION_MESSAGE);
					break;			
				}
				
			
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Runden kunne ikke oprettes pga. runden er enten allerede oprettet eller ingen forbindelse til databasen", "Info", JOptionPane.WARNING_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Runden er allerede oprettet", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void submitMatchResult(int roundNumber, ArrayList<JLabel> labels, ArrayList<JTextField> textFields)
	{
		boolean correctInput = true;
		int amountOfMatches = labels.size()/2;
		int counter = 0;
		int labelIndex = 0;
		int textfieldIndex = 0;
		while(amountOfMatches > counter)
		{
			if(textFields.get(textfieldIndex).getText().equals("") || textFields.get(textfieldIndex+1).getText().equals(""))
			{
				labelIndex = labelIndex+2;
				textfieldIndex = textfieldIndex +2;
			}
			else
			{
				String teamOneName = labels.get(labelIndex).getText();
				labelIndex++;
				String teamTwoName = labels.get(labelIndex).getText();
				labelIndex++;
				try
				{
					int teamOneScore = Integer.parseInt(textFields.get(textfieldIndex).getText());
					textfieldIndex++;
					int teamTwoScore = Integer.parseInt(textFields.get(textfieldIndex).getText());
					textfieldIndex++;
					if(teamOneScore > teamTwoScore)
					{
						labels.get(labelIndex-2).setForeground(Color.green.darker().darker());
						labels.get(labelIndex-1).setForeground(Color.red);
					}
					if(teamOneScore < teamTwoScore)
					{
						labels.get(labelIndex-1).setForeground(Color.green.darker().darker());
						labels.get(labelIndex-2).setForeground(Color.red);
					}
					
					if(roundNumber != 4)
					{
						roundController.submitMatchResult(teamOneName, teamTwoName, teamOneScore, teamTwoScore);
					}
					else
					{
						roundController.updateGroupStageResults(teamOneName, teamTwoName, teamOneScore, teamTwoScore);
					}
				}
				catch(Exception e)
				{
					correctInput = false;
					e.printStackTrace();
				}
				
			}
			counter++;
		}
		if(!correctInput)
		{
			JOptionPane.showMessageDialog(this, "Indtastede værdier skal være tal", "Info", JOptionPane.WARNING_MESSAGE);
		}
		try 
		{
			
			roundController.updateResultInDatabase();
		} 
		catch (MisMatchException e) 
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
		}
		if(roundController.readyForNextRound())
		{
			switch(roundNumber)
			{
			case 1:	
				btnGenerererKamprunde2.setEnabled(true);
				break;
			case 2:
				btnGenerererKamprunde3.setEnabled(true);
				break;
			case 3:
				btnGenerererGruppespil.setEnabled(true);
				break;
			case 4:
				btnFindFinalister.setEnabled(true);
				break;
			}
		}
	}
	
	public void connected(boolean isConnected)
	{
		if(isConnected)
		{
			lblForbindelse.setText("Forbindelse til databasen oprettet.");
			lblForbindelse.setBackground(Color.GREEN);
		}
		else
		{
			lblForbindelse.setText("Ingen forbindelse til databasen. Systemet kan ikke anvendes.");
			lblForbindelse.setBackground(Color.RED);
		}
	}
	
	private Object changedTeamSelection() 
	{
		Team team = getSelectedTeam();
		displayTeam(team);
		return null;
	}

	private void displayTeam(Team team) 
	{
		String name = "";
		String teamTag = "";
		String id = "";
		if(team != null)
		{
			name = team.getName();
			teamTag = team.getTeamTag();
			id = String.valueOf(team.getId());
		}
		textFieldHoldnavn.setText(name);
		textFieldTeamTag.setText(teamTag);
		textFieldHoldID.setText(id);
	}

	private Team getSelectedTeam() 
	{
		int selectedRow = this.teamTable.getSelectedRow();
		Team selectedTeam = null;
		if (selectedRow > -1) 
		{
			selectedTeam = this.teamTableModel.getSelectedTeam(selectedRow);
		}
		return selectedTeam;
	}
	
	private void clearInfo() 
	{
		this.textFieldHoldID.setText("");
		this.textFieldHoldnavn.setText("");
		this.textFieldTeamTag.setText("");
	}
	
	//create a Team from the "Hold"-Tab.
	private void createTeam() {
		
		try 
		{
			TeamController teamController = new TeamController();
			teamController.createTeam(textFieldHoldnavn.getText(), textFieldTeamTag.getText());
		} 
		catch (NoConnectionException e) 
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
		} 
		catch (MisMatchException e) 
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
		}
		btnFindClicked();
		JOptionPane.showMessageDialog(this, "Holdet blev oprettet.", "info", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	private void btnFindClicked()
	{
		{
			String text = textFieldSearchField.getText();
			TeamController teamController;
			try 
			{
				teamController = new TeamController();
				ArrayList<Team> teams = teamController.searchTeams(text);
				teamTableModel.setTeams(teams);
			} 
			catch (NoConnectionException e) 
			{
				e.printStackTrace();
			}
			catch (MisMatchException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void btnDeleteClicked()
	{
		if(teamTable.getSelectedRow() > -1)
		{
			Team team = getSelectedTeam();
			try 
			{
				TeamController teamController = new TeamController();
				teamController.deleteTeam(team);
			} 
			catch (NoConnectionException e) 
			{
				JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
			} 
			catch (WrongInputException e) 
			{
				JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
			} 
			catch (MisMatchException e) 
			{
				JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
			}
			btnFindClicked();	
			JOptionPane.showMessageDialog(this, "Holdet blev slettet.", "info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void btnClickedUpdate()
	{
		String name = "";
		String teamTag = "";
		int id = -1;
		name = textFieldHoldnavn.getText();
		teamTag = textFieldTeamTag.getText();
		try
		{
			id = Integer.parseInt(textFieldHoldID.getText());
		}
		catch (NumberFormatException e) 
		{
			JOptionPane.showMessageDialog(this, "ID kan ikke læses som et tal.", "Info", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		try 
		{
			TeamController teamController = new TeamController();
			teamController.updateTeam(name, teamTag, id);
		} 
		catch (NoConnectionException e) 
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "info", JOptionPane.WARNING_MESSAGE);
		} 
		catch (WrongInputException e) 
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "info", JOptionPane.WARNING_MESSAGE);
		}
		JOptionPane.showMessageDialog(this, "Holdet blev opdateret.", "info", JOptionPane.WARNING_MESSAGE);
	}
	
	
	private Object changedPlayerSelection()
	{
		Player player = getSelectedPlayer();
		displayPlayer(player);
		return null;
	}
	
	private Player getSelectedPlayer()
	{
		int selectedRow = this.playerTable.getSelectedRow();
		Player selectedPlayer = null;
		if(selectedRow > -1)
		{
			selectedPlayer = this.playerTabelModel.getSelectedPlayer(selectedRow);
		}
		return selectedPlayer;
	}
	
	private void displayPlayer(Player player)
	{
		String id = "";
		String fname = "";
		String lname = "";
		String age = "";
		String zipCode = "";
		String gamerTag = "";
		String email = "";
		
		if(player != null)
		{
			id = String.valueOf(player.getId());
			fname = player.getFname();
			lname = player.getLname();
			age = String.valueOf(player.getAge());
			zipCode = player.getZipCode();
			gamerTag = player.getGamerTag();
			email = player.getEmail();
		}
		textFieldPlayerID.setText(id);
		textFieldFname.setText(fname);
		textFieldLname.setText(lname);
		textFieldAge.setText(age);
		textFieldZipCode.setText(zipCode);
		textFieldGamerTag.setText(gamerTag);
		textFieldEmail.setText(email);
		
	}

	public void btnCreatePlayerClicked()
	{
		String fname = textFieldFname.getText();
		String lname = textFieldLname.getText();
		int age = 0;
		try
		{
			age = Integer.parseInt(textFieldAge.getText());
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this, "Feltet 'Alder skal være et helt tal.", "Info", JOptionPane.WARNING_MESSAGE);
		}
		String zipCode = textFieldZipCode.getText();
		String email = textFieldEmail.getText();
		String gamerTag = textFieldGamerTag.getText();
		
		try 
		{
			playerController.createPlayer(fname, lname, age, zipCode, email, gamerTag);
		} 
		catch (MisMatchException e) 
		{
			JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Info", JOptionPane.WARNING_MESSAGE);
		}
	}
}

