package test;

import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

import db.*;
import model.*;
import controller.*;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.internal.MethodSorter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.lang.annotation.ElementType;

public class GenerateRoundTest {
	Connection connection;
	private Tournament tournament;
	private TournamentDB tournamentDB;
	private Game game;
	private Game gameTest;
	private GameController gameController;
	private TournamentController tournamentController;
	private RoundController roundController;
	private Round round;
	private Match match;
	private Team teamOne;
	private Team teamTwo;
	private MatchController matchController;
	private DBConnection dbConnection;
	private final static String DELETE_ALL_MATCHES_Q = "delete from match";
	private PreparedStatement deleteAllMatchesPS;
	private final static String DELETE_TEAM_WITH_HIGHEST_ID_Q = "delete team where id = (select max(id) from team);";
	private PreparedStatement deleteTeamWithHighestIDPS;
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
	//Denne metode tester for, om systemet opretter rounder og kampe. 
	//Testtype: Integrationstest Blackbox
	@Test
	public void equalNumberOfTeams() throws NoConnectionException, WrongInputException, MisMatchException
	{
		roundController = new RoundController();
		round = roundController.createRound(1);
		assertTrue(roundController.getCurrentRound().getMatches().get(round.getMatches().size()-1).getTeamTwo().getName().equals("Origin")); 
	}
	//Test 2
	//Her tester vi om der er oprettet en turnering
	//BlackBox unit test
	@Test
	public void findTournament() throws MisMatchException 
	{
		connection = DBConnection.getInstance().getConnection();
		TournamentDB tDB = new TournamentDB();
		assumeTrue(tDB.findTournament().getId() == 2);
		
	}
		
	// Test 3
	// Her tester vi om om systemet selv kan finde ud af at indsætte et "dummy hold", og efterfølgende oprette runde og kampe
	//Testtype:Integrationstest blackbox test
	@Test
	public void oddNumberOfTeams() throws NoConnectionException, WrongInputException, MisMatchException, SQLException
	{
		
		connection = DBConnection.getInstance().getConnection();
		try {
			deleteAllMatchesPS = connection.prepareStatement(DELETE_ALL_MATCHES_Q);// sletter matches, for at kunne fjerne et hold.
			deleteAllMatchesPS.executeUpdate();
			deleteTeamWithHighestIDPS = connection.prepareStatement(DELETE_TEAM_WITH_HIGHEST_ID_Q); //sletter det team med højest id, for at vi kan få en dummy klasse ind.
			deleteTeamWithHighestIDPS.executeUpdate();
			roundController = new RoundController();
			roundController.createRound(1);
			int lastIndex = (roundController.getCurrentRound().getMatches().size()-1);
			assertTrue(roundController.getCurrentRound().getMatches().get(lastIndex).getTeamTwo().getName().equals("Intet hold"));

		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new NoConnectionException(e, "Der er ikke forbindelse til databasen.");
		}
	}

	//Test 4
	// Her tester vi om gameControlleren også returnerer et objekt af game, når man bruger findGame
	//Testtype: BlackBox testing
	@Test
	public void findGame() throws NoConnectionException, WrongInputException
	{
		GameController gameCtrl = new GameController();
		assertTrue(gameCtrl.findGame(0) == game); // checker om game er det samme objekt som game1
	}
	
	
	
	//Test 5
	//Her tester vi efter Exceptions, vi tester blot om NoConnectionExecption.
	//Testtype: unit test blackbox Testing
	@Test
	public void testException() throws NoConnectionException, WrongInputException, MisMatchException
	{
		boolean thrown1 = false;
		try 
		{
		DBConnection.getInstance().disconnect();
		roundController = new RoundController();
		roundController.createRound(1);
		}
		catch (NoConnectionException e)
		{
			thrown1 = true;
			
		}
		
		
	}
	@Test
	//Test 6
	//Her testes exceptions i matchController,
	//TestType: Integrationstest whitebox
	public void testException2() throws NoConnectionException, WrongInputException
	{
		boolean thrownNoConnectionException = false;
		boolean thrownWrongInputException = false;
		ArrayList<Match> testMatches = new ArrayList<>();
		try {
			matchController = new MatchController();
			teamOne = new Team("test", "hej", 60000000);
			teamTwo = new Team("Test2", "hej2", 900000000);
			match = new Match(teamOne, teamTwo);
			testMatches.add(match);
			matchController.addMatchesToDatabase(testMatches, 1);
			
			
			}
		catch (WrongInputException e)
		{
			thrownWrongInputException = true;
		}
		
		assertTrue(thrownWrongInputException);
		try 
		{
		DBConnection.getInstance().disconnect();
		matchController.addMatchesToDatabase(testMatches, 1);
		}
		catch (NoConnectionException e)
		{
			thrownNoConnectionException = true;
		}
		assertTrue(thrownNoConnectionException);
	
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
