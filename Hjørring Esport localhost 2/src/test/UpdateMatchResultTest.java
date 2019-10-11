package test;

import db.*;
import model.*;
import controller.*;
import ui.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.sql.Connection;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateMatchResultTest {
	Connection connection;
	private RoundController roundController;
	private Round round;
	
	
	private String scripts = "use dmaa0218_1071008\r\n" + 
			"\r\n" + 
			"delete from match;\r\n" + 
			"delete from team;\r\n" + 
			"delete from round;\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"insert into team(name, teamTag, tournament_id) values ('Team Solo Mid', 'TSM', '2');\r\n" + 
			"insert into team(name, teamTag, tournament_id) values ('Cloud9', 'C9', '2');\r\n" + 
			"insert into team(name, teamTag, tournament_id) values ('Fnatic', 'FNC', '2');\r\n" + 
			"insert into team(name, teamTag, tournament_id) values ('Team Liquid', 'LIQ', '2');\r\n" + 
			"insert into team(name, teamTag, tournament_id) values ('Astralis', 'AST', '2');\r\n" + 
			"insert into team(name, teamTag, tournament_id) values ('Trailer Park Bois', 'TPB', '2');\r\n" + 
			"insert into team(name, teamTag, tournament_id) values ('Copenhagen wolves', 'TN', '2');\r\n" + 
			"insert into team(name, teamTag, tournament_id) values ('Origin', 'ORG', '2');\r\n";
	
	@BeforeEach
	public void getReady() throws SQLException, WrongInputException, MisMatchException
	{
		
		DBConnection.getInstance().connect();
		connection = DBConnection.getInstance().getConnection();
		
		connection.prepareStatement(scripts).executeUpdate();
		
		
		
	}
	//Test 1
	//Her tester vi updateMatchScore()
	//Testtype: Integrationtest Blackbox
	@Test
	public void updateMatchScore()
	{
		ArrayList<Match> matches = new ArrayList<>();
		Team teamOne = new Team("Team 1", "T1");
		Team teamTwo = new Team("Team 2", "T2");
		Match m = new Match(teamOne, teamTwo);
		matches.add(m);
		Round round = new Round(40, matches);
		assertTrue(round.getMatches().get(0).getTeamOneScore()== 0);
		assertTrue(round.getMatches().get(0).getTeamTwoScore() == 0);
		round.submitMatchResult("Team 1", "Team 2", 16, 2);
		assertTrue(round.getMatches().get(0).getTeamOneScore()== 16);
		assertTrue(round.getMatches().get(0).getTeamTwoScore() == 2);
	}
	

	@After
	public void tearDown() 
	{
		try {
			connection.prepareStatement(scripts).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
